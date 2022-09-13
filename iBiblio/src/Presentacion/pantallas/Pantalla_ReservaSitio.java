package Presentacion.pantallas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import Negocio.Mesa;
import Negocio.Silla;
import Negocio.Transfer_Biblioteca;
import Presentacion.Controller;
import Presentacion.util.Boton;
import Presentacion.util.Text;

public class Pantalla_ReservaSitio extends Pantalla {

	private Transfer_Biblioteca biblioteca;
	private int ladoSilla;
	private final Color borderColor = new Color(20, 88, 72);
	private BotonSilla pulsado = null;
	private boolean puedeReservar = false;
	private int miSitio = -1;

	public Pantalla_ReservaSitio(Controller _ctrl, Transfer_Biblioteca biblio) {
		super(_ctrl);
		biblioteca = biblio;
		ladoSilla = biblio.getLadoSilla();
		if(ctrl.getUsuario().getBiblioteca().equals(""))
			puedeReservar = true;
		else if (ctrl.getUsuario().getBiblioteca().equals(biblio.getNombre()))
			miSitio = ctrl.getUsuario().getSitio();
		initGui();
	}

	@Override
	protected void initGui() {
		this.setLayout(null);
		this.setBackground(Color.white);

		JPanel arriba = new JPanel() {
			public void paint(Graphics g) {
				Image image = new ImageIcon((biblioteca.getImagen())).getImage();
				g.drawImage(image, 5, 15, 128, 128, this);
				super.paint(g);
			}
		};
		arriba.setBounds(50, 20, 279, 169);
		arriba.setOpaque(false);
		add(arriba);
		arriba.setLayout(new GridLayout(1, 2));
		JPanel panel1 = new JPanel();
		panel1.setOpaque(false);
		arriba.add(panel1);
		JPanel panel2 = new JPanel();
		panel2.setOpaque(false);
		panel2.setLayout(new GridLayout(2, 1));
		Text name = new Text(biblioteca.getNombre());
		name.setSize(15);
		panel2.add(name);
		Text valoracion = new Text("Valoracion: " + biblioteca.getValoracion());
		valoracion.setSize(15);
		panel2.add(valoracion);
		arriba.add(panel2);

		List<Mesa> mesas = biblioteca.getMesas();
		for (Mesa m : mesas) {
			PanelMesa p = new PanelMesa();
			p.setBounds(m.getX(), m.getY(), m.getAlto(), m.getAncho());
			add(p);
		}

		List<Silla> sillas = biblioteca.getSillas();
		for (int i = 0; i < sillas.size(); i++) {
			Silla s = sillas.get(i);
			BotonSilla p = new BotonSilla(i, s.isOcupado());
			p.setBounds(s.getX(), s.getY(), ladoSilla, ladoSilla);
			add(p);
		}
		// */
		
		
		
		Boton reservar = new Boton(puedeReservar ? "Reservar" : "Cancelar mi reserva");
		reservar.setBorde(null);
		reservar.setBounds(120, 735, 250, 31);
		reservar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if(!puedeReservar) {
					ctrl.cancelarSitio();
					ctrl.getVista().setPantalla(new Pantalla_exito(ctrl, new Pantalla_VistaPrincipal(ctrl),
							"¡Sitio cancelado!"));
				}
				else if (pulsado != null) {
					ctrl.reservarSitio(biblioteca, pulsado.getNum());
					ctrl.getVista().setPantalla(new Pantalla_exito(ctrl, new Pantalla_VistaPrincipal(ctrl),
						"¡Sitio reservado!"));
				}
				
			}
		});
		add(reservar);
		
		Boton volver = new Boton("Volver");
		volver.setBorde(null);
		volver.setBounds(10,735,100,31);
		volver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				ctrl.getVista().setPantalla(new Pantalla_Biblioteca(ctrl,biblioteca));
			}
		});
		add(volver);
	}

	public void cambiaPulsado(BotonSilla b) {
		if (pulsado != null)
			pulsado.quitarPulsado();
		pulsado = b;
	}

	private class BotonSilla extends JButton {
		private boolean reservada = false;
		private boolean clicked = false;
		private boolean mouseOver = false;
		private int num;

		public BotonSilla(int n, boolean r) {
			super();
			setBorderPainted(false);
			setContentAreaFilled(false);
			setFocusable(false);
			num = n;
			reservada = r;
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
			addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					if (!reservada && !clicked) {
						clicked = true;
						cambiaPulsado(BotonSilla.this);
					}
				}
			});
		}

		public int getNum() {
			return num;
		}

		public void quitarPulsado() {
			clicked = false;
			repaint();
		}

		public void paint(Graphics g) {

			if (reservada) {
				g.setColor(miSitio == num ? Color.yellow : borderColor);
				g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
			} else if (clicked) {
				g.setColor(new Color(70, 193, 163));
				g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
			}
			g.setColor(borderColor);
			if (mouseOver) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setStroke(new BasicStroke(3));
				g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 10, 10);
			} else
				g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
			super.paint(g);
		}
	}

	private class PanelMesa extends JPanel {

		public void paint(Graphics g) {
			g.setColor(borderColor);
			g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
		}
	}

	@Override
	public void back() {
		// TODO Auto-generated method stub
		ctrl.getVista().setPantalla(new Pantalla_Biblioteca(ctrl, biblioteca));
	}
}
