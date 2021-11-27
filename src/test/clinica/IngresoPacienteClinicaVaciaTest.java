package test.clinica;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import excepciones.NoHayPacienteEsperandoException;
import excepciones.PacienteRepetidoException;
import excepciones.PacienteYaIngresadoException;
import excepciones.TipoPacienteInvalidoException;
import pacientes.IPaciente;
import pacientes.PacienteFactory;
import test.escenarios.EscenarioClinicaVacia;

/**
 * 
 * verifica correcto funcionamiento de clinica.ingresarPaciente()
 *
 */
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

	@Test
	public void agregaPacienteHistoricoExitoso() {
		IPaciente p = null;
		try {
			p = PacienteFactory.getPaciente("99999999", "Carolina Dominguez", "155999999", "Falucho 7834",
					"Mar del Plata", 9, "Joven");
		} catch (TipoPacienteInvalidoException e) {
			fail("No deberia arrojar excepcion");
		}
		try {
			this.sindatos.getClinica().agregaHistorico(p);
		} catch (PacienteRepetidoException e) {
			fail("No deberia saltar una excepcion");
		}
		assertTrue("Deberia contener el paciente historico", this.sindatos.getClinica().gethPacientes().contains(p));
	}

	@Test
	public void llamarPacienteNOExitoso() {
		try {
			IPaciente paciente = this.sindatos.getClinica().llamarPaciente();
			fail("Deberia lanzarse una excepcion de tipo NoHayPacienteEsperandoException");
		} catch (NoHayPacienteEsperandoException e) {
			// Correcto
		}
	}

	@Test
	public void agregarListaEsperaExitoso() {
		IPaciente p = null;
		try {
			p = PacienteFactory.getPaciente("99999999", "Carolina Dominguez", "155999999", "Falucho 7834",
					"Mar del Plata", 9, "Joven");
		} catch (TipoPacienteInvalidoException e) {
			fail("No deberia arrojar excepcion");
		}
		try {
			this.sindatos.getClinica().agregaListaEspera(p);
		} catch (PacienteYaIngresadoException e) {
			fail("No deberia arrojar excepcion");
		}
		assertTrue("Deberia estar agregado a la lista de espera",
				this.sindatos.getClinica().getListaDeEspera().contains(p));
	}
}
