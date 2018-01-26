package export;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class TxtFileStrategy implements IExportStrategy {

	@Override
	public void export(String s) {
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
			test.write(s);
			test.close();
			System.out.println("\nData successfully exported.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
