package test.habitaciones;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import excepciones.TipoPacienteInvalidoException;
import habitaciones.HabCompartidaComun;
import pacientes.IPaciente;
import pacientes.PacienteFactory;

public class TestHabCompartidaComun {
	double costoHabitacion;
	EscenarioHabitacionSinPaciente escenarioSinPaciente = new EscenarioHabitacionSinPaciente();
	EscenarioHabitacionLlena escenarioLleno = new EscenarioHabitacionLlena();

	@Before
	public void setUp() throws Exception {
		costoHabitacion = 10;
		escenarioLleno.setUp(new HabCompartidaComun(costoHabitacion));
		escenarioSinPaciente.setUp(new HabCompartidaComun(costoHabitacion));
	}

	@Test
	public void testCalcularValorInternacion() {
		int dias = 4;
		double costo = escenarioSinPaciente.getHabitacion().calcularValorInternacion(dias);
		assertEquals("El costo de la habitacion debe ser " + dias * costoHabitacion, dias * costoHabitacion, costo, 0);
	}

	@Test
	public void testAgregarPacienteExitoso() {
		IPaciente j1;
		try {
			j1 = PacienteFactory.getPaciente("123456789", "Carlos Uno", "5111234", "Aire 1200", "Buenos Aires", 2,
					"Joven");
			escenarioSinPaciente.getHabitacion().agregaPaciente(j1);
			assertEquals("El paciente debe haber ingresado a la habitacion", escenarioSinPaciente.getHabitacion(),
					j1.getHabitacion());
		} catch (TipoPacienteInvalidoException e) {
			//
		}
	}

	@Test
	public void testAgregarPacienteErroneo() {
		IPaciente j1;
		try {
			j1 = PacienteFactory.getPaciente("123456789", "Carlos Uno", "5111234", "Aire 1200", "Buenos Aires", 2,
					"Joven");
			escenarioLleno.getHabitacion().agregaPaciente(j1);
			assertNotEquals("No puede haber mas de 8 pacientes en la HabCompartidaComun ",
					escenarioLleno.getHabitacion(), j1.getHabitacion());
		} catch (TipoPacienteInvalidoException e) {
			//
		}
	}

	@Test
	public void testEliminarPacienteExitoso() {
		escenarioLleno.getHabitacion().eliminaPaciente(escenarioLleno.getPaciente());
		assertNull("Se debe haber eliminado el paciente de la HabCompartidaComun",
				escenarioLleno.getPaciente().getHabitacion());
	}

	@Test
	public void testEliminarPacienteErroneo() {
		escenarioSinPaciente.getHabitacion().eliminaPaciente(escenarioLleno.getPaciente());
		assertNotNull("No se elimina ningun paciente si la HabCompartidaComun esta vacia",
				escenarioLleno.getPaciente().getHabitacion());
	}

}