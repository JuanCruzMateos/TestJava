package vista;
import java.awt.Component;

import javax.swing.JOptionPane;


public class FalsoOptionPane implements InterfazOptionPanel{

	private String mensaje = null;
	public FalsoOptionPane()  {
		// TODO Auto-generated constructor stub
	}
	
	public void ShowMessage(Component parent, String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String getMensaje() {
        return mensaje;
    }
	
}

