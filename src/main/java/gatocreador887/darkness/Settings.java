package gatocreador887.darkness;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Settings {
	public static final Path SETTINGS_FILE_PATH = Paths.get("/settings.txt");
	
	public static void read() {
		ArrayList<String> lines = new ArrayList<String>();
		
		try {
			lines.addAll(Files.readAllLines(SETTINGS_FILE_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (String line : lines) {
			if (line.contains("")) {
				if (line.equals("")) {
					
				}
			}
		}
	}
	
	public static void write() {
		ArrayList<String> lines = new ArrayList<String>();
		
		lines.add("");
		
		try {
			Files.write(SETTINGS_FILE_PATH, lines);
		} catch (IOException e) {
			System.err.println("Unable to write settings to '" + SETTINGS_FILE_PATH + "'. Stacktrace:");
			e.printStackTrace();
		}
	}
}
