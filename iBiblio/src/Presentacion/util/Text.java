package Presentacion.util;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Text extends JLabel {
	Color backColor = Color.WHITE;

	public Text(String s) {
		super("<html><body><center>" + s + "</center></body></html>", SwingConstants.CENTER);
		Font fuente = new Font("SansSerif", 1, 30);
		setFont(fuente);
		setForeground(new Color(20, 88, 72));
	}

	public void setBackColor(Color c) {
		backColor = c;
	}

	public void setTextColor(Color c) {
		setForeground(c);
	}

	public void setText(String s) {
		super.setText("<html><body><center>" + s + "</center></body></html>");
	}

	public void setSize(int n) {
		Font fuente = new Font("SansSerif", 1, n);
		setFont(fuente);
	}
}
