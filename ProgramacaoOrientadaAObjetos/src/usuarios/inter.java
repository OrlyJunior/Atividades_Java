package usuarios;
import java.util.ArrayList;

public interface inter<T> {
	public boolean post(ArrayList<T> lista);
	public void delete(ArrayList<T> lista);
	public boolean put(ArrayList<T> lista);
	public ArrayList<T> get(ArrayList<T> lista);
	public T getId(ArrayList<T> lista);
}