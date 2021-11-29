package pacientes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Observable;

import habitaciones.IHabitacion;

/**
 * @author Clase abstracta Paciente que determina a los pacientes que seran
 *         atendidos por la clinica. Implementa la interfaz IPaciente<br>
 * 
 */
public abstract class Paciente implements IPaciente {

	protected String dni, nomAp, telefono, domicilio, ciudad, pedido;
	protected int numHistoria;
	protected int nroTurno;
	protected GregorianCalendar fechaEgreso;
	protected IHabitacion habitacion = null;
	protected boolean facturo = false;
	protected String rangoEtareo;
	/**
	 * Constructor de Paciente que se le pasan varios parametros por string y un
	 * entero<br>
	 * 
	 * @param dni         : parametro string de dni el cual le asigna un dni al
	 *                    paciente<br>
	 * @param nomAp       : parametro string que determina el nombre y apellido del
	 *                    paciente<br>
	 * @param telefono    : parametro string que da el numero de telefono del
	 *                    paciente<br>
	 * @param domicilio   : parametro string que detalla en que direccion reside el
	 *                    paciente<br>
	 * @param ciudad      : parametro string en donde se ingresa la ciudad en donde
	 *                    vive el paciente<br>
	 * @param numHistoria : parametro entero, numero de historia clinica del
	 *                    paciente ingresante<br>
	 */
	public Paciente(String dni, String nomAp, String telefono, String domicilio, String ciudad, int numHistoria,
			String rangoEtareo) {
		this.dni = dni;
		this.nomAp = nomAp;
		this.telefono = telefono;
		this.domicilio = domicilio;
		this.ciudad = ciudad;
		this.numHistoria = numHistoria;
		this.rangoEtareo = rangoEtareo;
	}

	public boolean isFacturo() {
		return facturo;
	}

	public void setFacturo(boolean facturo) {
		this.facturo = facturo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + numHistoria;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Paciente other = (Paciente) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if ((!dni.equalsIgnoreCase(other.dni) && (!(numHistoria == other.numHistoria))))
			return false;
		return true;
	}

	public IHabitacion getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(IHabitacion habitacion) {
		this.habitacion = habitacion;
	}

	public String getPedido() {
		return pedido;
	}

	public void setPedido(String pedido) {
		this.pedido = pedido;
	}

	public GregorianCalendar getFechaEgreso() {
		return this.fechaEgreso;
	}

	public void setFechaEgreso(GregorianCalendar fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}

	public int getNroTurno() {
		return nroTurno;
	}

	public void setNroTurno(int nroTurno) {
		this.nroTurno = nroTurno;
	}

	public String getNombre() {
		return this.nomAp;
	}

	public int getNumHistoria() {
		return this.numHistoria;
	}

	public String getDNI() {
		return this.dni;
	}

	/**
	 * Metodo int de la interfaz comparable que ordena a los pacientes segun su
	 * fecha de egreso
	 */
	@Override
	public int compareTo(IPaciente paciente) {

		Paciente otro = (Paciente) paciente;
		return this.fechaEgreso.compareTo(otro.fechaEgreso);
	}

	@Override
	public String toString() {
		return "Paciente: " + this.nomAp + " DNI: " + this.dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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

	public void setNumHistoria(int numHistoria) {
		this.numHistoria = numHistoria;
	}

	public String getRangoEtareo() {
		return rangoEtareo;
	}

}
