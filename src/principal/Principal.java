package principal;

import java.sql.SQLException;
import java.util.Collection;
import modelo.AbstractDAO.DaoFactory;
import modelo.AbstractDAO.HotelDAO;
import modelo.AbstractDAO.PersonaDAO;
import modelo.AbstractDAO.ReservaDAO;
import modelo.Entity.Hotel;
import modelo.Entity.Persona;
import modelo.Entity.Reserva;
import modelo.ImplDAOJDBC.DAOFactoryJDBC;

public class Principal {

	public static void main(String[] args) {
		try {
			DaoFactory daoFactory = new DAOFactoryJDBC();
			PersonaDAO personaDAO = daoFactory.getPersonaDAO();
			HotelDAO hotelDAO = daoFactory.getHotelDAO();
			ReservaDAO reservaDAO = daoFactory.getReservaDAO();
			Collection<Persona> findAll = personaDAO.findAll();
			Collection<Hotel> findHotels = hotelDAO.findAll();
			Collection<Reserva> findReservas = reservaDAO.findAll();
			findAll.stream().forEach((a) -> {
				System.out.println(a);
			});
			System.out.println("acabe");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
