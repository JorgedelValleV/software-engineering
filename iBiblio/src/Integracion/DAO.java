package Integracion;

import java.io.FileNotFoundException;
import java.util.List;

import org.json.JSONException;

import Negocio.Transfer_Cliente;

public interface DAO<T> {
    void create(final T t);

    T read(final T t);

    List<T> readAll();

    void update(final T t);

    void delete(final T t);

	T read(String usuario);
}
