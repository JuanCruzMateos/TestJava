package test.escenarios;

import java.util.ArrayList;

import excepciones.ContratacionInvalidaException;
import excepciones.EspecialidadInvalidaException;
import excepciones.PosgradoInvalidoException;
import excepciones.TipoPacienteInvalidoException;
import medicos.IMedico;
import medicos.MedicoFactory;
import modulo.Clinica;
import pacientes.IPaciente;
import pacientes.PacienteFactory;

public class ClinicaPacMed {
	public static IMedico m;
	public static IPaciente p;

	public static void setUp() {
		try {
			ArrayList<IMedico> medicos = new ArrayList<>();
			ArrayList<IPaciente> pacientes = new ArrayList<>();
			m = MedicoFactory.getMedico("Juan Perez", "1", "casa", "Mardel", "987654", 1, "Cirujano", "Permanente",
					"Magister", 100);
			p = PacienteFactory.getPaciente("11111111", "Carlos Perez", "155111111", "Mitre 1234",
					"Mar del Plata", 1, "Mayor");
			medicos.add(m);
			pacientes.add(p);
			Clinica.getInstance().setMedicos(medicos);
			Clinica.getInstance().setListaDeAtencion(pacientes);
		} catch (PosgradoInvalidoException | ContratacionInvalidaException | EspecialidadInvalidaException e) {

		} catch (TipoPacienteInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
