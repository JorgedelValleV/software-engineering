package Negocio;


public interface SACliente {
    void altaCliente(String email, String nombre, String contrase�a);

    void modificarCliente(final int id, final String nombre, final String email, final int tlfn, final String contrase�a);

    void eliminarCliente(final String id);
    
   public Transfer_Cliente iniciarSesionCliente(String usuario, String contrase�a);



   public void reservaSitio(String nombre, String biblioteca, int sitio);
   
}
