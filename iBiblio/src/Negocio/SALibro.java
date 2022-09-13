package Negocio;


public interface SALibro {
    boolean eliminarLibro(final String isbn, final String autor, final String nombre);

    boolean modificarLibro(final String isbn, final String autor, final String nombre);

    boolean altaLibro(final String isbn, final String nombre, final String autor);

    boolean buscarLibro(final String nombre, final String isbn, final String autor);

}
