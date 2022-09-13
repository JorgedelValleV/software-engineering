import java.util.ArrayList;

public class IteratorVehiculo implements InterfazIteratorVehiculo{
	
	// Referencia al listado completo
    private ArrayList vehiculos;
 
    // Almacenaremos el �ndice en el que se encuentra el iterador
    private int posicionActual = 0;
 
    // El constructor inyectar� el ArrayList en el objeto
    public IteratorVehiculo(ArrayList listado)
    {
        this.vehiculos = listado;
    }

 // Operaci�n 1: Reinicio del �ndice, coloc�ndolo en el elemento anterior al primero
    public void Primero()
    {
        this.posicionActual = 0;
    }

 // Operaci�n 2: Acceso al elemento actual
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

 // Operaci�n 3: Acceso al siguiente elemento
    public Vehiculo Siguiente()
    {
        // Si no existen elementos, devolveremos null.
        // Si el indice siguiente es mayor que el mayor indice aceptable, devolveremos null.
        if ((this.vehiculos == null) || 
            (this.vehiculos.size() == 0) || 
            (posicionActual > this.vehiculos.size() - 1))
            return null;
     
        // Aumentamos el �ndice en una unidad y devolvemos ese elemento
        else
            return (Vehiculo)this.vehiculos.get(posicionActual++);
    }

 // Operaci�n 4: Comprobaci�n de si existen elementos en la colecci�n
    public boolean QuedanElementos()
    {
        // Devolvemos un booleano que ser� true si la posici�n siguiente es menor o igual que el
        // m�ximo �ndice aceptable (n�mero de elementos del array - 1).
        return (posicionActual  <= this.vehiculos.size() - 1);
    }

}
