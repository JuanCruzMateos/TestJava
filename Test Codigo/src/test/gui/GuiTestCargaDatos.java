package test.gui;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JList;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import controlador.ControladorMock;
import pacientes.IPaciente;
import util.Mensajes;

import vista.FalsoOptionPane;
import vista.Ventana;



public class GuiTestCargaDatos {

	 Robot robot;
	 ControladorMock controlador;
	 FalsoOptionPane op = new FalsoOptionPane();
	 Ventana vista = new Ventana();
	 String archivoPersistencia="ClinicaVaciaCpy.bin";
	 
	 
	 public GuiTestCargaDatos()
    {
        try
        {
            robot = new Robot();
        } catch (AWTException e)
        {
        }
    }
	
	
	@Before
    public void setUp() throws Exception
    {	
		
		controlador = new ControladorMock(vista, "ClinicaVacia.bin", archivoPersistencia);		
        controlador.setOptionpane(op);        
        vista.setVisible(true);
        
    }

	@After
    public void tearDown() throws Exception
    {       
		File file = new File(archivoPersistencia);		
		file.delete();
		vista.setVisible(false);	
        	
    }

	
    public void agregaUnPaciente(String nombre, String telefono, String dni, String historiaClinica, String domicilio, String ciudad, String rangoEtario)
    {
        robot.delay(TestUtils.getDelay());
        // Hay una JList dentro de un JScrollPane
        // Obtengo la referencia a la JList que contiene los pacientes     
        // JList pacientes = (JList) TestUtils.getComponentForName((Ventana)controlador.getVentana(), "ListaPacientes");
        // Obtengo la referencia al boton de borrar
        // JButton botonBorrar = (JButton) TestUtils.getComponentForName((Ventana)controlador.getVentana(), "btnEliminar");
        
        JButton botonAgregar = (JButton) TestUtils.getComponentForName((Ventana)controlador.getVentana(), "btnAgregar");
        JRadioButton radioBTNPaciente = (JRadioButton) TestUtils.getComponentForName((Ventana)controlador.getVentana(), "RadioBTNPaciente");
        JRadioButton radioBTNJoven = (JRadioButton) TestUtils.getComponentForName((Ventana)controlador.getVentana(), "RadioBTNJoven");
        JRadioButton radioBTNMayor = (JRadioButton) TestUtils.getComponentForName((Ventana)controlador.getVentana(), "RadioBTNMayor");
        JRadioButton radioBTNNiño = (JRadioButton) TestUtils.getComponentForName((Ventana)controlador.getVentana(), "RadioBTNNi�o");
        JTextField txtFieldNombreAp  = (JTextField)TestUtils.getComponentForName((Ventana)controlador.getVentana(), "txtFieldNombreAp");
        JTextField txtFieldTelefono  = (JTextField)TestUtils.getComponentForName((Ventana)controlador.getVentana(), "txtFieldTelefono");
        JTextField txtFieldHistoriamatricula  = (JTextField)TestUtils.getComponentForName((Ventana)controlador.getVentana(), "txtFieldHistoriamatricula");
        JTextField txtFieldDni  = (JTextField)TestUtils.getComponentForName((Ventana)controlador.getVentana(), "txtFieldDni");
        JTextField txtFieldDomicilio  = (JTextField)TestUtils.getComponentForName((Ventana)controlador.getVentana(), "txtFieldDomicilio");
        JTextField txtFieldCiudad  = (JTextField)TestUtils.getComponentForName((Ventana)controlador.getVentana(), "txtFieldCiudad");
        
        // Primero tengo que seleccionar el radioButton
        TestUtils.clickComponent(radioBTNPaciente, robot);        
        
        // Nombre y Apellido
        TestUtils.clickComponent(txtFieldNombreAp, robot);
        TestUtils.borraJTextField(txtFieldNombreAp, robot);        
        TestUtils.tipeaTexto(nombre, robot);
        
        // Telefono
        TestUtils.clickComponent(txtFieldTelefono, robot);
        TestUtils.borraJTextField(txtFieldTelefono, robot);        
        TestUtils.tipeaTexto(telefono, robot);
        
        // Historia clinica
        TestUtils.clickComponent(txtFieldHistoriamatricula, robot);
        TestUtils.borraJTextField(txtFieldHistoriamatricula, robot);        
        TestUtils.tipeaTexto(historiaClinica, robot);
        
        // DNI
        TestUtils.clickComponent(txtFieldDni, robot);
        TestUtils.borraJTextField(txtFieldDni, robot);        
        TestUtils.tipeaTexto(dni, robot);
     
        // Domicilio
        TestUtils.clickComponent(txtFieldDomicilio, robot);
        TestUtils.borraJTextField(txtFieldDomicilio, robot);        
        TestUtils.tipeaTexto(domicilio, robot);
        
        // Ciudad
        TestUtils.clickComponent(txtFieldCiudad, robot);
        TestUtils.borraJTextField(txtFieldCiudad, robot);        
        TestUtils.tipeaTexto(ciudad, robot);
        
        if (rangoEtario.equalsIgnoreCase("joven"))
        	TestUtils.clickComponent(radioBTNJoven, robot);
        else if (rangoEtario.equalsIgnoreCase("mayor"))
        	TestUtils.clickComponent(radioBTNMayor, robot);
        else if (rangoEtario.equalsIgnoreCase("niño"))
        	TestUtils.clickComponent(radioBTNNiño, robot);
        
        robot.delay(250);
        // presiono el boton agregar
        TestUtils.clickComponent(botonAgregar, robot);
        
        System.out.println("Agregado");
        robot.delay(250);
        
    }
	
	@Test
	public void agrega3PacientesOk()
	{
		// CUIDADO!!! PARA QUE NO FALLE ESTE TEST, DEBEMOS ASEGURARNOS QUE EL EL BEFORE SE BORREN TODOS LOS PACIENTES
		JList pacientes = (JList) TestUtils.getComponentForName((Ventana)controlador.getVentana(), "ListaPacientes");
		
		agregaUnPaciente("Freddy Krueger", "223 1563322", "33456888", "1", "Elm Street 666", "Springwood", "mayor");
		agregaUnPaciente("Jason Voorhees", "223 1542277", "15680230", "2", "Bloody Street 1", "Crystal Lake", "joven");
		agregaUnPaciente("Alien", "223 1555566", "50945798", "3", "OuterSpace 4", "Andromeda Galaxy", "ni�o");
		robot.delay(1000);
		//verifico los resultados
        Assert.assertEquals("Deberia haber tres pacientes en la lista", 3, pacientes.getModel().getSize());
	}
	
	@Test
	public void agrega1PacienteOk()
	{
		JList pacientes = (JList) TestUtils.getComponentForName((Ventana)controlador.getVentana(), "ListaPacientes");
		int cantidadAntes = pacientes.getModel().getSize();		
		agregaUnPaciente("Uzumaki Naruto", "223 3212322", "534522578", "8", "Kunai 12", "Konoha", "mayor");
		int cantidadDespues = pacientes.getModel().getSize();		
		robot.delay(1000);
		Assert.assertEquals("Deberia haber un paciente mas en la lista", (cantidadAntes+1), cantidadDespues);
	}
	
	@Test
	public void agregaPacienteRepetido()
	{
		agregaUnPaciente("Pedrito Ramirez", "223 1563322", "33456888", "11", "Elm Street 666", "Springwood", "mayor");
		agregaUnPaciente("Pedrito Ramirez", "223 1563322", "33456888", "11", "Elm Street 666", "Springwood", "mayor");		
		Assert.assertEquals("Mensaje incorrecto, deberia decir "+Mensajes.Error_Paciente_Repetido.getValor(), Mensajes.Error_Paciente_Repetido.getValor(), op.getMensaje());
		if (Mensajes.Error_Paciente_Repetido.getValor() == op.getMensaje())
		{
			System.out.println("Ignorar ingresado, excepcion por paciente repetido");
		}
			
	}
	
	@Test
	public void presionarAgregarSinSeleccionarPacienteOMedico()
	{
		JButton botonAgregar = (JButton) TestUtils.getComponentForName((Ventana)controlador.getVentana(), "btnAgregar");
		
		// presiono agregar sin elegir si es un medico o un paciente
		TestUtils.clickComponent(botonAgregar, robot);
		robot.delay(1000);
		Assert.assertEquals("Mensaje incorrecto, deberia decir "+Mensajes.Seleccione_MedicoOPaciente.getValor(), Mensajes.Seleccione_MedicoOPaciente.getValor(), op.getMensaje());
		
	}
	
	@Test
	public void agregarPacienteSinElegirRangoEtario()
	{
		JButton botonAgregar = (JButton) TestUtils.getComponentForName((Ventana)controlador.getVentana(), "btnAgregar");
		JRadioButton radioBTNPaciente = (JRadioButton) TestUtils.getComponentForName((Ventana)controlador.getVentana(), "RadioBTNPaciente");
		
		// hago click en el radio button de paciente
		TestUtils.clickComponent(radioBTNPaciente, robot);
		robot.delay(200);
		// presiono el boton agregar sin haber seleccionado rango etario
        TestUtils.clickComponent(botonAgregar, robot);
        robot.delay(1000);
        Assert.assertEquals("Mensaje incorrecto, deberia decir "+Mensajes.Error_Seleccionar_RanroEtario.getValor(), Mensajes.Error_Seleccionar_RanroEtario.getValor(), op.getMensaje());        
        
	}

	@Test
	public void agregarPacienteSinPonerDatos()
	{
		JButton botonAgregar = (JButton) TestUtils.getComponentForName((Ventana)controlador.getVentana(), "btnAgregar");
		JRadioButton radioBTNPaciente = (JRadioButton) TestUtils.getComponentForName((Ventana)controlador.getVentana(), "RadioBTNPaciente");
		JRadioButton radioBTNJoven = (JRadioButton) TestUtils.getComponentForName((Ventana)controlador.getVentana(), "RadioBTNJoven");
        
        
		// hago click en el radio button de paciente
		TestUtils.clickComponent(radioBTNPaciente, robot);
		robot.delay(200);
		// presiono el boton radioBTNJoven
        TestUtils.clickComponent(radioBTNJoven, robot);
        robot.delay(200);
        // presiono el boton agregar sin seleccionar ningun dato
        TestUtils.clickComponent(botonAgregar, robot);
        robot.delay(1000);
        
        Assert.assertEquals("Mensaje incorrecto, deberia decir "+Mensajes.Error_Numero_HistoriaClinica.getValor(), Mensajes.Error_Numero_HistoriaClinica.getValor(), op.getMensaje());        
        
	}


		
}
	
	

