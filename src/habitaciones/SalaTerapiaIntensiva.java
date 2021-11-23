package habitaciones;



/**
 * @author 
 * Clase Sala de Terapia Intensiva que se extiende de la superclase habitacion compartida <br>
 */
public class SalaTerapiaIntensiva extends HabCompartida{


	
	public SalaTerapiaIntensiva(double costoAsignacion) {
		super(costoAsignacion);
		// TODO Auto-generated constructor stub
	}


	@Override
	public double calcularValorInternacion(int dias) {
		return Math.pow(this.costoAsignacion, dias);
	}
	

	
	@Override
	public String toString() {
		return "SalaTerapiaIntensiva " + super.toString();
	}


	@Override
	public String getNombreHabitacion() {
		return "Terapia intensiva";
	}

}
