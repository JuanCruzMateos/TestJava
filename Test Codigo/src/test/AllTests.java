package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.clinica.AllTestsClinica;
import test.habitaciones.AllTestsHabitaciones;
import test.medicos.AllTestsMedicos;
import test.pacientes.AllTestsPacientes;
import test.persistencia.PersistenciaTest;

@RunWith(Suite.class)
@SuiteClasses({ AllTestsClinica.class, AllTestsMedicos.class, AllTestsHabitaciones.class, AllTestsPacientes.class,
		PersistenciaTest.class })
public class AllTests {

}
