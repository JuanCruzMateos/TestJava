package test.clinica;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import test.escenarios.EscenarioClinicaConDatos;

public class EliminarPacienteClinicaConDatosTest {
	EscenarioClinicaConDatos condatos = new EscenarioClinicaConDatos();

	@Before
	public void setUp() throws Exception {
		this.condatos.setUp();
	}

	@Test
	public void eliminaListaEsperaTest() {
		this.condatos.getClinica().eliminaListaEspera(this.condatos.getPacienteEnSalaPrivada());
		assertFalse("No se elimino correctamente al paciente de la lista de espera",
				this.condatos.getClinica().getListaDeEspera().contains(this.condatos.getPacienteEnSalaPrivada()));
	}

	@Test
	public void eliminaPatioTest() {
		this.condatos.getClinica().eliminaPatio(this.condatos.getPacienteEnPatio());
		assertFalse("No se elimino correctamente el paciente del patio",
				this.condatos.getClinica().getPatio().contains(this.condatos.getPacienteEnPatio()));
	}
}
