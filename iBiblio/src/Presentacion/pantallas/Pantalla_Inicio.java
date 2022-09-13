package Presentacion.pantallas;

import Presentacion.Controller;
import Presentacion.util.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import Presentacion.Controller;

public class Pantalla_Inicio extends Pantalla {

	public Pantalla_Inicio(Controller ctrl) {
		super(ctrl);
		initGui();
	}

	@Override
	protected void initGui() {
		this.setLayout(null);
		Boton comenzar = new Boton("Comenzar");

		comenzar.setBounds(50, 588, 274, 52);

		this.add(comenzar);
		comenzar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (ctrl.getUsuario() == null)
					ctrl.getVista().setPantalla(new Pantalla_LogIn(ctrl));
				else
					ctrl.getVista().setPantalla(new Pantalla_VistaPrincipal(ctrl));
			}
		});

		JButton registrar = new Boton("Registrarse");
		registrar.setBounds(50, 650, 274, 52);
		this.add(registrar);

		registrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				ctrl.getVista().setPantalla(new Pantalla_Register(ctrl));
			}
		});

	}

	@Override
	public void paint(Graphics g) {
		Image image = new ImageIcon(("recursos/Pantallainicio.png")).getImage();
		// Image image = new ImageIcon(("recursosAUX/Pantalla Inicio.png")).getImage();
		g.setColor(Color.BLACK);
		g.drawLine(0, 0, getWidth(), 0);
		g.drawImage(image, 0, 1, getWidth(), getHeight() - 1, this);// 375.812
		Image icon = new ImageIcon(("recursos/icono.png")).getImage();

		this.setOpaque(false);
		super.paint(g);// puede sobrar
	}

	@Override
	public void back() {
		// TODO Auto-generated method stub
		int n = JOptionPane.showOptionDialog(this,
				"Desea cerrar la aplicacion?", "Quit",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null);//new ImageIcon(this.getClass().getResource("arturo.png"))
		if (n == 0) {
			ctrl.getVista().setPantalla(new Pantalla_Movil(ctrl));
			ctrl.getVista().setUsuario("");
		}
	}

}
