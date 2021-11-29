package test.escenarios;

import java.util.ArrayList;

import excepciones.ContratacionInvalidaException;
import excepciones.EspecialidadInvalidaException;
import excepciones.PosgradoInvalidoException;
import medicos.IMedico;
import medicos.MedicoFactory;
import modulo.Clinica;

public class ClinicaConMedicos {

	public static IMedico m;

	public static void setUp() {
		try {
			ArrayList<IMedico> medicos = new ArrayList<>();
			m = MedicoFactory.getMedico("Juan Perez", "1", "casa", "Mardel", "987654", 1, "Cirujano", "Permanente",
					"Magister", 100);
			medicos.add(m);
			medicos.add(MedicoFactory.getMedico("Juan Perez", "12", "casa", "Mardel", "987654", 2, "Pediatra",
					"Residente", "Magister", 100));
			medicos.add(MedicoFactory.getMedico("Juan Perez", "123", "casa", "Mardel", "987654", 3, "Clinico",
					"Residente", "Magister", 100));
			medicos.add(MedicoFactory.getMedico("Juan Perez", "1234", "casa", "Mardel", "987654", 4, "Cirujano",
					"Permanente", "Doctorado", 100));
			Clinica.getInstance().setMedicos(medicos);
		} catch (PosgradoInvalidoException | ContratacionInvalidaException | EspecialidadInvalidaException e) {

		}
	}
}
