package test.clinica;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import excepciones.JugoRobinhoException;
import excepciones.PacienteRepetidoException;
import excepciones.PacienteYaIngresadoException;
import excepciones.TipoPacienteInvalidoException;
import pacientes.IPaciente;
import pacientes.PacienteFactory;
import test.escenarios.EscenarioClinicaConDatos;

public class IngresoPacienteClinicaConDatosTest {
	private EscenarioClinicaConDatos condatos = new EscenarioClinicaConDatos();

	@Before
	public void setUp() throws Exception {
		this.condatos.setUp();
	}

	@Test
	public void ingresoPacienteTest() {
		try {
			IPaciente joven = PacienteFactory.getPaciente("99999999", "Carolina Dominguez", "155999999", "Falucho 7834",
					"Mar del Plata", 9, "Joven");
			this.condatos.getClinica().ingresoPaciente(joven);

			if (!this.condatos.getClinica().getPatio().contains(joven)
					&& this.condatos.getClinica().getSalaDeEsperaPrivada() != joven) {
				fail("No se ingreso correctamente el paciente a la clinica");
			}

		} catch (TipoPacienteInvalidoException e) {
			fail("No deberian ocurrir excepciones: El paciente es valido");
		} catch (PacienteRepetidoException e) {
			fail("No deberian ocurrir excepciones: El paciente a ingresar es nuevo, no repetido");
		} catch (PacienteYaIngresadoException e) {
			fail("No deberian ocurrir excepciones: El paciente a ingresar es nuevo, no se lo ha ingresado antes");
		}
	}

	/**
	 * Lo primero que evalua el metodo es si esta en el historico de pacientes si
	 * esta, devuelve PacienteRepetidoException -> deberia ser
	 * PacienteExistenteEnClinica
	 * 
	 */
	@Test
	public void ingresoPacienteRepetidoTest() { // repetido en historicos
		try {
			this.condatos.getClinica().ingresoPaciente(this.condatos.getPaciente());
			fail("Deberia lanzarse una excepcion de tipo PacienteRepetidoException: ya esta en el historico");
		} catch (PacienteRepetidoException e) {
			// el paciente ya se encuentra en la lista de historicos
		} catch (PacienteYaIngresadoException e) {
			fail("Deberia lanzarse una excepcion de tipo PacienteRepetidoException: ya esta en el historico");
		}
	}

	/**
	 * La unica forma es
	 */
	@Test
	public void ingresoPacienteYaIngresadoTest() { // ingresado en lista de espera
		try {
			// borro el paciente del historico, pero no de la lista de espera
			this.condatos.getClinica().eliminaHPaciente(this.condatos.getPaciente());
			// lo ingreso
			this.condatos.getClinica().ingresoPaciente(this.condatos.getPaciente());
			fail("Deberia lanzarse una excepcion de tipo PacienteYaIngresadoException");
		} catch (PacienteRepetidoException e) {
			fail("Deberia lanzarse una excepcion de tipo PacienteYaIngresadoException");
		} catch (PacienteYaIngresadoException e) {
			// el paciente debe no estar en el historico pero si en la lista de espera
			// la unica forma es meterlo en el ArrayList puenteando al metodo ingreso
		} catch (JugoRobinhoException e) {
			fail("Deberia lanzarse una excepcion de tipo PacienteYaIngresadoException");
		}
	}

	@Test
	public void ingresoPacienteNuevamenteYaIngresadoTest() {
		try {
			// borro el paciente del historico, pero no de la lista de espera
			this.condatos.getClinica().eliminaHPaciente(this.condatos.getPaciente());
			// lo ingreso otra vez
			this.condatos.getClinica().ingresoNuevamente(this.condatos.getPaciente());
			fail("Deberia lanzarse una excepcion de tipo PacienteYaIngresadoException: ya esta en la lista de espera");
		} catch (PacienteYaIngresadoException e) {
			//
		} catch (JugoRobinhoException e) {
			fail("Deberia lanzarse una excepcion de tipo PacienteYaIngresadoException: ya esta en la lista de espera");
		}
	}

	@Test
	public void ingresoPacienteNuevamenteTest() {
		try {
			// borro el paciente de la lista de espera
			this.condatos.getClinica().eliminaListaEspera(this.condatos.getPaciente());
			if (this.condatos.getClinica().getSalaDeEsperaPrivada() == this.condatos.getPaciente()) {
				this.condatos.getClinica().setSalaDeEsperaPrivada(null);
			} else {
				this.condatos.getClinica().getPatio().remove(this.condatos.getPaciente());
			}
			// lo ingreso otra vez
			this.condatos.getClinica().ingresoNuevamente(this.condatos.getPaciente());

			if (!this.condatos.getClinica().getPatio().contains(this.condatos.getPaciente())
					&& this.condatos.getClinica().getSalaDeEsperaPrivada() != this.condatos.getPaciente()) {
				fail("No se ingreso correctamente el paciente a la clinica");
			}
		} catch (PacienteYaIngresadoException e) {
			fail("Deberia agregar al paciente que ya existe en el historico en la lista de espera");
		}
	}
}
