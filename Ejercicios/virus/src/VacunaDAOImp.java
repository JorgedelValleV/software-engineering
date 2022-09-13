import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VacunaDAOImp implements VacunaDAO {
	private List<VacunaTransfer> vacunas;
	public VacunaDAOImp() {
		vacunas = new ArrayList<VacunaTransfer>();
		try {
			BufferedReader inStream = new BufferedReader(new FileReader("vacunas.txt"));
			VirusDAO virDAO= new VirusDAOImp();
			for(int i = 0;i<24;++i) {
				String name = inStream.readLine();
				String line = inStream.readLine().trim();
				String[] words= line.split("\\s+");
				VacunaTransfer v = new VacunaTransfer(Integer.parseInt(words[0]),i,virDAO.getByName(name));
				vacunas.add(v);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
    public VacunaTransfer getById(int id) {
    	return vacunas.get(id);
    }

    public void update(VacunaTransfer v) {
    	vacunas.get(v.getId()).setEfectividad(v.getEfectividad());

    }

    public void baja(VacunaTransfer v) {
    	vacunas.remove(v.getId());
    }

    public void alta(VacunaTransfer v) {
    	vacunas.add(v);
    }

    public List<VacunaTransfer> getAllVacunas() {
    	return vacunas;
    }
}
