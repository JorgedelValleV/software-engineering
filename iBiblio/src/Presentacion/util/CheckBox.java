package Presentacion.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

public class CheckBox extends JRadioButton {

	private boolean check = false;

	public CheckBox(String s) {
		super("<html><body><center>" + s + "</center></body></html>");
		setFocusable(false);
		setOpaque(false);
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				check = !check;
			}
		});
	}

	public boolean getStatus() {
		return check;
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		g2.fillRect(1, 6, 19, 19);

		g2.setColor(Color.WHITE);
		g2.fillRect(4, 9, 13, 13);
		if (check) {
			g2.setColor(Color.BLACK);
			g2.fillRect(6, 12, 8, 8);
		}
	}
}
