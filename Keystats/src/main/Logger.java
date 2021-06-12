package main;

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
	private long totalTime;

	public Logger() {
		keys = new int[256];
		keysToday = new int[256];
		buttons = new int[4];
		buttonsToday = new int[4];
		hook = new Hook(this);
		new Tray(this);
		window = new Window();
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
			window.updateNumbers(totalKeys, totalKeysToday, keys, keysToday, totalButtons, totalButtonsToday, buttons, buttonsToday);
		}
	}

	public void mousePress(int button) {
		totalButtons++;
		totalButtonsToday++;
		buttons[button]++;
		buttonsToday[button]++;
		if (window.isVisible()) {
			window.updateNumbers(totalKeys, totalKeysToday, keys, keysToday, totalButtons, totalButtonsToday, buttons, buttonsToday);
		}
	}

	public void exit() {
		window.dispose();
		hook.stop();
		running = false;
		fileManager.save(totalKeys, totalKeysToday, keys, keysToday, totalButtons, totalButtonsToday, buttons, buttonsToday, totalTime);
	}

	public void trayClicked() {
		window.show();
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

	public void setTotalTime(long totalTime) {
		this.totalTime = totalTime;
		window.setTotalTime(totalTime);
	}
	
	public void setKey(int keyCode, int clicks) {
		keys[keyCode] = clicks;
	}
	
	public void setButton(int keyCode, int clicks) {
		buttons[keyCode] = clicks;
	}
}
