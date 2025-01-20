package modelo.AbstractDAO;

import modelo.Entity.Reserva;
import java.time.LocalDate;
import java.util.Collection;

public interface ReservaDAO extends GenericDAO<Reserva, Long> {
	public Collection<Reserva> findByDate(LocalDate date);

}
