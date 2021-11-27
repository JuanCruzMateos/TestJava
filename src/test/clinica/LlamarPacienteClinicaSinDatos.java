package test.clinica;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import excepciones.NoHayPacienteEsperandoException;
import pacientes.IPaciente;
import test.escenarios.EscenarioClinicaVacia;

public class LlamarPacienteClinicaSinDatos {
	private EscenarioClinicaVacia sindatos = new EscenarioClinicaVacia();

	@Before
	public void setUp() throws Exception {
		this.sindatos.setUp();
	}

	@Test
	public void llamarPaciente() {
		try {
			IPaciente paciente = this.sindatos.getClinica().llamarPaciente();
			fail("Deberia lanzarse una excepcion de tipo NoHayPacienteEsperandoException");
		} catch (NoHayPacienteEsperandoException e) {
			// Correcto
		}
	}

}
