import java.util.List;

public class TMensaje {
    private String texto;
    private String imagen;
    private int id;
    private List<TAutobus> autobuses;
	
	public int getId() {
		return id;
	}
    public List<TAutobus> getTAutobus() {
        return this.autobuses;
    }

    public void setTAutobus(List<TAutobus> value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.autobuses = value;
    }

}
