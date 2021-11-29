package medicos;

/**
 * @author usuario
 * Subclase Clinico que se extiende de la clase Medico<br>
 */
public class Clinico extends Medico {


	public Clinico(String nomAp, String dni, String domicilio, String ciudad, String telefono, int numMatricula,
			double honorario) {
		super(nomAp, dni, domicilio, ciudad, telefono, numMatricula, honorario);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double calculaHonorario() {
		
		return (honorarioBase*1.05);
	}



	
}