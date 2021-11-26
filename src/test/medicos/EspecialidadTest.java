package test.medicos;

import org.junit.Assert;
import org.junit.Test;

import medicos.Cirujano;
import medicos.Clinico;
import medicos.IMedico;
import medicos.Pediatra;

public class EspecialidadTest {
	
	@Test
	public void testCreacionCirujano() {
		IMedico c = new Cirujano("Juan Perez", "123456","casa", "Mardel", "987654", 1, 100);
		String nombre = c.getNombre();
		String dni = c.getDNI();
		String domicilio = c.getDomicilio();
		String ciudad = c.getCiudad();
		String telefono = c.getTelefono();
		int nro = c.getNumMatricula();
		double honorario = c.getHonorarioBase();
		Assert.assertEquals("El nombre no es el designado", "Juan Perez", nombre);
		Assert.assertEquals("El dni no es el designado", "123456", dni);
		Assert.assertEquals("El domicilio no es el designado", "casa", domicilio);
		Assert.assertEquals("La ciudad no es la designada", "Mardel", ciudad);
		Assert.assertEquals("El telefono no es el designado", "987654", telefono);
		Assert.assertEquals("El sueldo no es el designado", 1, nro);
		Assert.assertEquals("El nombre no es el designado", 100 ,honorario, 0.01);
	}
	
	@Test
	public void testCreacionClinico() {
		IMedico c = new Clinico("Juan Perez", "123456","casa", "Mardel", "987654", 1, 100);
		String nombre = c.getNombre();
		String dni = c.getDNI();
		String domicilio = c.getDomicilio();
		String ciudad = c.getCiudad();
		String telefono = c.getTelefono();
		int nro = c.getNumMatricula();
		double honorario = c.getHonorarioBase();
		Assert.assertEquals("El nombre no es el designado", "Juan Perez", nombre);
		Assert.assertEquals("El dni no es el designado", "123456", dni);
		Assert.assertEquals("El domicilio no es el designado", "casa", domicilio);
		Assert.assertEquals("La ciudad no es la designada", "Mardel", ciudad);
		Assert.assertEquals("El telefono no es el designado", "987654", telefono);
		Assert.assertEquals("El sueldo no es el designado", 1, nro);
		Assert.assertEquals("El nombre no es el designado", 100 ,honorario, 0.01);
	}
	
	@Test
	public void testCreacionPediatra() {
		IMedico p = new Pediatra("Juan Perez", "123456","casa", "Mardel", "987654", 1, 100);
		String nombre = p.getNombre();
		String dni = p.getDNI();
		String domicilio = p.getDomicilio();
		String ciudad = p.getCiudad();
		String telefono = p.getTelefono();
		int nro = p.getNumMatricula();
		double honorario = p.getHonorarioBase();
		Assert.assertEquals("El nombre no es el designado", "Juan Perez", nombre);
		Assert.assertEquals("El dni no es el designado", "123456", dni);
		Assert.assertEquals("El domicilio no es el designado", "casa", domicilio);
		Assert.assertEquals("La ciudad no es la designada", "Mardel", ciudad);
		Assert.assertEquals("El telefono no es el designado", "987654", telefono);
		Assert.assertEquals("El sueldo no es el designado", 1, nro);
		Assert.assertEquals("El nombre no es el designado", 100 ,honorario, 0.01);
	}
	
	@Test
	public void testSueldoPediatra() {
		Pediatra p = new Pediatra("Juan Perez", "123456","casa", "Mardel", "987654", 1, 100);
		double sueldo = p.calculaHonorario();
		double esperado = 100 * 1.07;
		Assert.assertEquals("El sueldo calculado no es correcto", esperado, sueldo, 0.01);
	}
	
	@Test
	public void testSueldoClinico() {
		Clinico c = new Clinico("Juan Perez", "123456","casa", "Mardel", "987654", 1, 100);
		double sueldo = c.calculaHonorario();
		double esperado = 100 * 1.05;
		Assert.assertEquals("El sueldo calculado no es correcto", esperado, sueldo, 0.01);
	}
	
	@Test
	public void testSueldoCirujano() {
		Cirujano c = new Cirujano("Juan Perez", "123456","casa", "Mardel", "987654", 1, 100);
		double sueldo = c.calculaHonorario();
		double esperado = 100 * 1.1;
		Assert.assertEquals("El sueldo calculado no es correcto", esperado, sueldo, 0.01);
	}

	
}
