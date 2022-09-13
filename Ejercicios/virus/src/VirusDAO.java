import java.util.List;

public interface VirusDAO {
    VirusTransfer getById(int id);
    void alta(VirusTransfer v);
    void update(VirusTransfer v);
    void baja(VirusTransfer v);
    List<VirusTransfer> getAllVirus();
    public VirusTransfer getByName(String name);
}
