package main;

import controlador.Controlador;
import excepciones.TipoHabitacionInvalidaException;
import habitaciones.HabitacionFactory;
import habitaciones.IHabitacion;
import modulo.Clinica;
import vista.Ventana;

/**
 * @author usuario <br>
 * clase main del sistema, crea y agrega los elementos iniciales del sistema y acciona la ventana 
 */
public class Main 
{
	public static void main(String[] args)
	{
		IHabitacion h1=null,h2=null,h3=null;
		try 
		{
			h1 = HabitacionFactory.getHabitacion("Habitacion Compartida", 500);
			h2 = HabitacionFactory.getHabitacion("Habitacion Privada", 1000);
			h3 = HabitacionFactory.getHabitacion("Sala Terapia Intensiva", 1500);
		}
		catch (TipoHabitacionInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		Clinica.getInstance().agregaHabitacion(h1);
		Clinica.getInstance().agregaHabitacion(h2);
		Clinica.getInstance().agregaHabitacion(h3);
		Ventana vista = new Ventana();
		Controlador controlador = new Controlador(vista);
		vista.setVisible(true);
		
	}

}
