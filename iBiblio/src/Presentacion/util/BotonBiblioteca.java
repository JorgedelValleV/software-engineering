package Presentacion.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Negocio.Transfer_Biblioteca;
import Presentacion.pantallas.Pantalla_VistaPrincipal;

public class BotonBiblioteca extends JButton {

	private boolean mouseOver = false;

	private String name = "nombre biblioteca", places = "aforo biblioteca", val = "valoracion",
			img = "recursos/defaultBiblioteca.png";

	public BotonBiblioteca() {
		init();
	}

	public BotonBiblioteca(Transfer_Biblioteca biblio) {
		name = biblio.getNombre();
		places = "Aforo: " + Integer.toString(biblio.getAforo());
		val = "Valoracion: " + Double.toString(biblio.getValoracion());
		if (biblio.getImagen() != null && !biblio.getImagen().equals(""))
			img = biblio.getImagen();
		init();
	}

	private void init() {
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusable(false);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				mouseOver = true;
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mouseOver = false;
				repaint();
			}
		});
		this.setPreferredSize(new Dimension(279, 169));
		this.setLayout(new GridLayout(1, 2));
		JPanel panel1 = new JPanel();
		panel1.setOpaque(false);
		this.add(panel1);
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(3, 1));
		Text nombre = new Text(name);
		nombre.setSize(12);
		panel2.add(nombre);
		Text aforo = new Text(places);
		aforo.setSize(12);
		panel2.add(aforo);
		Text rating = new Text(val);
		rating.setSize(12);
		panel2.add(rating);
		panel2.setOpaque(false);
		add(panel2);

	}

	public void paint(Graphics g) {

		Color borderColor = new Color(20, 88, 72);

		if (mouseOver) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(5));
			g2.setColor(borderColor);
			g2.drawRoundRect(1, 2, getWidth() - 2, getHeight() - 4, 15, 15);
		}
		// */
		g.setColor(borderColor);
		g.drawRoundRect(0, 1, getWidth() - 1, getHeight() - 2, 15, 15);

		Image image = new ImageIcon((img)).getImage();
		g.drawImage(image, 20, 20, 128, 128, this);

		super.paint(g);
	}
}