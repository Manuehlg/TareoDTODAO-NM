package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;

import modelo.AbstractDAO.PersonaDAO;
import modelo.Entity.Persona;
import modelo.ImplDAOJDBC.DAOFactoryJDBC;
import modelo.acceso.AccessJdbc;

class PersonaDAOJDBCTest {
	static DAOFactoryJDBC daoFactoryJDBC;
	PersonaDAO personaDAO;
	static AccessJdbc accessJDBC;

	@BeforeAll
	static void beforeAll() {
		try {
			daoFactoryJDBC = new DAOFactoryJDBC();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resetarTabla();
	}

	@BeforeEach
	void beforeEach() {
		personaDAO = daoFactoryJDBC.getPersonaDAO();
	}

	
	@Test
	void testCreate() {
		Persona entidad = new Persona("arturito");
		personaDAO.create(entidad);
		ResultSet execute = accessJDBC.execute("select * from persona");
		try {
			assertTrue(execute.next());
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	void testFindAll() {
		String nombre="arturo";
		String apellidos="Jimenez";
		accessJDBC.executeUpdate("INSERT INTO ejemplo.persona  (nombre, apellidos,alta)"
				+ " VALUES ('"+nombre+"' , '"+apellidos+"', null)");
		accessJDBC.executeUpdate("INSERT INTO ejemplo.persona  (nombre, apellidos,alta)"
				+ " VALUES ('"+nombre+"' , '"+apellidos+"', null)");
		accessJDBC.executeUpdate("INSERT INTO ejemplo.persona  (nombre, apellidos,alta)"
				+ " VALUES ('"+nombre+"' , '"+apellidos+"', null)");
		int cantidad=3;
		Collection<Persona> findAll = personaDAO.findAll();
		assertEquals(cantidad, findAll.size());
	}

	@Ignore
	void testFindById() {
		String nombre="arturo";
		String apellidos="Jimenez";
		accessJDBC.executeUpdate("INSERT INTO ejemplo.persona  (nombre, apellidos,alta)"
				+ " VALUES ('"+nombre+"' , '"+apellidos+"', null)");
		Persona persona = personaDAO.findById(1L);
		assertNotNull(persona);
		assertEquals(nombre, persona.getNombre());
	}

	@Test
	void testFindByName() {

	}

	@AfterEach
	void afterEach() {
//		resetarTabla();
	}
	
	private static void resetarTabla() {
		accessJDBC = daoFactoryJDBC.getAccessJDBC();
		accessJDBC.executeUpdate("delete FROM persona;");
		accessJDBC.executeUpdate("ALTER TABLE persona AUTO_INCREMENT = 1;");
	}

}
