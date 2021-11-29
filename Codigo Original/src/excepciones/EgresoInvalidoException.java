package excepciones;

import pacientes.IPaciente;

public class EgresoInvalidoException extends Exception {
	private IPaciente paciente;


	public EgresoInvalidoException(String message , IPaciente paciente) {
		super(message);
		this.paciente = paciente;
	}

	public String nombrePaciente() {
		return this.paciente.getNombre();
	}
}
