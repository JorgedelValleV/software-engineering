package Presentacion.pantallas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import Presentacion.Controller;
import Presentacion.util.Boton;
import Presentacion.util.Text;

public class Pantalla_exito extends Pantalla {

	private Pantalla siguiente;
	private String mensaje;

	public Pantalla_exito(Controller _ctrl, Pantalla pantallaSiguiente, String msg) {
		super(_ctrl);
		siguiente = pantallaSiguiente;
		mensaje = msg;
		initGui();
	}

	@Override
	protected void initGui() {
		this.setLayout(null);

		Text exito = new Text(mensaje);

		exito.setBounds(0, 350, 391, 80);
		add(exito);

		Boton volver = new Boton("Volver");
		volver.setBorde(null);
		volver.setBounds(50, 650, 274, 52);
		volver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				ctrl.getVista().setPantalla(siguiente);
			}
		});

		add(volver);
	}

	public void paint(Graphics g) {
		Image image = new ImageIcon(("recursos/Pantallaexito.png")).getImage();
		g.setColor(Color.BLACK);
		g.drawLine(0, 0, getWidth(), 0);
		g.drawImage(image, 0, 1, getWidth(), getHeight() - 1, this);// 375.812
		this.setOpaque(false);
		super.paint(g);// puede sobrar
	}

	@Override
	public void back() {
		// TODO Auto-generated method stub
		
	}
}
