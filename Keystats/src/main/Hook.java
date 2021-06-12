package main;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import lc.kra.system.mouse.GlobalMouseHook;
import lc.kra.system.mouse.event.GlobalMouseAdapter;
import lc.kra.system.mouse.event.GlobalMouseEvent;

public class Hook {

	private GlobalKeyboardHook keyboardHook;
	private GlobalMouseHook mouseHook;
	private Logger logger;

	public Hook(Logger logger) {
		this.logger = logger;
		begin();
	}

	public void stop() {
		keyboardHook.shutdownHook();
		mouseHook.shutdownHook();
	}

	public void pause() {
		keyboardHook.shutdownHook();
		mouseHook.shutdownHook();
	}
	
	public void begin() {
		keyboardHook = new GlobalKeyboardHook(true);

		keyboardHook.addKeyListener(new GlobalKeyAdapter() {

			@Override
			public void keyReleased(GlobalKeyEvent event) {
				logger.keyPress(event.getVirtualKeyCode());
			}
		});

		mouseHook = new GlobalMouseHook();

		mouseHook.addMouseListener(new GlobalMouseAdapter() {
			@Override
			public void mousePressed(GlobalMouseEvent event) {
				int btn = event.getButton();
				if (btn == 1 || btn == 2) {
					logger.mousePress(event.getButton());
				} else if (btn == 16) {
					logger.mousePress(3);
				}
			}
		});
	}
}
