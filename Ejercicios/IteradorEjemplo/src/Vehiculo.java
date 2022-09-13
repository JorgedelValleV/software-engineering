import java.util.Date;

public class Vehiculo {
	public String Marca;// { get; set; }
	public String Modelo;// { get; set; }
	public Date FechaFabricacion;// { get; set; }
	public double Precio;// { get; set; }

	public Vehiculo(String marca, String modelo, Date fechaFabricacion, double precio) {
		this.Marca = marca;
		this.Modelo = modelo;
		this.FechaFabricacion = fechaFabricacion;
		this.Precio = precio;
	}

	public String CaracteristicasVehiculo() {
		return Marca + " " + Modelo + " fabricado en " + FechaFabricacion.toString() + " con un precio de "
				+ Precio + " euros.\n";
	}

	public String getMarca() {
		return Marca;
	}

	public void setMarca(String marca) {
		Marca = marca;
	}

	public String getModelo() {
		return Modelo;
	}

	public void setModelo(String modelo) {
		Modelo = modelo;
	}

	public Date getFechaFabricacion() {
		return FechaFabricacion;
	}

	public void setFechaFabricacion(Date fechaFabricacion) {
		FechaFabricacion = fechaFabricacion;
	}

	public double getPrecio() {
		return Precio;
	}

	public void setPrecio(double precio) {
		Precio = precio;
	}

}