
public interface InterfazRegistroVehiculos {//iterable
    void InsertarVehiculo(String marca, String modelo, double precio);
    Vehiculo MostrarInformacionVehiculo(int indice);
    InterfazIteratorVehiculo ObtenerIterator();
}