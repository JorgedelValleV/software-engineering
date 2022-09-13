package Presentacion.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPasswordField;

public class PasswordTextField extends JPasswordField implements FocusListener {
	private Color borderColor = Color.black;
	private Color hintColor = new Color(20, 88, 72), textColor = Color.black, backColor = Color.WHITE;
	private final String hint;
	private boolean showingHint;

	public PasswordTextField(final String hint) {
		super(hint);
		this.hint = hint;
		this.showingHint = true;
		super.addFocusListener(this);
		Font fuente = new Font("SansSerif", 0, 20);
		this.setFont(fuente);
		this.setForeground(hintColor);
		this.setBorder(null);
		this.setHorizontalAlignment(JPasswordField.CENTER);
		this.setEchoChar((char) 0);
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (this.getText().isEmpty()) {
			super.setText("");
			this.setForeground(textColor);
			showingHint = false;
		}
		this.setEchoChar('\u2022');
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (this.getText().isEmpty()) {
			super.setText(hint);
			this.setForeground(hintColor);
			showingHint = true;
			this.setEchoChar((char) 0);
		}
	}

	@Override
	public String getText() {
		return showingHint ? "" : super.getText();
	}

	public void displayError(boolean err) {
		backColor = (err ? new Color(252, 214, 214) : Color.WHITE);
		repaint();

	}

	public void paint(Graphics g) {

		this.setBackground(backColor);
		super.paint(g);
		g.setColor(borderColor);
		g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);

	}

}
