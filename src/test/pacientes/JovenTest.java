package test.pacientes;

import static org.junit.Assert.*;

import org.junit.Test;

import pacientes.Joven;
import pacientes.Mayor;
import pacientes.Niño;

public class JovenTest {

	@Test
	public void constructorTest() {
		Joven joven = new Joven("38443617", "Juan Cruz Mateos", "155123456", "Calle Falsa 123", "Mar del Plata", 3);
		assertEquals("Error en constructor parametro dni", "38443617", joven.getDNI());
		assertEquals("Error en constructor parametro nombre", "Juan Cruz Mateos", joven.getNombre());
		assertEquals("Error en constructor parametro telefono", "155123456", joven.getTelefono());
		assertEquals("Error en constructor parametro direccion", "Calle Falsa 123", joven.getDomicilio());
		assertEquals("Error en constructor parametro ciudad", "Mar del Plata", joven.getCiudad());
		assertEquals("Error en constructor parametro nroHistoria", 3, joven.getNumHistoria());
	}

	@Test
	public void testBeatsNiño() {
		Joven joven = new Joven("38443617", "Juan Cruz Mateos", "155123456", "Calle Falsa 123", "Mar del Plata", 3);
		Niño otro = new Niño("42123456", "Maria Perez", "155457812", "Paso 1478", "Mar del Plata", 28);
		assertFalse("Deberia quedar el niño", joven.beats(otro));
	}

	@Test
	public void testBeatsJoven() {
		Joven joven = new Joven("38443617", "Juan Cruz Mateos", "155123456", "Calle Falsa 123", "Mar del Plata", 3);
		Joven otro = new Joven("42123456", "Maria Perez", "155457812", "Paso 1478", "Mar del Plata", 28);
		assertTrue("Deberia quedar el joven", joven.beats(otro));
	}

	@Test
	public void testBeatsMayor() {
		Joven joven = new Joven("38443617", "Juan Cruz Mateos", "155123456", "Calle Falsa 123", "Mar del Plata", 3);
		Mayor otro = new Mayor("42123456", "Maria Perez", "155457812", "Paso 1478", "Mar del Plata", 28);
		assertTrue("Deberia quedar el joven", joven.beats(otro));
	}
}
