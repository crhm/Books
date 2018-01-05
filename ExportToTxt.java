import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExportToTxt {
	
	/** This method writes the String passed as argument (ideally the value of GetData.allData, 
	 * or an OrderBy method for example) into a text file. 
	 * It asks the user to enter the filename. If the file already exists, it appends the String 
	 * at the end of the file. If not, it creates it and writes in it.
	 * Throws an IOException if there is a problem writing the file, or if the user inputs an incorrect file name.
	 * @param toExport String to be written in the file.
	 */
	public ExportToTxt(String toExport) {
		try {
			System.out.println("Please enter a name for the export file "
					+ "(Note that if the file already exists in the project folder, "
					+ "it will be appended and not overwritten.):");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String nameOfFile = br.readLine();
			FileWriter test = null;
			if (nameOfFile.endsWith(".txt")) {
				test = new FileWriter(nameOfFile, true);
			} else if (nameOfFile.contains(".")){
				throw new IOException("File name should end in .txt or not have an extension at all.");
			} else {
				test = new FileWriter(nameOfFile + ".txt", true);
			}
			test.write(toExport);
			test.close();
			System.out.println("\nData successfully exported.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
