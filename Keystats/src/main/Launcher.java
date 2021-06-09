package main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Launcher {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		new Logger();
	}
}
