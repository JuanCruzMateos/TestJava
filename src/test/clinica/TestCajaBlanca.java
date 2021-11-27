package test.clinica;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modulo.Clinica;
import pacientes.IPaciente;
import pacientes.Joven;
import pacientes.Mayor;

public class TestCajaBlanca {

	private Clinica clinica = Clinica.getInstance();
	ArrayList<Double> insumos = new ArrayList<Double>();
	IPaciente pacienteMayor = new Mayor("123456789", "Raul Tercero", "480111222", "Paso 1234", "Mar del Plata", 10);
	IPaciente pacienteJoven = new Joven("987654321", "Mateo Cuarto", "480333444", "Paso 5678", "Mar del Plata", 11);

	// A = 0.8, B = 0.4, C = 1.5, D = 0.9;
	@Before
	public void setUp() {
		insumos.add(80.5);
		insumos.add(19.5);
	}

	@After
	public void tearDown() {

	}

	@SuppressWarnings("deprecation")
	@Test
	public void testCamino1() {
		Assert.assertEquals("El importe adicional debe ser 0.", 0,
				clinica.getInstance().calculoImporteAdicionales(clinica.getNroFactura() + 100, new GregorianCalendar(),
						insumos, new GregorianCalendar(), 2000, 800, pacienteMayor), 0.01);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testCamino2() {
		double resultadoEsperado = (1000 - 800 * 0.8) * 1.5;
		Assert.assertEquals("El importe adicional debe ser " + resultadoEsperado, resultadoEsperado,
				clinica.getInstance().calculoImporteAdicionales(clinica.getNroFactura(),
						new GregorianCalendar(2021, 12, 10), insumos, new GregorianCalendar(2021, 12, 7), 1000, 800,
						pacienteMayor), 0.01);

	}

	@SuppressWarnings("deprecation")
	@Test
	public void testCamino3() {
		double resultadoEsperado = 1000 * 0.4 * 1.5 ;
		Assert.assertEquals("El importe adicional debe ser " + resultadoEsperado, resultadoEsperado,
				clinica.getInstance().calculoImporteAdicionales(clinica.getNroFactura(),
						new GregorianCalendar(2021, 12, 31), insumos, new GregorianCalendar(2021, 12, 1), 1000, 800,
						pacienteMayor), 0.01);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testCamino4() {
		double resultadoEsperado = 1000 * 0.4 * 0.9;
		Assert.assertEquals("El importe adicional debe ser " + resultadoEsperado, resultadoEsperado,
				clinica.getInstance().calculoImporteAdicionales(clinica.getNroFactura(),
						new GregorianCalendar(2021, 12, 31), insumos, new GregorianCalendar(2021, 12, 1), 1000, 800,
						pacienteJoven), 0.01);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testCamino5() {
		double resultadoEsperado = 2000 * 0.4 * 0.9 + 100;
		Assert.assertEquals("El importe adicional debe ser " + resultadoEsperado, resultadoEsperado,
				clinica.getInstance().calculoImporteAdicionales(clinica.getNroFactura(),
						new GregorianCalendar(2021, 12, 31), insumos, new GregorianCalendar(2021, 12, 1), 2000, 800,
						pacienteJoven), 0.01);
	}

}
