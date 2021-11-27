package test.clinica;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excepciones.NoFueLlamadoException;
import excepciones.NoHayEspacioException;
import excepciones.TipoPacienteInvalidoException;
import habitaciones.IHabitacion;
import habitaciones.SalaTerapiaIntensiva;
import modulo.Clinica;
import pacientes.IPaciente;
import pacientes.LineaFactura;
import pacientes.PacienteFactory;
import test.escenarios.ClinicaPacMed;

public class PrestacionesTest {

	@Before
	public void setUp() throws Exception {
		ClinicaPacMed.setUp();
	}

	@Test
	public void testAgregarPrestacionesEnAtencion() {
		IPaciente p = ClinicaPacMed.pL;
		try {
			LineaFactura f = new LineaFactura(ClinicaPacMed.h, 1, p, ClinicaPacMed.m);
			Clinica.getInstance().Prestaciones(p, ClinicaPacMed.m, 1, ClinicaPacMed.h);
			Assert.assertFalse(p.isFacturo());
			Assert.assertTrue("Deberia estar el reporte", Clinica.getInstance().getLineasReporte().contains(f));
			Assert.assertTrue("Deberia estar la factura", Clinica.getInstance().getLineasFacturas().contains(f));
		} catch (NoHayEspacioException e) {
			Assert.fail("La habitacion no deberia estar llena");
		} catch (NoFueLlamadoException e) {
			Assert.fail("El paciente si fue llamado");
		}
	}

	@Test
	public void testAgregarPrestacionesSinAtencion() {
		IPaciente p = ClinicaPacMed.pNL;
		try {
			Clinica.getInstance().Prestaciones(p, ClinicaPacMed.m, 1, ClinicaPacMed.h);
			Assert.fail("Deberia decir que no fue llamado");
		} catch (NoHayEspacioException e) {
			Assert.fail("La habitacion no deberia estar llena");
		} catch (NoFueLlamadoException e) {
		}
	}

	@Test
	public void testHabitacionLlena() {
		IPaciente p = ClinicaPacMed.pL;
		IHabitacion h = new SalaTerapiaIntensiva(100);
		IPaciente p1 = null, p2 = null, p3 = null, p4 = null, p5 = null, p6 = null, p7 = null;
		ArrayList<IPaciente> pacientes = new ArrayList<>();
		try {
			p1 = PacienteFactory.getPaciente("11111111", "Carlos Perez", "155111111", "Mitre 1234", "Mar del Plata", 1,
					"Mayor");
			p2 = PacienteFactory.getPaciente("22222222", "Maria Perez", "155222222", "Paso 1474", "Mar del Plata", 2,
					"Nino");
			p3 = PacienteFactory.getPaciente("33333333", "Marcos Lopez", "155333333", "Falucho 2334", "Mar del Plata",
					3, "Joven");
			p4 = PacienteFactory.getPaciente("44444444", "Lucia Sanchez", "155444444", "Gascon 7834", "Mar del Plata",
					4, "Joven");
			p5 = PacienteFactory.getPaciente("55555555", "Camila Diaz", "155555555", "Luro 1527", "Mar del Plata", 5,
					"Mayor");
			p6 = PacienteFactory.getPaciente("66666666", "Lucas Rodriguez", "155666666", "Colon 1472", "Mar del Plata",
					6, "Nino");
			p7 = PacienteFactory.getPaciente("77777777", "Juan Martinez", "155777777", "Alsina 63", "Mar del Plata", 7,
					"Mayor");
		} catch (TipoPacienteInvalidoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		pacientes.add(p1);
		pacientes.add(p2);
		pacientes.add(p3);
		pacientes.add(p4);
		pacientes.add(p5);
		pacientes.add(p6);
		pacientes.add(p7);

		Clinica.getInstance().setListaDeAtencion(pacientes);
		try {
			Clinica.getInstance().Prestaciones(p1, ClinicaPacMed.m, 1, h);
			Clinica.getInstance().Prestaciones(p2, ClinicaPacMed.m, 1, h);
			Clinica.getInstance().Prestaciones(p3, ClinicaPacMed.m, 1, h);
			Clinica.getInstance().Prestaciones(p4, ClinicaPacMed.m, 1, h);
			Clinica.getInstance().Prestaciones(p5, ClinicaPacMed.m, 1, h);
			Clinica.getInstance().Prestaciones(p6, ClinicaPacMed.m, 1, h);
			Clinica.getInstance().Prestaciones(p7, ClinicaPacMed.m, 1, h);
		} catch (NoHayEspacioException e) {
			Assert.fail("La habitacion no deberia estar llena");
		} catch (NoFueLlamadoException e) {
			Assert.fail("El paciente si fue llamado");
		}

		try {
			Clinica.getInstance().Prestaciones(p, ClinicaPacMed.m, 1, h);
			Assert.fail("La habitacion deberia estar llena");
		} catch (NoHayEspacioException e) {
		} catch (NoFueLlamadoException e) {
			Assert.fail("El paciente si fue llamado");
		}
	}
	
	@Test
	public void eliminaPrestaciones() {
		IPaciente p1 = ClinicaPacMed.pL;
		IPaciente p2 = null;
		ArrayList<IPaciente> pacientes = new ArrayList<>();
		try {
			p2 = PacienteFactory.getPaciente("999", "Juanse", "0", "A", "Mardel", 2, "Joven");
		} catch (TipoPacienteInvalidoException e1) {
		}
		pacientes.add(p1);
		pacientes.add(p2);
		p1.setFacturo(false);
		p2.setFacturo(false);
		Clinica.getInstance().setListaDeAtencion(pacientes);
		LineaFactura f1 = new LineaFactura(ClinicaPacMed.h, 1, p1, ClinicaPacMed.m);
		LineaFactura f2 = new LineaFactura(ClinicaPacMed.h, 2, p2, ClinicaPacMed.m);
		ArrayList<LineaFactura> fs = new ArrayList<>();
		fs.add(f1);
		fs.add(f2);
		Clinica.getInstance().setLineasFacturas(fs);

		Clinica.getInstance().eliminaListaAtencion(p2);
		fs = Clinica.getInstance().getLineasFacturas();
		p2.setFacturo(true);
		Clinica.getInstance().eliminaLineasPaciente(p2);

		fs = Clinica.getInstance().getLineasFacturas();
		
		Iterator<LineaFactura> it = Clinica.getInstance().getLineasFacturas().iterator();

		while (it.hasNext()) {
			if (it.next().getPaciente().equals(p2))
				Assert.fail("No deberia haber facturas de un paciente eliminado");
		}
	}
	
	@Test
	public void testCuentaOcurrencia() {
		IPaciente p1 = ClinicaPacMed.pL;
		IPaciente p2 = null;
		ArrayList<IPaciente> pacientes = new ArrayList<>();
		pacientes.add(p1);
		p1.setFacturo(false);
		Clinica.getInstance().setListaDeAtencion(pacientes);
		LineaFactura f1 = new LineaFactura(ClinicaPacMed.h, 1, p1, ClinicaPacMed.m);
		LineaFactura f2 = new LineaFactura(ClinicaPacMed.h, 2, p1, ClinicaPacMed.m);
		ArrayList<LineaFactura> fs = new ArrayList<>();
		fs.add(f1);
		fs.add(f2);
		Clinica.getInstance().setLineasFacturas(fs);
		int cant = Clinica.getInstance().retornaOcurrencias(ClinicaPacMed.pL, ClinicaPacMed.m);
		Assert.assertEquals("La cantida de ocurrencias no se calculan correctamente", cant, 2);
	}
}
