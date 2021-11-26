package test.habitaciones;

import habitaciones.IHabitacion;
import pacientes.Mayor;

public class EscenarioHabitacionLlena {
	private IHabitacion habitacion;

	public EscenarioHabitacionLlena(IHabitacion habitacion) {
		super();
		this.habitacion = habitacion;
		this.habitacion.agregaPaciente(new Mayor("23", "Hernan H", "223100", "Paso 12", "Mar del Plata", 18));
		this.habitacion.agregaPaciente(new Mayor("24", "Pablo H", "223101", "Paso 13", "Mar del Plata", 17));
		this.habitacion.agregaPaciente(new Mayor("25", "Mateo H", "223102", "Paso 14", "Mar del Plata", 16));
		this.habitacion.agregaPaciente(new Mayor("26", "Sergio H", "223103", "Paso 15", "Mar del Plata", 15));
		this.habitacion.agregaPaciente(new Mayor("27", "Paulo H", "223104", "Paso 16", "Mar del Plata", 14));
		this.habitacion.agregaPaciente(new Mayor("28", "Martin H", "223105", "Paso 17", "Mar del Plata", 13));
		this.habitacion.agregaPaciente(new Mayor("29", "Blas H", "223106", "Paso 18", "Mar del Plata", 12));
		this.habitacion.agregaPaciente(new Mayor("30", "Teo H", "223107", "Paso 19", "Mar del Plata", 11));
	}

	public IHabitacion getHabitacion() {
		return habitacion;
	}
}
