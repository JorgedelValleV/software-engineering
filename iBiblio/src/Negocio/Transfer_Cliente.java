package Negocio;


public class Transfer_Cliente {
    private String nombre;
    private String email;
    private String contrasea;
    private int idCliente;
    private int telefono; //quitar
    private boolean baja;
    private String biblioteca = "";
    private int sitio = 0;

    public String getBiblioteca() {
		return biblioteca;
	}

	public void setBiblioteca(String biblioteca) {
		this.biblioteca = biblioteca;
	}

	public int getSitio() {
		return sitio;
	}

	public void setSitio(int sitio) {
		this.sitio = sitio;
	}

	public Transfer_Cliente(String email, String nombre, String contrasea) {
		//Constructora de registro
    	this.nombre = nombre;
		this.email = email;
		this.contrasea = contrasea;
		
		this.baja = false;
//		this.idCliente = ??;
	}

	public Transfer_Cliente() {
	}

	public int getIdCliente() {
        return this.idCliente;
    }

    public void setIdCliente(final int value) {
        this.idCliente = value;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(final String value) {
        this.nombre = value;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String value) {
        this.email = value;
    }

    public int getTelefono() {
        return this.telefono;
    }

    public void setTelefono(final int value) {
        this.telefono = value;
    }

    public String getContrasea() {
        return this.contrasea;
    }

    public void setContrasea(final String value) {
        this.contrasea = value;
    }

    public boolean isBaja() {
        return this.baja;
    }

    public void setBaja(final boolean value) {
        this.baja = value;
    }

}
