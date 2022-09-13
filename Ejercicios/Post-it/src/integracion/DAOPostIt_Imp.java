package integracion;
import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONObject;
import negocio.LockedPostIt;
import negocio.PostIt;
public class DAOPostIt_Imp implements DAOPostIt{
	private List<JSONObject> dataSource;
	private static DAOPostIt_Imp instancia;
	private DAOPostIt_Imp() {
		dataSource=new ArrayList<JSONObject>();
	}
	public static DAOPostIt_Imp getInstancia(){
		if (instancia==null)
			instancia=new DAOPostIt_Imp();
		return instancia;
	}
	public void add(int id, String titulo, int color, String codigo) {
		JSONObject jo = new JSONObject();
		jo.put("id", id);
		jo.put("titulo", titulo);
		jo.put("color", color);
		jo.put("codigo", codigo);
		jo.put("texto", "");
		dataSource.add(jo);
	}
	public void eliminar(int id) {
		int i=0;
		while(i<dataSource.size()&&dataSource.get(i).getInt("id")!=id)
			++i;
		if(i<dataSource.size())
			dataSource.remove(i);
	}
	public void guardar(int id, String texto) {
		int i=0;
		while(i<dataSource.size()&&dataSource.get(i).getInt("id")!=id)
			++i;
		if(i<dataSource.size()) {
			dataSource.get(i).remove("texto");
			dataSource.get(i).put("texto",cifrar(texto));
		}
	}
	public boolean desbloquear(int id, String codigo) {
		int i=0;
		while(i<dataSource.size()&&dataSource.get(i).getInt("id")!=id)
			++i;
		if(i<dataSource.size())
			return codigo.equals(dataSource.get(i).getString("code"));
		return false;
	}
	public ArrayList<PostIt> cargar(){
		ArrayList<PostIt> lista=new ArrayList<PostIt>();
		for(JSONObject j: dataSource) {
			lista.add(new PostIt(j.getInt("id"),j.getString("titulo"),j.getInt("color"),descifrarTexto(j.getString("texto"))));
		}
		return lista;
	}
	private String cifrarText(String codigo) {
		String s="";
		for (int i = 0; i <codigo.length ();++i){ 
			char c = codigo.charAt(i);
			c++;
			if(c>122)
				c='A';
			s+=c;
		}
		return s;
	}
	private String descifrar(String codigo) {
		String s="";
		for (int i = 0; i <codigo.length ();++i){ 
			char c = codigo.charAt(i);
			c--;
			if(c==64)
				c='z';
			s+=c;
		}
		return s;
	}
}
