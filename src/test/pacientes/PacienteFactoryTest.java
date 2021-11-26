package test.pacientes;

import static org.junit.Assert.*;

import org.junit.Test;

import excepciones.TipoPacienteInvalidoException;
import pacientes.IPaciente;
import pacientes.PacienteFactory;

public class PacienteFactoryTest {

	@Test
	public void testPacienteNinoExistoso() {
		try {
			IPaciente nino = PacienteFactory.getPaciente("38443617", "Juan Perez", "155123456", "Mitre 1478",
					"Mar del Plata", 9, "Nino");
			assertNotNull("Deberia haberse instanciado un paciente Niño correctamente", nino);
		} catch (TipoPacienteInvalidoException e) {
			fail("No deberia lanzarse una excepcion al instanciar un paciente Niño valido");
		}
	}

	@Test
	public void testPacienteJovenExistoso() {
		try {
			IPaciente joven = PacienteFactory.getPaciente("38443617", "Juan Perez", "155123456", "Mitre 1478",
					"Mar del Plata", 9, "Joven");
			assertNotNull("Deberia haberse instanciado un paciente Joven correctamente", joven);
		} catch (TipoPacienteInvalidoException e) {
			fail("No deberia lanzarse una excepcion al instanciar un paciente Joven valido");
		}
	}

	@Test
	public void testPacienteMayorExistoso() {
		try {
			IPaciente mayor = PacienteFactory.getPaciente("38443617", "Juan Perez", "155123456", "Mitre 1478",
					"Mar del Plata", 9, "Mayor");
			assertNotNull("Deberia haberse instanciado un paciente Mayor correctamente", mayor);
		} catch (TipoPacienteInvalidoException e) {
			fail("No deberia lanzarse una excepcion al instanciar un paciente Mayor valido");
		}
	}

	@Test
	public void testRangoEtareoNulo() {
		try {
			IPaciente paciente = PacienteFactory.getPaciente("38443617", "Juan Perez", "155123456", "Mitre 1478",
					"Mar del Plata", 9, null);
			fail("No deberia lanzarse una excepcion al instanciar un paciente cuyo rango etareo es nulo");
		} catch (TipoPacienteInvalidoException e) {
			// Correcto
		}
	}

	@Test
	public void testRangoEtareoErroneo() {
		try {
			IPaciente paciente = PacienteFactory.getPaciente("38443617", "Juan Perez", "155123456", "Mitre 1478",
					"Mar del Plata", 9, "Anciano");
			fail("No deberia lanzarse una excepcion al instanciar un paciente cuyo rango etareo no es valido");
		} catch (TipoPacienteInvalidoException e) {
			// Correcto
		}
	}

}
