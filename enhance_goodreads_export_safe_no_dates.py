import requests
from bs4 import BeautifulSoup
import urllib.parse
import backoff
import csv
import argparse
import getpass

from typing import List, NewType, Tuple

AbsoluteUrl = NewType("AbsoluteUrl", str)
RelativeUrl = NewType("RelativeUrl", str)
IsoDateStr = NewType("IsoDateStr", str)
Path = NewType("Path", str)

BOOK_URL = AbsoluteUrl("https://www.goodreads.com/book/show/")
SIGNIN_URL = AbsoluteUrl("https://www.goodreads.com/user/sign_in")

STANDARD_FIELDNAMES = ["Book Id", "Title", "Author", "Author l-f", "Additional Authors", "ISBN", "ISBN13", "My Rating",
                       "Average Rating", "Publisher", "Binding", "Number of Pages", "Year Published",
                       "Original Publication Year", "Date Read", "Date Added", "Bookshelves",
                       "Bookshelves with positions", "Exclusive Shelf", "My Review", "Spoiler", "Private Notes",
                       "Read Count", "Recommended For", "Recommended By", "Owned Copies", "Original Purchase Date",
                       "Original Purchase Location", "Condition", "Condition Description", "BCID"]


class EnhanceExportException(Exception):
    def __init__(self, message):
        self.message = message


def sign_in(email: str, password: str) -> requests.Session:
    try:
        session = requests.Session()
        print("Getting login page")
        response = session.get(SIGNIN_URL)
        soup = BeautifulSoup(response.content, "html.parser")
        auth_token = soup.find(attrs={"name": "authenticity_token"})["value"]
        n_token = soup.find(attrs={"name": "n"})["value"]

        form_data = {"authenticity_token": auth_token, "user[email]": email, "user[password]": password,
                     "next": "Sign in", "n": n_token, "remember_me": "off", "utf8": "✓"} #Changed remember me to off
        print("Logging in")
        login_response = session.post(SIGNIN_URL, data=form_data)
        login_response.raise_for_status()
        if login_response.url == SIGNIN_URL:
            raise EnhanceExportException("Error logging in, check email / password.")

    except requests.RequestException as e:
        raise EnhanceExportException(f"Error logging in: {e}")

    except KeyError:
        raise EnhanceExportException(f"error parsing login page, maybe layout changed?")

    return session


def parse_csv(filename: Path):
    try:
        with open(filename, newline="", encoding="utf-8") as file:
            reader = csv.DictReader(file)
            if set(reader.fieldnames) < set(STANDARD_FIELDNAMES):
                raise ValueError("CSV file does not contain the standard fieldnames!")
            return list(reader)
    except (ValueError, csv.Error, IOError) as e:
        raise EnhanceExportException(f"Error reading export file: {e}")


def write_csv(data: List[dict], fieldnames: List[str], filename: Path):
    try:
        with open(filename, "w", newline="", encoding="utf-8") as f:
            writer = csv.DictWriter(f, fieldnames=fieldnames,
                                    delimiter=",", quotechar='"', quoting=csv.QUOTE_MINIMAL)
            writer.writeheader()
            writer.writerows(data)
    except (IOError, csv.Error):
        raise EnhanceExportException(f"Error writing export file: {e}")


@backoff.on_exception(backoff.expo, requests.exceptions.RequestException, max_tries=10)
def get_with_retry(session, *args, **kwargs) -> requests.Response:
    resp = session.get(*args, timeout=10, **kwargs)
    resp.raise_for_status()
    return resp


def make_book_url(book_id: str) -> AbsoluteUrl:
    return AbsoluteUrl(urllib.parse.urljoin(BOOK_URL, book_id))

def get_genres(soup):
    genrelinks = soup.find_all(class_="bookPageGenreLink")
    genres = []
    genre = []
    for text in (l.get_text() for l in genrelinks):
        if "users" in text:
            genres.append((genre, int("".join(c for c in text if c.isdigit()))))
            genre = []
        else:
            genre.append(text)

    return genres


def enhance_export(options: dict):
    books = parse_csv(options["csv"])
    session = sign_in(options["email"], options["password"])
    if options["update"]:
        old_books_by_id = {b["Book Id"]: b for b in parse_csv(options["update"])}
        for b in books:
            # Update genres from the old file for books that didn't change shelf
            ob = old_books_by_id.get(b["Book Id"], None)
            if ob and ob["Exclusive Shelf"] == b["Exclusive Shelf"]:
                b["genres"] = old_books_by_id[b["Book Id"]]["genres"]

    books_to_process = [b for b in books if (options["force"] or (not b.get("genres", None)))]
    for i, book in enumerate(books_to_process):
        if options["verbose"]:
            print(f"Book {i+1} of {len(books_to_process)}: {book['Title']} ({book['Author']})")
        page = get_with_retry(session, make_book_url(book["Book Id"]))
        soup = BeautifulSoup(page.content, 'html.parser')
        genres = get_genres(soup)

        book["genres"] = ";".join(f"{','.join(genre[0])}|{genre[1]}" for genre in genres)

        if i % 20 == 0 or i == len(books_to_process) - 1:
            if options["verbose"]:
                print("saving csv")
            write_csv(books, STANDARD_FIELDNAMES + ["genres"], options["csv"])               


def main():
    argument_parser = argparse.ArgumentParser(
        description="""Adds genres information to a GoodReads export file.""")
    argument_parser.add_argument("-c", "--csv", help="path of your GoodReads export file (the new columns will be "
                                                     "added to this file)")
    argument_parser.add_argument("-u", "--update", help="(optional) path of previously enhanced GoodReads export file "
                                                        "to update (output will still be written to the file "
                                                        "specified in --csv)")
    argument_parser.add_argument("-e", "--email", help="the email you use to login to GoodReads")

    argument_parser.add_argument(
        "-f", "--force", action="store_true",
        help="process all books (by default only those without genre information are processed)")

    argument_parser.add_argument("-g", "--gui", action="store_true", help="show GUI")
    
    argument_parser.add_argument("-v", "--verbose", action="store_true", help="Program will print out in console a message each time it expands a book's information.")

    options = vars(argument_parser.parse_args())
    password_safe = getpass.getpass()
    options["password"] = password_safe

    if not all((options["email"], options["csv"])):
        print("You need to provide the path to the export file, and an email address.")
        print()
        argument_parser.print_help()
        return

    try:
        enhance_export(options)
    except EnhanceExportException as e:
        print(e.message)


if __name__ == "__main__":
    main()
