import javax.swing.JOptionPane;

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
                    String nombre = "";
                    while (nombre.isEmpty()) {
                        nombre = JOptionPane.showInputDialog("Ingrese email");
                        if (nombre.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Incorrecto");
                        }
                    }

                    String contrasenia = "";
                    while (contrasenia.isEmpty()) {
                        contrasenia = JOptionPane.showInputDialog("Ingrese contraseña");
                        if (contrasenia.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Incorrecto");
                        }
                    }

                    Usuario usuario = controller.login(nombre, contrasenia);
                    if (usuario != null) {
                        if (usuario instanceof Cliente) {
                            JOptionPane.showMessageDialog(null, "Bienvenido Cliente " + usuario.getNombre());
                            // Ir a menu de profesor
                        } else if (usuario instanceof Operador) {
                            JOptionPane.showMessageDialog(null, "Bienvenido Operador " + usuario.getNombre());
                            // Ir a menu de alumno
                        } else if (usuario instanceof Encargado_Venta) {
                            JOptionPane.showMessageDialog(null, "Bienvenido Encargado de venta " + usuario.getNombre());
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                    }
                    break;

                case 1:  // REGISTRO (solo clientes)
                    String nombreRegistro = JOptionPane.showInputDialog("Ingrese nombre");
                    if (nombreRegistro == null || nombreRegistro.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Nombre inválido");
                        break;
                    }

                    String apellido = JOptionPane.showInputDialog("Ingrese apellido");
                    if (apellido == null || apellido.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Apellido inválido");
                        break;
                    }

                    String nuevoEmail = JOptionPane.showInputDialog("Ingrese email");
                    if (nuevoEmail == null || nuevoEmail.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Email inválido");
                        break;
                    }

                    if (controller.existeEmail(nuevoEmail)) {
                        JOptionPane.showMessageDialog(null, "El email ya está registrado");
                        break;
                    }

                    String nuevaContrasenia = JOptionPane.showInputDialog("Ingrese contraseña");
                    if (nuevaContrasenia == null || nuevaContrasenia.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Contraseña inválida");
                        break;
                    }

                    Usuario nuevoUsuario = new Cliente(0, nombreRegistro, apellido, "Cliente", nuevoEmail, nuevaContrasenia);

                    controller.agregarUsuario(nuevoUsuario);
                    JOptionPane.showMessageDialog(null, "Cliente registrado correctamente");
                    break;
            }
        } while (menu != 2);
		
		
	}
}
