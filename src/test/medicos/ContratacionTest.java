package test.medicos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import medicos.Cirujano;
import medicos.Doctorado;
import medicos.IMedico;
import medicos.Magister;
import medicos.PlantelPermanente;
import medicos.Residente;

public class ContratacionTest {
	
	IMedico c;

	@Before
	public void setUp() {
		c = new Cirujano("Juan Perez", "123456","casa", "Mardel", "987654", 1, 100);
		c = new Doctorado(c);
	}

	@Test
	public void testCreacionPermanente() {
		IMedico m = new PlantelPermanente(c);
		String nombre = m.getNombre();
		String dni = m.getDNI();
		String domicilio = m.getDomicilio();
		String ciudad = m.getCiudad();
		String telefono = m.getTelefono();
		int nro = m.getNumMatricula();
		double honorario = m.getHonorarioBase();
		Assert.assertEquals("El nombre no es el designado", "Juan Perez", nombre);
		Assert.assertEquals("El dni no es el designado", "123456", dni);
		Assert.assertEquals("El domicilio no es el designado", "casa", domicilio);
		Assert.assertEquals("La ciudad no es la designada", "Mardel", ciudad);
		Assert.assertEquals("El telefono no es el designado", "987654", telefono);
		Assert.assertEquals("El sueldo no es el designado", 1, nro);
		Assert.assertEquals("El nombre no es el designado", 100 ,honorario, 0.01);
	}
	
	@Test
	public void testCreacionResidente() {
		IMedico m = new Residente(c);
		String nombre = m.getNombre();
		String dni = m.getDNI();
		String domicilio = m.getDomicilio();
		String ciudad = m.getCiudad();
		String telefono = m.getTelefono();
		int nro = m.getNumMatricula();
		double honorario = m.getHonorarioBase();
		Assert.assertEquals("El nombre no es el designado", "Juan Perez", nombre);
		Assert.assertEquals("El dni no es el designado", "123456", dni);
		Assert.assertEquals("El domicilio no es el designado", "casa", domicilio);
		Assert.assertEquals("La ciudad no es la designada", "Mardel", ciudad);
		Assert.assertEquals("El telefono no es el designado", "987654", telefono);
		Assert.assertEquals("El sueldo no es el designado", 1, nro);
		Assert.assertEquals("El nombre no es el designado", 100 ,honorario, 0.01);
	}

	@Test
	public void testCalculoHonorarioPermanente() {
		IMedico m = new PlantelPermanente(c);
		double sueldo = m.calculaHonorario();
		Assert.assertEquals("El honorario calculado no es el esperado", sueldo, 100*1.1*1.1*1.1, 0.01);
	}
	
	@Test
	public void testCalculoHonorarioResidente() {
		IMedico m = new Residente(c);
		double sueldo = m.calculaHonorario();
		Assert.assertEquals("El honorario calculado no es el esperado", sueldo, 100*1.1*1.1*1.05, 0.01);
	}
}
