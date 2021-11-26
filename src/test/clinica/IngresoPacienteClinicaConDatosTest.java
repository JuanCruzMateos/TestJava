package test.clinica;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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

	@Test
	public void ingresoPacienteRepetidoTest() {
		try {
			this.condatos.getClinica().ingresoPaciente(this.condatos.getPaciente());
			fail("Deberia lanzarse una excepcion de tipo PacienteRepetidoException");
		} catch (PacienteRepetidoException e) {
			//
		} catch (PacienteYaIngresadoException e) {
			fail("No deberian ocurrir excepciones: El paciente a ingresar es nuevo, no se lo ha ingresado antes");
		}
	}

	@Test
	public void ingresoPacienteYaIngresasoTest() {
		try {
			this.condatos.getClinica().ingresoPaciente(this.condatos.getPaciente());
			fail("Deberia lanzarse una excepcion de tipo PacienteRepetidoException");
		} catch (PacienteRepetidoException e) {
			//
		} catch (PacienteYaIngresadoException e) {
			fail("No deberian ocurrir excepciones: El paciente a ingresar es nuevo, no se lo ha ingresado antes");
		}
	}

}
