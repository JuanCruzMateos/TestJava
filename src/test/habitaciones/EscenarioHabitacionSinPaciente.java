package test.habitaciones;

import habitaciones.IHabitacion;

public class EscenarioHabitacionSinPaciente {
	private IHabitacion habitacion;

	public void setUp(IHabitacion habitacion) {
		this.habitacion = habitacion;

	}

	public IHabitacion getHabitacion() {
		return habitacion;
	}

}
