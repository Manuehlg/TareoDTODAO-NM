package modelo.ImplDAOJDBC;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import modelo.AbstractDAO.HotelDAO;
import modelo.Entity.Hotel;
import modelo.acceso.AccessJdbc;

public class HotelDAOJdbc implements HotelDAO {

	private AccessJdbc accessJdbc;

	public HotelDAOJdbc(AccessJdbc accessJdbc) {
		this.accessJdbc = accessJdbc;
	}

	@Override
	public void create(Hotel entidad) {
		String sql = "INSERT INTO " + accessJdbc.getBBDD() + ".hotel (id, nombre) VALUES (?, ?)";
		accessJdbc.update(sql, (ps) -> {
			ps.setLong(1, entidad.getId());
			ps.setString(2, entidad.getNombre());
		});
	}

	@Override
	public Collection<Hotel> findAll() {
		ResultSet conjuntoResultados = accessJdbc.execute("SELECT * FROM hotel");
		Collection<Hotel> hoteles = new ArrayList<>();
		try {
			while (conjuntoResultados.next()) {
				Long id = conjuntoResultados.getLong("id");
				String nombre = conjuntoResultados.getString("nombre");
				hoteles.add(new Hotel(id, nombre));
			}
		} catch (Exception e) {
			return null;
		}
		return hoteles;
	}

	@Override
	public Hotel findById(Long id) {
		String sql = "SELECT * FROM " + accessJdbc.getBBDD() + ".hotel WHERE id = " + id;
		ResultSet conjuntoResultados = accessJdbc.execute(sql);
		try {
			if (conjuntoResultados.next()) {
				Long hotelId = conjuntoResultados.getLong("id");
				String nombre = conjuntoResultados.getString("nombre");
				return new Hotel(hotelId, nombre);
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	@Override
	public Hotel delete(Long id) {
		Hotel hotel = findById(id);
		if (hotel != null) {
			String sql = "DELETE FROM " + accessJdbc.getBBDD() + ".hotel WHERE id = " + id;
			accessJdbc.executeUpdate(sql);
			return hotel;
		}
		return null;
	}

	@Override
	public Hotel findByName(String name) {
		String sql = "SELECT * FROM " + accessJdbc.getBBDD() + ".hotel WHERE nombre LIKE '" + name + "'";
		ResultSet conjuntoResultados = accessJdbc.execute(sql);
		try {
			if (conjuntoResultados.next()) {
				Long id = conjuntoResultados.getLong("id");
				String nombre = conjuntoResultados.getString("nombre");
				return new Hotel(id, nombre);
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}
}
