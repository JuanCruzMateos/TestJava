package habitaciones;

import pacientes.IPaciente;

/**
 * @author Clase habitacion privada que se extiende de la superclase habitacion
 *         <br>
 */
public class HabPrivada extends Habitacion {

	private IPaciente paciente;

	public HabPrivada(double costoAsignacion) {
		super(costoAsignacion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double calcularValorInternacion(int dias) {
		if (dias == 1)
			return this.costoAsignacion;
		else if (dias >= 2 && dias <= 5)
			return (this.costoAsignacion * dias * 1.3);
		else
			return (this.costoAsignacion * dias * 2);
	}

	@Override
	public String getNombreHabitacion() {
		return "Habitacion Privada";
	}

	@Override
	public String toString() {
		String rta = null;
		if (this.paciente != null)
			rta = "HabPrivada: Paciente=" + paciente.getNombre();
		else
			rta = "HabPrivada: Paciente: (sin paciente)";
		return rta;
	}

	/**
	 * metodo boolean que devuelve true o false en el caso de poder haber agregado
	 * exitosamente a un paciente <br>
	 */
	@Override
	public boolean agregaPaciente(IPaciente paciente) {
		boolean rta = false;
		if (this.paciente == null) {
			if (paciente.getHabitacion() != null)
				paciente.getHabitacion().eliminaPaciente(paciente);
			rta = true;
			this.paciente = paciente;
			paciente.setHabitacion(this);
		}
		return rta;
	}

	/**
	 * metodo void en donde se elimina a un paciente de la habitacion compartida <br>
	 */
	@Override
	public void eliminaPaciente(IPaciente paciente) {

		this.paciente = null;
		paciente.setHabitacion(null);
	}

}