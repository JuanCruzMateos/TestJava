package pacientes;

/**
 * @author Clase joven que se extiende de la clase paciente que implementa la
 *         interfaz IPaciente
 */
public class Joven extends Paciente {

	public Joven(String dni, String nomAp, String telefono, String domicilio, String ciudad, int numHistoria) {
		super(dni, nomAp, telefono, domicilio, ciudad, numHistoria, "Joven");
	}

	@Override
	public boolean beats(IPaciente o) {
		return o.beatsJoven();
	}

	@Override
	public boolean beatsNiño() {
		return true;
	}

	@Override
	public boolean beatsJoven() {
		return true;
	}

	@Override
	public boolean beatsMayor() {
		return false;
	}

}