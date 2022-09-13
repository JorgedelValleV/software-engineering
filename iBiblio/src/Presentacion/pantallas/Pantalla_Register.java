package Presentacion.pantallas;

import java.awt.Color;
import java.awt.Graphics;
import Presentacion.util.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import Presentacion.Controller;

public class Pantalla_Register extends Pantalla {

	private CheckBox terminosAceptados, biblioteca;

	private Text error;

	public Pantalla_Register(Controller ctrl) {
		super(ctrl);
		initGui();
	}

	protected void initGui() {
		this.setLayout(null);

		error = new Text("");
		error.setSize(10);
		error.setTextColor(Color.RED);
		error.setBounds(53, 240, 273, 51);
		this.add(error);

		HintTextField correo = new HintTextField("Correo electronico");
		correo.setBounds(53, 281, 273, 51);// 50 268 262 49 +3 +13 +11 +2
		this.add(correo);

		HintTextField nombre = new HintTextField("Nombre de Usuario");
		nombre.setBounds(53, 340, 273, 51);
		this.add(nombre);

		PasswordTextField contraseña = new PasswordTextField("Contraseña");
		contraseña.setBounds(53, 399, 273, 51);
		this.add(contraseña);

		PasswordTextField contraseña2 = new PasswordTextField("Repita contraseña");
		contraseña2.setBounds(53, 458, 273, 51);
		this.add(contraseña2);

		JButton registrar = new Boton("Registrarme");
		registrar.setBounds(53, 689, 273, 51);
		this.add(registrar);
		registrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				correo.displayError(false);
				nombre.displayError(false);
				contraseña.displayError(false);
				contraseña2.displayError(false);
				if (!terminosAceptados.getStatus()) {
					// JOptionPane.showMessageDialog(Pantalla_Register.this, "Necesita aceptar los
					// terminos\npara continuar con el registro", "Error" , 0);
					error.setText("Necesita aceptar los terminos para <br>continuar con el registro");
				} else if (correo.getText().equals("") || nombre.getText().equals("") || contraseña.getText().equals("")
						|| contraseña2.getText().equals("")) {
					error.setText("Debe rellenar todas las casillas");
					if (correo.getText().equals("")) {
						correo.displayError(true);
					}
					if (nombre.getText().equals("")) {
						nombre.displayError(true);
					}
					if (contraseña.getText().equals("")) {
						contraseña.displayError(true);
					}
					if (contraseña2.getText().equals("")) {
						contraseña2.displayError(true);
					}
				} else if (!contraseña.getText().equals(contraseña2.getText())) {
					error.setText("Las contraseñas no coinciden");
					contraseña2.displayError(true);
				} else {
					try {
						ctrl.registrarse(correo.getText(), nombre.getText(), contraseña.getText());
						ctrl.getVista().setPantalla(
								new Pantalla_exito(ctrl, new Pantalla_Inicio(ctrl), "¡Registrado con éxito!"));
					} catch (Exception e) {
						error.setText(e.getMessage());
					}
				}
			}
		});

		terminosAceptados = new CheckBox("Acepto los terminos y condiciones de privacidad");
		terminosAceptados.setBounds(90, 531, 200, 30);
		this.add(terminosAceptados);

		biblioteca = new CheckBox("Deseo registrarme como biblioteca");
		biblioteca.setBounds(90, 573, 200, 30);
		this.add(biblioteca);

		// */
	}

	@Override
	public void paint(Graphics g) {
		Image image = new ImageIcon(("recursos/Registro.png")).getImage();
		// Image image = new ImageIcon(("recursosAUX/Registro.png")).getImage();
		g.setColor(Color.BLACK);
		g.drawLine(0, 0, getWidth(), 0);
		g.drawImage(image, 0, 1, getWidth(), getHeight() - 1, this);
		// g.drawString("Acepto los terminos", 138, 539);
		// g.drawString(" y condiciones de privacidad", 113, 552);
		// g.drawString("Deseo registrarme como", 123, 582);
		// g.drawString("biblioteca", 160, 595);
		this.setOpaque(false);
		super.paint(g);
	}

	@Override
	public void back() {
		// TODO Auto-generated method stub
		ctrl.getVista().setPantalla(new Pantalla_Inicio(ctrl));
	}

}
