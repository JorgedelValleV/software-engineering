package Presentacion.pantallas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Presentacion.Controller;
import Presentacion.util.Boton;
import Presentacion.util.BotonBiblioteca;
import Presentacion.util.Text;
import Negocio.Transfer_Biblioteca;

public class Pantalla_VistaPrincipal extends Pantalla {

	JPanel listaBibliotecas;
	private Text error;

	public Pantalla_VistaPrincipal(Controller ctrl) {
		super(ctrl);
		initGui();
	}

	@Override
	protected void initGui() {
		this.setLayout(null);
		
		error = new Text("");
		error.setSize(10);
		error.setTextColor(Color.RED);
		error.setBounds(53, 200, 273, 51);
		this.add(error);

		Boton buscar = new Boton("Buscar bibliotecas");
		buscar.setBounds(48, 676, 273, 51);
		buscar.setBorde(null);
		buscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					pintaBibliotecas();
				} catch (Exception e) {
					error.setText(e.getMessage());
				}
			}
		});
		add(buscar);

		listaBibliotecas = new JPanel();
		// listaBibliotecas.setLayout(new FlowLayout(FlowLayout.LEFT));
		listaBibliotecas.setBackground(Color.white);
		// listaBibliotecas.setLayout(new
		// BoxLayout(listaBibliotecas,BoxLayout.LINE_AXIS));

		JScrollPane scroll = new JScrollPane(listaBibliotecas);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBounds(30, 177, 316, 472);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBorder(null);
		scroll.getVerticalScrollBar().setPreferredSize (new Dimension(0,0)); 
		scroll.getVerticalScrollBar().setUnitIncrement(15);

		add(scroll);

		repaint();

	}

	private void pintaBibliotecas() {
		List<Transfer_Biblioteca> lista = ctrl.listaBibliotecas();

		int n = lista.size();
		listaBibliotecas.removeAll();
		listaBibliotecas.setPreferredSize(new Dimension(316, 18 + 170 * n));
		for (int i = 0; i < n; i++) {
			Transfer_Biblioteca biblio = lista.get(i);
			BotonBiblioteca b = new BotonBiblioteca(biblio);
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					ctrl.getVista().setPantalla(new Pantalla_Biblioteca(ctrl, biblio));
				}
			});
			listaBibliotecas.add(b);
		}
		validate();
	}

	@Override
	public void paint(Graphics g) {
		Image image = new ImageIcon(("recursos/fondoPrincipal.png")).getImage();
		g.setColor(Color.BLACK);
		g.drawLine(0, 0, getWidth(), 0);
		g.drawImage(image, 0, 1, getWidth(), getHeight(), this);
		this.setOpaque(false);
		super.paint(g);
		g.drawRoundRect(28,172,320,480,15,15);
	}

	@Override
	public void back() {
		// TODO Auto-generated method stub
		int n = JOptionPane.showOptionDialog(this,
				"Desea cerrar sesion?", "Quit",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null);//new ImageIcon(this.getClass().getResource("arturo.png"))
		if (n == 0) {
			ctrl.cerrarSesion();
			ctrl.getVista().setPantalla(new Pantalla_LogIn(ctrl));
		}
		else ctrl.getVista().setPantalla(new Pantalla_Inicio(ctrl));
	}
}
