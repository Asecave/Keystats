package main;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Logger {

	private Hook hook;
	private Window window;
	private FileManager fileManager;

	private boolean running;

	private int totalKeys;
	private int totalKeysToday;
	private int[] keys;
	private int[] keysToday;
	private int totalButtons;
	private int totalButtonsToday;
	private int[] buttons;
	private int[] buttonsToday;

	public Logger() {
		keys = new int[256];
		keysToday = new int[256];
		buttons = new int[4];
		buttonsToday = new int[4];
		hook = new Hook(this);
		new Tray(this);
		window = new Window(this);
		fileManager = new FileManager(this);
		window.updateNumbers(totalKeys, totalKeysToday, keys, keysToday, totalButtons, totalButtonsToday, buttons, buttonsToday);

		running = true;
		while (running) {
			window.update();
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
		window.dispose();
		hook.stop();
		running = false;
		fileManager.save(totalKeys, totalKeysToday, keys, keysToday, totalButtons, totalButtonsToday, buttons, buttonsToday, window.getTotalTime());
	}

	public void trayClicked() {
		window.show();
		updateNumbers();
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
				fileManager.save(totalKeys, totalKeysToday, keys, keysToday, totalButtons, totalButtonsToday, buttons, buttonsToday, window.getTotalTime(), file);
			} else {
				file = new File(file.getPath() + ".txt");
				fileManager.save(totalKeys, totalKeysToday, keys, keysToday, totalButtons, totalButtonsToday, buttons, buttonsToday, window.getTotalTime(), file);
			}
		}
	}
}
