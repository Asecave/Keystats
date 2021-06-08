package main;

public class Logger {
	
	private Hook hook;
	private Tray tray;

	public Logger() {
		
		hook = new Hook(this);
		
		tray = new Tray(this);
		
	}
	
	public void press(int keyCode) {
		
	}
	
	public void exit() {
		
	}
}
