package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import excepciones.CantDiasInvalidosException;
import excepciones.ContratacionInvalidaException;
import excepciones.EgresoInvalidoException;
import excepciones.EspecialidadInvalidaException;
import excepciones.FechaInvalidaException;
import excepciones.HistoriaInvalidaException;
import excepciones.HonorarioInvalidoException;
import excepciones.JugoRobinhoException;
import excepciones.MatriculaInvalidaException;
import excepciones.MedicoRepetidoException;
import excepciones.NoFueLlamadoException;
import excepciones.NoHayEspacioException;
import excepciones.NoHayPacienteEsperandoException;
import excepciones.PacienteRepetidoException;
import excepciones.PacienteYaIngresadoException;
import excepciones.PosgradoInvalidoException;
import excepciones.ReporteInvalidoException;
import excepciones.SeleccionIncorrectaException;
import excepciones.TipoPacienteInvalidoException;
import habitaciones.IHabitacion;
import medicos.IMedico;
import medicos.MedicoFactory;
import modulo.Clinica;
import pacientes.IPaciente;
import pacientes.PacienteFactory;
import persistencia.ClinicaDTO;
import persistencia.IPersistencia;
import persistencia.PersistenciaBIN;
import persistencia.UtilClinica;
import util.Mensajes;
import vista.IVista;
import vista.InterfazOptionPanel;
import vista.MiOptionPane;
/**
 * @author usuario <br>
 *Clase que representa al controlador, se encarga de independizar la vista del modelo
 */
public class Controlador implements ActionListener
{
	private IVista vista=null;
	private InterfazOptionPanel optionpane = new MiOptionPane();
	IPersistencia per = new PersistenciaBIN();
	
	//Ambulancia y clinica son singleton, por lo que no necesita un atributo 

	public void setOptionpane(InterfazOptionPanel optionpane)
    {
		this.optionpane = optionpane;
    }
	
	public IVista getVentana()
	{
		return this.vista;
	}
	
	/**
	 * constructor de la clase, parametro IVista, se realiza la lectura de del archivo que contiene los objetos del sistema
	 * @param vista : permite llamar al metodo que sea necesario por el evento recibido
	 */
	public Controlador(IVista vista)
	{
		this.vista= vista;
		this.vista.setActionListener(this);
		this.vista.pacientemedicoSeleccionado();
		try
		{
		     per.abrirInput("Clinica.bin");
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

	

	/**
	 *metodo que permite llamar al metodo de la interfaz IVista para la accion correspondiente segun el tipo de evento recibido de la interfaz de usuario<br>
	 *al final de cada accion persiste el estado actual del sistema
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String comando = e.getActionCommand();
		this.vista.pacientemedicoSeleccionado();
		if (comando.equalsIgnoreCase("Agregar"))  
			this.Agregar(comando);
		else if (comando.equalsIgnoreCase("Eliminar"))  
			this.Eliminar();		
		else if (comando.equalsIgnoreCase("PRESTACION"))
			this.prestacion();		
		else if (comando.equalsIgnoreCase("FACTURAR")) 	//hace el egreso
			this.Facturar();			
		else if (comando.equalsIgnoreCase("REPORTE")) 
			this.Reporte();
		else if (comando.equalsIgnoreCase("LlamaPaciente")) 
			this.LlamarPaciente();
		else if(comando.equalsIgnoreCase("INGRESO"))
			this.Ingreso();
		else if(comando.equalsIgnoreCase("DESELECCIONAR"))
			this.Deseleccionar();
			
		
		this.vista.soltarSeleccion();
		this.guardaPersistencia();
	}


	
	/**
	 * metodo que acciona la invocacion del metodo agrega pacientes y medicos, invoca a otro metodo para actualizar las listas y tambien se encarga de validar los datos <br>
	 * @param comando indica el tipo de objeto a crear y agregar
	 */
	public void Agregar(String comando)
	{
		if (this.vista.getTipoAgregarSeleccionado()=="Paciente") //recibe action comand
		{	
			try 
			{
				String rango = this.vista.getRangoEtario();
				IPaciente paciente = PacienteFactory.getPaciente(this.vista.getDNINuevo(),this.vista.getNomNuevo(),this.vista.getTelNuevo(), this.vista.getDomNuevo(), this.vista.getCiudadNuevo(), this.vista.getMatriculaNuevo(), this.vista.getRangoEtario());
				Clinica.getInstance().ingresoPaciente(paciente);
				this.vista.actualizaAgregaListaPacientes(Clinica.getInstance().getIteratorHPacientes());
			}
			catch (PacienteRepetidoException pre) 
			{
				this.optionpane.ShowMessage(null, Mensajes.Error_Paciente_Repetido.getValor());
			}	
			catch(PacienteYaIngresadoException pi)
			{
				this.optionpane.ShowMessage(null, Mensajes.Error_Paciente_Ya_Ingresado.getValor());	
			}						
			catch (TipoPacienteInvalidoException tpie) 
			{ 
				this.optionpane.ShowMessage(null, Mensajes.Error_Tipo_Paciente_Invalido.getValor());
			} 
			catch (SeleccionIncorrectaException e) 
			{
				this.optionpane.ShowMessage(null,Mensajes.Error_Seleccionar_RanroEtario.getValor());
			}
			catch(HistoriaInvalidaException hie)
			{
				this.optionpane.ShowMessage(null,Mensajes.Error_Numero_HistoriaClinica.getValor());	
		    }
			catch (MatriculaInvalidaException mie)
			{
				this.optionpane.ShowMessage(null, Mensajes.Error_Matricula_Invalida.getValor());
			}

		}
		else   //hacer ifs para comprobar si esta presionado: permanente, residente etc...
			if(this.vista.getTipoAgregarSeleccionado()=="Medico")
			{	
				try 
				{
					IMedico medico = MedicoFactory.getMedico(this.vista.getNomNuevo(),this.vista.getDNINuevo(), this.vista.getDomNuevo(), this.vista.getCiudadNuevo(), this.vista.getTelNuevo(), this.vista.getMatriculaNuevo(), this.vista.getEspecialidad(), this.vista.getContratacion(), this.vista.getPosgrado(), this.vista.getHonorario());
					Clinica.getInstance().agregaMedico(medico);
					this.vista.actualizaAgregaListaMedicos(Clinica.getInstance().getIteratorMedicos());
				} 
				catch (PosgradoInvalidoException pos) 
				{
					this.optionpane.ShowMessage(null, Mensajes. Error_Seleccione_Posgrado.getValor());
				} 
				catch (ContratacionInvalidaException con) 
				{
					this.optionpane.ShowMessage(null,Mensajes.Error_Seleccione_Contratacion.getValor());
				} 
				catch (MedicoRepetidoException med) 
				{
					this.optionpane.ShowMessage(null,Mensajes.Error_Medico_Repetido.getValor());
				}
				catch (HonorarioInvalidoException hon)
				{
					this.optionpane.ShowMessage(null,Mensajes.Error_Honorario_Incorrecto.getValor());	
				} 
				catch (EspecialidadInvalidaException e) 
				{
					this.optionpane.ShowMessage(null, Mensajes.Error_Seleccione_Especialidad.getValor());
				} 	
				catch(HistoriaInvalidaException hie)
				{
					this.optionpane.ShowMessage(null,Mensajes.Error_Numero_HistoriaClinica.getValor());	
			    }
				catch (MatriculaInvalidaException mie)
				{
					this.optionpane.ShowMessage(null, Mensajes.Error_Matricula_Invalida.getValor());
				}
		}
		else this.optionpane.ShowMessage(null, Mensajes.Seleccione_MedicoOPaciente.getValor());	
	}
	
	/**
	 * metodo que acciona el metodo que elimina medicos y pacientes de las listas del sistema de la Interfaz IVista, se invoca al metodo actualiza listas y se valida los datos <br> 
	 */
	public void Eliminar()
	{
		IPaciente paciente= this.vista.getPacienteSeleccionado();
		IMedico medico= this.vista.getMedicoSeleccionado();
		if  (paciente==null && medico==null)
		 	this.optionpane.ShowMessage(null, Mensajes.Seleccione_MedicoOPaciente.getValor());
		if (paciente!=null && medico!=null)
			this.optionpane.ShowMessage(null,Mensajes.Seleccione_UnMedico_o_UnPaciente.getValor());
		else
		{	
			if (paciente!=null) //algun socio selecciono
			{	
				try
				{
					Clinica.getInstance().eliminaSinEgreso(paciente);
					this.vista.actualizaEliminaListaPacientes(Clinica.getInstance().getIteratorHPacientes(),paciente);
				}
				catch(JugoRobinhoException r)
				{
					this.optionpane.ShowMessage(null, Mensajes.Error_Eliminar_Paciente.getValor());	
				}
			}
			if (medico!=null)  
			{
				this.vista.actualizaEliminaListaMedicos(Clinica.getInstance().getIteratorMedicos(),medico);
				Clinica.getInstance().eliminaMedico(medico);
			}
		}	
	}
	

	
	/**
	 * metodo que acciona la realizacion de una prestacion en el modelo y valida los datos antes
	 */
	public void prestacion()
	{
		IPaciente paciente = this.vista.getPacienteSeleccionado();
		if (paciente!=null)   //hay alguien
		{
			IMedico medico = this.vista.getMedicoSeleccionado();
			if(medico!=null) //tiene medico
			{
				IHabitacion habitacion=this.vista.getHabitacionSeleccionada();
				if(habitacion!=null)
				{
					try 
					{
						int cantDias=this.vista.getCantidadDias();
						Clinica.getInstance().Prestaciones(paciente, medico,cantDias,habitacion);
						this.vista.actualizaListaHabitaciones(Clinica.getInstance().getIteratorHabitaciones());
					}
					catch(NoHayEspacioException e2)
					{
						this.optionpane.ShowMessage(null,Mensajes.Error_Habitacion_Ocupada.getValor());
					}
					catch(NoFueLlamadoException e3)
					{
						this.optionpane.ShowMessage(null,Mensajes.Error_Prestacion_Llamado.getValor());
					}
					catch(CantDiasInvalidosException cd)
					{
						this.optionpane.ShowMessage(null, Mensajes.Error_Dias_Invalidos.getValor());
					}	
					catch(NumberFormatException f1)
					{
						this.optionpane.ShowMessage(null,  Mensajes.Error_Dias_Invalidos.getValor());
					}
				}
				else this.optionpane.ShowMessage(null, Mensajes.Seleccione_Habitacion.getValor());
			}
			else this.optionpane.ShowMessage(null, Mensajes.Seleccione_Medico.getValor());
		}
		else this.optionpane.ShowMessage(null, Mensajes.Seleccione_Paciente.getValor());
	}
	
	
	public void Facturar()
	{
		IPaciente pac = this.vista.getPacienteSeleccionado();  //vamos a unificar seguramente
		if(pac!=null)
		{
			try 
			{
				String factura = Clinica.getInstance().egreso(pac, new GregorianCalendar());  //cambiar el gregorian calendar
				this.vista.actualizaFactura(factura);  //aca mandaria para mostrar por pantalla
			}
			catch(EgresoInvalidoException egreso)
			{
				this.optionpane.ShowMessage(null, Mensajes.Error_Prestaciones.getValor());
			}
		}
		else
			this.optionpane.ShowMessage(null, Mensajes.Seleccione_Paciente.getValor());
	}
	
	
	public void Reporte()
	{
		IMedico medico = this.vista.getMedicoSeleccionado();
		if (medico!=null)
		{
			try 
			{
				GregorianCalendar f1=this.vista.getFecha1();
				GregorianCalendar f2=this.vista.getFecha2();
				String reporte=Clinica.getInstance().ReporteFechas(f1, f2,medico);
				this.vista.actualizaFactura(reporte);
			} catch (FechaInvalidaException e1) 
			{
				// TODO Auto-generated catch block
				this.optionpane.ShowMessage(null,Mensajes.Error_Fecha_Invalida.getValor());
			} 
			catch(ReporteInvalidoException rep)
			{
				this.optionpane.ShowMessage(null, Mensajes.Error_Medico_SinConsultas.getValor());
			}
		}
		else this.optionpane.ShowMessage(null, Mensajes.Seleccione_Medico.getValor());
	}

	
	/**
	 * metodo que acciona al metodo llamar paciente del modelo, valida que haya pacientes en espera 
	 */
	public void LlamarPaciente()
	{
		try 
		{
			IPaciente pac=Clinica.getInstance().llamarPaciente();
			this.optionpane.ShowMessage(null, Mensajes.Paciente_Llamado_Fue.getValor()+pac.getNombre());
		}
		catch (NoHayPacienteEsperandoException e1) 
		{
			this.optionpane.ShowMessage(null, Mensajes.Error_No_Pacientes_Esperando.getValor());	
		}
	}
	
	/**
	 * metodo que acciona al metodo ingreso de la clinica, valida que el paciente no este ingresado 
	 */
	public void Ingreso()
	{
		IPaciente p= this.vista.getPacienteSeleccionado();
		if (p!=null)
		{
			try 
			{
				Clinica.getInstance().ingresoNuevamente(p);
			}
			catch(PacienteYaIngresadoException pi)
			{
				this.optionpane.ShowMessage(null, Mensajes.Error_Paciente_Ya_Ingresado.getValor());	
			}
		}
		else this.optionpane.ShowMessage(null, Mensajes.Seleccione_Paciente.getValor());	
	}
	
		
	public void Deseleccionar()
	{
		this.vista.soltarSeleccion();
	}
	
	/**
	 * metodo que guarda el estado actual del sistema luego de que alguna accion se produjo
	 */
	public void guardaPersistencia()
	{
		try
	    {
	        per.abrirOutput("Clinica.bin");
	        per.escribir(UtilClinica.ClinicaDTOfromClinica());
	        per.cerrarOutput();
	    } catch (Exception ex)
	    {
	        // TODO Auto-generated catch block
	        System.out.println("Exception " + ex.getMessage());
	    }
	
	}

}
