package persistencia;

import java.util.ArrayList;

import habitaciones.IHabitacion;
import medicos.IMedico;
import modulo.Clinica;
import pacientes.IPaciente;
import pacientes.LineaFactura;

/**
 * @author usuario <br>
 *         clase que permite la transferencia de datos entre el objeto del
 *         sistema que no puede ser Serializable y el objeto DTO
 */
public class UtilClinica {
	/**
	 * metodo estatico que permite convertir de un objeto DTO a un objeto<br>
	 * 
	 * @param cDTO de tipo ClinicaDTO nos permite convertir a objeto de tipo Clinica
	 */
	public static void ClinicafromClinicaDTO(ClinicaDTO cDTO) {
		Clinica.getInstance();
		Clinica.getInstance().setMedicos(cDTO.getMedicos());
		Clinica.getInstance().sethPacientes(cDTO.gethPacientes());
		Clinica.getInstance().setHabitaciones(cDTO.getHabitaciones());
		Clinica.getInstance().setLineasFacturas(cDTO.getLineasFacturas());
		Clinica.getInstance().setPatio(cDTO.getPatio());
		Clinica.getInstance().setSalaDeEsperaPrivada(cDTO.getSalaDeEsperaPrivada());
		Clinica.getInstance().setListaDeEspera(cDTO.getListaDeEspera());
		Clinica.getInstance().setListaDeAtencion(cDTO.getListaDeAtencion());
		Clinica.getInstance().setTurno(cDTO.getTurno());
		Clinica.getInstance().setNroFactura(cDTO.getNroFactura());
		Clinica.getInstance().setNroOrden(cDTO.getNroOrden());
		Clinica.getInstance().setLineasReporte(cDTO.getLineasReporte());
	}

	/**
	 * metodo estatico que nos permite convertir el objeto del sistema a un objeto
	 * DTO, se crea un objetoDTO y se le transfieren los datos del objeto
	 * original<br>
	 * 
	 * @return de tipo ClinicaDTO
	 */
	public static ClinicaDTO ClinicaDTOfromClinica() {
		ClinicaDTO respuesta = new ClinicaDTO();
		respuesta.setNombre(Clinica.getInstance().getNombre());
		respuesta.setDireccion(Clinica.getInstance().getDireccion());
		respuesta.setTelefono(Clinica.getInstance().getTelefono());
		respuesta.setCiudad(Clinica.getInstance().getCiudad());
		respuesta.setMedicos(Clinica.getInstance().getMedicos());
		respuesta.sethPacientes(Clinica.getInstance().gethPacientes());
		respuesta.setHabitaciones(Clinica.getInstance().getHabitaciones());
		respuesta.setListaDeAtencion(Clinica.getInstance().getListaDeAtencion());
		respuesta.setListaDeEspera(Clinica.getInstance().getListaDeEspera());
		respuesta.setSalaDeEsperaPrivada(Clinica.getInstance().getSalaDeEsperaPrivada());
		respuesta.setLineasFacturas(Clinica.getInstance().getLineasFacturas());
		respuesta.setPatio(Clinica.getInstance().getPatio());
		respuesta.setTurno(Clinica.getInstance().getTurno());
		respuesta.setNroFactura(Clinica.getInstance().getNroFactura());
		respuesta.setNroOrden(Clinica.getInstance().getNroOrden());
		respuesta.setLineasReporte(Clinica.getInstance().getLineasReporte());
		return respuesta;
	}
}