package test.habitaciones;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import habitaciones.SalaTerapiaIntensiva;
import pacientes.Joven;

public class TestSalaTerapiaIntensiva {
	double costoHabitacion;
	EscenarioHabitacionSinPaciente escenarioSinPaciente = new EscenarioHabitacionSinPaciente();
	EscenarioHabitacionLlena escenarioLleno = new EscenarioHabitacionLlena();

	@Before
	public void setUp() throws Exception {
		costoHabitacion = 10;
		escenarioSinPaciente.setUp(new SalaTerapiaIntensiva(costoHabitacion));
		escenarioLleno.setUp(new SalaTerapiaIntensiva(costoHabitacion));
	}

	@Test
	public void testCalcularValorInternacion() {
		int dias = 4;
		double costo = escenarioSinPaciente.getHabitacion().calcularValorInternacion(dias);
		Assert.assertEquals("El costo de la habitacion debe ser " + Math.pow(this.costoHabitacion, dias),
				Math.pow(this.costoHabitacion, dias), costo, 0);
	}

	@Test
	public void testAgregarPacienteExitoso() {
		Joven j1 = new Joven("123456789", "Carlos Uno", "5111234", "Aire 1200", "Buenos Aires", 2);
		escenarioSinPaciente.getHabitacion().agregaPaciente(j1);
		Assert.assertEquals("El paciente debe haber ingresado a la habitacion", escenarioSinPaciente.getHabitacion(), j1.getHabitacion());
	}

	@Test
	public void testAgregarPacienteErroneo() {
		Joven j1 = new Joven("123456789", "Carlos Uno", "5111234", "Aire 1200", "Buenos Aires", 2);
		escenarioLleno.getHabitacion().agregaPaciente(j1);
		Assert.assertNotEquals("Solo puede haber un paciente en la habitacion TI", escenarioLleno.getHabitacion(), j1.getHabitacion());
	}

	@Test
	public void testEliminarPacienteExitoso() {
		escenarioLleno.getHabitacion().eliminaPaciente(escenarioLleno.getPaciente());
		Assert.assertNull("Se debe haber eliminado el paciente de la habitacion TI",
				escenarioLleno.getPaciente().getHabitacion());
	}

	@Test
	public void testEliminarPacienteErroneo() {
		escenarioSinPaciente.getHabitacion().eliminaPaciente(escenarioLleno.getPaciente());
		Assert.assertNotNull("No se elimina ningun paciente si la habitacion TI esta vacia",
				escenarioLleno.getPaciente());
	}

}