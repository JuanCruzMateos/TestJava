package medicos;

import java.util.GregorianCalendar;

import pacientes.IPaciente;

/**
 * @author usuario
 * Clase abstracta DecoratorPosgrado en donde se encapsula a un IMedico para evitar una posterior explosion de clases <br>
 */
public abstract class DecoratorPosgrado implements IMedico {
	
	protected IMedico encapsulado;
	
	/**Constructor de DecoratorPosgrado en donde se recibe un IMedico como parametro<br>
	 * @param encapsulado: parametro tipo IMedico, representa un encapsulado del medico afiliado al patron decorator<br>
	 * <b> Pre : </b> El encapsulado debe de ser distinto de NULL<br>
	 * <b> Post : </b> Al encapsulado de este decorator se le asigna correctamente el encapsulado del parametro<br>
	 * 
	 */
	public DecoratorPosgrado(IMedico encapsulado) {
		this.encapsulado = encapsulado;
	}
	

	@Override
	public String getNombre() {
		return this.encapsulado.getNombre();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((encapsulado == null) ? 0 : encapsulado.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DecoratorPosgrado other = (DecoratorPosgrado) obj;
		if (encapsulado == null) {
			if (other.encapsulado != null)
				return false;
		} else if (!encapsulado.equals(other.encapsulado))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return encapsulado.toString();
	}

	
	
	

}