package excepciones;

public class EspecialidadInvalidaException extends Exception {
	
	private String especialidad;

	public EspecialidadInvalidaException(String message,String especialidad) {
		super(message);
		this.especialidad = especialidad;
		// TODO Auto-generated constructor stub
	}

	public String getEspecialidad() {
		return this.especialidad;
	}


	
}
