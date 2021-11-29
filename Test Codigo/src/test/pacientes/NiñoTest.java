package test.pacientes;

import org.junit.Test;
import pacientes.Joven;
import pacientes.Mayor;
import pacientes.Ni�o;

import static org.junit.Assert.*;

public class Ni�oTest {
	@Test
	public void constructorGettersTest() {
		Ni�o nino = new Ni�o("38443617", "Juan Cruz Mateos", "155937802", "Almafuerte 1016", "Mar del Plata", 1);
		assertEquals("Error en constructor parametro dni", "38443617", nino.getDNI());
		assertEquals("Error en constructor parametro nombre", "Juan Cruz Mateos", nino.getNombre());
		assertEquals("Error en constructor parametro telefono", "155937802", nino.getTelefono());
		assertEquals("Error en constructor parametro domicilio", "Almafuerte 1016", nino.getDomicilio());
		assertEquals("Error en constructor parametro ciudad", "Mar del Plata", nino.getCiudad());
		assertEquals("Error en constructor parametro numHistoria", 1, nino.getNumHistoria());
	}

	@Test
	public void setGetCiudadTest() {
		Ni�o nino = new Ni�o("38443617", "Juan Cruz Mateos", "155937802", "Almafuerte 1016", "Mar del Plata", 1);
		nino.setCiudad("Buenos Aires");
		assertEquals("Error en setter/getter de ciudad", "Buenos Aires", nino.getCiudad());
	}

	@Test
	public void setGetDomicilioTest() {
		Ni�o nino = new Ni�o("38443617", "Juan Cruz Mateos", "155937802", "Almafuerte 1016", "Mar del Plata", 1);
		nino.setDomicilio("Falucho 1234");
		assertEquals("Error en setter/getter de ciudad", "Falucho 1234", nino.getDomicilio());
	}

	@Test
	public void setGetTelefonoTest() {
		Ni�o nino = new Ni�o("38443617", "Juan Cruz Mateos", "155937802", "Almafuerte 1016", "Mar del Plata", 1);
		nino.setTelefono("4521478");
		assertEquals("Error en setter/getter de ciudad", "4521478", nino.getTelefono());
	}

	@Test
	public void beatsNino() {
		Ni�o nino = new Ni�o("38443617", "Juan Cruz Mateos", "155937802", "Almafuerte 1016", "Mar del Plata", 1);
		Ni�o otro = new Ni�o("40443617", "Carlos perez", "4152369", "Quintana 1016", "Mar del Plata", 8);
		assertTrue("Deberia quedar nuevo nino", nino.beats(otro));
	}

	@Test
	public void beatsJoven() {
		Ni�o nino = new Ni�o("38443617", "Juan Cruz Mateos", "155937802", "Almafuerte 1016", "Mar del Plata", 1);
		Joven otro = new Joven("40443617", "Carlos perez", "4152369", "Quintana 1016", "Mar del Plata", 8);
		assertTrue("Deberia quedar nuevo nino", nino.beats(otro));
	}

	@Test
	public void beatsMayor() {
		Ni�o nino = new Ni�o("38443617", "Juan Cruz Mateos", "155937802", "Almafuerte 1016", "Mar del Plata", 1);
		Mayor otro = new Mayor("40443617", "Carlos perez", "4152369", "Quintana 1016", "Mar del Plata", 8);
		assertFalse("Deberia quedar nuevo nino", nino.beats(otro));
	}
}