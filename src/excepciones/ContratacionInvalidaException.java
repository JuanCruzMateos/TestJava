package excepciones;

public class ContratacionInvalidaException extends Exception {

	private String contratacion;
	public ContratacionInvalidaException(String message, String contratacion) {
		super(message);
		this.contratacion = contratacion;
	}
	public String getContratacion() {
		return this.contratacion;
	}
	
	

}
