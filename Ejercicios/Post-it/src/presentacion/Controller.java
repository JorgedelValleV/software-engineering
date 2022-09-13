package presentacion;

import java.awt.Color;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.JButton;

import negocio.View;
import negocio.PostIt;
import negocio.SA_PostIt_Imp;

public class Controller {
	private ArrayList<View> views;

	public Controller() {
		views = new ArrayList<View>();
	}

	//ACTION
	public void action(int event, int id, String title, int color, String code, String text) {
		switch (event) {
		// add
		case (0): {
			SA_PostIt_Imp postIt_Imp = new SA_PostIt_Imp();
			postIt_Imp.add(id, title, color, code);
			for (int i = 0; i < views.size(); ++i) {
				views.get(i).actualizar(0, id, title, code, color, false,text);
			}
			break;
		}
		// delete
		case (1): {
			SA_PostIt_Imp postIt_Imp = new SA_PostIt_Imp();
			postIt_Imp.delete(id);
			break;
		}
		// save
		case (2): {
			SA_PostIt_Imp postIt_Imp = new SA_PostIt_Imp();
			postIt_Imp.save(id, text);
			break;
		}
		// unlock
		case (3): {
			SA_PostIt_Imp postIt_Imp = new SA_PostIt_Imp();
			boolean unlocked = postIt_Imp.unlock(id, code);
			for (int i = 0; i < views.size(); ++i) {
				views.get(i).actualizar(3, id, title, code, color, unlocked,text);
			}
			break;
		}
		}
	}

	public void addView(View v) {
		views.add(v);
	}

	public void cargar() {
		// TODO Auto-generated method stub
		SA_PostIt_Imp postIt_Imp=new SA_PostIt_Imp();
		ArrayList<PostIt> notes=postIt_Imp.cargar();
		for(int j=0;j<notes.size();++j) {
			views.get(0).actualizar(0, notes.get(j).getId(),notes.get(j).getTitle(),notes.get(j).getCode(),notes.get(j).getColor(), false,notes.get(j).getText());
		}
	}
}
