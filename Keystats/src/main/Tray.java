package main;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class Tray {
	
	private SystemTray tray = SystemTray.getSystemTray();
	TrayIcon trayIcon = null;

	public Tray(Logger logger) {
		
		if (!SystemTray.isSupported()) {
			JOptionPane.showMessageDialog(null, "System tray is not supported.", "Unable to start", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		final PopupMenu popup = new PopupMenu();
		
		trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().getImage(Tray.class.getResource("icon_tray.png")));
		trayIcon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logger.trayClicked();
			}
		});

		MenuItem show = new MenuItem("Show");
		show.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				logger.trayClicked();
			}
		});
		MenuItem exit = new MenuItem("Exit");
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logger.exit();
				tray.remove(trayIcon);
			}
		});

		popup.add(show);
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
