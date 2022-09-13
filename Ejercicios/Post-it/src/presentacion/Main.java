package presentacion;

public class Main {
	
	public static void init() {
		Controller ctrl = new Controller();
		new GUI_MainWindow(ctrl);
	}
	
    public static void main(String[] args) {
    	init();
    }
}
