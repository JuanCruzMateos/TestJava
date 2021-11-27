package test.clinica;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import excepciones.NoHayPacienteEsperandoException;
import pacientes.IPaciente;
import test.escenarios.EscenarioClinicaConDatos;

public class LlamarPacienteClinicaConDatos {
	private EscenarioClinicaConDatos condatos = new EscenarioClinicaConDatos();

	@Before
	public void setUp() throws Exception {
		this.condatos.setUp();
	}

	@Test
	public void llamarPacienteTest() {
		try {
			ArrayList<IPaciente> enPatio = this.condatos.getClinica().getPatio();

			IPaciente paciente = this.condatos.getClinica().llamarPaciente();
			assertFalse("No elimino al paciente de la lista de espera correctamente",
					this.condatos.getClinica().getListaDeEspera().contains(paciente));
			assertTrue("No agrego correctamente al paciente en la lista de atencion",
					this.condatos.getClinica().getListaDeAtencion().contains(paciente));

			if (enPatio.contains(paciente)) {
				assertNull("No elimino correctamente al paciente de la sala de espera privada");
			} else {
				assertFalse("No elimino correctamente al paciente del patio",
						this.condatos.getClinica().getPatio().contains(paciente));
			}
		} catch (NoHayPacienteEsperandoException e) {
			fail("Deberia lanzarse una excepcion de tipo NoHayPacienteEsperandoException");
		}
	}

	@Test
	public void otro() {
		this.llamarPacienteTest();
		this.llamarPacienteTest();
	}
}
