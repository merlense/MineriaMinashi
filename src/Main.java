import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {

		Operador operador = new Operador("mora@gmail.com", "1234567");
		Cliente cliente = new Cliente("nicolas@gmail.com", "12345678");
		Encargado_Venta encargado = new Encargado_Venta("martina@gmail.com", "123456789");

		JOptionPane.showMessageDialog(null, "Bienvenido a la Mineria Minashi :)");

		String[] opcionesCliente = {"Ver catalogo", "Hacer pedido", "Finalizar pedido", "Salir"};
		String[] opcionesOperador = {"Ver pedidos", "Explotar mineral", "Agregar stock", "Salir"};
		String[] opcionesEncargado = {"Ver pedidos", "Revisar stock", "Eliminar pedidos", "Salir"};

		String mail = JOptionPane.showInputDialog("Ingrese su correo:");
		String contrasenia = JOptionPane.showInputDialog("Ingrese su contraseña:");

		if (mail.equals(operador.getMail()) && contrasenia.equals(operador.getContrasenia())) {
			JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso como OPERADOR");

			int opcion;
			do {
				opcion = JOptionPane.showOptionDialog(null, "Bienvenido " + mail, "Menú Operador",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
						opcionesOperador, opcionesOperador[0]);

				switch (opcion) {
					case 0:
						JOptionPane.showMessageDialog(null, "Ver pedidos");
						break;
					case 1:
						JOptionPane.showMessageDialog(null, "Explotar mineral");
						break;
					case 2:
						JOptionPane.showMessageDialog(null, "Agregar stock");
						break;
					case 3:
						JOptionPane.showMessageDialog(null, "Salir");
						break;
				}
			} while (opcion != 3);

		} else if (mail.equals(cliente.getMail()) && contrasenia.equals(cliente.getContrasenia())) {
			JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso como CLIENTE");

			int opcion;
			do {
				opcion = JOptionPane.showOptionDialog(null, "Bienvenido " + mail, "Menú Cliente",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
						opcionesCliente, opcionesCliente[0]);

				switch (opcion) {
					case 0:
						JOptionPane.showMessageDialog(null, "Ver catálogo");
						break;
					case 1:
						JOptionPane.showMessageDialog(null, "Hacer pedido");
						break;
					case 2:
						JOptionPane.showMessageDialog(null, "Finalizar pedido");
						break;
					case 3:
						JOptionPane.showMessageDialog(null, "Salir");
						break;
				}
			} while (opcion != 3);

		} else if (mail.equals(encargado.getMail()) && contrasenia.equals(encargado.getContrasenia())) {
			JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso como ENCARGADO DE VENTA");

			int opcion;
			do {
				opcion = JOptionPane.showOptionDialog(null, "Bienvenido " + mail, "Menú Encargado",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
						opcionesEncargado, opcionesEncargado[0]);

				switch (opcion) {
					case 0:
						JOptionPane.showMessageDialog(null, "Ver pedidos");
						break;
					case 1:
						JOptionPane.showMessageDialog(null, "Revisar stock");
						break;
					case 2:
						JOptionPane.showMessageDialog(null, "Eliminar pedidos");
						break;
					case 3:
						JOptionPane.showMessageDialog(null, "Salir");
						break;
				}
			} while (opcion != 3);

		} else {
			JOptionPane.showMessageDialog(null, "Cuenta no registrada");
		}
	}
}
