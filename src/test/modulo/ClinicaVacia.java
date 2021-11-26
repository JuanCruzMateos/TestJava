package test.modulo;

import java.util.ArrayList;

import habitaciones.IHabitacion;
import medicos.IMedico;
import modulo.Clinica;
import pacientes.IPaciente;
import pacientes.LineaFactura;

public class ClinicaVacia {
	private Clinica clinica = Clinica.getInstance();
	
	public void setUp() {
		clinica.setMedicos(new ArrayList<IMedico>()); 
		clinica.sethPacientes(new ArrayList<IPaciente>();
		this.listaDeAtencion = new ArrayList<IPaciente>();
		this.habitaciones = new ArrayList<IHabitacion>();
		this.patio = new ArrayList<IPaciente>();
		this.hPacientes = new ArrayList<IPaciente>();
		this.lineasFacturas = new ArrayList<LineaFactura>();
		this.lineasReporte = new ArrayList<LineaFactura>();
	}
}
