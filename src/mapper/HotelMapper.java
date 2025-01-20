package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Entity.Hotel;

public class HotelMapper implements Mapper<ResultSet, Hotel> {

    @Override
    public Hotel map(ResultSet rs) {
        try {
            if (rs.next()) {
                Long id = rs.getLong("id");          
                String nombre = rs.getString("nombre"); 
                return new Hotel(id, nombre);       
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
