package pacientes;

import excepciones.TipoPacienteInvalidoException;

/**
 * @author Clase Factory en donde se generaran los distintos pacientes segun su
 *         rangoEtario : nino, joven y mayor
 */
public class PacienteFactory {

	/**
	 * Metodo static en donde se instancian los pacientes. Consta de los siguientes
	 * parametros:
	 * 
	 * @param dni         : string, documento del paciente<br>
	 * @param nomAp       : string, nombre y apellido del paciente<br>
	 * @param telefono    : string, telefono del paciente<br>
	 * @param domicilio   : string, domicilio donde reside el paciente<br>
	 * @param ciudad      : string, ciudad donde vive el paciente<br>
	 * @param numHistoria : int, numero de historia clinica del paciente<br>
	 * @param rangoEtario : string, rangoEtario del paciente (acorde a su edad)<br>
	 *                    <b> Pre : </b> Paciente tiene que ser distinto de null,
	 *                    ademas de sus parametros<br>
	 *                    <b> Post : </b> Obtener un paciente de tipo nino,joven o
	 *                    mayor<br>
	 * @return retorna un paciente con su subclase determinada por el
	 *         rangoEtario<br>
	 * @throws TipoPacienteInvalidoException : lanza una excepcion si el rangoEtario
	 *                                       ingresado es nulo o erroneo
	 */
	public static IPaciente getPaciente(String dni, String nomAp, String telefono, String domicilio, String ciudad,
			int numHistoria, String rangoEtario) throws TipoPacienteInvalidoException {

		IPaciente respuesta = null;

		if (rangoEtario == "Nino")
			respuesta = new Niño(dni, nomAp, telefono, domicilio, ciudad, numHistoria);
		else if (rangoEtario == "Joven")
			respuesta = new Joven(dni, nomAp, telefono, domicilio, ciudad, numHistoria);
		else if (rangoEtario == "Mayor")
			respuesta = new Mayor(dni, nomAp, telefono, domicilio, ciudad, numHistoria);
		if (respuesta != null)
			return respuesta;
		else
			throw new TipoPacienteInvalidoException("No corresponde este rango etario", rangoEtario);
	}
}
