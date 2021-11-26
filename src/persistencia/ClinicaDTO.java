package persistencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import habitaciones.IHabitacion;
import medicos.IMedico;
import modulo.Clinica;
import pacientes.IPaciente;
import pacientes.LineaFactura;

/**
 * @author usuario <br>
 *         clase que nos permite crear un objeto para transferir los datos de
 *         otro objeto que no puede persistirse<br>
 *         Se aplica el Patron DTO <br>
 *         la clase implementa Serializable para poder persistir de manera
 *         binaria
 */
public class ClinicaDTO implements Serializable {
	private String nombre, direccion, telefono, ciudad;
	private ArrayList<IMedico> medicos;
	private ArrayList<IPaciente> hPacientes;
	private ArrayList<IHabitacion> habitaciones;
	private ArrayList<IPaciente> listaDeEspera;
	ArrayList<IPaciente> listaDeAtencion;
	IPaciente salaDeEsperaPrivada = null;
	ArrayList<IPaciente> patio;
	private ArrayList<LineaFactura> lineasFacturas;
	private ArrayList<LineaFactura> lineasReporte;
	private int nroOrden = 1, turno = 1, nroFactura = 1;

	/**
	 * Constructor de clase sin parametros para poder realizar una codificacion XML
	 */
	public ClinicaDTO() {
	}

	public int getNroOrden() {
		return nroOrden;
	}

	public void setNroOrden(int nroOrden) {
		this.nroOrden = nroOrden;
	}

	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
	}

	public int getNroFactura() {
		return nroFactura;
	}

	public void setNroFactura(int nroFactura) {
		this.nroFactura = nroFactura;
	}

	public ArrayList<IPaciente> getPatio() {
		return patio;
	}

	public void setPatio(ArrayList<IPaciente> patio) {
		this.patio = patio;
	}

	public ArrayList<LineaFactura> getLineasFacturas() {
		return lineasFacturas;
	}

	public void setLineasFacturas(ArrayList<LineaFactura> lineasFacturas) {
		this.lineasFacturas = lineasFacturas;
	}

	public ArrayList<IPaciente> getListaDeEspera() {
		return listaDeEspera;
	}

	public void setListaDeEspera(ArrayList<IPaciente> listaDeEspera) {
		this.listaDeEspera = listaDeEspera;
	}

	public ArrayList<IPaciente> getListaDeAtencion() {
		return listaDeAtencion;
	}

	public void setListaDeAtencion(ArrayList<IPaciente> listaDeAtencion) {
		this.listaDeAtencion = listaDeAtencion;
	}

	public IPaciente getSalaDeEsperaPrivada() {
		return salaDeEsperaPrivada;
	}

	public void setSalaDeEsperaPrivada(IPaciente salaDeEsperaPrivada) {
		this.salaDeEsperaPrivada = salaDeEsperaPrivada;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public ArrayList<IMedico> getMedicos() {
		return medicos;
	}

	public void setMedicos(ArrayList<IMedico> medicos) {
		this.medicos = medicos;
	}

	public ArrayList<IPaciente> gethPacientes() {
		return hPacientes;
	}

	public void sethPacientes(ArrayList<IPaciente> hPacientes) {
		this.hPacientes = hPacientes;
	}

	public ArrayList<IHabitacion> getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(ArrayList<IHabitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}

	public ArrayList<LineaFactura> getLineasReporte() {
		return lineasReporte;
	}

	public void setLineasReporte(ArrayList<LineaFactura> lineasReporte) {
		this.lineasReporte = lineasReporte;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ciudad, direccion, hPacientes, habitaciones, lineasFacturas, lineasReporte, listaDeAtencion,
				listaDeEspera, medicos, nombre, nroFactura, nroOrden, patio, salaDeEsperaPrivada, telefono, turno);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClinicaDTO other = (ClinicaDTO) obj;
		return Objects.equals(ciudad, other.ciudad) && Objects.equals(direccion, other.direccion)
				&& Objects.equals(hPacientes, other.hPacientes) && Objects.equals(habitaciones, other.habitaciones)
				&& Objects.equals(lineasFacturas, other.lineasFacturas)
				&& Objects.equals(lineasReporte, other.lineasReporte)
				&& Objects.equals(listaDeAtencion, other.listaDeAtencion)
				&& Objects.equals(listaDeEspera, other.listaDeEspera) && Objects.equals(medicos, other.medicos)
				&& Objects.equals(nombre, other.nombre) && nroFactura == other.nroFactura && nroOrden == other.nroOrden
				&& Objects.equals(patio, other.patio) && Objects.equals(salaDeEsperaPrivada, other.salaDeEsperaPrivada)
				&& Objects.equals(telefono, other.telefono) && turno == other.turno;
	}

}
