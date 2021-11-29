package habitaciones;

/**
 * @author Clase abstracta habitacion que representa parte de las habitaciones de la clinica<br>
 * <br>
 */
public abstract class Habitacion implements IHabitacion {

	protected double costoAsignacion;
	

	public Habitacion(double costoAsignacion) {
		this.costoAsignacion = costoAsignacion;
	}



	@Override
	public double getCostoAsignacion() {
		return this.costoAsignacion;
	}


	
	
}