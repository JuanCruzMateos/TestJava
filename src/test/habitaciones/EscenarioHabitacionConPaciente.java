package test.habitaciones;

import habitaciones.IHabitacion;
import pacientes.IPaciente;

public class EscenarioHabitacionConPaciente {
	private IHabitacion habitacion;

	public EscenarioHabitacionConPaciente(IHabitacion habitacion, IPaciente paciente) {
		super();
		this.habitacion = habitacion;
		this.habitacion.agregaPaciente(paciente);

	}

	public IHabitacion getHabitacion() {
		return habitacion;
	}

}
