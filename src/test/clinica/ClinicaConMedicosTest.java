package test.clinica;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excepciones.ContratacionInvalidaException;
import excepciones.EspecialidadInvalidaException;
import excepciones.MedicoRepetidoException;
import excepciones.PosgradoInvalidoException;
import medicos.IMedico;
import medicos.MedicoFactory;
import modulo.Clinica;
import test.escenarios.ClinicaConMedicos;

public class ClinicaConMedicosTest {

	@Before
	public void setUp() {
		ClinicaConMedicos.setUp();
	}

	@Test
	public void testAgregarMedicoExistente() {
		try {
			Clinica.getInstance().agregaMedico(ClinicaConMedicos.m);
			Assert.fail("Deberia marcar que el medico ya existe");
		} catch (MedicoRepetidoException e) {
		}
	}

	@Test
	public void testAgregarMedicoNuevo() {
		try {
			IMedico m = MedicoFactory.getMedico("Juan Perez", "1234567", "casa", "Mardel", "987654", 7, "Cirujano",
					"Permanente", "Magister", 100);
			Clinica.getInstance().agregaMedico(m);
			Assert.assertTrue("El medico no fue agregado normalmente", Clinica.getInstance().getMedicos().contains(m));
		} catch (MedicoRepetidoException e) {
			Assert.fail("No deberia lanzarse la excepcion de medico repetido");
		} catch (PosgradoInvalidoException e) {
			Assert.fail("No deberia lanzarse la excepcion postgrado invalido");
		} catch (ContratacionInvalidaException e) {
			Assert.fail("No deberia lanzarse la excepcion contratacion invalida");
		} catch (EspecialidadInvalidaException e) {
			Assert.fail("No deberia lanzarse la excepcion especialidad invalida");
		}
	}

	@Test
	public void testEliminaMedico() {
		int longitud = Clinica.getInstance().getMedicos().size();
		Clinica.getInstance().eliminaMedico(ClinicaConMedicos.m);
		Assert.assertEquals("El medico deberia haber sido eliminado", longitud - 1, Clinica.getInstance().getMedicos().size());
		Assert.assertFalse("El medico deberia haber sido eliminado", Clinica.getInstance().getMedicos().contains(ClinicaConMedicos.m));
	}

}
