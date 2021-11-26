package test;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import pacientes.IPaciente;
import pacientes.Paciente;
//import controlador.Controlador;
//import modelo.ConjuntoUsuarios;
import util.Mensajes;
import vista.MiOptionPane;
import vista.Ventana;
import java.awt.Point;

public class GuiTestConDatos {

	Robot robot;
	Controlador controlador;
	MiOptionPane op = new MiOptionPane();
	Ventana vista = new Ventana();

	public GuiTestConDatos() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
		}
	}

	@Before
	public void setUp() throws Exception {
		// Ventana vista = new Ventana();
		controlador = new Controlador(vista);

		controlador.setOptionpane(op);

	}

	@After
	public void tearDown() throws Exception {
		// Ventana ventana = (Ventana) controlador.getVentana();
		vista.setVisible(true);
	}

	@Test
	public void testEliminaOK() {
		robot.delay(TestUtils.getDelay());
		JList pacientes = (JList) TestUtils.getComponentForName((Ventana) controlador.getVentana(), "ListaPacientes");
		int numberOfElements = pacientes.getModel().getSize();

		IPaciente pac = (IPaciente) pacientes.getModel().getElementAt(4);
		System.out.println(pac.getNombre());
		Point indexToLocation = pacientes.indexToLocation(1);
		System.out.println(indexToLocation);
//        
//        if (numberOfElements!=0)
//        {
//        	TestUtils.clickComponent(pacientes, robot);
//        	Ventana myVentana = (Ventana) controlador.getVentana();
//        	IPaciente mypaciente = myVentana.getPacienteSeleccionado();
//        	System.out.println(mypaciente.getNombre());
//        }

		// obtengo las referencias a los componentes necesarios
//        JTextField nombre = (JTextField) TestUtils.getComponentForName(controlador.getVentana(), "jtNombreLogin");
//        JTextField contrasena =
//            (JTextField) TestUtils.getComponentForName(controlador.getVentana(), "jtContrasenaLogin");
//        JButton aceptarLog = (JButton) TestUtils.getComponentForName(controlador.getVentana(), "botonLogin");
//
//        //lleno los JTextField
//        TestUtils.clickComponent(nombre, robot);
//        TestUtils.tipeaTexto("juan", robot);
//        TestUtils.clickComponent(contrasena, robot);
//        TestUtils.tipeaTexto("Qwerty123", robot);
//        TestUtils.clickComponent(aceptarLog, robot);
//        //verifico los resultados
//        Assert.assertEquals("Deberia coincidir el nombre de usuario con el nombre ingresado", "juan",
//                            controlador.getUsuarioactual().getNombre());
//        Assert.assertEquals("Memnsaje incorrecto, deber�a decir"+Mensajes.LOGIN_OK.getValor(),Mensajes.LOGIN_OK.getValor(),op.getMensaje());
//        
	}

}
