package Negocio;


public interface SACliente {
    void altaCliente(String email, String nombre, String contraseņa);

    void modificarCliente(final int id, final String nombre, final String email, final int tlfn, final String contraseņa);

    void eliminarCliente(final String id);
    
   public Transfer_Cliente iniciarSesionCliente(String usuario, String contraseņa);



   public void reservaSitio(String nombre, String biblioteca, int sitio);
   
}
