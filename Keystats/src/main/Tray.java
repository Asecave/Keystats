package main;

import java.awt.AWTException;
import java.awt.CheckboxMenuItem;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JOptionPane;

public class Tray {
	
	private SystemTray tray = SystemTray.getSystemTray();
	TrayIcon trayIcon = null;
	private CheckboxMenuItem autostart;

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
		autostart = new CheckboxMenuItem("Enable autostart");
		autostart.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (autostart.getState()) {
					logger.enableAutostart();
				} else {
					logger.disableAutostart();
				}
			}
		});
		MenuItem exit = new MenuItem("Exit");
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logger.exit();
			}
		});

		popup.add(show);
		popup.add(autostart);
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

	public void setAutostart(boolean b) {
		autostart.setState(b);
	}

	public void remove() {
		tray.remove(trayIcon);
	}
}
