package gatocreador887.darkness;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Settings {
	public static final Path SETTINGS_FILE_PATH = Paths.get("settings.txt");
	public static boolean alternateCustomLevelStartSound = false;
	
	public static void read() {
		ArrayList<String> lines = new ArrayList<String>();
		
		try {
			if (!Files.exists(SETTINGS_FILE_PATH)) {
				Files.createFile(SETTINGS_FILE_PATH);
			}
			
			lines.addAll(Files.readAllLines(SETTINGS_FILE_PATH));
		} catch (IOException e) {
			System.err.println("Unable to read settings from '" + SETTINGS_FILE_PATH + "'. Stacktrace:");
			e.printStackTrace();
		}
		
		for (String line : lines) {
			String[] entry = line.split("=");
			String id = entry[0];
			String value = entry[1];
			
			if (id.equals("alternateCustomLevelStartSound")) {
				Settings.alternateCustomLevelStartSound = Boolean.parseBoolean(value);
			}
		}
	}
	
	public static void write() {
		ArrayList<String> lines = new ArrayList<String>();
		
		lines.add("alternateCustomLevelStartSound=" + Settings.alternateCustomLevelStartSound);
		
		try {
			if (!Files.exists(SETTINGS_FILE_PATH)) {
				Files.createFile(SETTINGS_FILE_PATH);
			}
			
			Files.write(SETTINGS_FILE_PATH, lines);
		} catch (IOException e) {
			System.err.println("Unable to write settings to '" + SETTINGS_FILE_PATH + "'. Stacktrace:");
			e.printStackTrace();
		}
	}
	
	public static boolean getAlternateCustomLevelStartSound() {
		return Settings.alternateCustomLevelStartSound;
	}
}
