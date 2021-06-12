package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

public class ListElement extends JPanel {

	private static final long serialVersionUID = 1L;

	private int keyCode;
	private JLabel textLabel, numberLabel;

	public ListElement(String text, int number, int keyCode) {
		
		this.keyCode = keyCode;
		
		textLabel = new JLabel(text);
		textLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		numberLabel = new JLabel();
		numberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		numberLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		setNumber(number);
		
		setLayout(new GridLayout());
		add(textLabel);
		add(numberLabel);
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(null);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				setBackground(Color.CYAN);
			}
		});
	}

	public boolean matches(int keyCode) {
		return keyCode == this.keyCode;
	}

	public void setNumber(int number) {
		numberLabel.setText("" + number);
	}
}
