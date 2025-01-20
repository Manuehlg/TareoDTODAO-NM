package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import modelo.Entity.Reserva;

public class ReservaMapper implements Mapper<ResultSet, Reserva> {

    @Override
    public Reserva map(ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                Long idPersona = resultSet.getLong("idPersona");
                Long idHotel = resultSet.getLong("idHotel");
                LocalDate fecha = resultSet.getDate("fecha").toLocalDate();
                
                return new Reserva(idPersona, idHotel, fecha);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
