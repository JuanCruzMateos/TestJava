package test.gui;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import controlador.ControladorMock;
import pacientes.IPaciente;
import pacientes.Paciente;
import persistencia.IPersistencia;
import persistencia.PersistenciaBIN;
//import controlador.Controlador;
//import modelo.ConjuntoUsuarios;
import util.Mensajes;
import vista.MiOptionPane;
import vista.Ventana;
import java.awt.Point;


public class GuiTestEliminaDatosConDatos {

	 Robot robot;
	 ControladorMock controlador;
	 MiOptionPane op = new MiOptionPane();
	 Ventana vista = new Ventana();
	 String archivoPersistencia="ClinicaConDatosCpy.bin";
	 
	
	 
	 public GuiTestEliminaDatosConDatos()
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

		controlador = new ControladorMock(vista, "ClinicaConDatos.bin", "ClinicaConDatosCpy.bin");			
        controlador.setOptionpane(op);        
        vista.setVisible(true);
        
    }

	@After
    public void tearDown() throws Exception
    {
               
		
        File file = new File("ClinicaConDatosCpy.bin");		
		file.delete();
		vista.setVisible(false);

    }

	@Test
    public void testEliminaOKUnoSolo()
    {
		System.out.println("test uno solo");
        robot.delay(TestUtils.getDelay());
        // Hay una JList dentro de un JScrollPane
        // Obtengo la referencia a la JList que contiene los pacientes     
        JList pacientes = (JList) TestUtils.getComponentForName((Ventana)controlador.getVentana(), "ListaPacientes");
        // Obtengo la referencia al boton de borrar
        JButton botonBorrar = (JButton) TestUtils.getComponentForName((Ventana)controlador.getVentana(), "btnEliminar");
        
        // Obtengo la referencia al JScrollPane
        JScrollPane contenedor = (JScrollPane) TestUtils.getComponentForName((Ventana)controlador.getVentana(), "scrollPaneListaPaciente");
        
        // Obtengo la posicion del contenedor (JScrollPane)
        Point posicion = contenedor.getLocationOnScreen();        
        System.out.println("contenedor pacientes position = "+posicion);
        
        
        //  Voy a hacer click cerca del inicio del contenido --> 20% del ancho
        int anchoContenedor = (int) (contenedor.getWidth()*0.2);
        
        // Obtengo la cantidad de elementos que tiene actualmente la lista
        int numeroDeElementos = pacientes.getModel().getSize();
        
       
        if (numeroDeElementos>0) {
        	int myPos=1;
        	if (numeroDeElementos>1)
        		myPos = (int)(numeroDeElementos/2);
        	System.err.println("Cantidad de elementos "+numeroDeElementos);	
        	System.out.println("Borrando elemento numero "+ myPos);
        	IPaciente pac = (IPaciente) pacientes.getModel().getElementAt(myPos);
            System.out.println("Borrando registro del paciente "+pac.getNombre());
            
            // Obtengo la posicion del elemento en la lista, referenciado a su contenedor
            Point indexToLocation = pacientes.indexToLocation(myPos);
            System.out.println(indexToLocation);
            
            //  BORRAR AL FINALIZAR -- ES PARA TESTEAR
            robot.delay(500);
            System.out.println("Ya espere 3 segundos");            
            
            
            
            // Nos posicionamos sobre el elemento que deseamos eliminar
            int x = (int) posicion.getX() + (int) indexToLocation.getX()+ anchoContenedor;
            int y = (int) posicion.getY() + (int) indexToLocation.getY();
            
            // movemos el mouse
            robot.mouseMove(x, y);
            System.out.println("moviendo mouse a x = "+x+" - y = "+y);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            robot.delay(500);
            // hago click en el boton borrar
            TestUtils.clickComponent(botonBorrar, robot); 
            

            //  BORRAR AL FINALIZAR -- ES PARA TESTEAR  -- QUE NO CIERRE LA VENTANA
            robot.delay(TestUtils.getDelay());
            robot.delay(500);
            System.out.println("Y 3 segundos mas por las dudas");            
        }
        int numeroDeElementosDespues = pacientes.getModel().getSize();        
        Assert.assertEquals("La lista deberia tener un elemento menos", numeroDeElementosDespues, (numeroDeElementos-1));
        robot.delay(2000);
    }
	
	@Test
    public void testEliminaOKTodos()
    {
		System.out.println("test Todos");
		System.out.println("testeando eliminar todos los pacientes");
        robot.delay(TestUtils.getDelay());
        // Hay una JList dentro de un JScrollPane
        // Obtengo la referencia a la JList que contiene los pacientes     
        JList pacientes = (JList) TestUtils.getComponentForName((Ventana)controlador.getVentana(), "ListaPacientes");
        // Obtengo la referencia al boton de borrar
        JButton botonBorrar = (JButton) TestUtils.getComponentForName((Ventana)controlador.getVentana(), "btnEliminar");
        
        // Obtengo la referencia al JScrollPane
        JScrollPane contenedor = (JScrollPane) TestUtils.getComponentForName((Ventana)controlador.getVentana(), "scrollPaneListaPaciente");
        
        // Obtengo la posicion del contenedor (JScrollPane)
        Point posicion = contenedor.getLocationOnScreen();        
        System.out.println("contenedor pacientes position = "+posicion);
        
        
        //  Voy a hacer click cerca del inicio del contenido --> 20% del ancho
        int anchoContenedor = (int) (contenedor.getWidth()*0.2);
        
        // Obtengo la cantidad de elementos que tiene actualmente la lista
        int numeroDeElementos = pacientes.getModel().getSize();
        
        int myPos=numeroDeElementos-1;
        while (myPos>=0) {        	
        	
        	System.err.println("Cantidad de elementos "+numeroDeElementos);	
        	System.out.println("Borrando elemento numero "+ myPos);
        	IPaciente pac = (IPaciente) pacientes.getModel().getElementAt(myPos);
            System.out.println("Borrando registro del paciente "+pac.getNombre());
            
            // Obtengo la posicion del elemento en la lista, referenciado a su contenedor
            Point indexToLocation = pacientes.indexToLocation(myPos);
            System.out.println(indexToLocation);
            
            //  BORRAR AL FINALIZAR -- ES PARA TESTEAR
            robot.delay(500);
            System.out.println("Ya espere 3 segundos");            
            //  BORRAR AL FINALIZAR -- ES PARA TESTEAR
            
            
            // Nos posicionamos sobre el elemento que deseamos eliminar
            int x = (int) posicion.getX() + (int) indexToLocation.getX()+ anchoContenedor;
            int y = (int) posicion.getY() + (int) indexToLocation.getY()+1;
            
            // movemos el mouse
            robot.mouseMove(x, y);
            System.out.println("moviendo mouse a x = "+x+" - y = "+y);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            robot.delay(500);
            // hago click en el boton borrar
            TestUtils.clickComponent(botonBorrar, robot); 
            
            //  BORRAR AL FINALIZAR -- ES PARA TESTEAR  -- QUE NO CIERRE LA VENTANA
            robot.delay(TestUtils.getDelay());
            robot.delay(500);
            System.out.println("Y 3 segundos mas por las dudas");   
            myPos --;
        }
        Assert.assertEquals("La lista deberia quedar vacia", 0, pacientes.getModel().getSize());
        robot.delay(2000);
    }
	
	@Test
    public void testEliminaTodosMasUnoMal()
    {
		System.out.println("\ntest todos mas 1");
		System.out.println("testeando eliminar todos los pacientes y un poquito mas");
        robot.delay(TestUtils.getDelay());
        // Hay una JList dentro de un JScrollPane
        // Obtengo la referencia a la JList que contiene los pacientes     
        JList pacientes = (JList) TestUtils.getComponentForName((Ventana)controlador.getVentana(), "ListaPacientes");
        // Obtengo la referencia al boton de borrar
        JButton botonBorrar = (JButton) TestUtils.getComponentForName((Ventana)controlador.getVentana(), "btnEliminar");
        
        // Obtengo la referencia al JScrollPane
        JScrollPane contenedor = (JScrollPane) TestUtils.getComponentForName((Ventana)controlador.getVentana(), "scrollPaneListaPaciente");
        
        // Obtengo la posicion del contenedor (JScrollPane)
        Point posicion = contenedor.getLocationOnScreen();        
        System.out.println("contenedor pacientes position = "+posicion);
        
        
        //  Voy a hacer click cerca del inicio del contenido --> 20% del ancho
        int anchoContenedor = (int) (contenedor.getWidth()*0.2);
        
        // Obtengo la cantidad de elementos que tiene actualmente la lista
        int numeroDeElementos = pacientes.getModel().getSize();
        
        int myPos=numeroDeElementos-1;
        while (myPos>=0) {        	
        	
        	System.err.println("Cantidad de elementos "+numeroDeElementos);	
        	System.out.println("Borrando elemento numero "+ myPos);
        	IPaciente pac = (IPaciente) pacientes.getModel().getElementAt(myPos);
            System.out.println("Borrando registro del paciente "+pac.getNombre());
            
            // Obtengo la posicion del elemento en la lista, referenciado a su contenedor
            Point indexToLocation = pacientes.indexToLocation(myPos);
            System.out.println(indexToLocation);
            
            //  BORRAR AL FINALIZAR -- ES PARA TESTEAR
            robot.delay(500);
            System.out.println("Ya espere 3 segundos");            
            //  BORRAR AL FINALIZAR -- ES PARA TESTEAR
            
            
            // Nos posicionamos sobre el elemento que deseamos eliminar
            int x = (int) posicion.getX() + (int) indexToLocation.getX()+ anchoContenedor;
            int y = (int) posicion.getY() + (int) indexToLocation.getY()+1;
            
            // movemos el mouse
            robot.mouseMove(x, y);
            System.out.println("moviendo mouse a x = "+x+" - y = "+y);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            robot.delay(500);
            // hago click en el boton borrar
            TestUtils.clickComponent(botonBorrar, robot); 
            
            //  BORRAR AL FINALIZAR -- ES PARA TESTEAR  -- QUE NO CIERRE LA VENTANA
            robot.delay(TestUtils.getDelay());
            robot.delay(500);
            System.out.println("Y 3 segundos mas por las dudas");   
            myPos --;
        }
        
        try
        {
        	System.err.println("Cantidad de elementos "+numeroDeElementos);	
        	System.out.println("Borrando elemento numero "+ myPos);
        	IPaciente pac = (IPaciente) pacientes.getModel().getElementAt(myPos);
            System.out.println("Borrando registro del paciente "+pac.getNombre());
            
            // Obtengo la posicion del elemento en la lista, referenciado a su contenedor
            Point indexToLocation = pacientes.indexToLocation(myPos);
            System.out.println(indexToLocation);
            
            //  BORRAR AL FINALIZAR -- ES PARA TESTEAR
            robot.delay(500);
            System.out.println("Ya espere 3 segundos");            
            //  BORRAR AL FINALIZAR -- ES PARA TESTEAR
            
            
            // Nos posicionamos sobre el elemento que deseamos eliminar
            int x = (int) posicion.getX() + (int) indexToLocation.getX()+ anchoContenedor;
            int y = (int) posicion.getY() + (int) indexToLocation.getY()+1;
            
            // movemos el mouse
            robot.mouseMove(x, y);
            System.out.println("moviendo mouse a x = "+x+" - y = "+y);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            robot.delay(500);
            // hago click en el boton borrar
            TestUtils.clickComponent(botonBorrar, robot); 
            
            //  BORRAR AL FINALIZAR -- ES PARA TESTEAR  -- QUE NO CIERRE LA VENTANA
            robot.delay(TestUtils.getDelay());
            robot.delay(500);
            System.out.println("Y 3 segundos mas por las dudas");
            Assert.assertTrue("Debia saltar la excepcion y no lo hizo", false);
        } catch (IndexOutOfBoundsException e)  {
        	
        	Assert.assertTrue("Siempre deberia saltar la excepcion al intentar borrar mas de la cantidad disponible", true);
        }
        
          
        
        
        robot.delay(2000);
    }
}
