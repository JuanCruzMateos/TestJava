package modulo;


import java.text.DecimalFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.PriorityQueue;

import excepciones.EgresoInvalidoException;
import excepciones.JugoRobinhoException;
import excepciones.MedicoRepetidoException;
import excepciones.NoFueLlamadoException;
import excepciones.NoHayEspacioException;
import excepciones.NoHayPacienteEsperandoException;
import excepciones.PacienteRepetidoException;
import excepciones.PacienteYaIngresadoException;
import excepciones.ReporteInvalidoException;
import habitaciones.IHabitacion;
import medicos.IMedico;
import pacientes.IPaciente;
import pacientes.LineaFactura;


//ver excepciones en lista de atencion , que no sea null 


/**
 * @author usuario
 *  Clase clinica la cual agruparia a los medicos y realizara las operaciones respectivas con los pacientes que ingresen a la clinica<br>
 */
public class Clinica 
{

	

	/**
	 * Atributos de la clase clinica<br>
	 * instance : instancia de clinica estatica para aplicar el patron Singleton posteriormente<br>
	 * salaDeEsperaPrivada: se emplea una variable de tipo IPaciente ya que solo habria un unico paciente en la sala de espera privada<br>
	 * nroOrden : entero que asigna los numeros de orden a medida que los pacientes ingresan a la lista de espera autoincrementable<br>
	 * turno : entero autoincrementable que se utiliza para ir llamando a los pacientes para que sean atendidos<br>
	 * nroFactura : entero autoincrementable que se lo emplea para ir generando las facturas de los pacientes egresantes<br>
	 * sdf : sirve para darle formato de cadena de String a una instancia de GregorianCalendar<br>
	 * df : sirve para truncar los decimales de un tipo double a la hora de hacer un system out<br>
	 * hPacientes : coleccion "historica" de pacientes que toma registro de los pacientes que ya han visitado la clinica en otras ocasiones<br>
	 * impresion : atributo de tipo StringBuilder para concatenar cadenas y retornarla en una unica
	 * 
	 */
	private static Clinica instance = null;
	private String nombre, direccion, telefono, ciudad;
	private StringBuilder impresion = new StringBuilder();
	

	/**
	 * @aggregation composite
	 */
	private ArrayList<IMedico> medicos;

	/**
	 * @aggregation shared
	 */
	private ArrayList<IPaciente> listaDeEspera;

	/**
	 * @aggregation shared
	 */
	ArrayList<IPaciente> listaDeAtencion;

	/**
	 * @aggregation composite
	 */
	private ArrayList<IHabitacion> habitaciones;

	/**
	 * @aggregation shared
	 */
	IPaciente salaDeEsperaPrivada = null; // de nada me sirve hacer un arraylist si solo tiene a un paciente

	/**
	 * @aggregation shared
	 */
	ArrayList<IPaciente> patio;
	private int nroOrden = 1, turno = 1, nroFactura = 1;
	static DecimalFormat df = new DecimalFormat("#.000");
	SimpleDateFormat sdf = new SimpleDateFormat("EEEEE dd 'de' MMMMMMMMM 'de' yyyy");

	/**
	 * @aggregation shared
	 */
	private ArrayList<IPaciente> hPacientes;
	/**
	 * @aggregation shared
	 */
	private ArrayList<LineaFactura>lineasFacturas;
	private ArrayList<LineaFactura> lineasReporte;

	/**
	 * @return Patron Singleton aplicado a la clinica, como durante la ejecucion
	 *         habria solo una clinica que administra todo, se aplica el patron, en
	 *         caso no haber una clinica se crea una
	 */
	public static Clinica getInstance() {
		if (Clinica.instance == null)
			Clinica.instance = new Clinica("Clinica Publica Berazategui Michael Scott", "Scranton 69", "4632190",
					"Berazategui");
		return instance;
	}

	/**
	 * Constructor de 4 parametros para setear el nombre, la direccion, el telefono
	 * y la ciudad de la clinica Sumado a esto se generan las siguientes listas:
	 * medicos, lista de espera (para pacientes), habitaciones y una lista para el
	 * patio que es uno de los 2 sitios donde los pacientes deberan esperar a ser
	 * atendidos <br>
	 * 
	 * @param nombre : parametro string para determinar el nombre de la clinica <br>
	 * @param direccion : parametro string que da la direccion de la clinica <br>
	 * @param telefono : parametro string para anotar el numero de telefono de la clinica<br>
	 * @param ciudad : ciudad en donde se ubica la clinica (parametro string)
	 */
	private Clinica(String nombre, String direccion, String telefono, String ciudad) {
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.nombre = nombre;
		this.telefono = telefono;
		this.medicos = new ArrayList<IMedico>();
		this.listaDeEspera = new ArrayList<IPaciente>();
		this.listaDeAtencion = new ArrayList<IPaciente>();
		this.habitaciones = new ArrayList<IHabitacion>();
		this.patio = new ArrayList<IPaciente>();
		this.hPacientes = new ArrayList<IPaciente>();
		this.lineasFacturas =  new ArrayList<LineaFactura>();
		this.lineasReporte = new ArrayList<LineaFactura>();
		

	}

	/**
	 * Metodo void en donde se determina si el paciente que ingresa va a esperar en
	 * la sala de espera privada o en el patio (la condicion "ganadora" es el rango
	 * etario): Entre un Nino y un Joven, la Sala queda para el Nino y el otro se va
	 * al Patio.<br>
	 * Entre un Joven y un Mayor, la Sala queda para el Joven y el otro se va al
	 * Patio.<br>
	 * Entre un Mayor y un Nino, la Sala queda para el Mayor y el otro se va al
	 * Patio.<br>
	 * <b> Pre : </b> El paciente debe ser nino,joven o mayor (en resumen no
	 * nulo)<br>
	 * <b> Post : </b> Un paciente sera enviado a la sala de espera privada o al
	 * patio<br>
	 * 
	 * @param paciente: parametro de tipo IPaciente que espera saber en que lugar de espera se lo situara
	 * @throws PacienteRepetidoException : se lanza esta excepcion cuando se intenta agregar un paciente con el mismo DNI, num de historia que un paciente que se encuentre en la lista
	 * @throws PacienteYaIngresadoException : se lanza esta excepcion cuando cuando ya se reingreso al paciente, pero se intenta reingresarlo nuevamente
	 */
	public void ingresoPaciente(IPaciente paciente) throws PacienteRepetidoException, PacienteYaIngresadoException
	{

		this.agregaHistorico(paciente);
		this.agregaListaEspera(paciente);
		paciente.setNroTurno(nroOrden++);
		paciente.setFacturo(true);  //lo ponemos en true pq todavia no tiene prestaciones, entoces es como q pago
		if (this.salaDeEsperaPrivada == null)
			this.salaDeEsperaPrivada = paciente;
		else {
			if (this.salaDeEsperaPrivada.beats(paciente))
				this.agregaPatio(paciente);
			else {
				this.agregaPatio(this.salaDeEsperaPrivada);
				this.salaDeEsperaPrivada = paciente;
			}
		}
	}

	/**
	 * Metodo void en donde se van agregando los pacientes a la lista de atencion
	 * una vez que fueron llamados <br>
	 * 
	 * @param nuevo : parametro de tipo IPaciente que sera agregado a la lista de
	 *               atencion
	 */
	public void agregaListaAtencion(IPaciente nuevo) {
		this.listaDeAtencion.add(nuevo);
	}

	/**
	 * Metodo void el cual se encarga de elminar a los pacientes de la lista de
	 * espera una vez que fueron llamados <br>
	 * 
	 * @param elim : parametro de tipo IPaciente el cual sera eliminado de la lista
	 *              de espera
	 */
	public void eliminaListaEspera(IPaciente elim) {
		this.listaDeEspera.remove(elim);
	}

	/**
	 * Metodo void que se encarga de eliminar a los pacientes que se encontraban
	 * esperando en el patio y seran, en consecuencia, atendidos <br>
	 * 
	 * @param elim : parametro de tipo IPaciente el cual sera eliminado de la lista de
	 *                       los que esperan en el patio
	 */
	public void eliminaPatio(IPaciente elim) {
		this.patio.remove(elim);
	}

	/**
	 * Metodo void que se encarga de eliminar a los pacientes que se encontraban
	 * esperando en la lista de atencion <br>
	 * 
	 * @param elim : parametro de tipo IPaciente el cual sera eliminado
	 */
	public void eliminaListaAtencion(IPaciente elim) {
		this.listaDeAtencion.remove(elim);
	}

	/**
	 * Metodo void el cual agrega al paciente a la coleccion de pacientes en caso de
	 * ser la primera vez que el mismo ingresa a la clinica<br>
	 * 
	 * @param paciente
	 */
	public void agregaHistorico(IPaciente paciente) throws PacienteRepetidoException
	{
		if (!(this.hPacientes.contains(paciente)))
			this.hPacientes.add(paciente);
		else
			throw new PacienteRepetidoException();
	}

	

	/**
	 * @return retorna un iterator de los pacientes historicos
	 */
	public Iterator<IPaciente> getIteratorHPacientes()
	{
		return this.hPacientes.iterator();
		
	}
	
	/**
	 * Metodo void que llama a los pacientes que seran atendidos. Ademas de agregar
	 * a los pacientes en la lista de atencion, se los eliminara de la lista de
	 * espera y del respectivo lugar en donde estaban esperando (sala privada o
	 * patio) <br>
	 * @throws NoHayPacienteEsperandoException : se lanza cuando se llama a un paciente que ya se encontraba en lista de atencion
	 */
	public IPaciente llamarPaciente() throws NoHayPacienteEsperandoException
	{
		IPaciente rta=null;
		if (this.listaDeEspera.isEmpty())
			throw new NoHayPacienteEsperandoException();
		Iterator<IPaciente> it = this.listaDeEspera.iterator();
		IPaciente aux = this.listaDeEspera.get(0);
		while (it.hasNext() && aux.getNroTurno() != turno) 
		{
			aux = it.next();
		}
		if (aux.getNroTurno() == turno)
		{
				rta=aux;
				if (this.salaDeEsperaPrivada!=null && aux.getNroTurno() == this.salaDeEsperaPrivada.getNroTurno())
					this.salaDeEsperaPrivada = null;
				else
					this.eliminaPatio(aux);

			this.eliminaListaEspera(aux);
			this.agregaListaAtencion(aux);
			turno++;
		}
		return rta;

	}


	/**
	 * Metodo void. Aqui se ingresaran los pacientes que deberan esperar en el
	 * patio<br>
	 * 
	 * @param nuevo : parametro de tipo IPaciente que representa al paciente que
	 *               esperara en el patio hasta ser atendido
	 */
	public void agregaPatio(IPaciente nuevo) {
		this.patio.add(nuevo);
	}

	/**
	 * Metodo void el cual va agregando a los medicos que trabajan en esta clinica
	 * <br>
	 * 
	 * @param nuevo : parametro de tipo IMedico que representa al medico que sera
	 *               agregado a la clinica
	 * @throws MedicoRepetidoException : se lanza cuando se quiere agregar un medico repetido
	 */
	public void agregaMedico(IMedico nuevo) throws MedicoRepetidoException 
	{
		if(!(this.medicos.contains(nuevo)))
			this.medicos.add(nuevo);
		else
			throw new MedicoRepetidoException(); //para saber si son equal
	}

	/**
	 * Metodo void en donde se agregaran las diferentes habitaciones con las que
	 * contara la clinica<br>
	 * 
	 * @param nuevo : parametro de tipo IHabitacion que representa la habitacion (y
	 *               su tipo) que forma parte de la clinica
	 */
	public void agregaHabitacion(IHabitacion nuevo) {
		this.habitaciones.add(nuevo);
	}

	/**
	 * Metodo void en donde se agregaran a los nuevos pacientes a la lista de
	 * espera, una vez se les haya asignado donde esperaran<br>
	 * 
	 * @param nuevo : parametro de tipo IPaciente el cual representa al paciente que
	 *               sera agregado a la lista de espera
	 * @throws PacienteYaIngresadoException : se lanza si se quiere ingresar un paciente que en este momento se encuentra dentro de la clinica
	 */
	public void agregaListaEspera(IPaciente nuevo) throws PacienteYaIngresadoException
	{
		if (!this.listaDeEspera.contains(nuevo))
			this.listaDeEspera.add(nuevo);
		else
			throw new PacienteYaIngresadoException();
	}

	/**
	 * Metodo void el cual asigna a un determinado paciente, una habitacion para
	 * internarse, y un medico del cual realizara consultas<br>
	 * El metodo aniade una linea de factura en base a la habitacion y medico que le fueron asignado<br>
	 * 
	 * @param paciente : de tipo IPaciente es aquel paciente que recibe una prestacion
	 * @param medico   : de tipo IMedico es aquel medico al que le realizan una consulta
	 * @param cantDias : de tipo int son la cantidad de dias que permanecera internado el paciente
	 * @param habitacion : de tipo IHabitacion representa la habitacion en la que se internara al paciente
	 * @throws NoFueLlamadoException se lanza cuando se le quiere agregar una prestacion a un paciente que aun esta en lista de espera 
	 * @throws NoHayEspacioException se lanza cuando se quiere agregar un paciente a una habitacion que esta llena
	 */
	public void Prestaciones(IPaciente paciente, IMedico medico, int cantDias, IHabitacion habitacion) throws NoHayEspacioException, NoFueLlamadoException
	{
		if(this.listaDeAtencion.contains(paciente))
		{
			if(habitacion.agregaPaciente(paciente))//elimina al paciente de su anterior habitacion, si es q no egreso
			{	
				paciente.setFacturo(false);
				this.lineasFacturas.add(new LineaFactura(habitacion,cantDias,paciente,medico));
				this.lineasReporte.add(new LineaFactura(habitacion,cantDias,paciente,medico));
			}
			else
				throw new NoHayEspacioException();
		}
		else throw new NoFueLlamadoException();

	}
	/**
	 * metodo que genera la facturacion de las habitaciones en las que se interno al paciente
	 * @param importeTotal : de tipo double es el importe actual a pagar que se acumulara con los costos de internacion 
	 * @param paciente : de tipo IPaciente es el peciente al cual se le esta realizando la facturacion de la internacion
	 * @return  devuelve un double que es el importe total a pagar del paciente
	 */
	public double printLineaFactura(double importeTotal, IPaciente paciente)
	{
		
		double acumImporte=0;
		for(int i=0; i<this.lineasFacturas.size();i++) 
		{
			if(this.lineasFacturas.get(i).getPaciente().equals(paciente)) 
			{
				acumImporte += this.lineasFacturas.get(i).getHabitacion().calcularValorInternacion(this.lineasFacturas.get(i).getCantDias());
				this.impresion.append("Tipo de Habitacion" + "          "  + "Costo" + "      " + "Cant. dias de internacion" + "    " +   "Subtotal"+"\n");
				
				this.impresion.append(this.lineasFacturas.get(i).getHabitacion().getNombreHabitacion() + "      " + df.format(this.lineasFacturas.get(i).getHabitacion().getCostoAsignacion())+"$" + "                " +
				this.lineasFacturas.get(i).getCantDias() + "                " + df.format(this.lineasFacturas.get(i).getHabitacion().calcularValorInternacion(this.lineasFacturas.get(i).getCantDias()))+"$" + "\n");
				
				this.impresion.append("********************************************************************************"+"\n");
			}
		}
	
		return acumImporte;
	}
	
	/**
	 * metodo que busca si el paciente pasado por parametro ha recibido alguna prestacion
	 * @param paciente : de tipo IPaciente paciente que se busca si ha recibido prestaciones
	 * @return de tipo boolean retorna true si ha recibido prestaciones el paciente y false en caso contrario
	 */
	public boolean hayPrestacion(IPaciente paciente) {
		Iterator<LineaFactura> it=this.lineasFacturas.iterator();
		boolean respuesta=false;
		if(!this.lineasFacturas.isEmpty())
		{	
			LineaFactura act=this.lineasFacturas.get(0);
			while(it.hasNext() && !(act.getPaciente().equals(paciente))){
				act=it.next();
			}
			if(act.getPaciente().equals(paciente))
			{
				respuesta=true;
				
			}
		}
		return respuesta;
	}

	/** Metodo void en donde se produce el egreso del paciente y por lo tanto se arma la factura para que el paciente abone las prestaciones servidas<br>
	 * @param paciente
	 * @param fecha
	 * @throws EgresoInvalidoException se lanza si el paciente a egresar no recibio prestaciones
	 */
	public String egreso(IPaciente paciente,GregorianCalendar fecha) throws EgresoInvalidoException { 
		
		double importeTotal=0;
		int ocurrencias;
		double honorario;
		this.impresion.delete(0, this.impresion.length());
		//this.impresion.append("");
		if(paciente.getHabitacion()!=null) 
		{
			paciente.getHabitacion().eliminaPaciente(paciente);  //preguntar, doble referencia
		}
		if(this.hayPrestacion(paciente) && this.listaDeAtencion.contains(paciente)) 
		{
			paciente.setFechaEgreso(fecha);
         	this.impresion.append("Nro.Factura:" + nroFactura + "   Fecha: " + sdf.format(fecha.getTime()) + "  Paciente: " + paciente.getNombre() + "\n");
         	this.impresion.append("********************************************************************************"+"\n");
		
         	for (int i=0;i<this.medicos.size(); i++)
         	{
         		ocurrencias= this.retornaOcurrencias(paciente, this.medicos.get(i)); 
         		if(ocurrencias != 0)
         		{       
            
         			honorario=this.medicos.get(i).calculaHonorario();
         			this.impresion.append("Nombre del medico     Valor Consulta       Cantidad Consultas      Subtotal"+"\n");
         			this.impresion.append(this.medicos.get(i).getNombre() + "           " + df.format(honorario)+"$" + "                " + ocurrencias + "               " + df.format(honorario*ocurrencias)+"$"+"\n");
         			this.impresion.append("********************************************************************************"+"\n"); 
					importeTotal+=ocurrencias*honorario;
         		}
			
         	}
		
         	//importeTotal DEFINITIVO*/
        
            importeTotal+=this.printLineaFactura(importeTotal , paciente);
		
            this.impresion.append("Importe total: " + df.format(importeTotal)+"$"+"\n");
            this.impresion.append("\n");
		
            nroFactura++;
            this.eliminaListaAtencion(paciente);
            paciente.setFacturo(true);
            this.eliminaLineasPaciente(paciente);
           
     
		}
		else throw new EgresoInvalidoException(null, paciente);
	
		return this.impresion.toString();
}
	
	
	/** metodo void que elimina las lineas de factura de un paciente que egreso de la clinica (y facturo previamente al egreso)
	 * @param p : es un paciente el cual esta egresando de la clinica y se eliminan sus lineas de factura para no cobrarle nuevamente
	 * una vez que reingrese, algo que ya pago en el pasado
	 */
	public void eliminaLineasPaciente (IPaciente p) {
		
		ArrayList <LineaFactura> aux = new ArrayList<LineaFactura>();
		for (int i=0; i<this.lineasFacturas.size();i++) {
			if(!this.lineasFacturas.get(i).getPaciente().equals(p))
				aux.add(this.lineasFacturas.get(i));
		}	
		this.lineasFacturas.clear();
		for(int i=0; i<aux.size();i++)
			this.lineasFacturas.add(aux.get(i));
	}
	
	
	

	/**
	 * Metodo void en donde por delegacion se ordena la coleccion de pacientes (por
	 * fecha de egreso cronologicamente) del medico seleccionado para luego dar el
	 * reporte del mismo, siempre y cuando haya realizado consultas entre las 2
	 * fechas, de no ser asi, obviamente no se mostraria nada<br>
	 * 
	 * @param fecha1 : limite "inferior", desde que fecha comenzaria el reporte del
	 *                medico en cuestion<br>
	 * @param fecha2 : limite "superior", hasta que fecha finalizaria el reporte del
	 *                medico en cuestion<br>
	 * @param medico : medico el cual por delegacion invocara los metodos para
	 *                realizar el reporte
	 */
	public String ReporteFechas(GregorianCalendar fecha1, GregorianCalendar fecha2, IMedico medico) throws ReporteInvalidoException
	{
		PriorityQueue<IPaciente> ordenaPacientes= new PriorityQueue<IPaciente>();
		//this.impresion.append("");
		this.impresion.delete(0, this.impresion.length());
		for(int i=0; i<this.lineasReporte.size() ; i++) {
			if(this.lineasReporte.get(i).getMedico().equals(medico))
			{
				ordenaPacientes.add(this.lineasReporte.get(i).getPaciente());
			}
		}
		this.reporte(medico,fecha1, fecha2 ,ordenaPacientes);
		
		return this.impresion.toString();

	}
	/**
	 * metodo que retorna la cantidad de consultas que un paciente les realizo a un determinado medico
	 * @param paciente : de tipo IPaciente es el paciente que recibio una prestacion con un determinado medico
	 * @param medico : de tipo IMedico es el medico al cual un paciente determinado le realizo consultas
	 * @return de tipo int retorna la cantidad de consultas que le realizo al medico un determinado paciente
	 */
	public int retornaOcurrencias(IPaciente paciente, IMedico medico) 
	{
		int acum=0;
		for(int i=0 ; i<this.lineasFacturas.size() ; i++) {
			if(this.lineasFacturas.get(i).getPaciente().equals(paciente) && this.lineasFacturas.get(i).getMedico().equals(medico)) {
				acum++;
			}
		}
		return acum;
	}
	
	/**
	 * metodo que realiza el reporte del Medico que haya realizado prestaciones a pacientes en un intervalo de fechas
	 * @param medico : de tipo IMedico es el medico al que se le realiza el reporte con las prestaciones que dio durante el intervalo de fechas
	 * @param fecha1  : de tipo GregorianCalendar fecha del limite inferior del intervalo
	 * @param fecha2 : de tipo GregorianCalendar  fecha limite del intervalo
	 * @param ordenaPacientes : de tipo PriorityQueue coleccion ordenada de pacientes que han recibido prestaciones del medico 
	 * @return de tipo String que muestra el reporte si es que se le realizo alguna consulta
	 * @throws ReporteInvalidoException : se lanza cuando el medico no tuvo pacientes que le hayan realizado alguna consulta
	 */
	public String reporte(IMedico medico,GregorianCalendar fecha1, GregorianCalendar fecha2,PriorityQueue<IPaciente> ordenaPacientes) throws  ReporteInvalidoException
	{
		double auxHonorario = medico.calculaHonorario();
		GregorianCalendar auxFecha;
		int auxCont=0,tamaño = ordenaPacientes.size();
		if(tamaño==0)
			throw new ReporteInvalidoException();
		this.impresion.append("Medico: " + medico.getNombre()+"\n");
		this.impresion.append("Fecha Consulta                     Nombre Paciente         Honorario de Consulta"+"\n");
		this.impresion.append("********************************************************************************"+"\n");
		  for(int i=0; i<tamaño ;i++) {
			  IPaciente pacienteAct=ordenaPacientes.poll();
			  if(pacienteAct.getFechaEgreso() != null) {
			  auxFecha= pacienteAct.getFechaEgreso();
			  if(auxFecha.after(fecha1) && auxFecha.before(fecha2)) {
				  auxCont++;
				  this.impresion.append(sdf.format(auxFecha.getTime()) + "      " + pacienteAct.getNombre() + "              " + df.format(auxHonorario) + "$"+"\n");
				  this.impresion.append("********************************************************************************"+"\n");
		        }
			 }
			  
		  }
		  this.impresion.append("Suma Total: " + df.format(auxHonorario*auxCont) + "$"+"\n");
		  this.impresion.append("\n");
		  
		  return this.impresion.toString();
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public ArrayList<IMedico> getMedicos() {
		return medicos;
	}

	public void setMedicos(ArrayList<IMedico> medicos) {
		this.medicos = medicos;
	}

	public ArrayList<IPaciente> getListaDeEspera() {
		return listaDeEspera;
	}

	public void setListaDeEspera(ArrayList<IPaciente> listaDeEspera) {
		this.listaDeEspera = listaDeEspera;
	}

	public ArrayList<IPaciente> getListaDeAtencion() {
		return listaDeAtencion;
	}

	public void setListaDeAtencion(ArrayList<IPaciente> listaDeAtencion) {
		this.listaDeAtencion = listaDeAtencion;
	}

	public ArrayList<IHabitacion> getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(ArrayList<IHabitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}

	public IPaciente getSalaDeEsperaPrivada() {
		return salaDeEsperaPrivada;
	}

	public void setSalaDeEsperaPrivada(IPaciente salaDeEsperaPrivada) {
		this.salaDeEsperaPrivada = salaDeEsperaPrivada;
	}

	public ArrayList<IPaciente> getPatio() {
		return patio;
	}

	public void setPatio(ArrayList<IPaciente> patio) {
		this.patio = patio;
	}

	public ArrayList<IPaciente> gethPacientes() {
		return hPacientes;
	}

	public void sethPacientes(ArrayList<IPaciente> hPacientes) {
		this.hPacientes = hPacientes;
	}
	

	public Iterator<IMedico> getIteratorMedicos() {
		// TODO Auto-generated method stub
		return this.medicos.iterator();
	}
	
	
	
	/**
	 * metodo que elimina medico de la lista de medicos de la clinica
	 * @param medico : de tipo IMedico el medico a eliminar de la lista
	 */
	public void eliminaMedico(IMedico medico)
	{
		if (this.medicos.contains(medico))
			this.medicos.remove(medico);
	}
	
	/**
	 * metodo que elimina paciente del historico de pacientes revisa que antes de retirarlo se le hayan facturado las prestaciones 
	 * @param p : de tipo IPaciente el paciente que se quiere eliminar de la lista
	 * @throws JugoRobinhoException : se lanza si se trata de eliminar un paciente que recibio prestaciones y no se le facturo
	 */
	public void eliminaHPaciente(IPaciente p) throws JugoRobinhoException
	{
		if(this.hPacientes.contains(p) && this.NoJugoRobinho(p))
			this.hPacientes.remove(p);
		else 
			throw new JugoRobinhoException();
	}
	/**
	 * metodo que verifica que el atributo del paciente este en true que significa que se le ha hecho la facturacion
	 * @param p : de tipo IPaciente el paciente al cual se le fija si el valor del atributo facturo es true o false
	 * @return de tipo boolean que devuelve true si se le facturo y false si no se le facturo
	 */
	public boolean NoJugoRobinho(IPaciente p) //se fija si choreo
	{
		return p.isFacturo(); //true si tiene algo
	}
	
	
	public Iterator<IHabitacion> getIteratorHabitaciones()
	{
		return this.habitaciones.iterator();
	}

	public ArrayList<LineaFactura> getLineasFacturas() {
		return lineasFacturas;
	}

	public void setLineasFacturas(ArrayList<LineaFactura> lineasFacturas) {
		this.lineasFacturas = lineasFacturas;
	}

	public int getNroOrden() {
		return nroOrden;
	}

	public void setNroOrden(int nroOrden) {
		this.nroOrden = nroOrden;
	}

	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
	}

	public int getNroFactura() {
		return nroFactura;
	}

	public void setNroFactura(int nroFactura) {
		this.nroFactura = nroFactura;
	}
	
	/**
	 * metodo que realiza el reingreso de un paciente a la clinica ya que pudo haberse eliminado o egresado luego de facturacion
	 * @param paciente : de tipo IPaciente 
	 * @throws PacienteYaIngresadoException : se propaga esta excepcion del metodo agregaListaEspera(paciente)
	 */
	public void ingresoNuevamente(IPaciente paciente) throws PacienteYaIngresadoException
	{
		paciente.setFacturo(false);
		this.agregaListaEspera(paciente);
		paciente.setNroTurno(nroOrden++);
		paciente.setFacturo(true);
		if (this.salaDeEsperaPrivada == null)
			this.salaDeEsperaPrivada = paciente;
		else {
			if (this.salaDeEsperaPrivada.beats(paciente))
				this.agregaPatio(paciente);
			else {
				this.agregaPatio(this.salaDeEsperaPrivada);
				this.salaDeEsperaPrivada = paciente;
			}
		}
	}
	
	/**
	 * metodo que elimina pacientes pero primero comprueba si se le ha hecho la facturacion de las prestaciones que puede haber recibido
	 * @param p : de tipo IPaciente el paciente que se desea eliminar de la lista
	 * @throws JugoRobinhoException : se propaga esta excepcion del metodo eliminaHpaciente(p)
	 */
	public void eliminaSinEgreso(IPaciente p) throws JugoRobinhoException
	{
		this.eliminaHPaciente(p);
		this.eliminaListaAtencion(p);
		this.eliminaListaEspera(p);
		
	}

	public ArrayList<LineaFactura> getLineasReporte() {
		return lineasReporte;
	}

	public void setLineasReporte(ArrayList<LineaFactura> lineasReporte) {
		this.lineasReporte = lineasReporte;
	}
	
	

}