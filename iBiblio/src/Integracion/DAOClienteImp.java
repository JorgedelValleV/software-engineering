package Integracion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.json.*;

import Negocio.Transfer_Cliente;

public class DAOClienteImp implements DAO<Transfer_Cliente> {
	private static final String FICHEROCLIENTES = "bbdd/ficheroClientes.txt";
	private static int ID = 1;

	@Override
	public void create(Transfer_Cliente t) {
		List<Transfer_Cliente> clientes = readAll();
		if (clientes.isEmpty())
			ID = 1;
		else {
			ID = clientes.get(clientes.size() - 1).getIdCliente() + 1;
		}
		t.setIdCliente(ID);
		
		
		for(Transfer_Cliente c: clientes) {
			if(c.getEmail().equals(t.getEmail())) throw new IllegalArgumentException("Ya existe un usuario con ese email");
			if(c.getNombre().equals(t.getNombre())) throw new IllegalArgumentException("Nombre de usuario en uso");
		}
		clientes.add(t);
			
		escribirClientes(clientes);
	}

	public void escribirClientes(List<Transfer_Cliente> clientes) {
		try {
			OutputStream out = FICHEROCLIENTES == null ? System.out : new FileOutputStream(new File(FICHEROCLIENTES));
			PrintStream print = (out == null) ? null : new PrintStream(out);
			print.println("{\n\"clientes\": [");

			for (int i = 0; i < clientes.size(); ++i) {
				print.print("\n{\n" + this.toString(clientes.get(i)) + "\n}");
				if (i != clientes.size() - 1) {
					print.println(",");
				}
			}

			print.print("\n]\n}");
			print.close();
		}
		catch (FileNotFoundException e) {
			throw new IllegalArgumentException("BBDDException: " + e.getMessage());
		}
	}

	public String toString(Transfer_Cliente t) {
		String str = "";

		str += "\"nombre\" : \"" + t.getNombre() + "\",\n";
		str += "\"email\" : \"" + t.getEmail() + "\",\n";
		str += "\"password\" : \"" + t.getContrasea() + "\",\n";

		str += "\"id\" : \"" + t.getIdCliente()+ "\",\n";
		str += "\"biblioteca\" : \"" + t.getBiblioteca()+ "\",\n";
		str += "\"sitio\" : \"" + t.getSitio()+"\"";


		return str;
	}

	@Override
	public void update(Transfer_Cliente t) {
		List<Transfer_Cliente> clientes = readAll();
		for(int i = 0; i < clientes.size(); ++i) {
			Transfer_Cliente cliente = clientes.get(i);
			if(cliente.getIdCliente() == t.getIdCliente()) {
				cliente.setNombre(t.getNombre());
				cliente.setEmail(t.getEmail());
				cliente.setContrasea(t.getContrasea());
				cliente.setIdCliente(t.getIdCliente());
				cliente.setBiblioteca(t.getBiblioteca());
				cliente.setSitio(t.getSitio());
			}
		}
		escribirClientes(clientes);
	}

	@Override
	public Transfer_Cliente read(String usuario) {
		Transfer_Cliente cliente = null;

		try {
			cliente = new Transfer_Cliente();
			JSONObject jsonInput = new JSONObject(new JSONTokener(new FileInputStream(new File(FICHEROCLIENTES))));
			JSONArray clientes = jsonInput.getJSONArray("clientes");
			boolean parar = false;

			for (int i = 0; i < clientes.length() && !parar; ++i) {
				JSONObject jo = clientes.getJSONObject(i);
				if (jo.get("nombre").equals(usuario)) {
					cliente.setNombre(jo.getString("nombre"));
					cliente.setEmail(jo.getString("email"));
					cliente.setContrasea(jo.getString("password"));
					cliente.setIdCliente(jo.getInt("id"));
					cliente.setBiblioteca(jo.getString("biblioteca"));
					cliente.setSitio(jo.getInt("sitio"));
					cliente.setBaja(false); // no se si es false
					parar = true;
				}
			}
			if (!parar) {
				cliente = null;
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("BBDDException: " + e.getMessage());
		}

		return cliente;
	}

	@Override
	public void delete(Transfer_Cliente t) {
		List<Transfer_Cliente> clientes = readAll();
		int id = t.getIdCliente();
		boolean parar = false;

		for (int i = 0; i < clientes.size() && !parar; ++i) {
			if (clientes.get(i).getIdCliente() == id) {
				clientes.remove(i);
				parar = true;
			}
		}

		escribirClientes(clientes);
	}

	@Override
	public List<Transfer_Cliente> readAll() {
		List<Transfer_Cliente> clientes = new ArrayList<Transfer_Cliente>();

		try {
			JSONObject jsonInput = new JSONObject(new JSONTokener(new FileInputStream(new File(FICHEROCLIENTES))));
			JSONArray jArray = jsonInput.getJSONArray("clientes");

			for (int i = 0; i < jArray.length(); ++i) {
				JSONObject jo = jArray.getJSONObject(i);
				Transfer_Cliente cliente = new Transfer_Cliente();

				cliente.setNombre(jo.getString("nombre"));
				cliente.setEmail(jo.getString("email"));
				cliente.setContrasea(jo.getString("password"));
				cliente.setIdCliente(jo.getInt("id"));
				cliente.setBiblioteca(jo.getString("biblioteca"));
				cliente.setSitio(jo.getInt("sitio"));
				cliente.setBaja(false); // no se si es false

				clientes.add(cliente);

			}
		} catch (Exception e) {
			throw new IllegalArgumentException("BBDDException: " + e.getMessage());
		}

		return clientes;
	}
	
	@Override
	public Transfer_Cliente read(Transfer_Cliente t) {
		return read(t.getNombre());
	}
}
