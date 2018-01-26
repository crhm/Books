package main;

import java.util.HashMap;

public class ListShelves {
	
	private HashMap<String, Shelf> list;
	
	public ListShelves() {
		this.list = new HashMap<String, Shelf>();
	}

	public HashMap<String, Shelf> getList() {
		return list;
	}

	public void setList(HashMap<String, Shelf> list) {
		this.list = list;
	}
	
	public void add(Shelf shelf) {
		list.put(shelf.getName(), shelf);
	}
	
	@Override
	public String toString() {
		String printableList = "";
		for (Shelf sh : list.values()) {
			printableList = printableList.concat(sh + "\n");
		}
		return printableList;
	}

}
