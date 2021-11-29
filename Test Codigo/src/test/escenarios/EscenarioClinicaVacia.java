package test.escenarios;

import java.util.ArrayList;

import habitaciones.IHabitacion;
import medicos.IMedico;
import modulo.Clinica;
import pacientes.IPaciente;
import pacientes.LineaFactura;

public class EscenarioClinicaVacia {
	private Clinica clinica = Clinica.getInstance();

	public void setUp() {
		clinica.setMedicos(new ArrayList<IMedico>());
		clinica.setListaDeEspera(new ArrayList<IPaciente>());
		clinica.setListaDeAtencion(new ArrayList<IPaciente>());
		clinica.setHabitaciones(new ArrayList<IHabitacion>());
		clinica.setPatio(new ArrayList<IPaciente>());
		clinica.setSalaDeEsperaPrivada(null);
		clinica.sethPacientes(new ArrayList<IPaciente>());
		clinica.setLineasFacturas(new ArrayList<LineaFactura>());
		clinica.setLineasReporte(new ArrayList<LineaFactura>());
	}

	public Clinica getClinica() {
		return this.clinica;
	}
}
