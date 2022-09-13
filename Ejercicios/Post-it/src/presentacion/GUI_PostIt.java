package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import negocio.View;
import negocio.PostIt;

public class GUI_PostIt extends JFrame implements View {
	private int id, xPos, yPos;
	private Controller ctrl;
	private JButton delete, save, view;
	private JPanel titlePanel, mainPanel;
	private JTextArea area;
	private JPasswordField codeArea;

	//Acciones de los componentes visuales
	//delete
	private void deleteAction() {
		dispose();
	}
	//protected view
	private void viewAction() {
		if (!area.isVisible()) {
			ctrl.action(3, id, "", 0, codeArea.getText(), "");
		} else if (area.isVisible()) {
			area.setVisible(false);
			setSize(new Dimension(250, 40));
			view.setIcon(new ImageIcon("resources/eye-closed.png"));
		}
	}
	//save
	private void saveAction() throws IOException {
		ctrl.action(2, id, "", 0, "", area.getText());
		
	}
	
	public void actualizar(int event, int id, String title, String code, int color, boolean unlocked,String text) {
		if(id==this.id && event == 3 && unlocked) {	
			area.setVisible(true);
			codeArea.setText("");
			setSize(new Dimension(250, 280));
			view.setIcon(new ImageIcon("resources/eye-open.png"));
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
										     $$$" 									     
*/
	
	public GUI_PostIt(Controller ctrl, int id, String title, Point p, Color color, boolean locked,String text) {
		this.ctrl = ctrl;
		ctrl.addView(this);

		this.id = id;
		setSize(new Dimension(250, 280));
		setAlwaysOnTop(true);
		setLocation(p);
		setUndecorated(true);
		setBackground(color);
		setPreferredSize(new Dimension(200, 230));

		delete = new JButton();
		delete.setOpaque(false);
		delete.setContentAreaFilled(false);
		delete.setBorderPainted(false);
		delete.setIcon(new ImageIcon("resources/close.png"));
		delete.setPreferredSize(new Dimension(25, 25));
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteAction();
			}
		});

		save = new JButton();
		save.setOpaque(false);
		save.setContentAreaFilled(false);
		save.setBorderPainted(false);
		save.setIcon(new ImageIcon("resources/save.png"));
		save.setPreferredSize(new Dimension(25, 25));
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					saveAction();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		codeArea = new JPasswordField();
		codeArea.setPreferredSize(new Dimension(90, 30));
		codeArea.setMaximumSize(new Dimension(90, 30));

		view = new JButton();
		view.setOpaque(false);
		view.setContentAreaFilled(false);
		view.setBorderPainted(false);
		view.setIcon(new ImageIcon("resources/eye-open.png"));
		view.setPreferredSize(new Dimension(25, 25));
		view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewAction();
			}
		});

		titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		titlePanel.add(new Label(" " + title));
		if (locked) {
			titlePanel.add(view);
			titlePanel.add(codeArea);
		}
		titlePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		titlePanel.add(save);
		titlePanel.add(delete);
		titlePanel.setBackground(Color.white);
		titlePanel.setFont(new Font("Helvetica", Font.PLAIN, 16));
		titlePanel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				xPos = me.getX();
				yPos = me.getY();
			}
		});
		titlePanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent me) {
				GUI_PostIt.this.setLocation(getX() + me.getX() - xPos, getY() + me.getY() - yPos);
			}
		});

		area = new JTextArea();
		area.setBackground(color);
		area.setText(text);
		area.setPreferredSize(new Dimension(200, 200));
		area.setForeground(Color.BLACK);
		area.setBorder(new EmptyBorder(10, 10, 10, 10));
		area.setFont(new Font("Helvetica", Font.PLAIN, 14));

		mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(titlePanel, BorderLayout.NORTH);
		mainPanel.add(area, BorderLayout.CENTER);
		getContentPane().add(mainPanel);

		setVisible(true);
	}
}