package habitaciones;

import java.io.Serializable;

import pacientes.IPaciente;

/**
 * @author Interfaz IHabitacion en donde se declararan los metodos a implementar
 *         para la clase habitacion y sus extensiones <br>
 */
public interface IHabitacion extends Serializable {

	/**
	 * Metodo double que calcula el costo de internacion en una habitacion de
	 * cualquier tipo<br>
	 * <b> Pre : </b> la cantidad de dias debe ser un valor natural<br>
	 * <b> Post : </b> se obtiene un double con el costo de internacion de tal
	 * habitacion<br>
	 * 
	 * @param dias : parametro integer que determina los dias de internacion de un
	 *             paciente<br>
	 * @return retorna el costo de internacion
	 */
	double calcularValorInternacion(int dias);

	/**
	 * @return retorna una string con el tipo de habitacion espec�fica
	 */
	String getNombreHabitacion();

	/**
	 * @return retorna el costo de asignacion inicial dependiendo el tipo de
	 *         habitacion
	 */
	double getCostoAsignacion();

	boolean agregaPaciente(IPaciente paciente);

	void eliminaPaciente(IPaciente paciente);

}
