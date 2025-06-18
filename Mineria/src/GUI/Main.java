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
//
//	
//		JOptionPane.showMessageDialog(null, "Bienvenido a la Mineria Minashi :)");
//
//		String[] acciones = { "Login", "Registrar", "Salir" };
//        int menu = 0;
//        
//        do {
//            menu = JOptionPane.showOptionDialog(null, "Bienvenido", null, 0, 0, null, acciones, acciones[0]);
//
//            switch (menu) {
//                case 0:
//                	 Usuario intentoLogin = new Usuario();
//                	    intentoLogin.setEmail(JOptionPane.showInputDialog("Ingrese email"));
//                	    intentoLogin.setContrasenia(JOptionPane.showInputDialog("Ingrese contraseña"));
//
//                	    if (intentoLogin.login()) {
//                	        Usuario usuarioLogueado = controller.login(intentoLogin.getEmail(), intentoLogin.getContrasenia());
//
//                	        if (usuarioLogueado != null) {
//                	            JOptionPane.showMessageDialog(null, "Bienvenido " + usuarioLogueado.getTipo() + " " + usuarioLogueado.getNombre());
//                	        } else {
//                	            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
//                	        }
//                	    }
//                             	    
//         
//                	    
//                    break;
//
//                case 1:  // REGISTRO (solo clientes)
//                	
//                	Usuario nuevoUsuario = new Cliente();
//                    nuevoUsuario.setNombre(JOptionPane.showInputDialog("Ingrese nombre"));
//                    nuevoUsuario.setApellido(JOptionPane.showInputDialog("Ingrese apellido"));
//                    nuevoUsuario.setEmail(JOptionPane.showInputDialog("Ingrese email"));
//                    nuevoUsuario.setContrasenia(JOptionPane.showInputDialog("Ingrese contraseña"));
//                    nuevoUsuario.setTipo("cliente");
//
////FALTA REGISTRAR QUE ESTA EN DISCORD                    nuevoUsuario.registrarUsuario();
//                    break;
//
////                	
//            }
//        } while (menu != 2);
//		
//		
	}
}
