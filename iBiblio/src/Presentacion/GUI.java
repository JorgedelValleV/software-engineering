package Presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Presentacion.pantallas.Pantalla;
import Presentacion.pantallas.Pantalla_Biblioteca;
import Presentacion.pantallas.Pantalla_Inicio;
import Presentacion.pantallas.Pantalla_VistaPrincipal;

public class GUI extends JFrame {//abstract
    
    private Controller _ctrl;
    Pantalla screen;
    JPanel barra;
    JPanel mainPanel;
    JLabel usuario;
    JButton atras;
    JButton home;
    
    public GUI(Controller ctrl) {
		super();
		_ctrl=ctrl;
		_ctrl.setVista(this);
		init();
	}

    
    private void init() {
    	this.setIconImage(new ImageIcon("recursos/icono.png").getImage());
    	this.setSize(391, 852);//375, 813  359 774 (diferencia de 16, 39)//391, 852
    	this.setResizable(false);
    	this.setLocationRelativeTo(null); //para que salga centrada la ventana
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
    	mainPanel = new JPanel();
    	this.getContentPane().add(mainPanel);
    	mainPanel.setLayout(new BorderLayout());
    	
    	screen = new Pantalla_Inicio(_ctrl);
    	mainPanel.add(screen,BorderLayout.CENTER);
    			
    	barra=new JPanel(){
			public void paint(Graphics g) {
				Image image = new ImageIcon(("recursos/barra.png")).getImage();
				g.drawImage(image, 0, 0, 391, 39, this);//antes 20 20
				super.paint(g);
			}
		};
		barra.setOpaque(false);
//		dos.setBackground(Color.BLACK);
		barra.setPreferredSize(new Dimension(391,39));
		barra.setMaximumSize(new Dimension(391,39));
		barra.setVisible(true);
		mainPanel.add(barra,BorderLayout.PAGE_END);
		barra.setLayout(null);
		
		usuario=new JLabel("");
		usuario.setBackground(Color.black);
		usuario.setFont(new Font("SansSerif", 1, 14));
		usuario.setForeground(new Color(57, 212, 184));
		usuario.setOpaque(true);
		usuario.setBounds(275,8,100,24);
		barra.add(usuario);
		
		atras = new JButton();
		atras.setToolTipText("back");
		atras.setBounds(50,4,68,30);
		atras.setIcon(new ImageIcon("recursos/back2.png"));
		atras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				//FUNCIONALIDAD BOTON BACK NO DISPONIBLE
				screen.back();
			}
		});
		atras.addMouseListener(new MouseListener() {

			public void mousePressed(MouseEvent arg0) {
				atras.setIcon(new ImageIcon("recursos/back1.png"));
			}
			public void mouseReleased(MouseEvent arg0) {
				atras.setIcon(new ImageIcon("recursos/back2.png"));
			}
			public void mouseClicked(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0)  {}
			public void mouseExited(MouseEvent arg0) {}
			
			
		});
		atras.setBorderPainted(false);
		atras.setContentAreaFilled(false);
		atras.setFocusable(false);
		barra.add(atras);
		
		home = new JButton();
		home.setToolTipText("inicio");
		home.setBounds(160,4,68,30);
		home.setIcon(new ImageIcon("recursos/home2.png"));
		home.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				setPantalla(new Pantalla_Inicio(_ctrl));
			}
		});
		home.addMouseListener(new MouseListener() {

			public void mousePressed(MouseEvent arg0) {
				home.setIcon(new ImageIcon("recursos/home1.png"));
			}
			public void mouseReleased(MouseEvent arg0) {
				home.setIcon(new ImageIcon("recursos/home2.png"));
			}
			public void mouseClicked(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0)  {}
			public void mouseExited(MouseEvent arg0) {}
			
			
		});
		home.setBorderPainted(false);
		home.setContentAreaFilled(false);
		home.setFocusable(false);
		barra.add(home);
		
    }

    public void setPantalla(Pantalla p) {
    	screen.setVisible(false);
    	screen = p;
    	mainPanel.add(screen,BorderLayout.CENTER);  	
    	screen.setVisible(true);
    	this.setVisible(true);
    }


	public void setUsuario(String nombre) {
		usuario.setText(nombre);
	}
    
    
}