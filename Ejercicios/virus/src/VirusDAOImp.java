import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VirusDAOImp implements VirusDAO {
    private ArrayList<VirusTransfer> virus;
    public VirusDAOImp() {
    	virus = new ArrayList<VirusTransfer>();
		try {
			BufferedReader inStream = new BufferedReader(new FileReader("virus.txt"));
			for(int i = 0;i<19;++i) {
				String name = inStream.readLine();
				String line = inStream.readLine().trim();
				String[] words= line.split(" ");
				VirusTransfer v = new VirusTransfer(name,Integer.parseInt(words[0]),Integer.parseInt(words[1]),i,null);
				virus.add(v);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

    public void update(VirusTransfer v) {
    	virus.get(v.getId()).setPropagacion(v.getPropagacion());
    	virus.get(v.getId()).setNombre(v.getNombre());
    	virus.get(v.getId()).setVirulencia(v.getVirulencia());
    }

	public void baja(VirusTransfer v) {
		virus.remove(v.getId());
	}
	public VirusTransfer getById(int id) {
		return virus.get(id);
	}
	public VirusTransfer getByName(String name) {
		VirusTransfer sol=null;
		for (VirusTransfer v : virus) {
		       if(v.getNombre().equals(name))sol=new VirusTransfer(name,v.getPropagacion(),v.getVirulencia(),v.getId(),v.getVacuna());;
		    }
		return sol;
	}

	public void alta(VirusTransfer v) {
		virus.add(v);
	}

	public List<VirusTransfer> getAllVirus() {
		return virus;
	}
	
}
