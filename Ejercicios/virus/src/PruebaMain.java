import java.util.ArrayList;
import java.util.List;

public class PruebaMain {

	public static void main(String[] args) {
		VirusDAO virDAO = new VirusDAOImp();
		VacunaDAO vacDAO = new VacunaDAOImp();

	    for (VirusTransfer vir : virDAO.getAllVirus()) {
	    	List<VacunaTransfer> vacunas = new ArrayList<VacunaTransfer> ();
	    	for (VacunaTransfer vac : vacDAO.getAllVacunas()) {
	 	       if(vac.getVirus().getNombre().equals(vir.getNombre()))vacunas.add(vac);
		    }
	    	vir.setVacuna(vacunas);
	    }
	    //introducir nuevo virus
	    String nombre="ALEX";
	    int propagacion = 7000;
	    int virulencia = 0;
	    int id = virDAO.getAllVirus().size();
	    virDAO.alta(new VirusTransfer(nombre,propagacion,virulencia,id,null));
	    
	    //introducir nueva vacuna
	    String name="MIGUEL";
	    int efectividad = 10;
	    int ID = vacDAO.getAllVacunas().size();
	    if(virDAO.getByName(name)==null) {
	    	virDAO.alta(new VirusTransfer(name,0,0,virDAO.getAllVirus().size(),null));
	    	VacunaTransfer nuevaVacuna = new VacunaTransfer(efectividad,ID,virDAO.getByName(name));
	    	List<VacunaTransfer> vacunas = new ArrayList<VacunaTransfer> ();
	    	vacunas.add(nuevaVacuna);
		    virDAO.getByName(name).setVacuna(vacunas);
		    vacDAO.alta(nuevaVacuna);
	    }
	    else {
	    	VacunaTransfer nuevaVacuna = new VacunaTransfer(efectividad,ID,virDAO.getByName(name));
		    List<VacunaTransfer> vacunas= virDAO.getByName(name).getVacuna();
		    vacunas.add(nuevaVacuna);
		    virDAO.getByName(name).setVacuna(vacunas);
		    vacDAO.alta(nuevaVacuna);
	    }
	    
	  	    
	    //mostrar
	    for (VirusTransfer v : virDAO.getAllVirus()) {
	    	System.out.println(virToString(v));
	    }
	    for (VacunaTransfer v : vacDAO.getAllVacunas()) {
	    	System.out.println(vacToString(v));
		}
	    
	    //eliminar por id 
	    vacDAO.baja(vacDAO.getById(0));
	    
	    //modificar virus
	    VirusTransfer v = virDAO.getById(0);
	    v.setPropagacion(v.getPropagacion()+10);
	    virDAO.update(v);
	    
	}
	public static String vacToString(VacunaTransfer v) {
		StringBuilder sb = new StringBuilder();
		sb.append("Virus asociado: ").append(v.getVirus().getNombre()).append(" Efectividad: ").append(v.getEfectividad()).append(" Id: ").append(v.getId());
		sb.append("\n");
		return sb.toString();
	}
	public static String virToString(VirusTransfer v) {
		StringBuilder sb = new StringBuilder();
		sb.append("Nombre: ").append(v.getNombre()).append(" Propagacion: ").append(v.getPropagacion()).append(" Virulencia: ").append(v.getVirulencia()).append(" Id: ").append(v.getId());
		sb.append("\n");
		if(v.getVacuna()!=null) {
			for(int i = 0;i < v.getVacuna().size();++i) {
			sb.append("\t").append(vacToString(v.getVacuna().get(i)));
			}
		}
		return sb.toString();
	}

}
