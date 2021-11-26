package test.habitaciones;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import habitaciones.HabCompartida;
import habitaciones.SalaTerapiaIntensiva;
import pacientes.IPaciente;
import pacientes.Joven;

public class TestSalaTerapiaIntensiva {

	double costoHabitacion;
	HabCompartida habitacion;
	IPaciente paciente;
	EscenarioHabitacionSinPaciente escenarioSinPaciente;
	EscenarioHabitacionLlena escenarioLleno;

	@Before
	public void setUp() throws Exception {
		costoHabitacion = 10;
		habitacion = new SalaTerapiaIntensiva(costoHabitacion);
		escenarioSinPaciente = new EscenarioHabitacionSinPaciente(habitacion);
		escenarioLleno = new EscenarioHabitacionLlena(habitacion);
	}

	@Test
	public void testCalcularValorInternacion() {
		int dias = 4;
		double costo = habitacion.calcularValorInternacion(dias);
		Assert.assertEquals("El costo de la habitacion debe ser " + Math.pow(this.costoHabitacion, dias),
				Math.pow(this.costoHabitacion, dias), costo, 0);
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
		escenarioLleno.getHabitacion().agregaPaciente(j1);
		Assert.assertNotEquals("Solo puede haber un paciente en la habitacion privada", habitacion, j1.getHabitacion());
	}

	@Test
	public void testEliminarPacienteExitoso() {
		escenarioLleno.getHabitacion().eliminaPaciente(paciente);
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