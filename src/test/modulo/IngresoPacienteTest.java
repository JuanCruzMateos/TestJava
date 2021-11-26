package test.modulo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import excepciones.PacienteRepetidoException;
import excepciones.PacienteYaIngresadoException;
import pacientes.IPaciente;
import pacientes.PacienteFactory;
import test.escenarios.EscenarioClinicaVacia;

public class IngresoPacienteTest {
	private EscenarioClinicaVacia sindatos = new EscenarioClinicaVacia();

	@Before
	public void setUp() throws Exception {
		this.sindatos.setUp();
	}

	@Test
	public void ingresoNiñoConNiñoEnSalaVIP() {
		try {
			IPaciente paciente = PacienteFactory.getPaciente("11111111", "Carlos Perez", "155111111", "Mitre 1234",
					"Mar del Plata", 1, "Nino");
			this.sindatos.getClinica().ingresoPaciente(paciente);
		} catch (PacienteRepetidoException e) {

		} catch (PacienteYaIngresadoException e) {
			// TODO: handle exception
		}
	}

	@Test
	public void ingresoNiñoConJovenEnSalaVIP() {
		fail("Not yet implemented");
	}

	@Test
	public void ingresoNiñoConMayorEnSalaVIP() {
		fail("Not yet implemented");
	}

}
