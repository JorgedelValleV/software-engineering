package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import negocio.View;
import negocio.PostIt;
import negocio.SA_PostIt_Imp;

public class GUI_MainWindow extends JFrame implements View {
	private static Color[] COLORS = { Color.yellow, Color.cyan, Color.pink, Color.white };

	private int id = 0, x, y, colornumber;
	private boolean locked = false;
	
	private Controller ctrl;
	private JButton add, close, color, lock,open;
	private JTextField titleArea, codeArea;

	public GUI_MainWindow(Controller ctrl) {
		super();
		this.ctrl = ctrl;
		ctrl.addView(this);
		initGUI();

		setUndecorated(true);
		setSize(410, 40);
		setLocation(new Point(250, 250));
		setVisible(true);
	}
	
	//Acciones de los componentes visuales
	//add
	void addAction() {
		if (titleArea.getText().equals("")) {
			JOptionPane.showMessageDialog(this.getParent(),"Introduce a title");
		} else {
			String code = codeArea.getText();
			ctrl.action(0, id, titleArea.getText(), colornumber, code, "");
			id++;
		}
	}
	
	//lock
	void lockAction() {
		locked = !locked;
		if (locked) {
			lock.setIcon(new ImageIcon("resources/lock-closed.png"));
			codeArea.setEnabled(true);
		} else {
			lock.setIcon(new ImageIcon("resources/lock-open.png"));
			codeArea.setEnabled(false);
		}
	}
	void openAction() {
		ctrl.cargar();
	}

	public void actualizar(int event, int id, String title, String code, int color, boolean unlocked,String text) {
		switch (event) {
		//add
		case(0) : { 
			x = getLocation().x + 200;
			y = getLocation().y + 100;
			new GUI_PostIt(ctrl, id, title, new Point(x, y), COLORS[color], locked,text);
			break;
		}
		}
	}
	
/*	 
										                 uuuuuuu
										             uu$$$$$$$$$$$uu
										          uu$$$$$$$$$$$$$$$$$uu
										         u$$$$$$$$$$$$$$$$$$$$$u
										        u$$$$$$$$$$$$$$$$$$$$$$$u
										       u$$$$$$$$$$$$$$$$$$$$$$$$$u
										       u$$$$$$$$$$$$$$$$$$$$$$$$$u
										       u$$$$$$"   "$$$"   "$$$$$$u
										       "$$$$"      u$u       $$$$"
										        $$$u       u$u       u$$$
										        $$$u      u$$$u      u$$$
										         "$$$$uu$$$   $$$uu$$$$"
										          "$$$$$$$"   "$$$$$$$"
										            u$$$$$$$u$$$$$$$u
										             u$"$"$"$"$"$"$u
										  uuu        $$u$ $ $ $ $u$$       uuu
										 u$$$$        $$$$$u$u$u$$$       u$$$$
										  $$$$$uu      "$$$$$$$$$"     uu$$$$$$
										u$$$$$$$$$$$uu    """""    uuuu$$$$$$$$$$
										$$$$"""$$$$$$$$$$uuu   uu$$$$$$$$$"""$$$"
										 """      ""$$$$$$$$$$$uu ""$"""
										           uuuu ""$$$$$$$$$$uuu
										  u$$$uuu$$$$$$$$$uu ""$$$$$$$$$$$uuu$$$
										  $$$$$$$$$$""""           ""$$$$$$$$$$$"
										   "$$$$$"                      ""$$$$""
										     $$$"                         $$$$"										     
*/
	
	public void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		setContentPane(mainPanel);
		mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		titleArea = new JTextField("Title");
		titleArea.setPreferredSize(new Dimension(90, 30));
		titleArea.setMaximumSize(new Dimension(90, 30));
		
		codeArea = new JPasswordField("Pass");
		codeArea.setPreferredSize(new Dimension(90, 30));
		codeArea.setMaximumSize(new Dimension(90, 30));
		codeArea.setEnabled(false);

		add = new JButton();
		add.setIcon(new ImageIcon("resources/add.png"));
		add.setContentAreaFilled(false);
		add.setBorderPainted(false);
		add.setPreferredSize(new Dimension(25, 25));
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addAction();
			}
		});

		close = new JButton();
		close.setOpaque(false);
		close.setContentAreaFilled(false);
		close.setBorderPainted(false);
		close.setIcon(new ImageIcon("resources/close.png"));
		close.setPreferredSize(new Dimension(25, 25));
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		color = new JButton();
		colornumber = 0;
		color.setBackground(COLORS[colornumber]);
		color.setOpaque(true);
		color.setBorderPainted(false);
		color.setPreferredSize(new Dimension(25, 25));
		color.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colornumber++;
				if (colornumber >= COLORS.length) colornumber = 0;
				color.setBackground(COLORS[colornumber]);
			}
		});

		lock = new JButton();
		lock.setOpaque(false);
		lock.setContentAreaFilled(false);
		lock.setBorderPainted(false);
		lock.setIcon(new ImageIcon("resources/lock-open.png"));
		lock.setPreferredSize(new Dimension(25, 25));
		lock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lockAction();
			}
		});
		
	
		
		open=new JButton();
		lock.setBorderPainted(false);
		open.setIcon(new ImageIcon("resources/fileChooser.png"));
		open.setPreferredSize(new Dimension(20, 20));
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openAction();
			}
		});

		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		mainPanel.add(close);
		mainPanel.add(open);
		mainPanel.add(new JSeparator());
		mainPanel.add(new Label("Post-it"));
		mainPanel.add(titleArea);
		mainPanel.add(codeArea);
		mainPanel.add(color);
		mainPanel.add(lock);
		mainPanel.add(add);
	}
}
