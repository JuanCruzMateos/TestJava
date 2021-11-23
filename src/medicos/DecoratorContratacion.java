package medicos;

import java.util.GregorianCalendar;

import pacientes.IPaciente;

/**
 * @author usuario
 * Clase abstracta decoratorcontratacion en donde se encapsula("decora") a un IMedico para evitar una posterior explosion de clases <br>
 */
public abstract class DecoratorContratacion implements IMedico{

   
    protected IMedico encapsuladobis;
	

	/**Constructor de DecoratorContratacion en donde se recibe un IMedico como parametro <br>
	 * @param encapsuladobis : parametro tipo IMedico, representa un encapsulado del medico afiliado al patron decorator<br>
	 * <b> Pre : </b> El encapsulado debe de ser distinto de NULL<br>
	 * <b> Post : </b> Al encapsulado de este decorator se le asigna correctamente el encapsulado del parametro<br>
	 * 
	 */
	public DecoratorContratacion(IMedico encapsuladobis) {
		this.encapsuladobis = encapsuladobis;
	}
	

	@Override
	public String getNombre() {
		return this.encapsuladobis.getNombre();
	}

	
	

	@Override
	public String toString() {
		return encapsuladobis.toString();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((encapsuladobis == null) ? 0 : encapsuladobis.hashCode());
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
		DecoratorContratacion other = (DecoratorContratacion) obj;
		if (encapsuladobis == null) {
			if (other.encapsuladobis != null)
				return false;
		} else if (!encapsuladobis.equals(other.encapsuladobis))
			return false;
		return true;
	}
	
	







	
	

	
	

}