package Negocio;

import java.util.ArrayList;
import java.util.List;

public class Transfer_Biblioteca {
	private String nombre = "";
	private int idBiblioteca;
	private int aforo = 0;
	private int aforoDisponible = 0;
	private String imagen = null;
	private float valoracion = 3;
	private List<Mesa> mesas = new ArrayList<Mesa>();
	private List<Silla> sillas = new ArrayList<Silla>();
	private int lado_silla = 0;

	private List<Transfer_Libro> libros = new ArrayList<Transfer_Libro>();

	public Transfer_Biblioteca() {
	}

	public Transfer_Biblioteca(String n, int a, int d, float v, String i) {
		nombre = n;
		aforo = a;
		aforoDisponible = d;
		valoracion = v;
		imagen = i;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(final String value) {
		this.nombre = value;
	}

	public int getIdBiblioteca() {
		return this.idBiblioteca;
	}

	public String getImagen() {
		return this.imagen;
	}

	public int getAforo() {
		return aforo;
	}

	public void setAforo(int aforo) {
		this.aforo = aforo;
	}

	public int getAforoDisponible() {
		return aforoDisponible;
	}

	public void setAforoDisponible(int aforoDisponible) {
		this.aforoDisponible = aforoDisponible;
	}

	public void setImagen(final String value) {
		this.imagen = value;
	}

	public void setValoracion(final float value) {
		this.valoracion = value;
	}

	public float getValoracion() {
		return this.valoracion;
	}

	public List<Mesa> getMesas() {
		return mesas;
	}

	public void setMesas(List<Mesa> mesas) {
		this.mesas = mesas;
	}

	public List<Silla> getSillas() {
		return sillas;
	}

	public void setSillas(List<Silla> sillas) {
		this.sillas = sillas;
	}

	public int getLadoSilla() {
		return lado_silla;
	}

	public void setLadoSilla(int lado) {
		this.lado_silla = lado;
	}

	public List<Transfer_Libro> getLibrillos() {
		return libros;
	}

	public void setLibrillos(List<Transfer_Libro> librillos) {
		this.libros = librillos;
	}

	public void setIdBiblioteca(int idBiblioteca) {
		this.idBiblioteca = idBiblioteca;
	}

}
