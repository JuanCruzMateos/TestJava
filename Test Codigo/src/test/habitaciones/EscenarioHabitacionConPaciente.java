package test.habitaciones;

import habitaciones.IHabitacion;
import pacientes.IPaciente;

public class EscenarioHabitacionConPaciente {
	private IHabitacion habitacion;

	public void setUp(IHabitacion habitacion, IPaciente paciente) {
		this.habitacion = habitacion;
		this.habitacion.agregaPaciente(paciente);
	}

	public IHabitacion getHabitacion() {
		return habitacion;
	}

}
