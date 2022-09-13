package negocio;

import java.util.ArrayList;

import integracion.DAOPostIt;
import integracion.DAOPostIt_Imp;
import presentacion.Controller;

public class SA_PostIt_Imp implements SA_PostIt {
	public void add(int id, String title, int color, String code) {
		DAOPostIt DAO= DAOPostIt_Imp.getInstancia();
		DAO.add(id, title, color, code);
	}
	public void delete(int id) {
		DAOPostIt DAO= DAOPostIt_Imp.getInstancia();
		DAO.eliminar(id);
	}
	public void save(int id, String text) {
		DAOPostIt DAO= DAOPostIt_Imp.getInstancia();
		DAO.guardar(id, text);
	}
	public boolean unlock(int id, String code) {
		DAOPostIt DAO= DAOPostIt_Imp.getInstancia();
		return DAO.desbloquear(id, code);
	}
	public ArrayList<PostIt> cargar() {
		DAOPostIt DAO= DAOPostIt_Imp.getInstancia();
		return DAO.cargar();
	}
}
