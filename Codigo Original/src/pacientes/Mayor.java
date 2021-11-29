package pacientes;

/**
 * @author 
 * Clase mayor que se extiende la superclase Paciente e implementa la interfaz IPaciente
 */
public class Mayor extends Paciente {

	public Mayor(String dni, String nomAp, String telefono, String domicilio, String ciudad, int numHistoria) {
		super(dni, nomAp, telefono, domicilio, ciudad, numHistoria);
	}

	@Override
	public boolean beats(IPaciente o) {
		return o.beatsMayor();
	}

	@Override
	public boolean beatsNiño() {
		return false;
	}

	@Override
	public boolean beatsJoven() {
		return true;
	}

	@Override
	public boolean beatsMayor() {
		return true;
	}
	
}