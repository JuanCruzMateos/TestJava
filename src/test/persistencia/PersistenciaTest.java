package test.persistencia;

import static org.junit.Assert.*;

import org.junit.Test;

import persistencia.ClinicaDTO;
import persistencia.PersistenciaBIN;

import org.junit.After;
import org.junit.Before;

import java.io.File;
import java.io.IOException;

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

	@Test
	public void persisteClincaConPacientesTest() {
		// TODO
	}
//		ClinicaDTO persistDTO = new ClinicaDTO();
//		try {
//			this.persistenciaBin.abrirOutput(this.fileName);
//			this.persistenciaBin.escribir(persistDTO);
//			this.persistenciaBin.cerrarOutput();
//
//			this.persistenciaBin.abrirInput(this.fileName);
//			ClinicaDTO desp = (ClinicaDTO) this.persistenciaBin.leer();
//			assertTrue("Las clinicas deberian ser iguales", desp.equals(persistDTO));
//		} catch (IOException e) {
//			fail("No deberia ocurrir ningun tipo de excepcion");
//		} catch (ClassNotFoundException e) {
//			fail("No deberia ocurrir ningun tipo de excepcion");
//		}
}
