package main;

import java.awt.AWTException;
import java.awt.CheckboxMenuItem;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Tray {

	public Tray(Logger logger) {
		
		if (!SystemTray.isSupported()) {
			JOptionPane.showMessageDialog(null, "System tray is not supported.", "Unable to start", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		final PopupMenu popup = new PopupMenu();
		
		TrayIcon trayIcon = null;
		try {
			trayIcon = new TrayIcon(ImageIO.read(Tray.class.getResourceAsStream("icon.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		final SystemTray tray = SystemTray.getSystemTray();

		CheckboxMenuItem enable = new CheckboxMenuItem("Set auto size");
		MenuItem infoItem = new MenuItem("Info");
		MenuItem exit = new MenuItem("Exit") {
			
		};

		// Add components to pop-up menu
		popup.add(enable);
		popup.add(infoItem);
		popup.addSeparator();
		popup.add(exit);

		trayIcon.setImageAutoSize(true);
		trayIcon.setPopupMenu(popup);

		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			System.out.println("TrayIcon could not be added.");
		}
	}
}
