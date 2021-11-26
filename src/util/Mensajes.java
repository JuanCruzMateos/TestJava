package util;

public enum Mensajes {

	Error_Paciente_Repetido("PACIENTE REPETIDO"), Error_Paciente_Ya_Ingresado("PACIENTE YA INGRESADO, ERROR"),
	Error_Tipo_Paciente_Invalido("TIPO DE PACIENTE INVALIDO"), Error_Seleccionar_RanroEtario("SELECCIONE RANGO ETARIO"),
	Error_Numero_HistoriaClinica("INGRESE UN NUMERO DE HISTORIA CLINICA VALIDO"),
	Error_Matricula_Invalida("INGRESE UN NUMERO DE MATRICULA VALIDO"), Error_Seleccione_Posgrado("Seleccione posgrado"),
	Error_Seleccione_Contratacion("Seleccione contratacion"), Error_Medico_Repetido("Medico repetido"),
	Error_Honorario_Incorrecto("Ingrese honorario correcto"), Error_Seleccione_Especialidad("Seleccione especialidad"),
	Seleccione_MedicoOPaciente("Seleccione un paciente o medico de la lista"),
	Seleccione_UnMedico_o_UnPaciente("Seleccione solo un paciente o solo un medico"),
	Error_Eliminar_Paciente("No puede eliminarse el paciente si no pago las prestaciones"),
	Error_Habitacion_Ocupada("No hay espacio disponible en esa habitacion, selecciona otra"),
	Error_Prestacion_Llamado("Para realizar las prestaciones, primero debe ser llamado"),
	Error_Dias_Invalidos("Ingrese una cantidad valida de dias"), Seleccione_Habitacion("Seleccione una habitacion"),
	Seleccione_Medico("Seleccione un medico de la lista"), Seleccione_Paciente("Seleccione un paciente de la lista"),
	Error_Prestaciones("El paciente nunca recibio prestaciones o ya las recibio"),
	Error_Fecha_Invalida("Ingrese fecha valida"), Error_Medico_SinConsultas("El medico no realizo consultas"),
	Error_No_Pacientes_Esperando("No hay pacientes esperando"), Paciente_Llamado_Fue("Se ha llamado al paciente: ");

	private String valor;

	private Mensajes(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}
