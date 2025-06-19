package GUI;
import javax.swing.JOptionPane;

import BLL.Cliente;
import BLL.Encargado_Venta;
import BLL.Operador;
import BLL.Usuario;
import DLL.Conexion;
import DLL.ControllerUsuario;

public class Main {

	public static void main(String[] args) {
		
		Conexion.getInstance();
		ControllerUsuario controller = new ControllerUsuario();
		
		GUI.PantallaPrincipal.main(args);
		
	}
}
