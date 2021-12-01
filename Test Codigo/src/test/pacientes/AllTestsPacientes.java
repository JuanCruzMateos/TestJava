package test.pacientes;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ JovenTest.class, MayorTest.class, NiñoTest.class, PacienteFactoryTest.class })
public class AllTestsPacientes {

}
