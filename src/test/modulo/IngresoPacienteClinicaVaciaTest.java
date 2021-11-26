package test.modulo;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import excepciones.PacienteRepetidoException;
import excepciones.PacienteYaIngresadoException;
import excepciones.TipoPacienteInvalidoException;
import pacientes.IPaciente;
import pacientes.PacienteFactory;
import test.escenarios.EscenarioClinicaVacia;

public class IngresoPacienteClinicaVaciaTest {
	private EscenarioClinicaVacia sindatos = new EscenarioClinicaVacia();

	@Before
	public void setUp() throws Exception {
		this.sindatos.setUp();
	}

	@Test
	public void ingresoPacienteTest() {
		try {
			IPaciente paciente = PacienteFactory.getPaciente("11111111", "Carlos Perez", "155111111", "Mitre 1234",
					"Mar del Plata", 1, "Mayor");
			this.sindatos.getClinica().ingresoPaciente(paciente);
			assertTrue("El paciente no se encuentra en la sala vip",
					paciente == this.sindatos.getClinica().getSalaDeEsperaPrivada());
		} catch (TipoPacienteInvalidoException e) {
			fail("No deberian ocurrir excepciones: El paciente es valido");
		} catch (PacienteRepetidoException e) {
			fail("No deberian ocurrir excepciones: El paciente a ingresar es nuevo, no repetido");
		} catch (PacienteYaIngresadoException e) {
			fail("No deberian ocurrir excepciones: El paciente a ingresar es nuevo, no se lo ha ingresado antes");
		}
	}
}
