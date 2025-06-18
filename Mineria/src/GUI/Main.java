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

	
		JOptionPane.showMessageDialog(null, "Bienvenido a la Mineria Minashi :)");

		String[] acciones = { "Login", "Registrar", "Salir" };
        int menu = 0;
        
        do {
            menu = JOptionPane.showOptionDialog(null, "Bienvenido", null, 0, 0, null, acciones, acciones[0]);

            switch (menu) {
                case 0:
                	 Usuario intentoLogin = new Usuario();
                	    intentoLogin.setEmail(JOptionPane.showInputDialog("Ingrese email"));
                	    intentoLogin.setContrasenia(JOptionPane.showInputDialog("Ingrese contraseña"));

                	    if (intentoLogin.login()) {
                	        Usuario usuarioLogueado = controller.login(intentoLogin.getEmail(), intentoLogin.getContrasenia());

                	        if (usuarioLogueado != null) {
                	            JOptionPane.showMessageDialog(null, "Bienvenido " + usuarioLogueado.getTipo() + " " + usuarioLogueado.getNombre());
                	        } else {
                	            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                	        }
                	    }
                             	    
                	    
//                    Usuario usuario = controller.login(nombre, contrasenia);
//                    if (usuario != null) {
//                        if (usuario instanceof Cliente) {
//                            JOptionPane.showMessageDialog(null, "Bienvenido Cliente " + usuario.getNombre());
//                        } else if (usuario instanceof Operador) {
//                            JOptionPane.showMessageDialog(null, "Bienvenido Operador " + usuario.getNombre());
//                        } else if (usuario instanceof Encargado_Venta) {
//                            JOptionPane.showMessageDialog(null, "Bienvenido Encargado de venta " + usuario.getNombre());
//                        }
//                    } else {
//                        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
//                    }
                	    
                    break;

                case 1:  // REGISTRO (solo clientes)
                    //lajshdkjas
            }
        } while (menu != 2);
		
		
	}
}
