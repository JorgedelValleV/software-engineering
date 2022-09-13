package Negocio;

import java.io.FileNotFoundException;

import org.json.JSONException;

import Integracion.*;

public class SAClienteImp implements SACliente {
	@Override
	public void altaCliente(String email, String nombre, String contrase�a) {
		DAO<Transfer_Cliente> daoCliente= FactoriaDAO.getInstancia().crearDAOCliente();
		Transfer_Cliente cliente = new Transfer_Cliente(email, nombre, contrase�a);
		daoCliente.create(cliente);
	}

    public void modificarCliente(final int id, final String nombre, final String email, final int tlfn, final String contrase�a) {
        // TODO Auto-generated return
      
    }

    public void eliminarCliente(final String id) {
        // TODO Auto-generated return
    	
    }
    
    public Transfer_Cliente iniciarSesionCliente(String usuario, String contrase�a) {
    	DAO<Transfer_Cliente> daoCliente= FactoriaDAO.getInstancia().crearDAOCliente();
    	Transfer_Cliente cliente = daoCliente.read(usuario); 
    	
    	if(cliente == null || !cliente.getContrasea().equals(contrase�a))
    		throw new IllegalArgumentException("Contrase�a incorrecta");
    	return cliente;
    }

	@Override
	public void reservaSitio(String nombre, String biblioteca, int sitio) {
		DAO<Transfer_Cliente> daoCliente= FactoriaDAO.getInstancia().crearDAOCliente();
		Transfer_Cliente cliente = daoCliente.read(nombre);
		cliente.setBiblioteca(biblioteca);
		cliente.setSitio(sitio);
		daoCliente.update(cliente);
	}

	
}
