import java.util.ArrayList;

public class IteratorVehiculo implements InterfazIteratorVehiculo{
	
	// Referencia al listado completo
    private ArrayList vehiculos;
 
    // Almacenaremos el índice en el que se encuentra el iterador
    private int posicionActual = 0;
 
    // El constructor inyectará el ArrayList en el objeto
    public IteratorVehiculo(ArrayList listado)
    {
        this.vehiculos = listado;
    }

 // Operación 1: Reinicio del índice, colocándolo en el elemento anterior al primero
    public void Primero()
    {
        this.posicionActual = 0;
    }

 // Operación 2: Acceso al elemento actual
    public Vehiculo Actual()
    {
        // Si no existen elementos, devolveremos null.
        // Si el indice actual es mayor que el mayor indice aceptable, devolveremos null.
        // Si el indice actual es -1, devolveremos null.
        if ((this.vehiculos == null) || 
            (this.vehiculos.size() == 0) || 
            (posicionActual > this.vehiculos.size() - 1) ||
            (this.posicionActual < 0))
            return null;
     
        // Devolvemos el elemento correspondiente al elemento actual
        else
            return (Vehiculo)this.vehiculos.get(posicionActual);
    }

 // Operación 3: Acceso al siguiente elemento
    public Vehiculo Siguiente()
    {
        // Si no existen elementos, devolveremos null.
        // Si el indice siguiente es mayor que el mayor indice aceptable, devolveremos null.
        if ((this.vehiculos == null) || 
            (this.vehiculos.size() == 0) || 
            (posicionActual > this.vehiculos.size() - 1))
            return null;
     
        // Aumentamos el índice en una unidad y devolvemos ese elemento
        else
            return (Vehiculo)this.vehiculos.get(posicionActual++);
    }

 // Operación 4: Comprobación de si existen elementos en la colección
    public boolean QuedanElementos()
    {
        // Devolvemos un booleano que será true si la posición siguiente es menor o igual que el
        // máximo índice aceptable (número de elementos del array - 1).
        return (posicionActual  <= this.vehiculos.size() - 1);
    }

}
