import java.util.ArrayList;
import java.util.List;

public class DAOMensajeImp implements DAOMensaje {
    private List<TMensaje> mensajes= new ArrayList<TMensaje>();

    public TMensaje busca(int id) {
    	TMensaje m = null;
    	for(int i = 0;i<mensajes.size();++i) {
    		if(mensajes.get(i).getId()==id)m=mensajes.get(i);
      	}
    	return m;
    }
    public void delete(TMensaje m) {
    	mensajes.remove(m);
    }

    List<TMensaje> getMensajes() {
        return this.mensajes;
    }

    void setMensajes(List<TMensaje> value) {
        this.mensajes = value;
    }

}
