package Presentacion.pantallas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Negocio.Transfer_Biblioteca;
import Presentacion.Controller;
import Presentacion.util.Boton;
import Presentacion.util.Text;

public class Pantalla_Biblioteca extends Pantalla {

	private Text name, valoracion, aforo;
	private String img = "recursos/bibliotecas/defaultBiblioteca.png";
	private Transfer_Biblioteca biblioteca;

	public Pantalla_Biblioteca(Controller _ctrl, Transfer_Biblioteca biblio) {
		super(_ctrl);
		biblioteca = biblio;
		initGui();

		name.setText(biblioteca.getNombre());
		valoracion.setText(Double.toString(biblioteca.getValoracion()));
		aforo.setText(
				Integer.toString(biblioteca.getAforoDisponible()) + "/" + Integer.toString(biblioteca.getAforo()));
		if (biblioteca.getImagen() != null && !biblioteca.getImagen().equals(""))
			img = biblioteca.getImagen();
	}

	@Override
	protected void initGui() {
		this.setLayout(null);

		Boton buscar = new Boton("Buscar mas bibliotecas");
		buscar.setBounds(48, 676, 273, 51);
		buscar.setBorde(null);
		buscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				ctrl.getVista().setPantalla(new Pantalla_VistaPrincipal(ctrl));
			}
		});
		add(buscar);

		JPanel principal = new JPanel() {
			public void paint(Graphics g) {
				g.drawRoundRect(0, 1, getWidth() - 1, getHeight() - 2, 25, 25);
				super.paint(g);
			}
		};
		principal.setBounds(30, 177, 316, 472);
		principal.setOpaque(false);
		add(principal);

		JPanel arriba = new JPanel() {
			public void paint(Graphics g) {
				Image image = new ImageIcon((img)).getImage();
				g.drawImage(image, 5, 15, 128, 128, this);// antes 20 20
				super.paint(g);
			}
		};
		arriba.setPreferredSize(new Dimension(279, 169));
		arriba.setOpaque(false);
		principal.add(arriba);

		arriba.setLayout(new GridLayout(1, 2));
		JPanel panel1 = new JPanel();
		panel1.setOpaque(false);
		arriba.add(panel1);
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(2, 1));
		name = new Text("");
		name.setSize(12);
		panel2.add(name);
		Boton valorar = new Boton("Valorar"); // Cambiar
		valorar.setBorde(null);
		panel2.add(valorar);
		panel2.setOpaque(false);
		arriba.add(panel2);

		JPanel medio = new JPanel();
		medio.setPreferredSize(new Dimension(279, 120));
		medio.setOpaque(false);
		principal.add(medio);

		medio.setLayout(new GridLayout(2, 2));
		Text punt = new Text("Puntuacion: ");
		punt.setSize(15);
		medio.add(punt);
		valoracion = new Text("");
		valoracion.setSize(15);
		medio.add(valoracion);
		Text asientos = new Text("Asientos disponibles: ");
		asientos.setSize(15);
		medio.add(asientos);
		aforo = new Text("");
		aforo.setSize(15);
		medio.add(aforo);

		Boton reserva = new Boton("Reservar");
		reserva.setBorde(null);
		reserva.setBounds(77, 480, 222, 36);
		reserva.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				ctrl.getVista().setPantalla(new Pantalla_ReservaSitio(ctrl, biblioteca));
			}
		});
		add(reserva);

		Boton libro = new Boton("Pedir libro");
		libro.setBorde(null);
		libro.setBounds(77, 530, 222, 36);
		add(libro);

		Boton valoraciones = new Boton("Ver Valoraciones");
		valoraciones.setBorde(null);
		valoraciones.setBounds(77, 580, 222, 36);
		add(valoraciones);
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		Image image = new ImageIcon(("recursos/fondoPrincipal.png")).getImage();
		g.setColor(Color.BLACK);
		g.drawLine(0, 0, getWidth(), 0);
		g.drawImage(image, 0, 1, getWidth(), getHeight(), this);
		this.setOpaque(false);
		super.paint(g);
	}

	@Override
	public void back() {
		// TODO Auto-generated method stub
		ctrl.getVista().setPantalla(new Pantalla_VistaPrincipal(ctrl));	}
}
