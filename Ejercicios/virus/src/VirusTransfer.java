import java.util.ArrayList;
import java.util.List;

public class VirusTransfer {
    private String nombre;
    private int propagacion;
    private int virulencia;
    private int id;
	private List<VacunaTransfer> vacuna = new ArrayList<VacunaTransfer> ();
	public VirusTransfer(String nombre, int propagacion, int virulencia, int id, List<VacunaTransfer> vacuna) {
		super();
		this.nombre = nombre;
		this.propagacion = propagacion;
		this.virulencia = virulencia;
		this.id = id;
		this.vacuna = vacuna;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getPropagacion() {
		return propagacion;
	}
	public void setPropagacion(int propagacion) {
		this.propagacion = propagacion;
	}
	public int getVirulencia() {
		return virulencia;
	}
	public void setVirulencia(int virulencia) {
		this.virulencia = virulencia;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<VacunaTransfer> getVacuna() {
		return vacuna;
	}
	public void setVacuna(List<VacunaTransfer> vacuna) {
		this.vacuna = vacuna;
	}
	

}
