package Integracion;

import Negocio.Transfer_Biblioteca;

public class FactoriaDAOImp extends FactoriaDAO {

	@Override
	public DAOLibroImp crearDAOLibro() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DAOClienteImp crearDAOCliente() {
		// TODO Auto-generated method stub
		return new DAOClienteImp();
	}

	@Override
	public DAOBibliotecaImp crearDAOBiblioteca() {
		return new DAOBibliotecaImp();
	}


}
