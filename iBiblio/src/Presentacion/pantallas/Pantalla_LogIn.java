package Presentacion.pantallas;

import java.awt.Color;
import java.awt.Graphics;
import Presentacion.util.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

import Presentacion.Controller;

public class Pantalla_LogIn extends Pantalla {

	private Text error;

	public Pantalla_LogIn(Controller ctrl) {
		super(ctrl);
		initGui();
	}

	@Override
	protected void initGui() {
		// TODO Auto-generated method stub
		this.setLayout(null);

		error = new Text("");
		error.setSize(10);
		error.setTextColor(Color.RED);
		error.setBounds(53, 300, 273, 51);
		this.add(error);

		HintTextField nombre = new HintTextField("Nombre de Usuario");
		nombre.setBounds(53, 340, 273, 52);
		nombre.setHorizontalAlignment(JTextField.CENTER);
		this.add(nombre);

		PasswordTextField contraseña = new PasswordTextField("Contraseña"); // JPasswordField
		contraseña.setEchoChar((char) 0);
		contraseña.setBounds(53, 399, 273, 52);
		contraseña.setHorizontalAlignment(JTextField.CENTER);
		this.add(contraseña);

		JButton iniciar = new Boton("Iniciar");
		iniciar.setBounds(50, 650, 274, 52);
		this.add(iniciar);

		iniciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				nombre.displayError(false);
				contraseña.displayError(false);
				if (nombre.getText().equals("") || contraseña.getText().equals("")) {
					error.setText("Debe rellenar todas las casillas");
					if (nombre.getText().equals(""))
						nombre.displayError(true);
					if (contraseña.getText().equals(""))
						contraseña.displayError(true);
				} else
					try {
						ctrl.inicarSesion(nombre.getText(), contraseña.getText());
						ctrl.getVista().setPantalla(new Pantalla_VistaPrincipal(ctrl));
					} catch (Exception e) {
						error.setText(e.getMessage());
					}
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		Image image = new ImageIcon(("recursos/InicioSesion.png")).getImage();
		// Image image = new ImageIcon(("recursosAUX/Inicio Sesión.png")).getImage();
		g.setColor(Color.BLACK);
		g.drawLine(0, 0, getWidth(), 0);
		g.drawImage(image, 0, 1, getWidth(), getHeight() - 1, this);
		this.setOpaque(false);
		super.paint(g);
	}

	@Override
	public void back() {
		// TODO Auto-generated method stub
		ctrl.getVista().setPantalla(new Pantalla_Inicio(ctrl));
	}

}
