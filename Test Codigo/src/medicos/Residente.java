package medicos;

/**
 * @author usuario Clase residente que se extiende del decorator de
 *         contratacion.Residente es una forma de contratacion que afecta al
 *         honorario del medico <br>
 */
public class Residente extends DecoratorContratacion {

	public Residente(IMedico encapsuladobis) {
		super(encapsuladobis);
	}

	@Override
	public double calculaHonorario() {
		return (this.encapsuladobis.calculaHonorario() * 1.05);
	}

}