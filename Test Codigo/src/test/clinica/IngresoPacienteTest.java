package test.clinica;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import excepciones.PacienteRepetidoException;
import excepciones.PacienteYaIngresadoException;
import excepciones.TipoPacienteInvalidoException;
import pacientes.IPaciente;
import pacientes.PacienteFactory;
import test.escenarios.EscenarioClinicaVacia;

/**
 * 
 * Verifica que quede en sala VIP quien corresponda
 *
 */
public class IngresoPacienteTest {
	private EscenarioClinicaVacia sindatos = new EscenarioClinicaVacia();

	@Before
	public void setUp() throws Exception {
		this.sindatos.setUp();
	}

	@Test
	public void ingresoJovenConNiñoEnSalaVIP() {
		try {
			IPaciente nino = PacienteFactory.getPaciente("11111111", "Carlos Perez", "155111111", "Mitre 1234",
					"Mar del Plata", 1, "Nino");
			this.sindatos.getClinica().ingresoPaciente(nino);

			IPaciente joven = PacienteFactory.getPaciente("66666666", "Lucas Rodriguez", "155666666", "Colon 1472",
					"Mar del Plata", 6, "Joven");
			this.sindatos.getClinica().ingresoPaciente(joven);

			assertEquals("Deberia quedar el Niño", nino, this.sindatos.getClinica().getSalaDeEsperaPrivada());
		} catch (PacienteRepetidoException e) {
			fail("No deberian lanzarse excepciones");
		} catch (PacienteYaIngresadoException e) {
			fail("No deberian lanzarse excepciones");
		} catch (TipoPacienteInvalidoException e) {
			fail("No deberian lanzarse excepciones");
		}
	}

	@Test
	public void ingresoNiñoConJovenEnSalaVIP() {
		try {
			IPaciente joven = PacienteFactory.getPaciente("66666666", "Lucas Rodriguez", "155666666", "Colon 1472",
					"Mar del Plata", 6, "Joven");
			this.sindatos.getClinica().ingresoPaciente(joven);

			IPaciente nino = PacienteFactory.getPaciente("11111111", "Carlos Perez", "155111111", "Mitre 1234",
					"Mar del Plata", 1, "Nino");
			this.sindatos.getClinica().ingresoPaciente(nino);

			assertEquals("Deberia quedar el Niño", nino, this.sindatos.getClinica().getSalaDeEsperaPrivada());
		} catch (PacienteRepetidoException e) {
			fail("No deberian lanzarse excepciones");
		} catch (PacienteYaIngresadoException e) {
			fail("No deberian lanzarse excepciones");
		} catch (TipoPacienteInvalidoException e) {
			fail("No deberian lanzarse excepciones");
		}
	}

	@Test
	public void ingresoJovenConMayorEnSalaVIP() {
		try {
			IPaciente mayor = PacienteFactory.getPaciente("11111111", "Carlos Perez", "155111111", "Mitre 1234",
					"Mar del Plata", 1, "Mayor");
			this.sindatos.getClinica().ingresoPaciente(mayor);

			IPaciente joven = PacienteFactory.getPaciente("66666666", "Lucas Rodriguez", "155666666", "Colon 1472",
					"Mar del Plata", 6, "Joven");
			this.sindatos.getClinica().ingresoPaciente(joven);

			assertEquals("Deberia quedar el Joven", joven, this.sindatos.getClinica().getSalaDeEsperaPrivada());
		} catch (PacienteRepetidoException e) {
			fail("No deberian lanzarse excepciones");
		} catch (PacienteYaIngresadoException e) {
			fail("No deberian lanzarse excepciones");
		} catch (TipoPacienteInvalidoException e) {
			fail("No deberian lanzarse excepciones");
		}
	}

	@Test
	public void ingresoMayorConJovenEnSalaVIP() {
		try {
			IPaciente joven = PacienteFactory.getPaciente("66666666", "Lucas Rodriguez", "155666666", "Colon 1472",
					"Mar del Plata", 6, "Joven");
			this.sindatos.getClinica().ingresoPaciente(joven);

			IPaciente mayor = PacienteFactory.getPaciente("11111111", "Carlos Perez", "155111111", "Mitre 1234",
					"Mar del Plata", 1, "Mayor");
			this.sindatos.getClinica().ingresoPaciente(mayor);

			assertEquals("Deberia quedar el Joven", joven, this.sindatos.getClinica().getSalaDeEsperaPrivada());
		} catch (PacienteRepetidoException e) {
			fail("No deberian lanzarse excepciones");
		} catch (PacienteYaIngresadoException e) {
			fail("No deberian lanzarse excepciones");
		} catch (TipoPacienteInvalidoException e) {
			fail("No deberian lanzarse excepciones");
		}
	}

	@Test
	public void ingresoMayorConNiñoEnSalaVIP() {
		try {
			IPaciente nino = PacienteFactory.getPaciente("66666666", "Lucas Rodriguez", "155666666", "Colon 1472",
					"Mar del Plata", 6, "Nino");
			this.sindatos.getClinica().ingresoPaciente(nino);

			IPaciente mayor = PacienteFactory.getPaciente("11111111", "Carlos Perez", "155111111", "Mitre 1234",
					"Mar del Plata", 1, "Mayor");
			this.sindatos.getClinica().ingresoPaciente(mayor);

			assertEquals("Deberia quedar el Mayor", mayor, this.sindatos.getClinica().getSalaDeEsperaPrivada());
		} catch (PacienteRepetidoException e) {
			fail("No deberian lanzarse excepciones");
		} catch (PacienteYaIngresadoException e) {
			fail("No deberian lanzarse excepciones");
		} catch (TipoPacienteInvalidoException e) {
			fail("No deberian lanzarse excepciones");
		}
	}

	@Test
	public void ingresoNiñoConMayorEnSalaVIP() {
		try {
			IPaciente mayor = PacienteFactory.getPaciente("11111111", "Carlos Perez", "155111111", "Mitre 1234",
					"Mar del Plata", 1, "Mayor");
			this.sindatos.getClinica().ingresoPaciente(mayor);

			IPaciente nino = PacienteFactory.getPaciente("66666666", "Lucas Rodriguez", "155666666", "Colon 1472",
					"Mar del Plata", 6, "Nino");
			this.sindatos.getClinica().ingresoPaciente(nino);

			assertEquals("Deberia quedar el Mayor", mayor, this.sindatos.getClinica().getSalaDeEsperaPrivada());
		} catch (PacienteRepetidoException e) {
			fail("No deberian lanzarse excepciones");
		} catch (PacienteYaIngresadoException e) {
			fail("No deberian lanzarse excepciones");
		} catch (TipoPacienteInvalidoException e) {
			fail("No deberian lanzarse excepciones");
		}
	}
}
