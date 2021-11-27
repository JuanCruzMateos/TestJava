package controlador;

import java.io.IOException;

import excepciones.JugoRobinhoException;
import medicos.IMedico;
import modulo.Clinica;
import pacientes.IPaciente;
import persistencia.ClinicaDTO;
import persistencia.UtilClinica;
import util.Mensajes;
import vista.IVista;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
 
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
 


public class ControladorMock extends Controlador {
	
	private String archivoPersistencia=null;
	
	
	public ControladorMock()
	{
		super();
	}
	
	public ControladorMock(IVista vista) {
		super(vista);		
	}
	
	
	public ControladorMock(IVista vista, String nombreBinOrigen, String nombreBinDestino){
		super();
		this.setVista(vista);
		
		
		this.vista.setActionListener(this);
		this.vista.pacientemedicoSeleccionado();
		try
		{
			// primero copio el archivo binario original a una copia para que no se destruya durante el test
			copyFile(nombreBinOrigen, nombreBinDestino);
			this.archivoPersistencia = nombreBinDestino;
			// persisto la clinica con la copia del binario
		    per.abrirInput(nombreBinDestino);
             ClinicaDTO cDTO=(ClinicaDTO)per.leer();
            // System.out.println("lectura con exito");
             UtilClinica.ClinicafromClinicaDTO(cDTO);
             per.cerrarInput();
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			catch(IOException e) 
			{
				System.out.println(e.getMessage());
	        }
		
			//REFRESCA LISTAS, PARA QUE CARGUE NUEVAMENTE LOS MEDICOS DE LA PERSISTENCIA
		this.vista.actualizaListaHabitaciones(Clinica.getInstance().getIteratorHabitaciones());
		this.vista.actualizaAgregaListaMedicos(Clinica.getInstance().getIteratorMedicos());
		this.vista.actualizaAgregaListaPacientes(Clinica.getInstance().getIteratorHPacientes());
	}
	
	
	@Override
	public void guardaPersistencia() {
		
		try
	    {
	        per.abrirOutput(archivoPersistencia);
	        per.escribir(UtilClinica.ClinicaDTOfromClinica());
	        per.cerrarOutput();
	    } catch (Exception ex)
	    {
	        // TODO Auto-generated catch block
	        System.out.println("Exception " + ex.getMessage());
	    }
	}
	
	
	
	public boolean copyFile(String fromFile, String toFile) {
        File origin = new File(fromFile);
        File destination = new File(toFile);
        if (origin.exists()) {
            try {
                InputStream in = new FileInputStream(origin);
                OutputStream out = new FileOutputStream(destination);
                // We use a buffer for the copy (Usamos un buffer para la copia).
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                return true;
            } catch (IOException ioe) {
                ioe.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

}
