package main;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import mslinks.ShellLink;

public class Logger {

	private Hook hook;
	private Window window;
	private FileManager fileManager;
	private Tray tray;
	private Thread shutdownHook;

	private boolean running;

	private int totalKeys;
	private int totalKeysToday;
	private int[] keys;
	private int[] keysToday;
	private int totalButtons;
	private int totalButtonsToday;
	private int[] buttons;
	private int[] buttonsToday;
	private int autoSaveInteval = 300000; // 5min
	private long lastAutoSave = System.currentTimeMillis();

	public Logger() {
		keys = new int[256];
		keysToday = new int[256];
		buttons = new int[4];
		buttonsToday = new int[4];
		hook = new Hook(this);
		tray = new Tray(this);
		window = new Window(this);
		fileManager = new FileManager(this);
		window.updateNumbers(totalKeys, totalKeysToday, keys, keysToday, totalButtons, totalButtonsToday, buttons, buttonsToday);

		tray.setAutostart(getAutostartFile().exists());

		shutdownHook = new Thread(new Runnable() {
			@Override
			public void run() {
				exitWithoutAsking();
			}
		}, "Shutdownhook");

		Runtime.getRuntime().addShutdownHook(shutdownHook);

		running = true;
		while (running) {
			window.update();
			if (System.currentTimeMillis() - lastAutoSave >= autoSaveInteval) {
				lastAutoSave = System.currentTimeMillis();
				save();
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void keyPress(int keyCode) {
		totalKeys++;
		totalKeysToday++;
		keys[keyCode]++;
		keysToday[keyCode]++;
		if (window.isVisible()) {
			updateNumbers();
		}
	}

	public void mousePress(int button) {
		totalButtons++;
		totalButtonsToday++;
		buttons[button]++;
		buttonsToday[button]++;
		if (window.isVisible()) {
			updateNumbers();
		}
	}

	public void exit() {
		int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to stop the program?\nThis can lead to wrong stat results.", "Continue?",
				JOptionPane.WARNING_MESSAGE);
		if (choice == 0) {
			exitWithoutAsking();
		}
	}
	
	private void exitWithoutAsking() {
		save();
		window.dispose();
		hook.stop();
		Runtime.getRuntime().removeShutdownHook(shutdownHook);
		running = false;
		tray.remove();
	}

	public void trayClicked() {
		window.show();
		updateNumbers();
	}

	private void save() {
		fileManager.save(totalKeys, totalKeysToday, keys, keysToday, totalButtons, totalButtonsToday, buttons, buttonsToday, window.getTotalTime());
	}

	private void updateNumbers() {
		window.updateNumbers(totalKeys, totalKeysToday, keys, keysToday, totalButtons, totalButtonsToday, buttons, buttonsToday);
	}

	public void setTotalKeys(int totalKeys) {
		this.totalKeys = totalKeys;
	}

	public void setKeys(int[] keys) {
		this.keys = keys;
	}

	public void setTotalButtons(int totalButtons) {
		this.totalButtons = totalButtons;
	}

	public void setButtons(int[] buttons) {
		this.buttons = buttons;
	}

	public void setKey(int keyCode, int clicks) {
		keys[keyCode] = clicks;
	}

	public void setButton(int keyCode, int clicks) {
		buttons[keyCode] = clicks;
	}

	public void reset() {
		fileManager.reset();
		totalKeys = 0;
		totalButtons = 0;
		totalButtonsToday = 0;
		keys = new int[256];
		keysToday = new int[256];
		buttons = new int[4];
		buttonsToday = new int[4];
		updateNumbers();
		window.resetKeys();
	}

	public void pauseHook() {
		hook.pause();
	}

	public void continueHook() {
		hook.begin();
	}

	public void setTotalTime(long totalTime) {
		window.setTotalTime(totalTime);
	}

	public void load() {
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", "txt", "text");
		fc.setFileFilter(filter);
		int result = fc.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			fileManager.load(file);
			updateNumbers();
		}
	}

	public void store() {
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", "txt", "text");
		fc.setFileFilter(filter);
		int result = fc.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			if (file.getPath().endsWith(".txt")) {
				fileManager.save(totalKeys, totalKeysToday, keys, keysToday, totalButtons, totalButtonsToday, buttons, buttonsToday, window.getTotalTime(),
						file);
			} else {
				file = new File(file.getPath() + ".txt");
				fileManager.save(totalKeys, totalKeysToday, keys, keysToday, totalButtons, totalButtonsToday, buttons, buttonsToday, window.getTotalTime(),
						file);
			}
		}
	}

	public void firstStart() {
		if (!getAutostartFile().exists()) {
			enableAutostart();
			tray.setAutostart(true);
		}
	}

	private File getAutostartFile() {
		final String fileName = "Keystats.lnk";
		return new File(System.getProperty("user.home") + "/AppData/Roaming/Microsoft/Windows/Start Menu/Programs/Startup/" + fileName);
	}

	public void enableAutostart() {
		try {
			File jar = new File(Logger.class.getProtectionDomain().getCodeSource().getLocation().toURI());
			File autostart = getAutostartFile();
			System.out.println("Trying to create shortcut from file " + jar.getPath() + " to shortcut " + autostart.getPath());
			if (jar.exists() && autostart.getParentFile().exists()) {
				ShellLink.createLink(jar.getPath(), autostart.getPath());
			} else {
				JOptionPane.showMessageDialog(null, "Could not add program to autostart.", "Autostart error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public void disableAutostart() {
		getAutostartFile().delete();
	}
}
