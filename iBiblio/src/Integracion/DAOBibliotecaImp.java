package Integracion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import Negocio.Mesa;
import Negocio.Silla;
import Negocio.Transfer_Biblioteca;

public class DAOBibliotecaImp implements DAO<Transfer_Biblioteca> {
	private static final String FICHEROBIBLIOTECAS = "bbdd/ficheroBibliotecas.txt";
	private static int ID = 1;

	@Override
	public void create(Transfer_Biblioteca t) {
		List<Transfer_Biblioteca> bibliotecas = readAll();
		/*if (bibliotecas.isEmpty())
			ID = 1;
		else {
			ID = bibliotecas.get(bibliotecas.size() - 1).getIdBiblioteca() + 1;
		}
		t.setIdBiblioteca(ID);

		for (Transfer_Biblioteca c : bibliotecas) {
			if (c.getNombre().equals(t.getNombre()) || c.getIdBiblioteca() == t.getIdBiblioteca())
				throw new IllegalArgumentException("Ya existe esa biblioteca");
		}
		bibliotecas.add(t);//*/
		escribirBibliotecas(bibliotecas);
	}

	public void escribirBibliotecas(List<Transfer_Biblioteca> bibliotecas) {
		try {
			OutputStream out = FICHEROBIBLIOTECAS == null ? System.out : new FileOutputStream(new File(FICHEROBIBLIOTECAS));
			PrintStream print = (out == null) ? null : new PrintStream(out);
			print.println("{\n\"bibliotecas\": [");

			for (int i = 0; i < bibliotecas.size(); ++i) {
				print.print("{\n" + this.toString(bibliotecas.get(i)) + "\n}");
				if (i != bibliotecas.size() - 1) {
					print.println(",");
				}
			}

			print.print("\n]\n}");
			print.close();
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("BBDDException: " + e.getMessage());
		}
	}

	private String toString(Transfer_Biblioteca t) {
		String str = "";

		str += "\"nombre\" : \"" + t.getNombre() + "\",\n";
		str += "\"idBiblioteca\" : \"" + t.getIdBiblioteca() + "\",\n";
		str += "\"aforo\" : \"" + t.getAforo() + "\",\n";
		str += "\"aforoDisponible\" : \"" + t.getAforoDisponible() + "\",\n";
		str += "\"imagen\" : \"" + t.getImagen() + "\",\n";
		str += "\"valoracion\" : \"" + t.getValoracion()+ "\",\n";
		str += toStringMapa(t);
		return str;
	}
	
	private String toStringMapa(Transfer_Biblioteca t) {
		StringBuilder sb = new StringBuilder();
		sb.append("\"mapa\" : {\n");
		
		sb.append("\"mesas\" : [\n");
		List<Mesa> mesas = t.getMesas();
		for(int i = 0; i < mesas.size();i++) {
			Mesa m = mesas.get(i);
			sb.append("{\"coordinates\" : [").append(m.getX())
			.append(',').append(m.getY()).append(',').append(m.getAlto())
			.append(',').append(m.getAncho()).append("]}");
			if(i != mesas.size()-1)
				sb.append(",\n");
		}
		sb.append("\n],\n");
		
		sb.append("\"lado_silla\" : \"").append(t.getLadoSilla()).append("\",\n");
		
		sb.append("\"sillas\" : [\n");
		List<Silla> sillas = t.getSillas();
		for(int i = 0; i < sillas.size();i++) {
			Silla s = sillas.get(i);
			sb.append("{\"coordinates\" : [").append(s.getX()).append(',').append(s.getY())
			.append("], \"occupied\" : ").append(s.isOcupado()).append('}');
			if(i != sillas.size()-1)
				sb.append(",\n");
		}
		sb.append("\n]\n}\n");
		return sb.toString();
	}

	@Override
	public void update(Transfer_Biblioteca t) {
		List<Transfer_Biblioteca> bibliotecas = readAll();
		for (int i = 0; i < bibliotecas.size(); ++i) {
			Transfer_Biblioteca biblioteca = bibliotecas.get(i);
			if (biblioteca.getIdBiblioteca() == t.getIdBiblioteca()) {
				biblioteca.setNombre(t.getNombre());
				biblioteca.setIdBiblioteca(t.getIdBiblioteca());
				biblioteca.setAforo(t.getAforo());
				biblioteca.setAforoDisponible(t.getAforoDisponible());
				biblioteca.setImagen(t.getImagen());
				biblioteca.setValoracion(t.getValoracion());
				biblioteca.setMesas(t.getMesas());
				biblioteca.setSillas(t.getSillas());
				biblioteca.setLadoSilla(t.getLadoSilla());
			}
		}
		escribirBibliotecas(bibliotecas);
	}

	@Override
	public Transfer_Biblioteca read(String nombre) {
		Transfer_Biblioteca biblioteca = null;

		try {
			biblioteca = new Transfer_Biblioteca();
			JSONObject jsonInput = new JSONObject(new JSONTokener(new FileInputStream(new File(FICHEROBIBLIOTECAS))));
			JSONArray bibliotecas = jsonInput.getJSONArray("bibliotecas");
			boolean parar = false;

			for (int i = 0; i < bibliotecas.length() && !parar; ++i) {
				JSONObject jo = bibliotecas.getJSONObject(i);
				if (jo.get("nombre").equals(nombre)) {
					biblioteca.setNombre(jo.getString("nombre"));
					biblioteca.setIdBiblioteca(jo.getInt("idBiblioteca"));
					
					biblioteca.setAforoDisponible(jo.getInt("aforoDisponible"));
					biblioteca.setImagen(jo.getString("imagen"));
					biblioteca.setValoracion(jo.getFloat("valoracion"));

					JSONObject jMapa = jo.getJSONObject("mapa");
					biblioteca.setLadoSilla(jMapa.getInt("lado_silla"));
					List<Mesa> mesas = new ArrayList<Mesa>();
					List<Silla> sillas = new ArrayList<Silla>();
					JSONArray jMesas = jMapa.getJSONArray("mesas");
					JSONArray jSillas = jMapa.getJSONArray("sillas");
					
					biblioteca.setAforo(jSillas.length()); ///nuevo

					for (int j = 0; j < jMesas.length(); ++j) {
						JSONObject jo2 = jMesas.getJSONObject(j);
						JSONArray jCoord = jo2.getJSONArray("coordinates");
						Mesa mesa = new Mesa();
						mesa.setX(jCoord.getInt(0));
						mesa.setY(jCoord.getInt(1));
						mesa.setAlto(jCoord.getInt(2));
						mesa.setAncho(jCoord.getInt(3));

						mesas.add(mesa);
					}
					int aforoDisponible = 0;
					for (int j = 0; j < jSillas.length(); ++j) {
						JSONObject jo2 = jSillas.getJSONObject(j);
						JSONArray jCoord = jo2.getJSONArray("coordinates");
						Silla silla = new Silla();
						silla.setX(jCoord.getInt(0));
						silla.setY(jCoord.getInt(1));
						silla.setOcupado(jo2.getBoolean("occupied"));
						if(!jo2.getBoolean("occupied"))  ++aforoDisponible;
						sillas.add(silla);
					}
					
					biblioteca.setAforoDisponible(aforoDisponible); // nuevo
					
					biblioteca.setMesas(mesas);
					biblioteca.setSillas(sillas);

					parar = true;
				}
			}
			if (!parar) {
				biblioteca = null;
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("BBDDException: " + e.getMessage());
		}

		return biblioteca;
	}

	@Override
	public void delete(Transfer_Biblioteca t) {
		List<Transfer_Biblioteca> bibliotecas = readAll();
		int id = t.getIdBiblioteca();
		boolean parar = false;

		for (int i = 0; i < bibliotecas.size() && !parar; ++i) {
			if (bibliotecas.get(i).getIdBiblioteca() == id) {
				bibliotecas.remove(i);
				parar = true;
			}
		}

		escribirBibliotecas(bibliotecas);
	}

	@Override
	public List<Transfer_Biblioteca> readAll() {
		List<Transfer_Biblioteca> bibliotecas = new ArrayList<Transfer_Biblioteca>();

		try {
			JSONObject jsonInput = new JSONObject(new JSONTokener(new FileInputStream(new File(FICHEROBIBLIOTECAS))));
			JSONArray jArray = jsonInput.getJSONArray("bibliotecas");

			for (int i = 0; i < jArray.length(); ++i) {
				JSONObject jo = jArray.getJSONObject(i);
				Transfer_Biblioteca biblioteca = new Transfer_Biblioteca();

				biblioteca.setNombre(jo.getString("nombre"));
				biblioteca.setIdBiblioteca(jo.getInt("idBiblioteca"));
				
				
				biblioteca.setImagen(jo.getString("imagen"));
				biblioteca.setValoracion(jo.getFloat("valoracion"));

				JSONObject jMapa = jo.getJSONObject("mapa");
				biblioteca.setLadoSilla(jMapa.getInt("lado_silla"));
				List<Mesa> mesas = new ArrayList<Mesa>();
				List<Silla> sillas = new ArrayList<Silla>();
				JSONArray jMesas = jMapa.getJSONArray("mesas");
				JSONArray jSillas = jMapa.getJSONArray("sillas");
				
				biblioteca.setAforo(jSillas.length()); ///nuevo

				for (int j = 0; j < jMesas.length(); ++j) {
					JSONObject jo2 = jMesas.getJSONObject(j);
					JSONArray jCoord = jo2.getJSONArray("coordinates");
					Mesa mesa = new Mesa();
					mesa.setX(jCoord.getInt(0));
					mesa.setY(jCoord.getInt(1));
					mesa.setAlto(jCoord.getInt(2));
					mesa.setAncho(jCoord.getInt(3));

					mesas.add(mesa);
				}
				int aforoDisponible = 0;
				for (int j = 0; j < jSillas.length(); ++j) {
					JSONObject jo2 = jSillas.getJSONObject(j);
					JSONArray jCoord = jo2.getJSONArray("coordinates");
					Silla silla = new Silla();
					silla.setX(jCoord.getInt(0));
					silla.setY(jCoord.getInt(1));
					silla.setOcupado(jo2.getBoolean("occupied"));
					if(!jo2.getBoolean("occupied"))  ++aforoDisponible;
					sillas.add(silla);
				}
				
				biblioteca.setAforoDisponible(aforoDisponible); // nuevo
				
				biblioteca.setMesas(mesas);
				biblioteca.setSillas(sillas);

				bibliotecas.add(biblioteca);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("BBDDException: " + e.getMessage());
		}

		return bibliotecas;
	}

	@Override
	public Transfer_Biblioteca read(Transfer_Biblioteca t) {
		return read(t.getNombre());
	}
}
