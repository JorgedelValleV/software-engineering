package negocio;

public class LockedPostIt extends PostIt {
	private String code;

	public LockedPostIt(int id, String title, int color, String code) {
		super(id, title, color);
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
