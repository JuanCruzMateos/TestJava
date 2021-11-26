package test.testMedico;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excepciones.ContratacionInvalidaException;
import excepciones.EspecialidadInvalidaException;
import excepciones.PosgradoInvalidoException;
import medicos.IMedico;
import medicos.MedicoFactory;

public class TestFactory {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreacionSatisfactoria() {
		IMedico m = null;
		try {
			m = MedicoFactory.getMedico("Juan Perez", "123456", "casa", "Mardel", "987654", 1, "Cirujano", "Permanente",
					"Doctorado", 100);
		} catch (PosgradoInvalidoException e) {
			Assert.fail("El postgrado deberia ser valido");
		} catch (ContratacionInvalidaException e) {
			Assert.fail("La contrataciondeberia ser valida");
		} catch (EspecialidadInvalidaException e) {
			Assert.fail("La especialidad ser valida");
		}
	}

	@Test
	public void testEspecialidadIncorrecta() {
		IMedico m = null;
		try {
			m = MedicoFactory.getMedico("Juan Perez", "123456", "casa", "Mardel", "987654", 1, "Carnicero",
					"Permanente", "Doctorado", 100);
			Assert.fail("El medico no deberia ser creado");
		} catch (PosgradoInvalidoException e) {
			Assert.fail("Deberia lanzarse la excepcion de la especialidad");
		} catch (ContratacionInvalidaException e) {
			Assert.fail("Deberia lanzarse la excepcion de la especialidad");
		} catch (EspecialidadInvalidaException e) {

		}
	}

	@Test
	public void testContratacionIncorrecta() {
		IMedico m = null;
		try {
			m = MedicoFactory.getMedico("Juan Perez", "123456", "casa", "Mardel", "987654", 1, "Cirujano",
					"Turno noche", "Doctorado", 100);
			Assert.fail("El medico no deberia ser creado");
		} catch (PosgradoInvalidoException e) {
			Assert.fail("Deberia lanzarse la excepcion de la contratacion");
		} catch (ContratacionInvalidaException e) {
		} catch (EspecialidadInvalidaException e) {
			Assert.fail("Deberia lanzarse la excepcion de la contratacion");
		}
	}

	@Test
	public void testGradoIncorrecto() {
		IMedico m = null;
		try {
			m = MedicoFactory.getMedico("Juan Perez", "123456", "casa", "Mardel", "987654", 1, "Cirujano",
					"Permanente", "Estudiante", 100);
			Assert.fail("El medico no deberia ser creado");
		} catch (PosgradoInvalidoException e) {

		} catch (ContratacionInvalidaException e) {
			Assert.fail("Deberia lanzarse la excepcion del grado");
		} catch (EspecialidadInvalidaException e) {
			Assert.fail("Deberia lanzarse la excepcion del grado");
		}
	}

}
