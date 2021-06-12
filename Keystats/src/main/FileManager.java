package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileManager {

	File saves = new File("saves.txt");
	
	private Logger logger;

	public FileManager(Logger logger) {
		
		this.logger = logger;
		
		if (!saves.exists()) {
			try {
				saves.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		load(saves);
	}

	public void save(int totalKeys, int totalKeysToday, int[] keys, int[] keysToday, int totalButtons, int totalButtonsToday, int[] buttons,
			int[] buttonsToday, long totalTime) {
		save(totalKeys, totalKeysToday, keys, keysToday, totalButtons, totalButtonsToday, buttons, buttonsToday, totalTime, saves);
	}

	public void reset() {
		try {
			PrintWriter writer = new PrintWriter(saves);
			writer.append("");
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void save(int totalKeys, int totalKeysToday, int[] keys, int[] keysToday, int totalButtons, int totalButtonsToday, int[] buttons, int[] buttonsToday,
			long totalTime, File file) {
		try {
			FileWriter myWriter = new FileWriter(file);
			StringBuilder sb = new StringBuilder();
			sb.append("totalKeys " + totalKeys + System.lineSeparator());
			sb.append("totalButtons " + totalButtons + System.lineSeparator());
			sb.append("totalTime " + totalTime + System.lineSeparator());
			for (int i = 0; i < keys.length; i++) {
				if (keys[i] != 0) {
					sb.append("key " + i + " " + keys[i] + System.lineSeparator());
				}
			}
			for (int i = 0; i < buttons.length; i++) {
				if (buttons[i] != 0) {
					sb.append("button " + i + " " + buttons[i] + System.lineSeparator());
				}
			}
			myWriter.write(sb.toString());
			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void load(File file) {
		String[] lines = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			try {
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();

				while (line != null) {
					sb.append(line);
					sb.append(System.lineSeparator());
					line = br.readLine();
				}
				lines = sb.toString().split(System.lineSeparator());
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				br.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.setTotalTime(System.currentTimeMillis());
		
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].startsWith("totalKeys")) {
				logger.setTotalKeys(Integer.parseInt(lines[i].split(" ")[1]));
			} else if (lines[i].startsWith("totalButtons")) {
				logger.setTotalButtons(Integer.parseInt(lines[i].split(" ")[1]));
			} else if (lines[i].startsWith("key")) {
				String[] ln = lines[i].split(" ");
				int keyCode = Integer.parseInt(ln[1]);
				int clicks = Integer.parseInt(ln[2]);
				logger.setKey(keyCode, clicks);
			} else if (lines[i].startsWith("button")) {
				String[] ln = lines[i].split(" ");
				int keyCode = Integer.parseInt(ln[1]);
				int clicks = Integer.parseInt(ln[2]);
				logger.setButton(keyCode, clicks);
			} else if (lines[i].startsWith("totalTime")) {
				logger.setTotalTime(Long.parseLong(lines[i].split(" ")[1]));
			}
		}
	}
}
