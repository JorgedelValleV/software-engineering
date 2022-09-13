import java.util.Calendar;

public class Main {

	public static void main(String[] args) {
		Calendar c= Calendar.getInstance();
				
		
		// Declaramos el registro
		InterfazRegistroVehiculos registro = new RegistroVehiculos();

		// Insertamos unos cuantos elementos
		registro.InsertarVehiculo("Volkswagen", "Polo", 12300);
		int i=c.getTime().getHours();
		++i;
		c.getTime().setHours(i);
		c.setTime(Calendar.getInstance().getTime());
		registro.InsertarVehiculo("Volkswagen", "Golf GTI", 18900);
		++i;
		c.getTime().setHours(i);
		c.setTime(Calendar.getInstance().getTime());
		registro.InsertarVehiculo("Volkswagen", "Passat", 27000);
		++i;
		c.getTime().setHours(i);
		c.setTime(Calendar.getInstance().getTime());
		registro.InsertarVehiculo("Volkswagen", "Scirocco", 32100);	
		++i;
		c.getTime().setHours(i);
		c.setTime(Calendar.getInstance().getTime());
		registro.InsertarVehiculo("Volkswagen", "Touareg", 21800);	
		++i;
		c.getTime().setHours(i);
		c.setTime(Calendar.getInstance().getTime());

		// Obtenemos el iterator
		InterfazIteratorVehiculo iterador = registro.ObtenerIterator();

		// Mientras queden elementos
		while (iterador.QuedanElementos()) {
			// Obtenemos el siguiente elemento
			Vehiculo v = iterador.Siguiente();

			// Mostramos su contenido
			System.out.println(v.Marca + " " + v.Modelo + " fabricado el " + v.FechaFabricacion.toLocaleString() + " ("
					+ v.Precio + " euros)");
		}
		
		//otra opcion
//		for(iterador.Primero();iterador.QuedanElementos();iterador.Siguiente()) {
//			Vehiculo v = iterador.Actual();
//			System.out.println(v.Marca + " " + v.Modelo + " fabricado el " + v.FechaFabricacion.toLocaleString() + " ("
//					+ v.Precio + " euros)");
//		} 
	}

}
