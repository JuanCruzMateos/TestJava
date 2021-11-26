package test.modulo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.escenarios.EscenarioClinicaConDatos;

public class IngresoPacienteClinicaConDatosTest {
	private EscenarioClinicaConDatos condatos = new EscenarioClinicaConDatos();

	@Before
	public void setUp() throws Exception {
		this.condatos.setUp();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
