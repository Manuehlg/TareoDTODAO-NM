package modelo.ImplDAOJDBC;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.time.LocalDate;

import modelo.AbstractDAO.ReservaDAO;
import modelo.Entity.Reserva;
import modelo.acceso.AccessJdbc;

public class ReservaDAOJdbc implements ReservaDAO {

	private AccessJdbc accessJdbc;

	public ReservaDAOJdbc(AccessJdbc accessJdbc) {
		this.accessJdbc = accessJdbc;
	}

	@Override
	public void create(Reserva entidad) {
		String sql = "INSERT INTO " + accessJdbc.getBBDD() + ".reserva (id_persona, id_hotel, fecha) VALUES (?, ?, ?)";
		accessJdbc.update(sql, (ps) -> {
			ps.setLong(1, entidad.getIdPersona());
			ps.setLong(2, entidad.getIdHotel());
			ps.setObject(3, entidad.getFecha());
		});
	}

	@Override
	public Collection<Reserva> findAll() {
		ResultSet conjuntoResultados = accessJdbc.execute("SELECT * FROM reserva");
		Collection<Reserva> reservas = new ArrayList<>();
		try {
			while (conjuntoResultados.next()) {
				Long idPersona = conjuntoResultados.getLong("id_persona");
				Long idHotel = conjuntoResultados.getLong("id_hotel");
				LocalDate fecha = conjuntoResultados.getDate("fecha").toLocalDate();
				reservas.add(new Reserva(idPersona, idHotel, fecha));
			}
		} catch (Exception e) {
			return null;
		}
		return reservas;
	}

	@Override
	public Reserva findById(Long id) {
		String sql = "SELECT * FROM " + accessJdbc.getBBDD() + ".reserva WHERE id = " + id;
		ResultSet conjuntoResultados = accessJdbc.execute(sql);
		try {
			if (conjuntoResultados.next()) {
				Long idPersona = conjuntoResultados.getLong("id_persona");
				Long idHotel = conjuntoResultados.getLong("id_hotel");
				LocalDate fecha = conjuntoResultados.getDate("fecha").toLocalDate();
				return new Reserva(idPersona, idHotel, fecha);
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	@Override
	public Reserva delete(Long id) {
		Reserva reserva = findById(id);
		if (reserva != null) {
			String sql = "DELETE FROM " + accessJdbc.getBBDD() + ".reserva WHERE id = " + id;
			accessJdbc.executeUpdate(sql);
			return reserva;
		}
		return null;
	}

	public Collection<Reserva> findByHotelId(Long hotelId) {
		String sql = "SELECT * FROM " + accessJdbc.getBBDD() + ".reserva WHERE id_hotel = " + hotelId;
		ResultSet conjuntoResultados = accessJdbc.execute(sql);
		Collection<Reserva> reservas = new ArrayList<>();
		try {
			while (conjuntoResultados.next()) {
				Long idPersona = conjuntoResultados.getLong("id_persona");
				LocalDate fecha = conjuntoResultados.getDate("fecha").toLocalDate();
				reservas.add(new Reserva(idPersona, hotelId, fecha));
			}
		} catch (Exception e) {
			return null;
		}
		return reservas;
	}

	@Override
	public Collection<Reserva> findByDate(LocalDate date) {
		String sql = "SELECT * FROM " + accessJdbc.getBBDD() + ".reserva WHERE fecha = '" + date + "'";
		ResultSet conjuntoResultados = accessJdbc.execute(sql);
		Collection<Reserva> reservas = new ArrayList<>();
		try {
			while (conjuntoResultados.next()) {
				Long idPersona = conjuntoResultados.getLong("id_persona");
				Long idHotel = conjuntoResultados.getLong("id_hotel");
				reservas.add(new Reserva(idPersona, idHotel, date));
			}
		} catch (Exception e) {
			return null;
		}
		return reservas;
	}
}
