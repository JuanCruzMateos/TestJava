package test.escenarios;

import java.util.ArrayList;

import excepciones.TipoPacienteInvalidoException;
import modulo.Clinica;
import pacientes.IPaciente;
import pacientes.PacienteFactory;

public class ClinicaConPacientes {
	private Clinica clinica = Clinica.getInstance();
	
	public void setUp() {
		ArrayList<IPaciente> pacientes = new ArrayList<>();
		try {
			pacientes.add(PacienteFactory.getPaciente("11111111", "Carlos Perez", "155111111", "Mitre 1234", "Mar del Plata", 1, "Mayor"));
			pacientes.add(PacienteFactory.getPaciente("22222222", "Carlos Perez", "155111111", "Mitre 1234", "Mar del Plata", 1, "Mayor"));
			pacientes.add(PacienteFactory.getPaciente("33333333", "Carlos Perez", "155111111", "Mitre 1234", "Mar del Plata", 1, "Mayor"));
			pacientes.add(PacienteFactory.getPaciente("44444444", "Carlos Perez", "155111111", "Mitre 1234", "Mar del Plata", 1, "Mayor"));
			pacientes.add(PacienteFactory.getPaciente("55555555", "Carlos Perez", "155111111", "Mitre 1234", "Mar del Plata", 1, "Mayor"));
			pacientes.add(PacienteFactory.getPaciente("66666666", "Carlos Perez", "155111111", "Mitre 1234", "Mar del Plata", 1, "Mayor"));
			pacientes.add(PacienteFactory.getPaciente("77777777", "Carlos Perez", "155111111", "Mitre 1234", "Mar del Plata", 1, "Mayor"));
			
		} catch (TipoPacienteInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
