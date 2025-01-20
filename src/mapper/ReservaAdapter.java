package mapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import modelo.Entity.Reserva;

public class ReservaAdapter implements Adapter {

    private Reserva reserva;

    public ReservaAdapter(Reserva reserva) {
        super();
        this.reserva = reserva;
    }

    @Override
    public void adapt(PreparedStatement declaracion) throws SQLException {
        declaracion.setLong(1, reserva.getIdPersona()); 
        declaracion.setLong(2, reserva.getIdHotel());   
        declaracion.setDate(3, Date.valueOf(reserva.getFecha()));
    }
}
