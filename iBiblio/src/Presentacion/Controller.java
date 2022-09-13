package Presentacion;

import java.util.ArrayList;
import java.util.Stack;

import Integracion.DAO;
import Integracion.FactoriaDAO;
import Negocio.*;
import Presentacion.pantallas.Pantalla;
import Presentacion.pantallas.Pantalla_Biblioteca;
import Presentacion.pantallas.Pantalla_Inicio;
import Presentacion.pantallas.Pantalla_LogIn;
import Presentacion.pantallas.Pantalla_Register;
import Presentacion.pantallas.Pantalla_ReservaSitio;
import Presentacion.pantallas.Pantalla_VistaPrincipal;
import Presentacion.pantallas.Pantalla_exito;

import java.util.List;

public class Controller {
	private static Controller instancia;
	private GUI vista;
	private Transfer_Cliente usuario = null; // Guardar el usuario que está utilizando la app

	public static Controller getInstancia() {
		if (instancia == null)
			instancia = new Controller();
		return instancia;
	}

	private Controller() {
	}

	public void setVista(GUI value) {
		this.vista = value;
	}

	public GUI getVista() {
		return vista;
	}

	public Transfer_Cliente getUsuario() {
		return usuario;
	}

	public void inicarSesion(String usuario, String contraseña) {
		FactoriaSA factoria = FactoriaSA.getInstancia();
		SACliente sa = factoria.generarSACliente();
		this.usuario = sa.iniciarSesionCliente(usuario, contraseña);
		vista.setUsuario(this.usuario.getNombre());
	}
	public void cerrarSesion() {
		this.usuario = null;
		vista.setUsuario("");
	}

	public void registrarse(String email, String usuario, String contraseña) {
		FactoriaSA factoria = FactoriaSA.getInstancia();
		SACliente sa = factoria.generarSACliente();
		sa.altaCliente(email, usuario, contraseña);
	}

	public List<Transfer_Biblioteca> listaBibliotecas() {
		FactoriaSA factoria = FactoriaSA.getInstancia();
		SABiblioteca sa = factoria.generarSABiblioteca();
		return sa.getListaBibliotecas();
	}

	public void reservarSitio(Transfer_Biblioteca biblio, int num) {
		FactoriaSA factoria = FactoriaSA.getInstancia();
		SABiblioteca sa = factoria.generarSABiblioteca();
		sa.reservarSitio(biblio.getNombre(), num,true);
		factoria.generarSACliente().reservaSitio(usuario.getNombre(), biblio.getNombre(), num);
		usuario.setBiblioteca(biblio.getNombre());
		usuario.setSitio(num);
		
	}

	public void cancelarSitio() {
		FactoriaSA factoria = FactoriaSA.getInstancia();
		factoria.generarSACliente().reservaSitio(usuario.getNombre(), "", 0);
		factoria.generarSABiblioteca().reservarSitio(usuario.getBiblioteca(), usuario.getSitio(), false);
		usuario.setBiblioteca("");
		usuario.setSitio(0);
	}

}
