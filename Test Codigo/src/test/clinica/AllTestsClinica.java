package test.clinica;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ClinicaConMedicosTest.class, ClinicaSinMedicosTest.class, EliminarPacienteClinicaConDatosTest.class,
		IngresoPacienteClinicaConDatosTest.class, IngresoPacienteClinicaVaciaTest.class, IngresoPacienteTest.class,
		PrestacionesTest.class })
public class AllTestsClinica {

}
