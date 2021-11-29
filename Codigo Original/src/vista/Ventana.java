package vista;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import excepciones.CantDiasInvalidosException;
import excepciones.ContratacionInvalidaException;
import excepciones.EspecialidadInvalidaException;
import excepciones.FechaInvalidaException;
import excepciones.HistoriaInvalidaException;
import excepciones.HonorarioInvalidoException;
import excepciones.MatriculaInvalidaException;
import excepciones.PosgradoInvalidoException;
import excepciones.SeleccionIncorrectaException;
import habitaciones.IHabitacion;
import medicos.IMedico;
import pacientes.IPaciente;

/**
 * @author usuario <br>
 *         clase que contiene los elementos de la ventana y los metodos para
 *         obtener los datos ingresados de la interfaz de usuario<br>
 *         se extiende de JFrame e implementa la interfaz IVista
 */
public class Ventana extends JFrame implements IVista {
	private JPanel contentPane;
	private JPanel panelCentral;
	private JPanel panelSur;
	private JPanel panelNorte;
	private JPanel panelIzq;
	private JPanel panelDer;
	private JPanel panelIzquierdo;
	private JPanel panelCentro;
	private JPanel panel_3;
	private JPanel panelListaMedicos;
	private JPanel panel_11;
	private JPanel PanelMedioAnteUlt;
	private JPanel PanelMedioArriba;
	private JPanel PanelMedioSegundo;
	private JPanel PanelMedioAbajo;
	private JRadioButton RadioBTNMedico;
	private JRadioButton RadioBTNPaciente;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JTextField txtFieldNombreAp;
	private JTextField txtFieldDni;
	private JTextField txtFieldDomicilio;
	private JTextField txtFieldTelefono;
	private JTextField txtFieldHistoriamatricula;
	private JPanel panelDerecho;
	private JPanel panelEstadoFactura;
	private JPanel panelListaPacientes;
	private JScrollPane scrollPaneListaMedicos;
	private JList ListaMedicos;
	private ArrayList<IPaciente> ListaPacientesRegistrados = new ArrayList<IPaciente>();
	private ArrayList<IMedico> ListaMedicosRegistrados = new ArrayList<IMedico>();
	private ArrayList<IHabitacion> ListaHabitacionesRegistrados = new ArrayList<IHabitacion>();
	private DefaultListModel<IMedico> modeloListaMedicos;
	private DefaultListModel<IPaciente> modeloListaPacientes;
	private DefaultListModel<IHabitacion> modeloListaHabitaciones;
	private JScrollPane scrollPaneEstadoFactura;
	private JTextArea txtAreaEstadoFactura;
	private JRadioButton RadioButtonPediatra;
	private JRadioButton RadioButtonDoctorado;
	private JRadioButton RadioButtonClinico;
	private JRadioButton RadioButtonCirujano;
	private JRadioButton RadioButtonMagister;
	private JRadioButton RadioButtonResidente;
	private JRadioButton RadioButtonPermanente;
	private JPanel PanelContenedorMedico;
	private JPanel panelContenedorPosgrado;
	private JPanel panelContenedorContratacion;
	private JPanel panelContenedorLlamarPac;
	private JButton btnEliminar;
	private JButton btnAgregar;
	private JPanel panelContenedorSolicitud;
	private JPanel panelContenedorTipoPersona;
	private JTextField txtFieldCiudad;
	private JTextField txtFieldHonorarioBase;
	private JRadioButton RadioBTNNiño;
	private JRadioButton RadioBTNJoven;
	private JRadioButton RadioBTNMayor;
	private JPanel panelContenedorAgElim;
	private JList ListaPacientes;
	private ActionListener actionListener;
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	private final ButtonGroup buttonGroup_3 = new ButtonGroup();
	private final ButtonGroup buttonGroup_4 = new ButtonGroup();
	private final ButtonGroup buttonGroup_5 = new ButtonGroup();
	private JPanel panel;
	private JButton btnFACTURAR;
	private JButton btnPRESTACION;
	private JTextField txtFieldCantidadDias;
	private JPanel panelListaHabitaciones;
	private JScrollPane scrollListaHabitaciones;
	private JList ListaHabitaciones;
	private JButton btnLlamarPaciente;
	private JTextField txtFieldD1;
	private JTextField txtFieldM1;
	private JButton REPORTE;
	private JPanel panelReporte;
	private JPanel panelFechasReporte;
	private JTextField txtFieldA1;
	private JTextField txtFieldA2;
	private JTextField txtFieldM2;
	private JTextField txtFieldD2;
	private JPanel panel_2;
	private JPanel panel_4;
	private JButton btnIngresarPaciente;
	private JButton btnDeseleccionar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ventana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 925, 684);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new TitledBorder(null, "HOSPITAL BERAZATEGUI \"EVITA ES PUEBLO\"",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(this.contentPane);

		this.panelCentral = new JPanel();
		this.contentPane.add(this.panelCentral, BorderLayout.CENTER);
		this.panelCentral.setLayout(new GridLayout(0, 3, 0, 0));

		this.panelIzquierdo = new JPanel();
		this.panelCentral.add(this.panelIzquierdo);
		this.panelIzquierdo.setLayout(new GridLayout(3, 0, 0, 0));

		this.panelListaPacientes = new JPanel();
		this.panelListaPacientes.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Pacientes",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		this.panelIzquierdo.add(this.panelListaPacientes);
		this.panelListaPacientes.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPaneListaPaciente = new JScrollPane();
		this.panelListaPacientes.add(scrollPaneListaPaciente, BorderLayout.CENTER);

		this.ListaPacientes = new JList();
		scrollPaneListaPaciente.setViewportView(this.ListaPacientes);

		this.panelListaMedicos = new JPanel();
		this.panelListaMedicos
				.setBorder(new TitledBorder(null, "Medicos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panelIzquierdo.add(this.panelListaMedicos);
		this.panelListaMedicos.setLayout(new BorderLayout(0, 0));

		this.scrollPaneListaMedicos = new JScrollPane();
		this.panelListaMedicos.add(this.scrollPaneListaMedicos, BorderLayout.CENTER);

		this.ListaMedicos = new JList<IMedico>();
		this.scrollPaneListaMedicos.setViewportView(this.ListaMedicos);

		this.panel_3 = new JPanel();
		this.panelIzquierdo.add(this.panel_3);
		this.panel_3.setLayout(new GridLayout(2, 0, 0, 0));

		this.panel_11 = new JPanel();
		this.panel_3.add(this.panel_11);
		this.panel_11.setLayout(new GridLayout(1, 0, 0, 0));

		this.panelContenedorSolicitud = new JPanel();
		this.panel_11.add(this.panelContenedorSolicitud);

		this.btnIngresarPaciente = new JButton("REINGRESAR PACIENTE");
		this.btnIngresarPaciente.setActionCommand("INGRESO");
		this.panelContenedorSolicitud.add(this.btnIngresarPaciente);

		this.btnDeseleccionar = new JButton("DESELECCIONAR TODO");
		this.panelContenedorSolicitud.add(this.btnDeseleccionar);

		this.panelCentro = new JPanel();
		this.panelCentral.add(this.panelCentro);
		this.panelCentro.setLayout(new GridLayout(4, 0, 0, 0));

		this.PanelMedioArriba = new JPanel();
		this.panelCentro.add(this.PanelMedioArriba);

		this.panelContenedorTipoPersona = new JPanel();
		this.PanelMedioArriba.add(this.panelContenedorTipoPersona);

		this.RadioBTNMedico = new JRadioButton("Medico");
		buttonGroup.add(this.RadioBTNMedico);
		this.panelContenedorTipoPersona.add(this.RadioBTNMedico);

		this.RadioBTNPaciente = new JRadioButton("Paciente");
		buttonGroup.add(this.RadioBTNPaciente);
		this.panelContenedorTipoPersona.add(this.RadioBTNPaciente);

		this.txtFieldNombreAp = new JTextField();
		this.txtFieldNombreAp.setText("Nombre y Ap.");
		this.txtFieldNombreAp.setToolTipText("Nombre y apellido");
		this.PanelMedioArriba.add(this.txtFieldNombreAp);
		this.txtFieldNombreAp.setColumns(10);

		this.txtFieldTelefono = new JTextField();
		this.txtFieldTelefono.setToolTipText("Telefono");
		this.txtFieldTelefono.setText("Telefono");
		this.PanelMedioArriba.add(this.txtFieldTelefono);
		this.txtFieldTelefono.setColumns(10);

		this.txtFieldHistoriamatricula = new JTextField();
		this.txtFieldHistoriamatricula.setToolTipText("Historia/Matricula");
		this.txtFieldHistoriamatricula.setText("Historia/Matricula");
		this.PanelMedioArriba.add(this.txtFieldHistoriamatricula);
		this.txtFieldHistoriamatricula.setColumns(10);

		this.txtFieldDni = new JTextField();
		this.txtFieldDni.setToolTipText("DNI");
		this.txtFieldDni.setText("DNI");
		this.PanelMedioArriba.add(this.txtFieldDni);
		this.txtFieldDni.setColumns(10);

		this.txtFieldDomicilio = new JTextField();
		this.txtFieldDomicilio.setToolTipText("Domicilio");
		this.txtFieldDomicilio.setText("Domicilio");
		this.PanelMedioArriba.add(this.txtFieldDomicilio);
		this.txtFieldDomicilio.setColumns(10);

		this.txtFieldCiudad = new JTextField();
		this.txtFieldCiudad.setText("Ciudad");
		this.PanelMedioArriba.add(this.txtFieldCiudad);
		this.txtFieldCiudad.setColumns(10);

		this.txtFieldHonorarioBase = new JTextField();
		this.txtFieldHonorarioBase.setText("Honorario Base");
		this.PanelMedioArriba.add(this.txtFieldHonorarioBase);
		this.txtFieldHonorarioBase.setColumns(10);

		this.PanelMedioSegundo = new JPanel();
		this.panelCentro.add(this.PanelMedioSegundo);

		this.PanelContenedorMedico = new JPanel();
		this.PanelMedioSegundo.add(this.PanelContenedorMedico);

		this.RadioButtonPediatra = new JRadioButton("Pediatra");
		buttonGroup_1.add(this.RadioButtonPediatra);
		this.PanelContenedorMedico.add(this.RadioButtonPediatra);

		this.RadioButtonCirujano = new JRadioButton("Cirujano");
		buttonGroup_1.add(this.RadioButtonCirujano);
		this.PanelContenedorMedico.add(this.RadioButtonCirujano);

		this.RadioButtonClinico = new JRadioButton("Clinico");
		buttonGroup_1.add(this.RadioButtonClinico);
		this.PanelContenedorMedico.add(this.RadioButtonClinico);

		this.panelContenedorPosgrado = new JPanel();
		this.PanelMedioSegundo.add(this.panelContenedorPosgrado);

		this.RadioButtonDoctorado = new JRadioButton("Doctorado");
		buttonGroup_2.add(this.RadioButtonDoctorado);
		this.panelContenedorPosgrado.add(this.RadioButtonDoctorado);

		this.RadioButtonMagister = new JRadioButton("Magister");
		buttonGroup_2.add(this.RadioButtonMagister);
		this.panelContenedorPosgrado.add(this.RadioButtonMagister);

		this.panelContenedorContratacion = new JPanel();
		this.PanelMedioSegundo.add(this.panelContenedorContratacion);

		this.RadioButtonResidente = new JRadioButton("Residente");
		buttonGroup_3.add(this.RadioButtonResidente);
		this.panelContenedorContratacion.add(this.RadioButtonResidente);

		this.RadioButtonPermanente = new JRadioButton("Permanente");
		buttonGroup_3.add(this.RadioButtonPermanente);
		this.panelContenedorContratacion.add(this.RadioButtonPermanente);

		this.panel_2 = new JPanel();
		this.PanelMedioSegundo.add(this.panel_2);

		this.RadioBTNJoven = new JRadioButton("Joven");
		this.panel_2.add(this.RadioBTNJoven);
		buttonGroup_4.add(this.RadioBTNJoven);

		this.RadioBTNMayor = new JRadioButton("Mayor");
		this.panel_2.add(this.RadioBTNMayor);
		buttonGroup_4.add(this.RadioBTNMayor);

		this.RadioBTNNiño = new JRadioButton("Niño");
		this.panel_2.add(this.RadioBTNNiño);
		buttonGroup_4.add(this.RadioBTNNiño);

		this.PanelMedioAnteUlt = new JPanel();
		this.panelCentro.add(this.PanelMedioAnteUlt);
		this.PanelMedioAnteUlt.setLayout(new GridLayout(0, 1, 0, 0));

		this.panelContenedorAgElim = new JPanel();
		this.PanelMedioAnteUlt.add(this.panelContenedorAgElim);

		this.btnAgregar = new JButton("Agregar");
		this.panelContenedorAgElim.add(this.btnAgregar);
		this.btnAgregar.setHorizontalAlignment(SwingConstants.RIGHT);

		this.btnEliminar = new JButton("Eliminar");
		this.panelContenedorAgElim.add(this.btnEliminar);
		this.btnEliminar.setAlignmentY(1.0f);
		this.btnEliminar.setAlignmentX(1.0f);

		this.panelContenedorLlamarPac = new JPanel();
		this.PanelMedioAnteUlt.add(this.panelContenedorLlamarPac);

		this.btnLlamarPaciente = new JButton("LLAMAR PACIENTE");
		this.panelContenedorLlamarPac.add(this.btnLlamarPaciente);
		this.btnLlamarPaciente.setActionCommand("LlamaPaciente");

		this.panelFechasReporte = new JPanel();
		this.PanelMedioAnteUlt.add(this.panelFechasReporte);
		this.panelFechasReporte.setLayout(new GridLayout(0, 7, 0, 0));

		this.txtFieldD1 = new JTextField();
		this.panelFechasReporte.add(this.txtFieldD1);
		this.txtFieldD1.setText("DD");
		this.txtFieldD1.setColumns(10);

		this.txtFieldM1 = new JTextField();
		this.panelFechasReporte.add(this.txtFieldM1);
		this.txtFieldM1.setText("MM");
		this.txtFieldM1.setColumns(10);

		this.txtFieldA1 = new JTextField();
		this.panelFechasReporte.add(this.txtFieldA1);
		this.txtFieldA1.setText("AAAA");
		this.txtFieldA1.setColumns(10);

		this.panel_4 = new JPanel();
		this.panelFechasReporte.add(this.panel_4);

		this.txtFieldD2 = new JTextField();
		this.panelFechasReporte.add(this.txtFieldD2);
		this.txtFieldD2.setText("DD");
		this.txtFieldD2.setColumns(10);

		this.txtFieldM2 = new JTextField();
		this.panelFechasReporte.add(this.txtFieldM2);
		this.txtFieldM2.setText("MM");
		this.txtFieldM2.setColumns(10);

		this.txtFieldA2 = new JTextField();
		this.panelFechasReporte.add(this.txtFieldA2);
		this.txtFieldA2.setText("AAAA");
		this.txtFieldA2.setColumns(10);

		this.panelReporte = new JPanel();
		this.PanelMedioAnteUlt.add(this.panelReporte);
		this.panelReporte.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		this.REPORTE = new JButton("REPORTE");
		this.panelReporte.add(this.REPORTE);

		this.PanelMedioAbajo = new JPanel();
		this.panelCentro.add(this.PanelMedioAbajo);
		this.PanelMedioAbajo.setLayout(new GridLayout(2, 1, 0, 0));

		this.panel = new JPanel();
		this.PanelMedioAbajo.add(this.panel);

		this.btnFACTURAR = new JButton("FACTURAR");
		this.panel.add(this.btnFACTURAR);

		this.btnPRESTACION = new JButton("PRESTACION");
		this.panel.add(this.btnPRESTACION);

		this.txtFieldCantidadDias = new JTextField();
		this.txtFieldCantidadDias.setText("Cantidad dias");
		this.txtFieldCantidadDias.setColumns(10);
		this.panel.add(this.txtFieldCantidadDias);

		this.panelListaHabitaciones = new JPanel();
		this.panelListaHabitaciones
				.setBorder(new TitledBorder(null, "Habitaciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.PanelMedioAbajo.add(this.panelListaHabitaciones);
		this.panelListaHabitaciones.setLayout(new BorderLayout(0, 0));

		this.scrollListaHabitaciones = new JScrollPane();
		this.panelListaHabitaciones.add(this.scrollListaHabitaciones, BorderLayout.CENTER);

		this.ListaHabitaciones = new JList();
		this.scrollListaHabitaciones.setViewportView(this.ListaHabitaciones);

		this.panelDerecho = new JPanel();
		this.panelCentral.add(this.panelDerecho);
		panelDerecho.setLayout(null);

		this.panelEstadoFactura = new JPanel();
		panelEstadoFactura.setBounds(0, 0, 298, 605);
		this.panelEstadoFactura.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Factura",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		this.panelDerecho.add(this.panelEstadoFactura);
		this.panelEstadoFactura.setLayout(new BorderLayout(0, 0));

		this.scrollPaneEstadoFactura = new JScrollPane();
		this.panelEstadoFactura.add(this.scrollPaneEstadoFactura, BorderLayout.CENTER);

		this.txtAreaEstadoFactura = new JTextArea();
		this.scrollPaneEstadoFactura.setViewportView(this.txtAreaEstadoFactura);

		this.panelSur = new JPanel();
		this.contentPane.add(this.panelSur, BorderLayout.SOUTH);

		this.panelNorte = new JPanel();
		this.contentPane.add(this.panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		this.panelIzq = new JPanel();
		this.contentPane.add(this.panelIzq, BorderLayout.WEST);

		this.panelDer = new JPanel();
		this.contentPane.add(this.panelDer, BorderLayout.EAST);

		this.modeloListaMedicos = new DefaultListModel<IMedico>();
		this.modeloListaPacientes = new DefaultListModel<IPaciente>();
		this.modeloListaHabitaciones = new DefaultListModel<IHabitacion>();

		this.ListaMedicos.setModel(modeloListaMedicos);
		this.ListaPacientes.setModel(modeloListaPacientes);
		this.ListaHabitaciones.setModel(modeloListaHabitaciones);
		
		
		//seteo nombres
		this.RadioBTNMedico.setName("RadioBTNMedico");
		this.RadioBTNPaciente.setName("RadioBTNPaciente");
		this.txtFieldNombreAp.setName("txtFieldNombreAp");
		this.txtFieldTelefono.setName("txtFieldTelefono");
		this.txtFieldHistoriamatricula.setName("txtFieldHistoriamatricula");
		this.txtFieldDni.setName("txtFieldDni");
		this.txtFieldDomicilio.setName("txtFieldDomicilio");
		this.txtFieldCiudad.setName("txtFieldCiudad");
		this.txtFieldHonorarioBase.setName("txtFieldHonorarioBase");
		this.RadioButtonPediatra.setName("RadioButtonPediatra");
		this.RadioButtonCirujano.setName("RadioButtonCirujano");
		this.RadioButtonClinico.setName("RadioButtonClinico");
		this.RadioButtonDoctorado.setName("RadioButtonDoctorado");
		this.RadioButtonMagister.setName("RadioButtonMagister");
		this.RadioButtonResidente.setName("RadioButtonResidente");
		this.RadioButtonPermanente.setName("RadioButtonPermanente");
		this.RadioBTNJoven.setName("RadioBTNJoven");
		this.RadioBTNMayor.setName("RadioBTNMayor");
		this.RadioBTNNiño.setName("RadioBTNNiño");
		this.btnAgregar.setName("btnAgregar");
		this.btnEliminar.setName("btnEliminar");
		this.btnLlamarPaciente.setName("");
		this.btnIngresarPaciente.setName("btnIngresarPaciente");
		this.btnDeseleccionar.setName("btnDeseleccionar");
		this.REPORTE.setName("REPORTE");
		this.btnFACTURAR.setName("btnPRESTACION");
		this.btnPRESTACION.setName("btnPRESTACION");
		this.txtFieldCantidadDias.setName("txtFieldCantidadDias");
		this.txtFieldD1.setName("txtFieldD1");
		this.txtFieldM1.setName("txtFieldM1");
		this.txtFieldA1.setName("txtFieldA1");
		this.txtFieldD2.setName("txtFieldD2");
		this.txtFieldM2.setName("txtFieldM2");
		this.txtFieldA2.setName("txtFieldA2");
		this.ListaPacientes.setName("ListaPacientes");
		this.ListaMedicos.setName("ListaMedicos");
		this.ListaHabitaciones.setName("ListaHabitaciones");

	}

	/**
	 * metodo que indica que boton se ha seleccionado para agregar objetos, boton de
	 * medico o boton de paciente<br>
	 * retorna un string con el comando que corresponde
	 */
	@Override
	public String getTipoAgregarSeleccionado() {
		String rta = null;
		if (this.RadioBTNMedico.isSelected())
			rta = this.RadioBTNMedico.getActionCommand();
		else if (this.RadioBTNPaciente.isSelected())
			rta = this.RadioBTNPaciente.getActionCommand();
		return rta;
	}

	/**
	 * metodo que retorna el paciente que se ha seleccionado de la lista de
	 * pacientes de la interfaz
	 */
	@Override
	public IPaciente getPacienteSeleccionado() {
		return (IPaciente) this.ListaPacientes.getSelectedValue(); // No se pq me pide casteo, pq lo cambie en el
																	// codigo, preguntar (igual es lo mismo, estoy
																	// seguro q vendra un Asociado)
	}

	/**
	 * metodo que retorna el medico que se ha seleccionado de la lista de medicos de
	 * la interfaz
	 */
	@Override
	public IMedico getMedicoSeleccionado() {
		return (IMedico) this.ListaMedicos.getSelectedValue();

	}

	/**
	 * metodo que retorna la habitacion que se ha seleccionado de la lista de
	 * habitaciones de la interfaz
	 */
	@Override
	public IHabitacion getHabitacionSeleccionada() {

		return (IHabitacion) this.ListaHabitaciones.getSelectedValue();
	}

	@Override
	public void setActionListener(ActionListener listener) // PREGUNTAR SI A LOS RADIO BUTTON LES HACE FALTA ACCION
															// LISTENER, PQ YO LOS GETTEO APARTE
	{
		this.btnAgregar.addActionListener(listener);
		this.btnEliminar.addActionListener(listener);
		this.btnFACTURAR.addActionListener(listener);
		this.btnPRESTACION.addActionListener(listener);
		this.REPORTE.addActionListener(listener);
		this.RadioBTNMedico.addActionListener(listener);
		this.RadioBTNPaciente.addActionListener(listener);
		this.btnLlamarPaciente.addActionListener(listener);
		this.btnIngresarPaciente.addActionListener(listener);
		this.btnDeseleccionar.addActionListener(listener);

	}

	/**
	 * metodo que revisa que el paciente ingresado se encuentra o no en la lista de
	 * pacientes registrados<br>
	 * retorna false si no se encuentra y true si lo contiene
	 */
	@Override
	public boolean actualizaAgregaListaPacientes(Iterator<IPaciente> iterator) {
		boolean rta = false;
		while (iterator.hasNext()) {
			IPaciente paciente = iterator.next();
			if (!this.ListaPacientesRegistrados.contains(paciente)) {
				rta = true;
				this.agregaNuevoPaciente(paciente);
			}
		}
		return rta;
	}

	/**
	 * metodo que que comprueba si el paciente se encuentra o no en la lista de
	 * registrados e invoca al metodo que lo elimina
	 */
	@Override
	public void actualizaEliminaListaPacientes(Iterator<IPaciente> iterator, IPaciente paciente) {

		if (this.ListaPacientesRegistrados.contains(paciente)) {
			this.EliminaPaciente(paciente);
		}
	}

	/**
	 * metodo que elimina a paciente de las listas
	 */
	@Override
	public void EliminaPaciente(IPaciente paciente) {
		this.modeloListaPacientes.removeElement(paciente);
		this.ListaPacientesRegistrados.remove(paciente); // agrego a lista visual
		this.validate();
	}

	/**
	 * metodo que elimina a medico de las listas
	 */
	@Override
	public void EliminaMedico(IMedico medico) {
		this.modeloListaMedicos.removeElement(medico);
		this.ListaMedicosRegistrados.remove(medico);
		this.validate();

	}

	/**
	 * metodo que agrega paciente a la lista
	 * 
	 * @param paciente de tipo IPaciente
	 */
	public void agregaNuevoPaciente(IPaciente paciente) {
		this.modeloListaPacientes.addElement(paciente);
		this.ListaPacientesRegistrados.add(paciente);
		this.validate();
	}

	/**
	 * metodo que agrega medico a la lista
	 * 
	 * @param medico de tipo IMedico
	 */
	public void agregaNuevoMedico(IMedico medico) {
		this.modeloListaMedicos.addElement(medico);
		this.ListaMedicosRegistrados.add(medico);
		this.validate();

	}

	/**
	 * metodo que actualiza la lista de medicos si el medicoa ingresar no fue
	 * registrado antes
	 */
	@Override
	public boolean actualizaAgregaListaMedicos(Iterator<IMedico> iterator) {
		boolean rta = false;
		while (iterator.hasNext()) {
			IMedico medico = iterator.next();
			if (!this.ListaMedicosRegistrados.contains(medico)) {
				rta = true;
				this.agregaNuevoMedico(medico);
			}

		}
		return rta;
	}

	/**
	 * metodo que comprueba si el medico a eliminar se encuentra o no en la lista de
	 * medicos registrados e invoca al metodo que elimina
	 */
	@Override
	public void actualizaEliminaListaMedicos(Iterator<IMedico> iterator, IMedico medico) {

		if (this.ListaMedicosRegistrados.contains(medico)) {
			this.EliminaMedico(medico);
		}

	}

	/**
	 * metodo que obtiene el domicilio ingresado en la interfaz de usuario y lo
	 * retorna como String
	 */
	@Override
	public String getDomNuevo() {
		// TODO Auto-generated method stub
		return this.txtFieldDomicilio.getText();
	}

	/**
	 * metodo que obtiene el domicilio ingresado en la interfaz de usuario y lo
	 * retorna como String
	 */
	@Override
	public String getTelNuevo() {
		// TODO Auto-generated method stub
		return this.txtFieldTelefono.getText();
	}

	/**
	 * metodo que obtiene el DNI ingresado en la interfaz de usuario y lo retorna
	 * como String
	 */
	@Override
	public String getDNINuevo() {

		return this.txtFieldDni.getText();
	}

	/**
	 * metodo que obtiene el nombre ingresado en la interfaz de usuario y lo retorna
	 * como String
	 */
	@Override
	public String getNomNuevo() {
		// TODO Auto-generated method stub
		return this.txtFieldNombreAp.getText();
	}

	/**
	 * metodo que obtiene la cantidad de dias de internacion ingresados en la
	 * interfaz de usuario y los retorna en un int <br>
	 * 
	 * @throws CantDiasInvalidosException se obtiene un valor int negativo de dias
	 * @throws NumberFormatException      si el string no es compatible con el
	 *                                    formato de un numero entero
	 */
	@Override
	public int getCantidadDias() throws CantDiasInvalidosException, NumberFormatException {
		int rta = 0;
		rta = Integer.parseInt(this.txtFieldCantidadDias.getText());
		if (rta < 0)
			throw new CantDiasInvalidosException();
		return rta;
	}

	/**
	 * metodo que obtiene la matricula de un medico ingresada en la interfaz de
	 * usuario y la retorna en un int
	 * 
	 * @throws MatriculaInvalidaException : se produce cuando la cadena es nula o no tiene el formato valido para este campo
	 * @throws HistoriaInvalidaException : se produce cuando la cadena es nula o no tiene el formato valido para este campo
	 */
	@Override
	public int getMatriculaNuevo() throws MatriculaInvalidaException, HistoriaInvalidaException {
		String cad = this.txtFieldHistoriamatricula.getText();
		boolean corta = false;
		int i = 0;
		if (cad != null) {
			while (i < cad.length() && corta == false) {
				if (Character.isLetter(cad.charAt(i)))
					corta = true;
				i++;
			}
			if (corta) {
				if (this.RadioBTNMedico.isSelected())
					throw new MatriculaInvalidaException();
				else
					throw new HistoriaInvalidaException();
			} else
				return Integer.parseInt(this.txtFieldHistoriamatricula.getText());

		} 
		else {
			if (this.RadioBTNMedico.isSelected())
				throw new MatriculaInvalidaException();
			else
				throw new HistoriaInvalidaException();
		}
	}

	/**
	 * metodo que obtiene la ciudad ingresada en la interfaz de usuario y la retorna
	 * en un String
	 */
	@Override
	public String getCiudadNuevo() {
		// TODO Auto-generated method stub
		return this.txtFieldCiudad.getText();
	}

	/**
	 * metodo que obtiene el rango Etario del paciente ingresado en la interfaz de
	 * usuario y lo retorna en un String<br>
	 * 
	 * @throws SeleccionIncorrectaException si no esta seleccionado el boton de
	 *                                      mayor o joven o ninio
	 */
	@Override
	public String getRangoEtario() throws SeleccionIncorrectaException {
		String rta = null;
		if (this.RadioBTNNiño.isSelected())
			rta = "Nino";
		else if (this.RadioBTNJoven.isSelected())
			rta = "Joven";
		else if (this.RadioBTNMayor.isSelected())
			rta = "Mayor";
		else
			throw new SeleccionIncorrectaException();
		return rta;
	}

	/**
	 * metodo que obtiene la especialidad del Medico ingresada en la interfaz de
	 * usuario, la retorna en un String<br>
	 * 
	 * @throws EspecialidadInvalidaException si el boton de cirujano o clinico o
	 *                                       pediatra no esta seleccionado
	 */
	@Override
	public String getEspecialidad() throws EspecialidadInvalidaException {
		// TODO Auto-generated method stub
		String rta = null;
		if (this.RadioButtonCirujano.isSelected())
			rta = "Cirujano";
		else if (this.RadioButtonClinico.isSelected())
			rta = "Clinico";
		else if (this.RadioButtonPediatra.isSelected())
			rta = "Pediatra";
		else
			throw new EspecialidadInvalidaException(null, null);
		return rta;
	}

	/**
	 * metodo que obtiene el tipo de contratacion que tiene el medico ingresada en
	 * la interfaz de usuario y la retorna en un String<br>
	 * 
	 * @throws ContratacionInvalidaException si el boton de Permanente o Residente
	 *                                       no estan seleccionados
	 */
	@Override
	public String getContratacion() throws ContratacionInvalidaException {
		String rta = null;
		if (this.RadioButtonPermanente.isSelected())
			rta = "Permanente";
		else if (this.RadioButtonResidente.isSelected())
			rta = "Residente";
		else
			throw new ContratacionInvalidaException(null, null);
		return rta;
	}

	/**
	 * metodo que obtiene el posgrado que tiene el medico ingresado en la interfaz
	 * de usuario y lo retorna en un String<br>
	 * 
	 * @throws PosgradoInvalidoException se lanza si el boton Doctorado o Magister
	 *                                   no estan seleccionados
	 */
	@Override
	public String getPosgrado() throws PosgradoInvalidoException {
		// TODO Auto-generated method stub
		String rta = null;
		if (this.RadioButtonDoctorado.isSelected())
			rta = "Doctorado";
		else if (this.RadioButtonMagister.isSelected())
			rta = "Magister";
		else
			throw new PosgradoInvalidoException(null, null);
		return rta;
	}

	/**
	 * metodo que obtiene el honorario base del medico ingresado en la interfaz de
	 * usuario y lo retorna en un double<br>
	 * 
	 * @throws HonorarioInvalidoException se lanza cuando el formato del honorario
	 *                                    no es un numero
	 */
	@Override
	public double getHonorario() throws HonorarioInvalidoException {
		String cad = this.txtFieldHonorarioBase.getText();
		boolean corta = false;
		int i = 0;
		if (cad != null) {
			while (i < cad.length() && corta == false) {
				if (Character.isLetter(cad.charAt(i)))
					corta = true;
				i++;
			}
			if (corta)
				throw new HonorarioInvalidoException();
			else
				return Double.parseDouble(this.txtFieldHonorarioBase.getText());
		} else
			throw new HonorarioInvalidoException();

	}

	/**
	 * metodo que actualiza la lista de habitaciones
	 */
	@Override
	public void actualizaListaHabitaciones(Iterator<IHabitacion> iterator) {
		while (iterator.hasNext()) {
			IHabitacion hab = iterator.next();
			if (!this.ListaHabitacionesRegistrados.contains(hab)) {
				this.agregaNuevaHabitacion(hab);
			}

		}

	}

	/**
	 * metodo que agrega una habitacion a la lista de habitaciones
	 * 
	 * @param hab
	 */
	public void agregaNuevaHabitacion(IHabitacion hab) {
		this.modeloListaHabitaciones.addElement(hab);
		this.ListaHabitacionesRegistrados.add(hab);
		this.validate();

	}

	/**
	 * metodo para bloquear botones de la interfaz de usuario segun la accion
	 * seleccionada anteriormente
	 */
	public void soltarSeleccion() {
		this.ListaHabitaciones.clearSelection();
		this.ListaPacientes.clearSelection();
		this.ListaMedicos.clearSelection();
		this.RadioBTNJoven.setSelected(false);
		this.RadioBTNMayor.setSelected(false);
		this.RadioBTNNiño.setSelected(false);
		this.RadioBTNMedico.setSelected(false);
		this.RadioBTNPaciente.setSelected(false);
		this.RadioButtonCirujano.setSelected(false);
		this.RadioButtonClinico.setSelected(false);
		this.RadioButtonDoctorado.setSelected(false);
		this.RadioButtonMagister.setSelected(false);
		this.RadioButtonPediatra.setSelected(false);
		this.RadioButtonPermanente.setSelected(false);
		this.RadioButtonResidente.setSelected(false);
	}

	public void soltarSeleccionHabitaciones() {
		this.ListaHabitaciones.clearSelection();
	}

	public void soltarSeleccionPacientes() {
		this.ListaPacientes.clearSelection();
	}

	public void soltarSeleccionMedicos() {
		this.ListaMedicos.clearSelection();
	}

	/**
	 * metodo que permite deseleccionar los botones que no son necesarios para
	 * medico o paciente
	 */
	@Override
	public void pacientemedicoSeleccionado() {
		if (this.RadioBTNMedico.isSelected())
			deseleccionaBotonesPaciente(); // y habilita los corresp
		else if (this.RadioBTNPaciente.isSelected())
			deseleccionaBotonesMedico();// y habilita los corresp
		else
			deseleccionarBotones();
	}

	public void deseleccionaBotonesMedico() {
		this.RadioBTNJoven.setEnabled(true);
		this.RadioBTNMayor.setEnabled(true);
		this.RadioBTNNiño.setEnabled(true);
		this.RadioButtonCirujano.setEnabled(false);
		this.RadioButtonClinico.setEnabled(false);
		this.RadioButtonMagister.setEnabled(false);
		this.RadioButtonDoctorado.setEnabled(false);
		this.RadioButtonPediatra.setEnabled(false);
		this.RadioButtonPermanente.setEnabled(false);
		this.RadioButtonResidente.setEnabled(false);
		this.txtFieldHonorarioBase.setEnabled(false);
		this.txtFieldDni.setEnabled(true);
		this.txtFieldDomicilio.setEnabled(true);
		this.txtFieldHistoriamatricula.setEnabled(true);
		this.txtFieldNombreAp.setEnabled(true);
		this.txtFieldTelefono.setEnabled(true);
		this.txtFieldCiudad.setEnabled(true);
	}

	public void deseleccionaBotonesPaciente() {
		this.RadioBTNJoven.setEnabled(false);
		this.RadioBTNMayor.setEnabled(false);
		this.RadioBTNNiño.setEnabled(false);
		this.RadioButtonCirujano.setEnabled(true);
		this.RadioButtonClinico.setEnabled(true);
		this.RadioButtonMagister.setEnabled(true);
		this.RadioButtonDoctorado.setEnabled(true);
		this.RadioButtonPediatra.setEnabled(true);
		this.RadioButtonPermanente.setEnabled(true);
		this.RadioButtonResidente.setEnabled(true);
		this.txtFieldHonorarioBase.setEnabled(true);
		this.txtFieldDni.setEnabled(true);
		this.txtFieldDomicilio.setEnabled(true);
		this.txtFieldHistoriamatricula.setEnabled(true);
		this.txtFieldNombreAp.setEnabled(true);
		this.txtFieldTelefono.setEnabled(true);
		this.txtFieldCiudad.setEnabled(true);
	}

	public void deseleccionarBotones() {
		this.RadioBTNJoven.setEnabled(false);
		this.RadioBTNMayor.setEnabled(false);
		this.RadioBTNNiño.setEnabled(false);
		this.RadioButtonCirujano.setEnabled(false);
		this.RadioButtonClinico.setEnabled(false);
		this.RadioButtonMagister.setEnabled(false);
		this.RadioButtonDoctorado.setEnabled(false);
		this.RadioButtonPediatra.setEnabled(false);
		this.RadioButtonPermanente.setEnabled(false);
		this.RadioButtonResidente.setEnabled(false);
		this.txtFieldCiudad.setEnabled(false);
		this.txtFieldDni.setEnabled(false);
		this.txtFieldDomicilio.setEnabled(false);
		this.txtFieldHistoriamatricula.setEnabled(false);
		this.txtFieldHonorarioBase.setEnabled(false);
		this.txtFieldNombreAp.setEnabled(false);
		this.txtFieldTelefono.setEnabled(false);

	}

	/**
	 * escribe la factura del paciente al que se le selecciono para facturar,<br>
	 * recibe el String con toda la factura armada
	 */
	@Override
	public void actualizaFactura(String factura) {
		this.txtAreaEstadoFactura.append(factura);
	}

	/**
	 * metodo que obtiene la primer fecha ingresada en la interfaz de usuario,<br>
	 * la retorna en un GregorianCalendar<br>
	 * 
	 * @throws FechaInvalidaException se lanza si el campo dia,mes o anio son nulos
	 *                                o no corresponden a un formato valido
	 */
	public GregorianCalendar getFecha1() throws FechaInvalidaException {
		GregorianCalendar rta = null;
		if (this.txtFieldD1.getText() != null && this.txtFieldM1.getText() != null && this.txtFieldA1.getText() != null
				&& !(this.txtFieldD1.getText().equalsIgnoreCase("DD"))
				&& !(this.txtFieldM1.getText().equalsIgnoreCase("MM"))
				&& !(this.txtFieldA1.getText().equalsIgnoreCase("AAAA"))) {
			int dia = Integer.parseInt(this.txtFieldD1.getText());
			int mes = Integer.parseInt(this.txtFieldM1.getText());
			int anio = Integer.parseInt(this.txtFieldA1.getText());
			if (dia > 0 && dia < 32 && mes > 0 && mes <= 12 && anio > 0)
				rta = new GregorianCalendar(anio, mes - 1, dia);
			else
				throw new FechaInvalidaException();

		} else
			throw new FechaInvalidaException();
		return rta;
	}

	/**
	 * metodo que obtiene la segunda fecha ingresada en la interfaz de usuario,<br>
	 * la retorna en un GregorianCalendar<br>
	 * 
	 * @throws FechaInvalidaException se lanza si el campo dia,mes o anio son nulos
	 *                                o no corresponden a un formato valido
	 */
	public GregorianCalendar getFecha2() throws FechaInvalidaException {
		GregorianCalendar rta = null;
		if (this.txtFieldM2.getText() != null && this.txtFieldD2.getText() != null && this.txtFieldA2.getText() != null
				&& !(this.txtFieldD2.getText().equalsIgnoreCase("DD"))
				&& !(this.txtFieldM2.getText().equalsIgnoreCase("MM"))
				&& !(this.txtFieldA2.getText().equalsIgnoreCase("AAAA"))) {
			int dia = Integer.parseInt(this.txtFieldD2.getText());
			int mes = Integer.parseInt(this.txtFieldM2.getText());
			int anio = Integer.parseInt(this.txtFieldA2.getText());
			if (dia > 0 && dia < 32 && mes > 0 && mes <= 12 && anio > 0)
				rta = new GregorianCalendar(anio, mes - 1, dia);
			else
				throw new FechaInvalidaException();
		} else
			throw new FechaInvalidaException();
		return rta;
	}
}

/*----------------------------------------------------------------------------*/
