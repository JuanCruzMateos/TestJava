package test.escenarios;

import java.util.ArrayList;

import excepciones.ContratacionInvalidaException;
import excepciones.EspecialidadInvalidaException;
import excepciones.PosgradoInvalidoException;
import excepciones.TipoPacienteInvalidoException;
import habitaciones.IHabitacion;
import habitaciones.SalaTerapiaIntensiva;
import medicos.IMedico;
import medicos.MedicoFactory;
import modulo.Clinica;
import pacientes.IPaciente;
import pacientes.PacienteFactory;

public class ClinicaPacMed {
	public static IMedico m;
	public static IPaciente pL, pNL;
	public static IHabitacion h;

	public static void setUp() {
		try {
			ArrayList<IMedico> medicos = new ArrayList<>();
			ArrayList<IPaciente> pacientesL = new ArrayList<>();
			ArrayList<IPaciente> pacientesNL = new ArrayList<>();
			ArrayList<IHabitacion> habitaciones = new ArrayList<>();
			h = new SalaTerapiaIntensiva(100);
			m = MedicoFactory.getMedico("Juan Perez", "1", "casa", "Mardel", "987654", 1, "Cirujano", "Permanente",
					"Magister", 100);
			pL = PacienteFactory.getPaciente("11111111", "Carlos Perez", "155111111", "Mitre 1234",
					"Mar del Plata", 1, "Mayor");
			pNL = PacienteFactory.getPaciente("2222222", "Carlos Perez", "155111111", "Mitre 1234",
					"Mar del Plata", 2, "Mayor");
			habitaciones.add(h);
			medicos.add(m);
			pacientesL.add(pL);
			pacientesNL.add(pNL);
			Clinica.getInstance().setMedicos(medicos);
			Clinica.getInstance().setListaDeAtencion(pacientesL);
			Clinica.getInstance().sethPacientes(pacientesNL);
			Clinica.getInstance().setHabitaciones(habitaciones);
		} catch (PosgradoInvalidoException | ContratacionInvalidaException | EspecialidadInvalidaException e) {
		} catch (TipoPacienteInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
