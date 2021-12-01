package test.pacientes;

import static org.junit.Assert.*;


import org.junit.Test;

import pacientes.Joven;
import pacientes.Mayor;
import pacientes.Niño;

public class MayorTest {

	@Test
	public void testConstructor() {
		Mayor mayor = new Mayor("38443617", "Juan Cruz Mateos", "155123456", "Calle Falsa 123", "Mar del Plata", 3);
		assertEquals("Error en constructor parametro dni", "38443617", mayor.getDNI());
		assertEquals("Error en constructor parametro nombre", "Juan Cruz Mateos", mayor.getNombre());
		assertEquals("Error en constructor parametro telefono", "155123456", mayor.getTelefono());
		assertEquals("Error en constructor parametro direccion", "Calle Falsa 123", mayor.getDomicilio());
		assertEquals("Error en constructor parametro ciudad", "Mar del Plata", mayor.getCiudad());
		assertEquals("Error en constructor parametro nroHistoria", 3, mayor.getNumHistoria());
	}

	@Test
	public void testBeatsNiño() {
		Mayor mayor = new Mayor("38443617", "Juan Cruz Mateos", "155123456", "Calle Falsa 123", "Mar del Plata", 3);
		Niño otro = new Niño("42123456", "Maria Perez", "155457812", "Paso 1478", "Mar del Plata", 28);
		assertTrue("Deberia quedar el mayor", mayor.beats(otro));
	}

	@Test
	public void testBeatsJoven() {
		Mayor mayor = new Mayor("38443617", "Juan Cruz Mateos", "155123456", "Calle Falsa 123", "Mar del Plata", 3);
		Joven otro = new Joven("42123456", "Maria Perez", "155457812", "Paso 1478", "Mar del Plata", 28);
		assertFalse("Deberia quedar el joven", mayor.beats(otro));
	}

	@Test
	public void testBeatsMayor() {
		Mayor mayor = new Mayor("38443617", "Juan Cruz Mateos", "155123456", "Calle Falsa 123", "Mar del Plata", 3);
		Mayor otro = new Mayor("42123456", "Maria Perez", "155457812", "Paso 1478", "Mar del Plata", 28);
		assertTrue("Deberia quedar el joven", mayor.beats(otro));
	}

}
