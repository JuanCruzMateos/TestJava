package test.clinica;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import excepciones.ContratacionInvalidaException;
import excepciones.EspecialidadInvalidaException;
import excepciones.JugoRobinhoException;
import excepciones.NoHayPacienteEsperandoException;
import excepciones.PacienteRepetidoException;
import excepciones.PacienteYaIngresadoException;
import excepciones.PosgradoInvalidoException;
import excepciones.TipoPacienteInvalidoException;
import habitaciones.SalaTerapiaIntensiva;
import medicos.MedicoFactory;
import pacientes.IPaciente;
import pacientes.LineaFactura;
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
			this.condatos.getClinica().ingresoPaciente(this.condatos.getPacienteEnSalaPrivada());
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
			this.condatos.getClinica().eliminaHPaciente(this.condatos.getPacienteEnSalaPrivada());
			// lo ingreso
			this.condatos.getClinica().ingresoPaciente(this.condatos.getPacienteEnSalaPrivada());
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
	public void eliminaHistoricoSinFactura() {
		IPaciente p = this.condatos.getPacienteEnSalaPrivada();
		try {
			this.condatos.getClinica().eliminaHPaciente(p);
		} catch (JugoRobinhoException e) {
			fail("No deberia lanzarse la excepcion");
			
		}
	}
	
	@Test
	public void eliminaHistoricoConFactura() {
		IPaciente p = this.condatos.getPacienteEnSalaPrivada();
		p.setFacturo(false);
		LineaFactura f1 = null;
		ArrayList<LineaFactura> facturas = new ArrayList<>();
		try {
			f1 = new LineaFactura(new SalaTerapiaIntensiva(100), 1, p,
					MedicoFactory.getMedico("Juan Perez", "1234567", "casa", "Mardel", "987654", 7, "Cirujano",
							"Permanente", "Magister", 100));
		} catch (PosgradoInvalidoException | ContratacionInvalidaException | EspecialidadInvalidaException e) {
		}
		facturas.add(f1);
		this.condatos.getClinica().setLineasFacturas(facturas);
		try {
			this.condatos.getClinica().eliminaHPaciente(p);
			fail("Deberia lanzarse la excepcion");
		} catch (JugoRobinhoException e) {
		}
	}

	@Test
	public void ingresoPacienteNuevamenteYaIngresadoTest() {
		try {
			// borro el paciente del historico, pero no de la lista de espera
			this.condatos.getClinica().eliminaHPaciente(this.condatos.getPacienteEnSalaPrivada());
			// lo ingreso otra vez
			this.condatos.getClinica().ingresoNuevamente(this.condatos.getPacienteEnSalaPrivada());
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
			this.condatos.getClinica().eliminaListaEspera(this.condatos.getPacienteEnSalaPrivada());
			if (this.condatos.getClinica().getSalaDeEsperaPrivada() == this.condatos.getPacienteEnSalaPrivada()) {
				this.condatos.getClinica().setSalaDeEsperaPrivada(null);
			} else {
				this.condatos.getClinica().getPatio().remove(this.condatos.getPacienteEnSalaPrivada());
			}
			// lo ingreso otra vez
			this.condatos.getClinica().ingresoNuevamente(this.condatos.getPacienteEnSalaPrivada());

			if (!this.condatos.getClinica().getPatio().contains(this.condatos.getPacienteEnSalaPrivada())
					&& this.condatos.getClinica().getSalaDeEsperaPrivada() != this.condatos
							.getPacienteEnSalaPrivada()) {
				fail("No se ingreso correctamente el paciente a la clinica");
			}
		} catch (PacienteYaIngresadoException e) {
			fail("Deberia agregar al paciente que ya existe en el historico en la lista de espera");
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
			this.condatos.getClinica().agregaHistorico(p);
		} catch (PacienteRepetidoException e) {
			fail("No deberia saltar una excepcion");
		}
		assertTrue("Deberia contener el paciente historico", this.condatos.getClinica().gethPacientes().contains(p));
	}

	@Test
	public void testAgregaPacienteHistoricoRepetido() {
		IPaciente p = null;
		try {
			p = PacienteFactory.getPaciente("99999999", "Carolina Dominguez", "155999999", "Falucho 7834",
					"Mar del Plata", 9, "Joven");
		} catch (TipoPacienteInvalidoException e) {
			fail("No deberia arrojar excepcion");
		}
		try {
			this.condatos.getClinica().agregaHistorico(p);
			this.condatos.getClinica().agregaHistorico(p);
			fail("Deberia saltar una excepcion");
		} catch (PacienteRepetidoException e) {
		}
	}

	@Test
	public void llamarPacienteExitoso() {
		try {
			ArrayList<IPaciente> enPatio = this.condatos.getClinica().getPatio();

			IPaciente paciente = this.condatos.getClinica().llamarPaciente();
			assertEquals("No retiro al paciente con el primer turno", this.condatos.getPrimerPaciente(), paciente);
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
	public void testAgregarListaEsperaExitoso() {
		IPaciente p = null;
		try {
			p = PacienteFactory.getPaciente("99999999", "Carolina Dominguez", "155999999", "Falucho 7834",
					"Mar del Plata", 9, "Joven");
		} catch (TipoPacienteInvalidoException e) {
			fail("No deberia arrojar excepcion");
		}
		try {
			this.condatos.getClinica().agregaListaEspera(p);
		} catch (PacienteYaIngresadoException e) {
			fail("No deberia arrojar excepcion");
		}
		assertTrue("Deberia estar agregado a la lista de espera",
				this.condatos.getClinica().getListaDeEspera().contains(p));
	}

	@Test
	public void testAgregaListaEsperaExistente() {
		IPaciente p = this.condatos.getPacienteEnPatio();
		try {
			this.condatos.getClinica().agregaListaEspera(p);
			fail("Deberia arrojar excepcion");
		} catch (PacienteYaIngresadoException e) {
		}
	}

	@Test
	public void testHayPrestacion() {
		LineaFactura f1 = null;
		ArrayList<LineaFactura> facturas = new ArrayList<>();
		try {
			f1 = new LineaFactura(new SalaTerapiaIntensiva(100), 1, this.condatos.getPacienteEnPatio(),
					MedicoFactory.getMedico("Juan Perez", "1234567", "casa", "Mardel", "987654", 7, "Cirujano",
							"Permanente", "Magister", 100));
		} catch (PosgradoInvalidoException | ContratacionInvalidaException | EspecialidadInvalidaException e) {
		}
		facturas.add(f1);
		this.condatos.getClinica().setLineasFacturas(facturas);
		assertTrue("Deberia contener prestaciones",
				this.condatos.getClinica().hayPrestacion(this.condatos.getPacienteEnPatio()));
	}

	@Test
	public void testNoHayPrestacion() {
		assertFalse("Deberia contener prestaciones",
				this.condatos.getClinica().hayPrestacion(this.condatos.getPacienteEnPatio()));
	}
}
