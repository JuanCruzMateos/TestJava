package excepciones;

public class TipoPacienteInvalidoException extends Exception {
	private String Tipo;

	public TipoPacienteInvalidoException(String message, String Tipo) {
		super(message);
		this.Tipo = Tipo;
	}

	public String getTipo() {
		return Tipo;
	}
}
