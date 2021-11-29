package excepciones;


public class PosgradoInvalidoException extends Exception{

	private String posgrado;

	public PosgradoInvalidoException(String message,String posgrado) {
		super(message);
		this.posgrado = posgrado;
		// TODO Auto-generated constructor stub
	}

	public String getPosgrado() {
		return this.posgrado;
	}
	
	

}
