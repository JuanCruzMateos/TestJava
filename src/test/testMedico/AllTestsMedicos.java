package test.testMedico;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestContratacion.class, TestEspecialidad.class, TestFactory.class, TestGrado.class })
public class AllTestsMedicos {

}
