package integracion;

import java.util.ArrayList;

import negocio.PostIt;

public interface DAOPostIt {
	public void add(int id, String title, int color, String code);
	public void eliminar(int id);
	public void guardar(int id, String text);
	public boolean desbloquear(int id, String code);
	public ArrayList<PostIt> cargar();
}
