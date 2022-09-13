public class VacunaTransfer {
	private int id;
	private int efectividad;
    private VirusTransfer virus;
    public VacunaTransfer(int efectividad, int id, VirusTransfer virus) {
		super();
		this.efectividad = efectividad;
		this.id = id;
		this.virus = virus;
	}



    public void setEfectividad(int efectividad) {
		this.efectividad = efectividad;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}



	public int getEfectividad() {
		return efectividad;
	}



	public VirusTransfer getVirus() {
		return virus;
	}



	public void setVirus(VirusTransfer virus) {
		this.virus = virus;
	}

	

}
