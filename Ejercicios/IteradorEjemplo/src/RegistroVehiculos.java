import java.util.ArrayList;
import java.util.Calendar;

public class RegistroVehiculos implements InterfazRegistroVehiculos{
    private ArrayList listaVehiculos;
 
    public RegistroVehiculos()
    {
        this.listaVehiculos = new ArrayList();
    }

	public void InsertarVehiculo(String marca, String modelo, double precio) {
        Vehiculo v = new Vehiculo(marca, modelo, Calendar.getInstance().getTime(), precio);
        listaVehiculos.add(v);
    }
	
	 public Vehiculo MostrarInformacionVehiculo(int indice)
	 {
		 return (Vehiculo)listaVehiculos.get(indice);
	 }

	 public InterfazIteratorVehiculo ObtenerIterator()
	 {
	     return new IteratorVehiculo(listaVehiculos);
	 }
}