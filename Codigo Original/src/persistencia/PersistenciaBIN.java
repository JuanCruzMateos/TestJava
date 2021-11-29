package persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author usuario <br>
 * clase que nos permite crear un objeto de acceso a los datos para poder escribir y leer el objeto que representa nuestro sistema de un archivo binario<br>
 * Se aplica el patron DAO
 */
public class PersistenciaBIN implements IPersistencia<Serializable>{
	private FileInputStream fileInput;
	private FileOutputStream fileOutput;
	private ObjectInputStream objectInput;
	private ObjectOutputStream objectOutput;
	
	
	
	/**
	 *metodo que nos permite abrir archivo para su lectura
	 */
	@Override
	public void abrirInput(String nombre) throws IOException {
		// TODO Auto-generated method stub
		fileInput = new FileInputStream(nombre);
		objectInput = new ObjectInputStream(fileInput);
		
	}

	/**
	 *metodo que nos permite abrir archivo para su escritura
	 */
	@Override
	public void abrirOutput(String nombre) throws IOException {
		// TODO Auto-generated method stub
		fileOutput= new FileOutputStream(nombre);
		objectOutput= new ObjectOutputStream(fileOutput);
	}

	/**
	 *metodo que nos permite cerrar archivo luego de su lectura
	 */
	@Override
	public void cerrarInput() throws IOException {
		// TODO Auto-generated method stub
		if(objectInput!=null)
		 objectInput.close();
	}

	/**
	 *metodo que nos permite cerrar archivo luego de su escritura
	 */
	@Override
	public void cerrarOutput() throws IOException {
		// TODO Auto-generated method stub
		if(objectOutput!=null)
		 objectOutput.close();
	}

	/**
	 *metodo que escribe un objeto de tipo Serializable en el archivo
	 */
	@Override
	public void escribir(Serializable serializable) throws IOException {
		// TODO Auto-generated method stub
		if(this.objectOutput!=null)
			objectOutput.writeObject(serializable);
			
	}

	/**
	 *metodo que lee un objeto de tipo Serializable desde el archivo y lo retorna 
	 */
	@Override
	public Serializable leer() throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Serializable serializable=null;
		if(objectInput!=null)
			serializable= (Serializable)objectInput.readObject();
		return serializable;
	}

}
