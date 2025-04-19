import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
  
		Operador operador = new Operador ("mora@gmail.com", "1234567");
		Cliente cliente = new Cliente ("nicolas@gmail.com", "12345678"); 
		Encargado_Venta encargado = new Encargado_Venta ("martina@gmail.com", "123456789");
		
		JOptionPane.showMessageDialog(null, "Bienvenido a la Mineria Minashi :)");
		
		String[] opcionesCliente = {"Ver catalogo", "Hacer pedido", "Finalizar pedido", "Salir"};
		String[] opcionesOperador = {"Ver pedidos", "Explotar mineral", "Agregar stock", "Salir"};
		String[] opcionesEncargado = {"Ver pedidos","Revisar stock", "Eliminar pedidos", "Salir"};
					
		String mail;
		String contrasenia;

		do {
			operador.login();

			if (mail.equals("mora@gmail.com") && contrasenia.equals("1234567")) {
				int opcion;
				do {
					opcion = JOptionPane.showOptionDialog(null, "Bienvenido " + mail, mail, 0, 0, null,
							opcionesProfesor, opcionesProfesor[0]);
					switch (opcion) {
					case 0:
						JOptionPane.showMessageDialog(null, "Creando clase");
						break;
					case 1:
						JOptionPane.showMessageDialog(null, "Ver clase");
						break;
					case 2:
						JOptionPane.showMessageDialog(null, "Ediar clase");
						break;
					case 3:
						JOptionPane.showMessageDialog(null, "Salir");
						break;
					default:
						break;
					}
				} while (opcion != 3);
		
	   

	    do {
	    	opcion = JOptionPane.showOptionDialog(null, "Selecione una opcion", null, 0, 0, null, opciones, opciones[0]);
	    	
	    	switch (opcion) {
			case 0:
				 cliente.login();
				break;
			case 1:
				
				break;
			case 2:
				JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
				break;
			default:
				break;
			}

	    }while(opcion!=2);
		
	}

		}}
