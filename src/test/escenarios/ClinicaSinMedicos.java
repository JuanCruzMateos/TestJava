package test.escenarios;

import java.util.ArrayList;

import medicos.IMedico;
import modulo.Clinica;

public class ClinicaSinMedicos {
	public static void setUp() {
		Clinica.getInstance().setMedicos(new ArrayList<IMedico>());
	}
}
