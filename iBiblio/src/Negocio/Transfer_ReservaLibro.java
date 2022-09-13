package Negocio;

import java.util.Date;

public class Transfer_ReservaLibro {
    private int idReserva;
    private Date fecha;
    private Double duracion;
    private Transfer_Cliente cliente;
    private Transfer_Libro libro;

    public int getIdReserva() {
        return this.idReserva;
    }

    public void setIdReserva(final int value) {
        this.idReserva = value;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(final Date value) {
        this.fecha = value;
    }

    public Double getDuracion() {
        return this.duracion;
    }

    public void setDuracion(final Double value) {
        this.duracion = value;
    }

    public Transfer_Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(final Transfer_Cliente value) {
        this.cliente = value;
    }

	public Transfer_Libro getLibro() {
		return libro;
	}

	public void setLibro(Transfer_Libro libro) {
		this.libro = libro;
	}

}
