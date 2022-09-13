package Negocio;

public class FactoriaSAImp extends FactoriaSA {

	@Override
	public SALibro generarSALibro() {
		// TODO Auto-generated method stub
		return new SALibroImp();
	}

	@Override
	public SACliente generarSACliente() {
		return new SAClienteImp();
	}

	@Override
	public SABiblioteca generarSABiblioteca() {
		return new SABibliotecaImp();
	}
}
