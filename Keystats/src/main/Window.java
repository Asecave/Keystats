package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Window {

	private JFrame frmKeystats;
	private JLabel totalTime, totalTimeToday;
	private JLabel totalKeys, totalButtons, button1, button2, button3, totalKeysToday, totalButtonsToday, button1Today, button2Today, button3Today;
	private JButton load, save, reset, stop;
	private JScrollPane keys, todayKeys;
	private ArrayList<ListElement> keyList, todayList;
	private JPanel keysGroundPanel, todayKeysGroundPanel;

	private long totalTimee;
	private long todayStartTime;

	/**
	 * Create the application.
	 */
	public Window(Logger logger) {
		initialize(logger);
		todayStartTime = System.currentTimeMillis();
		keyList = new ArrayList<>();
		todayList = new ArrayList<>();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Logger logger) {
		frmKeystats = new JFrame();
		frmKeystats.setResizable(false);
		frmKeystats.setIconImage(Toolkit.getDefaultToolkit().getImage(Window.class.getResource("icon32.png")));
		frmKeystats.setTitle("Keystats");
		frmKeystats.setBounds(100, 100, 700, 428);
		frmKeystats.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmKeystats.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBounds(385, 11, 289, 91);
		frmKeystats.getContentPane().add(panel);

		JLabel lblNewLabel = new JLabel("Total:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));

		totalTime = new JLabel("<totalTime>");
		totalTime.setFont(new Font("Tahoma", Font.BOLD, 25));

		JLabel lblNewLabel_2 = new JLabel("Today:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 25));

		totalTimeToday = new JLabel("<timeToday>");
		totalTimeToday.setFont(new Font("Tahoma", Font.BOLD, 25));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING,
										gl_panel.createSequentialGroup().addComponent(lblNewLabel)
												.addPreferredGap(ComponentPlacement.RELATED, 177, Short.MAX_VALUE).addComponent(totalTime))
								.addGroup(gl_panel.createSequentialGroup().addComponent(lblNewLabel_2)
										.addPreferredGap(ComponentPlacement.RELATED, 177, Short.MAX_VALUE).addComponent(totalTimeToday)))
						.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(totalTime).addComponent(lblNewLabel))
				.addPreferredGap(ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_2).addComponent(totalTimeToday)).addContainerGap()));
		panel.setLayout(gl_panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(10, 11, 365, 330);
		frmKeystats.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("Total keys pressed:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(10, 11, 172, 19);
		panel_1.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Total buttons pressed:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(10, 41, 172, 19);
		panel_1.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Middle mousebutton:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_6.setBounds(10, 211, 172, 19);
		panel_1.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Right mousebutton:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_7.setBounds(10, 181, 172, 19);
		panel_1.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Left mousebutton:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_8.setBounds(10, 151, 172, 19);
		panel_1.add(lblNewLabel_8);

		totalKeys = new JLabel("<totalKeys>");
		totalKeys.setHorizontalAlignment(SwingConstants.RIGHT);
		totalKeys.setFont(new Font("Tahoma", Font.PLAIN, 15));
		totalKeys.setBounds(192, 11, 163, 19);
		panel_1.add(totalKeys);

		totalButtons = new JLabel("<totalButtons>");
		totalButtons.setFont(new Font("Tahoma", Font.PLAIN, 15));
		totalButtons.setHorizontalAlignment(SwingConstants.RIGHT);
		totalButtons.setBounds(192, 41, 163, 18);
		panel_1.add(totalButtons);

		button1 = new JLabel("<Button1>");
		button1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button1.setHorizontalAlignment(SwingConstants.RIGHT);
		button1.setBounds(192, 151, 163, 19);
		panel_1.add(button1);

		button3 = new JLabel("<Button3>");
		button3.setHorizontalAlignment(SwingConstants.RIGHT);
		button3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button3.setBounds(192, 181, 163, 18);
		panel_1.add(button3);

		button2 = new JLabel("<Button2>");
		button2.setHorizontalAlignment(SwingConstants.RIGHT);
		button2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button2.setBounds(183, 211, 172, 18);
		panel_1.add(button2);

		JLabel lblNewLabel_14 = new JLabel("Keys today:");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_14.setBounds(10, 71, 172, 19);
		panel_1.add(lblNewLabel_14);

		totalKeysToday = new JLabel("<totalKeysToday>");
		totalKeysToday.setFont(new Font("Tahoma", Font.PLAIN, 15));
		totalKeysToday.setHorizontalAlignment(SwingConstants.RIGHT);
		totalKeysToday.setBounds(192, 70, 163, 19);
		panel_1.add(totalKeysToday);

		JLabel lblNewLabel_16 = new JLabel("Buttons today:");
		lblNewLabel_16.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_16.setBounds(10, 101, 172, 19);
		panel_1.add(lblNewLabel_16);

		totalButtonsToday = new JLabel("<totalButtonsToday>");
		totalButtonsToday.setFont(new Font("Tahoma", Font.PLAIN, 15));
		totalButtonsToday.setHorizontalAlignment(SwingConstants.RIGHT);
		totalButtonsToday.setBounds(192, 100, 163, 20);
		panel_1.add(totalButtonsToday);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 131, 345, 2);
		panel_1.add(separator);

		JLabel lblNewLabel_18 = new JLabel("Left today:");
		lblNewLabel_18.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_18.setBounds(10, 241, 172, 19);
		panel_1.add(lblNewLabel_18);

		JLabel lblNewLabel_19 = new JLabel("Right today:");
		lblNewLabel_19.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_19.setBounds(10, 271, 172, 19);
		panel_1.add(lblNewLabel_19);

		JLabel lblNewLabel_20 = new JLabel("Middle today:");
		lblNewLabel_20.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_20.setBounds(10, 301, 172, 19);
		panel_1.add(lblNewLabel_20);

		button1Today = new JLabel("<Button1Today>");
		button1Today.setHorizontalAlignment(SwingConstants.RIGHT);
		button1Today.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button1Today.setBounds(192, 240, 163, 20);
		panel_1.add(button1Today);

		button3Today = new JLabel("<Button3Today>");
		button3Today.setHorizontalAlignment(SwingConstants.RIGHT);
		button3Today.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button3Today.setBounds(192, 271, 163, 19);
		panel_1.add(button3Today);

		button2Today = new JLabel("<Button2Today>");
		button2Today.setHorizontalAlignment(SwingConstants.RIGHT);
		button2Today.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button2Today.setBounds(192, 301, 163, 19);
		panel_1.add(button2Today);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 352, 365, 26);
		frmKeystats.getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));

		load = new JButton("Load");
		load.setFont(new Font("Tahoma", Font.PLAIN, 15));
		load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				logger.pauseHook();
				logger.load();
				logger.continueHook();
			}
		});
		panel_2.add(load);

		save = new JButton("Save");
		save.setFont(new Font("Tahoma", Font.PLAIN, 15));
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				logger.pauseHook();
				logger.store();
				logger.continueHook();
			}
		});
		panel_2.add(save);

		reset = new JButton("Reset");
		reset.setFont(new Font("Tahoma", Font.PLAIN, 15));
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				logger.pauseHook();
				String input = JOptionPane
						.showInputDialog(
								new JFrame(), "Are you sure you want to reset your statistics?\n"
										+ "Unless you exported a backup berfore it will be lost forever.\n" + "Please type \"reset\" to cornfirm.",
								"Reset", JOptionPane.WARNING_MESSAGE);
				if (input != null && input.toLowerCase().equals("reset")) {
					logger.reset();
				}
				logger.continueHook();
			}
		});
		panel_2.add(reset);

		stop = new JButton("Stop");
		stop.setFont(new Font("Tahoma", Font.PLAIN, 15));
		stop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				logger.exit();
			}
		});
		panel_2.add(stop);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tabbedPane.setBounds(385, 113, 289, 265);
		frmKeystats.getContentPane().add(tabbedPane);

		keysGroundPanel = new JPanel();
		keysGroundPanel.setLayout(new GridBagLayout());

		keys = new JScrollPane(keysGroundPanel);
		keys.setBorder(null);
		keys.getVerticalScrollBar().setUnitIncrement(5);

		tabbedPane.addTab("Total keys", null, keys, null);

		todayKeysGroundPanel = new JPanel();
		todayKeysGroundPanel.setLayout(new GridBagLayout());

		todayKeys = new JScrollPane(todayKeysGroundPanel);
		todayKeys.setBorder(null);
		todayKeys.getVerticalScrollBar().setUnitIncrement(5);

		tabbedPane.addTab("Today keys", null, todayKeys, null);

	}

	public void show() {
		frmKeystats.setVisible(true);
	}

	public void dispose() {
		frmKeystats.dispose();
	}

	public void update() {
		if (frmKeystats.isVisible()) {
			long millis = System.currentTimeMillis() - todayStartTime + totalTimee;
			int seconds = (int) (millis / 1000) % 60;
			int minutes = (int) ((millis / (1000 * 60)) % 60);
			int hours = (int) ((millis / (1000 * 60 * 60)));
			totalTime.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
			millis = System.currentTimeMillis() - todayStartTime;
			seconds = (int) (millis / 1000) % 60;
			minutes = (int) ((millis / (1000 * 60)) % 60);
			hours = (int) ((millis / (1000 * 60 * 60)));
			totalTimeToday.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
		}
	}

	public void updateNumbers(int totalKeys, int totalKeysToday, int[] keys, int[] keysToday, int totalButtons, int totalButtonsToday, int[] buttons,
			int[] buttonsToday) {
		this.totalKeys.setText("" + totalKeys);
		this.totalKeysToday.setText("" + totalKeysToday);
		this.totalButtons.setText("" + totalButtons);
		this.totalButtonsToday.setText("" + totalButtonsToday);
		this.button1.setText("" + buttons[1]);
		this.button2.setText("" + buttons[2]);
		this.button3.setText("" + buttons[3]);
		this.button1Today.setText("" + buttonsToday[1]);
		this.button2Today.setText("" + buttonsToday[2]);
		this.button3Today.setText("" + buttonsToday[3]);
		for (int i = 0; i < keys.length; i++) {
			if (keys[i] != 0) {
				boolean exists = false;
				for (ListElement l : keyList) {
					if (l.matches(i)) {
						l.setNumber(keys[i]);
						exists = true;
						break;
					}
				}
				if (!exists) {
					char c = (char) i;
					String text = null;
					if (isPrintableChar(c)) {
						text = "" + c;
					} else {
						text = "" + i;
					}
					ListElement e = new ListElement(text, keys[i], i);
					GridBagConstraints gbc = new GridBagConstraints();
					gbc.gridwidth = GridBagConstraints.REMAINDER;
					gbc.weightx = 1;
					gbc.fill = GridBagConstraints.HORIZONTAL;
					keysGroundPanel.add(e, gbc, 0);
					keyList.add(e);
				}
			}
		}
		for (int i = 0; i < keysToday.length; i++) {
			if (keysToday[i] != 0) {
				boolean exists = false;
				for (ListElement l : todayList) {
					if (l.matches(i)) {
						l.setNumber(keysToday[i]);
						exists = true;
						break;
					}
				}
				if (!exists) {
					char c = (char) i;
					String text = null;
					if (isPrintableChar(c)) {
						text = "" + c;
					} else {
						text = "" + i;
					}
					ListElement e = new ListElement(text, keysToday[i], i);
					GridBagConstraints gbc = new GridBagConstraints();
					gbc.gridwidth = GridBagConstraints.REMAINDER;
					gbc.weightx = 1;
					gbc.fill = GridBagConstraints.HORIZONTAL;
					todayKeysGroundPanel.add(e, gbc, 0);
					todayList.add(e);
				}
			}
		}
	}

	public boolean isVisible() {
		return frmKeystats.isVisible();
	}

	public void setTotalTime(long totalTime) {
		totalTimee = totalTime;
	}

	public boolean isPrintableChar(char c) {
		Character.UnicodeBlock block = Character.UnicodeBlock.of(c);
		return (!Character.isISOControl(c)) && c != KeyEvent.CHAR_UNDEFINED && block != null && block != Character.UnicodeBlock.SPECIALS;
	}

	public void resetKeys() {
		keyList = new ArrayList<>();
		todayList = new ArrayList<>();
		keysGroundPanel.removeAll();
		keysGroundPanel.repaint();
		todayKeysGroundPanel.removeAll();
		todayKeysGroundPanel.repaint();
		totalTimee = 0;
		todayStartTime = System.currentTimeMillis();
	}

	public long getTotalTime() {
		return System.currentTimeMillis() - todayStartTime + totalTimee;
	}
}
