package test.habitaciones;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestHabCompartidaComun.class, TestHabPrivada.class, TestSalaTerapiaIntensiva.class })
public class AllTestsHabitaciones {

}
