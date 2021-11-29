package medicos;

/**
 * @author usuario
 * Clase doctorado que se extiende del decorator posgrado (significando, doctorado, un tipo de contratacion para los medicos)<br>
 */
public class Doctorado extends DecoratorPosgrado {

	public Doctorado(IMedico encapsulado) {
		super(encapsulado);
	}

	@Override
	public double calculaHonorario() {
		return (this.encapsulado.calculaHonorario()*1.1);
		
	}

	
}

	