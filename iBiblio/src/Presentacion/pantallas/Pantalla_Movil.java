package Presentacion.pantallas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import Presentacion.Controller;
import Presentacion.util.Boton;

public class Pantalla_Movil extends Pantalla {

	public Pantalla_Movil(Controller ctrl) {
		super(ctrl);
		initGui();
	}

	@Override
	protected void initGui() {
		this.setLayout(null);
		JButton app = new JButton();

		app.setBounds(287, 462, 60, 60);
		
		app.setBorderPainted(false); 
		app.setContentAreaFilled(false);
		app.setFocusable(false);
		
		Image icon= new ImageIcon(("recursos/icono.png")).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon=new ImageIcon(icon);
		app.setIcon(scaledIcon);
		
		this.add(app);
		app.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				ctrl.getVista().setPantalla(new Pantalla_Inicio(ctrl));
				if(ctrl.getUsuario()!=null)
					ctrl.getVista().setUsuario(ctrl.getUsuario().getNombre());
			}
		});


	}

	@Override
	public void paint(Graphics g) {
		Image image = new ImageIcon(("recursos/movil.png")).getImage();//.getScaledInstance(getWidth(), getHeight()-1, Image.SCALE_SMOOTH);
		g.setColor(Color.BLACK);
		g.drawLine(0, 0, getWidth(), 0);
		g.drawImage(image, 0, 1, getWidth(), getHeight() - 1, this);// 375.812
		g.setColor(Color.white);
		g.drawString("IBiblio", 300, 544);
		this.setOpaque(false);
		super.paint(g);// puede sobrar
	}

	@Override
	public void back() {
		//nada
	}

}