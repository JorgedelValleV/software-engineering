package negocio;

public class PostIt {
	private int id, color;
	private String title;
	private String text;
	public PostIt(int id,String title,int color, String text) {
		this.id = id;
		this.title = title;
		this.color = color;
		this.text=text;
	}
	public PostIt(int id, String title, int color) {
		this.id = id;
		this.title = title;
		this.color = color;
	}

	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}

	public String getCode() {
		return "";
	}

	public void setText(String text) {
		this.text = text;
	}
}
