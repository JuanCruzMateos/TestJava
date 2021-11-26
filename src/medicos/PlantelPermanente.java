package medicos;

/**
 * @author usuario Clase plantelPermanente que se extiende del decorator de
 *         contratacion. Plantel permanente es una forma de contratacion que
 *         afecta al honorario del medico<br>
 */
public class PlantelPermanente extends DecoratorContratacion {

	public PlantelPermanente(IMedico encapsuladobis) {
		super(encapsuladobis);
	}

	@Override
	public double calculaHonorario() {
		return (this.encapsuladobis.calculaHonorario() * 1.1);

	}

}
