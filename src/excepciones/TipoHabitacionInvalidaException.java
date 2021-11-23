package excepciones;

public class TipoHabitacionInvalidaException extends Exception {
	private String Tipo;


	public TipoHabitacionInvalidaException(String message , String Tipo) {
		super(message);
		this.Tipo=Tipo;
	}

	public String getTipo() {
		return Tipo;
	}
	
}
