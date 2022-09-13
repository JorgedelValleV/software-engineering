package Negocio;

import java.util.List;

import Integracion.DAO;
import Integracion.FactoriaDAO;

public class SABibliotecaImp implements SABiblioteca {
	public boolean modificarBiblioteca(final String imagen, final String nombre, final int id,
			final List<Transfer_Libro> listaLibros) {
		// TODO Auto-generated return
		return false;
	}

	public boolean altaBiblioteca(final String nombre, final int id, final String imagen,
			final List<Transfer_Libro> listaLibros) {
		// TODO Auto-generated return
		return false;
	}

	public boolean eliminarBiblioteca(final String nombre, final int id) {
		// TODO Auto-generated return
		return false;
	}

	@Override
	public List<Transfer_Biblioteca> getListaBibliotecas() {
		DAO<Transfer_Biblioteca> daoBiblio = FactoriaDAO.getInstancia().crearDAOBiblioteca();
		return daoBiblio.readAll();
	}

	@Override
	public void reservarSitio(String biblioteca, int num, boolean b) {
		DAO<Transfer_Biblioteca> daoBiblio = FactoriaDAO.getInstancia().crearDAOBiblioteca();
		Transfer_Biblioteca biblio = daoBiblio.read(biblioteca);
		biblio.getSillas().get(num).setOcupado(b);
		biblio.setAforoDisponible(biblio.getAforoDisponible() + (b ? - 1 : 1));

		daoBiblio.update(biblio);
	}

}
