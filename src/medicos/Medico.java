package medicos;

import java.io.Serializable;

/**
 * @author usuario Clase abstracta Medico de la cual sera extendida por las
 *         distintas especialidades de medicos de la clinica<br>
 */
public abstract class Medico implements IMedico, Serializable {
	/**
	 * Atributos de la clase medico<br>
	 */
	protected String dni, nomAp, domicilio, ciudad, telefono;
	protected int numMatricula;
	protected double honorarioBase;

	// SOBRESCRIBIR METODOS EQUALS

	/**
	 * Constructor del medico que cuenta con los siguientes parametros<br>
	 * 
	 * @param nomAp        : nombre y apellido del mismo
	 * @param dni
	 * @param domicilio
	 * @param ciudad
	 * @param telefono
	 * @param numMatricula : numero de matricula del medico
	 */
	public Medico(String nomAp, String dni, String domicilio, String ciudad, String telefono, int numMatricula,
			double honorario) {
		this.dni = dni;
		this.nomAp = nomAp;
		this.domicilio = domicilio;
		this.ciudad = ciudad;
		this.telefono = telefono;
		this.numMatricula = numMatricula;
		this.honorarioBase = honorario;

	}

	@Override
	public String toString() {
		return "Medico: " + this.nomAp + " DNI: " + this.dni + " numMatricula: " + this.numMatricula;
	}

	public Medico() {
	}

	public String getNombre() {
		return this.nomAp;
	}


	public String getDNI() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNomAp() {
		return nomAp;
	}

	public void setNomAp(String nomAp) {
		this.nomAp = nomAp;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getNumMatricula() {
		return numMatricula;
	}

	public void setNumMatricula(int numMatricula) {
		this.numMatricula = numMatricula;
	}

	public double getHonorarioBase() {
		return honorarioBase;
	}

	public void setHonorarioBase(double honorarioBase) {
		this.honorarioBase = honorarioBase;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + numMatricula;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Medico other = (Medico) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if ((!dni.equalsIgnoreCase(other.dni)) && (numMatricula != other.numMatricula))
			return false;
		return true;
	}

}