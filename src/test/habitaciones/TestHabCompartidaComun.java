package test.habitaciones;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import habitaciones.HabCompartida;
import habitaciones.HabCompartidaComun;
import pacientes.Joven;

public class TestHabCompartidaComun {
	double costoHabitacion;
	HabCompartida habitacion;
	EscenarioHabitacionSinPaciente escenarioSinPaciente = new EscenarioHabitacionSinPaciente();
	EscenarioHabitacionLlena escenarioLleno = new EscenarioHabitacionLlena();

	@Before
	public void setUp() throws Exception {
		costoHabitacion = 10;
		habitacion = new HabCompartidaComun(costoHabitacion);
		escenarioLleno.setUp(habitacion);
		escenarioSinPaciente.setUp(habitacion);
	}

	@Test
	public void testCalcularValorInternacion() {
		int dias = 4;
		double costo = habitacion.calcularValorInternacion(dias);
		assertEquals("El costo de la habitacion debe ser " + dias * costoHabitacion, dias * costoHabitacion, costo, 0);
	}

	@Test
	public void testAgregarPacienteExitoso() {
		Joven j1 = new Joven("123456789", "Carlos Uno", "5111234", "Aire 1200", "Buenos Aires", 2);
		escenarioSinPaciente.getHabitacion().agregaPaciente(j1);
		assertEquals("El paciente debe haber ingresado a la habitacion", habitacion, j1.getHabitacion());
	}

	@Test
	public void testAgregarPacienteErroneo() {
		Joven j1 = new Joven("123456789", "Carlos Uno", "5111234", "Aire 1200", "Buenos Aires", 2);
		escenarioLleno.getHabitacion().agregaPaciente(j1);
		assertNotEquals("Solo puede haber un paciente en la habitacion privada", habitacion, j1.getHabitacion());
	}

	@Test
	public void testEliminarPacienteExitoso() {
		escenarioLleno.getHabitacion().eliminaPaciente(escenarioLleno.getPaciente());
		assertEquals("Se debe haber eliminado el paciente de la habitacion privada", null,
				escenarioLleno.getPaciente().getHabitacion());
	}

	@Test
	public void testEliminarPacienteErroneo() {
		escenarioSinPaciente.getHabitacion().eliminaPaciente(escenarioLleno.getPaciente());
		assertEquals("No se elimina ningun paciente si la habitacion privada esta vacia", null,
				escenarioLleno.getPaciente());
	}

}