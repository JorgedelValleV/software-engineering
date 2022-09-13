package Presentacion;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
		// TODO Auto-generated method stub
    	try {
    		Controller ctrl= Controller.getInstancia();
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					new GUI(ctrl);
				} 
			});
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
