package Negocio;


public class Transfer_Libro {
    private int idLibro;
    private String isbn;
    private String autor;
    private String sinopsis;
    private boolean descatalogado;
    private Transfer_ReservaLibro reserva;
    private String titulo;

    public Transfer_ReservaLibro getReserva() {
        return this.reserva;
    }

    public void setReserva(final Transfer_ReservaLibro value) {
        this.reserva = value;
    }

    public int getIdLibro() {
        return this.idLibro;
    }

    public void setIdLibro(final int value) {
        this.idLibro = value;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(final String value) {
        this.isbn = value;
    }

    public String getAutor() {
        return this.autor;
    }

    public void setAutor(final String value) {
        this.autor = value;
    }
    
    public String getSinopsis() {
        return this.sinopsis;
    }

    public void setSinopsis(final String value) {
        this.sinopsis = value;
    }

    public boolean isDescatalogado() {
        return this.descatalogado;
    }

    public void setDescatalogado(final boolean value) {
        this.descatalogado = value;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(final String value) {
        this.titulo = value;
    }

}
