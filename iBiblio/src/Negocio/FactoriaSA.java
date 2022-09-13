package Negocio;

public abstract class FactoriaSA {
	private static FactoriaSA instancia=null;
	


	public static FactoriaSA getInstancia() {
		if (instancia == null)
			instancia = new FactoriaSAImp();
		return instancia;
	}

	abstract public SALibro generarSALibro();

	abstract public SACliente generarSACliente();
	
	abstract public SABiblioteca generarSABiblioteca();

}
