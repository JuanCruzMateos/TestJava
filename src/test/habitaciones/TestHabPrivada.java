package test.habitaciones;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import habitaciones.HabPrivada;
import pacientes.IPaciente;
import pacientes.Joven;
import pacientes.Mayor;

public class TestHabPrivada {
	double costoHabitacion;
	HabPrivada habitacion;
	IPaciente paciente;
	EscenarioHabitacionConPaciente escenarioConPaciente;
	EscenarioHabitacionSinPaciente escenarioSinPaciente;

	@Before
	public void setUp() throws Exception {
		costoHabitacion = 100;
		habitacion = new HabPrivada(costoHabitacion);
		paciente = new Mayor("23", "Hernan H", "223100", "Paso 12", "Mar del Plata", 1);
		escenarioConPaciente = new EscenarioHabitacionConPaciente(habitacion, paciente);
		escenarioSinPaciente = new EscenarioHabitacionSinPaciente(habitacion);
	}

	@Test
	public void testCalcularValorInternacion1() {
		int dias = 1;
		double costo = habitacion.calcularValorInternacion(dias);
		Assert.assertEquals("El costo de la habitacion debe ser " + costoHabitacion, costoHabitacion, costo, 0);
	}

	@Test
	public void testCalcularValorInternacion2() {
		int dias = 2;
		double costo = habitacion.calcularValorInternacion(dias);
		Assert.assertEquals("El costo de la habitacion debe ser " + costoHabitacion * 1.3 * dias,
				dias * 1.3 * costoHabitacion, costo, 0);
	}

	@Test
	public void testCalcularValorInternacion3() {
		int dias = 6;
		double costo = habitacion.calcularValorInternacion(dias);
		Assert.assertEquals("El costo de la habitacion debe ser " + costoHabitacion * 2 * dias,
				dias * 2 * costoHabitacion, costo, 0);
	}

	@Test
	public void testAgregarPacienteExitoso() {
		Joven j1 = new Joven("123456789", "Carlos Uno", "5111234", "Aire 1200", "Buenos Aires", 2);
		escenarioSinPaciente.getHabitacion().agregaPaciente(j1);
		Assert.assertEquals("El paciente debe haber ingresado a la habitacion", habitacion, j1.getHabitacion());
	}

	@Test
	public void testAgregarPacienteErroneo() {
		Joven j1 = new Joven("123456789", "Carlos Uno", "5111234", "Aire 1200", "Buenos Aires", 2);
		escenarioConPaciente.getHabitacion().agregaPaciente(j1);
		Assert.assertNotEquals("Solo puede haber un paciente en la habitacion privada", habitacion, j1.getHabitacion());
	}

	@Test
	public void testEliminarPacienteExitoso() {
		escenarioConPaciente.getHabitacion().eliminaPaciente(paciente);
		Assert.assertEquals("Se debe haber eliminado el paciente de la habitacion privada", null,
				paciente.getHabitacion());
	}

	@Test
	public void testEliminarPacienteErroneo() {
		escenarioSinPaciente.getHabitacion().eliminaPaciente(paciente);
		Assert.assertEquals("No se elimina ningun paciente si la habitacion privada esta vacia", null,
				paciente.getHabitacion());
	}

}
