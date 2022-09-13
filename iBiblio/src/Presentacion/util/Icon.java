package Presentacion.util;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Icon extends JLabel {

	Image image;

	public Icon(String s) {
		super();
		image = new ImageIcon(s).getImage();
	}

	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		super.paint(g);
	}
}
