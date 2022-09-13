package Negocio;

import java.util.List;

public interface SABiblioteca {
	public boolean altaBiblioteca(final String nombre, final int id, final String imagen,
			final List<Transfer_Libro> listaLibros);

	public boolean eliminarBiblioteca(final String nombre, final int id);

	public boolean modificarBiblioteca(final String imagen, final String nombre, final int id,
			final List<Transfer_Libro> listaLibros);

	public void reservarSitio(String biblioteca, int num,boolean b);

	public List<Transfer_Biblioteca> getListaBibliotecas();

}
