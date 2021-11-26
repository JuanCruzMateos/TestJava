package habitaciones;

import java.util.ArrayList;

import pacientes.IPaciente;

/**
 * @author Clase habitacion compartida que se extiende de la clase abstracta
 *         habitacion <br>
 */
public abstract class HabCompartida extends Habitacion {

	protected ArrayList<IPaciente> pacientes;
	protected transient static int capacidad = 8;

	public HabCompartida(double costoAsignacion) {
		super(costoAsignacion);
		pacientes = new ArrayList<IPaciente>();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return this.pacientes.size() + "/" + capacidad;
	}

	@Override
	public double calcularValorInternacion(int dias) {
		return (this.costoAsignacion * dias);
	}

	@Override
	public String getNombreHabitacion() {
		return "Habitacion compartida";
	}

	/**
	 * metodo boolean que devuelve true o false en el caso de poder haber agregado
	 * exitosamente a un paciente <br>
	 */
	@Override
	public boolean agregaPaciente(IPaciente paciente) {
		boolean rta = false;
		if (!this.pacientes.contains(paciente) && this.pacientes.size() < capacidad) {
			if (paciente.getHabitacion() != null)
				paciente.getHabitacion().eliminaPaciente(paciente);
			this.pacientes.add(paciente);
			paciente.setHabitacion(this);
			rta = true;
		}
		return rta;
	}

	/**
	 * metodo void en donde se elimina a un paciente de la habitacion compartida
	 * <br>
	 */
	@Override
	public void eliminaPaciente(IPaciente paciente) {
		if (this.pacientes.contains(paciente)) {
			this.pacientes.remove(paciente);
			paciente.setHabitacion(null);
		}
	}

}