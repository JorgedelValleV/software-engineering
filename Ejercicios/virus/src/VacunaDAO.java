import java.util.List;


public interface VacunaDAO {
    VacunaTransfer getById(int id);
    void update(VacunaTransfer v);
    void baja(VacunaTransfer v);
    void alta(VacunaTransfer v);    
    List<VacunaTransfer> getAllVacunas();
}
