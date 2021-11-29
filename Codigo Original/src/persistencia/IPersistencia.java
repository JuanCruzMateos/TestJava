package persistencia;

import java.io.IOException;

/**
 * @author usuario <br>
 * Interfaz con los metodos necesarios para poder realizar la persistencia del sistema<br>
 * @param <E> de tipo Serializable y sera el objeto que representa a nuestro sistema
 */
public interface IPersistencia <E> {
	
	void abrirInput(String nombre) throws IOException;
	
	void abrirOutput(String nombre) throws IOException;
	
	void cerrarInput() throws IOException;
	
	void cerrarOutput() throws IOException;
	
	void escribir(E objeto) throws IOException;
	
	E leer() throws IOException,ClassNotFoundException;

}
