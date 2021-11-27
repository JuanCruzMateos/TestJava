package pacientes;

import java.io.Serializable;

import habitaciones.IHabitacion;
import medicos.IMedico;

/**
 * @author usuario Clase linea factura en donde se retornan los datos de cada
 *         linea para informar a la hora del egreso de un paciente
 */
public class LineaFactura implements Serializable {
	private IHabitacion habitacion;
	private int cantDias;
	private IPaciente paciente;
	private IMedico medico;

	/**
	 * @param habitacion : habitacion a la cual se le asigna al paciente
	 * @param cantDias
	 * @param paciente
	 * @param medico
	 */
	public LineaFactura(IHabitacion habitacion, int cantDias, IPaciente paciente, IMedico medico) {
		this.habitacion = habitacion;
		this.cantDias = cantDias;
		this.paciente = paciente;
		this.medico = medico;
	}

	public IPaciente getPaciente() {
		return paciente;
	}

	public IHabitacion getHabitacion() {
		return habitacion;
	}

	public int getCantDias() {
		return cantDias;
	}

	public IMedico getMedico() {
		return medico;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		LineaFactura other = (LineaFactura) obj;
		if (cantDias != other.cantDias || paciente != other.paciente || medico != other.medico)
			return false;
		return true;
	}

}