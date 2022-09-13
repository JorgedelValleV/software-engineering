package negocio;

public interface SA_PostIt {
	public void add(int id, String title, int color, String code);

	public void delete(int id);

	public void save(int id, String text);

	public boolean unlock(int id, String code);
}
