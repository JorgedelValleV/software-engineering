package Integracion;

import Negocio.Transfer_Biblioteca;
import Negocio.Transfer_Cliente;

public abstract class FactoriaDAO {
    private static FactoriaDAO instancia = null;


	public static FactoriaDAO getInstancia() {
		if (instancia == null)
			instancia = new FactoriaDAOImp();
		return instancia;
	}

    abstract public DAOLibroImp crearDAOLibro();

    abstract public DAOClienteImp crearDAOCliente();

	abstract public DAO<Transfer_Biblioteca> crearDAOBiblioteca();

}
