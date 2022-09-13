package Presentacion.pantallas;

import Presentacion.Controller;

import javax.swing.JPanel;

public abstract class Pantalla extends JPanel {

	protected Controller ctrl;

	public Pantalla() {

	}

	public Pantalla(Controller _ctrl) {
		super();
		ctrl = _ctrl;
		
	}

	protected abstract void initGui();
	public abstract void back();
}
