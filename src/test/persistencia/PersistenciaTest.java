package test.persistencia;

import static org.junit.Assert.*;

import org.junit.Test;

import excepciones.ContratacionInvalidaException;
import excepciones.EspecialidadInvalidaException;
import excepciones.PosgradoInvalidoException;
import habitaciones.IHabitacion;
import medicos.IMedico;
import medicos.MedicoFactory;
import modulo.Clinica;
import pacientes.IPaciente;
import pacientes.LineaFactura;
import pacientes.PacienteFactory;
import persistencia.ClinicaDTO;
import persistencia.PersistenciaBIN;
import persistencia.UtilClinica;

import org.junit.After;
import org.junit.Before;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PersistenciaTest {
	private final String fileName = "testClinica.bin";
	private File file;
	private PersistenciaBIN persistenciaBin;

	@Before
	public void setUp() {
		this.persistenciaBin = new PersistenciaBIN();
		this.file = new File(this.fileName);
		if (this.file.exists())
			this.file.delete();
	}

	@After
	public void tearDown() {
		this.file = null;
	}

	@Test
	public void lanzaExcpecionAlIntentarLeerArchivoInexistenteTest() {
		try {
			this.persistenciaBin.abrirInput(this.fileName);
			fail("Deberia lanzarse una excepcion de tipo IOExcepction al ");
		} catch (IOException e) {
			// Correcto
		}
	}

	@Test
	public void crearArchivoConExitoTest() {
		try {
			this.persistenciaBin.abrirOutput(this.fileName);
			this.persistenciaBin.cerrarOutput();
			assertTrue("Deberia haberse creado el archivo " + this.fileName, file.exists());
		} catch (IOException e) {
			fail("Deberia generarse el archivo");
		}
	}

	@Test
	public void persisteClincaConPacientesTest() {
		Clinica.getInstance().setNroOrden(1);
		Clinica.getInstance().setTurno(1);
		Clinica.getInstance().setNroFactura(1);
		Clinica.getInstance().setMedicos(new ArrayList<IMedico>());
		Clinica.getInstance().setListaDeEspera(new ArrayList<IPaciente>());
		Clinica.getInstance().setListaDeAtencion(new ArrayList<IPaciente>());
		Clinica.getInstance().setHabitaciones(new ArrayList<IHabitacion>());
		Clinica.getInstance().setPatio(new ArrayList<IPaciente>());
		Clinica.getInstance().setSalaDeEsperaPrivada(null);
		Clinica.getInstance().sethPacientes(new ArrayList<IPaciente>());
		Clinica.getInstance().setLineasFacturas(new ArrayList<LineaFactura>());
		Clinica.getInstance().setLineasReporte(new ArrayList<LineaFactura>());

		try {
			IPaciente p1 = PacienteFactory.getPaciente("11111111", "Carlos Perez", "155111111", "Mitre 1234",
					"Mar del Plata", 1, "Mayor");
			IPaciente p2 = PacienteFactory.getPaciente("22222222", "Maria Perez", "155222222", "Paso 1474",
					"Mar del Plata", 2, "Nino");
			IPaciente p3 = PacienteFactory.getPaciente("33333333", "Marcos Lopez", "155333333", "Falucho 2334",
					"Mar del Plata", 3, "Joven");
			IPaciente p4 = PacienteFactory.getPaciente("44444444", "Lucia Sanchez", "155444444", "Gascon 7834",
					"Mar del Plata", 4, "Joven");
			IPaciente p5 = PacienteFactory.getPaciente("55555555", "Camila Diaz", "155555555", "Luro 1527",
					"Mar del Plata", 5, "Mayor");
			IPaciente p6 = PacienteFactory.getPaciente("66666666", "Lucas Rodriguez", "155666666", "Colon 1472",
					"Mar del Plata", 6, "Nino");
			IPaciente p7 = PacienteFactory.getPaciente("77777777", "Juan Martinez", "155777777", "Alsina 63",
					"Mar del Plata", 7, "Mayor");

			Clinica.getInstance().ingresoPaciente(p1);
			Clinica.getInstance().ingresoPaciente(p2);
			Clinica.getInstance().ingresoPaciente(p3);
			Clinica.getInstance().ingresoPaciente(p4);
			Clinica.getInstance().ingresoPaciente(p5);
			Clinica.getInstance().ingresoPaciente(p6);
			Clinica.getInstance().ingresoPaciente(p7);

			p1 = Clinica.getInstance().llamarPaciente();
			p2 = Clinica.getInstance().llamarPaciente();
			p3 = Clinica.getInstance().llamarPaciente();
		} catch (Exception e) {
			//
		}

		try {
			ArrayList<IMedico> medicos = new ArrayList<>();
			medicos.add(MedicoFactory.getMedico("Juan Perez", "1", "casa", "Mardel", "987654", 1, "Cirujano",
					"Permanente", "Magister", 100));
			medicos.add(MedicoFactory.getMedico("Juan Perez", "12", "casa", "Mardel", "987654", 2, "Pediatra",
					"Residente", "Magister", 100));
			medicos.add(MedicoFactory.getMedico("Juan Perez", "123", "casa", "Mardel", "987654", 3, "Clinico",
					"Residente", "Magister", 100));
			medicos.add(MedicoFactory.getMedico("Juan Perez", "1234", "casa", "Mardel", "987654", 4, "Cirujano",
					"Permanente", "Doctorado", 100));
			Clinica.getInstance().setMedicos(medicos);
		} catch (PosgradoInvalidoException | ContratacionInvalidaException | EspecialidadInvalidaException e) {
			//
		}

		try {
			ClinicaDTO anterior = UtilClinica.ClinicaDTOfromClinica();
			this.persistenciaBin.abrirOutput(this.fileName);
			this.persistenciaBin.escribir(anterior);
			this.persistenciaBin.cerrarOutput();
			this.persistenciaBin.abrirInput(this.fileName);
			ClinicaDTO desp = (ClinicaDTO) this.persistenciaBin.leer();
			assertTrue("Las clinicas deberian ser iguales", desp.equals(anterior));
		} catch (IOException e) {
			fail("No deberia ocurrir ningun tipo de excepcion");
		} catch (ClassNotFoundException e) {
			fail("No deberia ocurrir ningun tipo de excepcion");
		}
	}

	@Test
	public void persisteClincaVacia() {
		ClinicaDTO persistDTO = new ClinicaDTO();
		try {
			this.persistenciaBin.abrirOutput(this.fileName);
			this.persistenciaBin.escribir(persistDTO);
			this.persistenciaBin.cerrarOutput();

			this.persistenciaBin.abrirInput(this.fileName);
			ClinicaDTO desp = (ClinicaDTO) this.persistenciaBin.leer();
			assertTrue("Las clinicas deberian ser iguales", desp.equals(persistDTO));
		} catch (IOException e) {
			fail("No deberia ocurrir ningun tipo de excepcion");
		} catch (ClassNotFoundException e) {
			fail("No deberia ocurrir ningun tipo de excepcion");
		}
	}

}
