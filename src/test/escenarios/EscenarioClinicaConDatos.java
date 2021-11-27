package test.escenarios;

import java.util.ArrayList;

import excepciones.PacienteRepetidoException;
import excepciones.PacienteYaIngresadoException;
import excepciones.TipoPacienteInvalidoException;
import habitaciones.IHabitacion;
import medicos.IMedico;
import modulo.Clinica;
import pacientes.IPaciente;
import pacientes.LineaFactura;
import pacientes.PacienteFactory;

/**
 * en sala VIP: Juan Martinez DNI: 77777777
 * 
 * en patio: Maria Perez DNI: 22222222, Carlos Perez DNI: 11111111, Lucia
 * Sanchez DNI: 44444444, Camila Diaz DNI: 55555555, Marcos Lopez DNI: 33333333
 * Lucas Rodriguez DNI: 66666666
 */
public class EscenarioClinicaConDatos {
	private final Clinica clinica = Clinica.getInstance();
	private IPaciente pacienteEnSalaPrivada;
	private IPaciente pacienteEnPatio;
	private IPaciente primerPaciente;

	public void setUp() {
		this.clinica.setNroOrden(1);
		this.clinica.setTurno(1);
		this.clinica.setNroFactura(1);
		this.clinica.setMedicos(new ArrayList<IMedico>());
		this.clinica.setListaDeEspera(new ArrayList<IPaciente>());
		this.clinica.setListaDeAtencion(new ArrayList<IPaciente>());
		this.clinica.setHabitaciones(new ArrayList<IHabitacion>());
		this.clinica.setPatio(new ArrayList<IPaciente>());
		this.clinica.setSalaDeEsperaPrivada(null);
		this.clinica.sethPacientes(new ArrayList<IPaciente>());
		this.clinica.setLineasFacturas(new ArrayList<LineaFactura>());
		this.clinica.setLineasReporte(new ArrayList<LineaFactura>());

		try {
			this.primerPaciente = PacienteFactory.getPaciente("11111111", "Carlos Perez", "155111111", "Mitre 1234",
					"Mar del Plata", 1, "Mayor");
			IPaciente p2 = PacienteFactory.getPaciente("22222222", "Maria Perez", "155222222", "Paso 1474",
					"Mar del Plata", 2, "Nino");
			IPaciente p3 = PacienteFactory.getPaciente("33333333", "Marcos Lopez", "155333333", "Falucho 2334",
					"Mar del Plata", 3, "Joven");
			IPaciente p4 = PacienteFactory.getPaciente("44444444", "Lucia Sanchez", "155444444", "Gascon 7834",
					"Mar del Plata", 4, "Joven");
			IPaciente p5 = PacienteFactory.getPaciente("55555555", "Camila Diaz", "155555555", "Luro 1527",
					"Mar del Plata", 5, "Mayor");
			this.pacienteEnPatio = PacienteFactory.getPaciente("66666666", "Lucas Rodriguez", "155666666", "Colon 1472",
					"Mar del Plata", 6, "Nino");
			this.pacienteEnSalaPrivada = PacienteFactory.getPaciente("77777777", "Juan Martinez", "155777777",
					"Alsina 63", "Mar del Plata", 7, "Mayor");

			this.clinica.ingresoPaciente(this.primerPaciente);
			this.clinica.ingresoPaciente(p2);
			this.clinica.ingresoPaciente(p3);
			this.clinica.ingresoPaciente(p4);
			this.clinica.ingresoPaciente(p5);
			this.clinica.ingresoPaciente(this.pacienteEnPatio);
			this.clinica.ingresoPaciente(this.pacienteEnSalaPrivada);

		} catch (TipoPacienteInvalidoException e) {
			//
		} catch (PacienteRepetidoException e) {
			//
		} catch (PacienteYaIngresadoException e) {
			//
		}
	}

	public Clinica getClinica() {
		return this.clinica;
	}

	public IPaciente getPacienteEnSalaPrivada() {
		return this.pacienteEnSalaPrivada;
	}

	public IPaciente getPacienteEnPatio() {
		return pacienteEnPatio;
	}

	public IPaciente getPrimerPaciente() {
		return primerPaciente;
	}

}
