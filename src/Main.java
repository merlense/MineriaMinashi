import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {

		Operador operador = new Operador("mora@gmail.com", "1234567");
		Cliente cliente = new Cliente("nicolas@gmail.com", "12345678");
		Encargado_Venta encargado = new Encargado_Venta("martina@gmail.com", "123456789");

		JOptionPane.showMessageDialog(null, "Bienvenido a la Mineria Minashi :)");

		String[] opcionesOperador = {"Ver pedidos", "Explotar mineral", "Agregar stock", "Salir"};
		String[] opcionesCliente = {"Ver catalogo", "Hacer pedido", "Finalizar pedido", "Salir"};
		String[] opcionesEncargado = {"Ver pedidos", "Revisar stock", "Eliminar pedidos", "Salir"};

		String mail = JOptionPane.showInputDialog("Ingrese su correo:");
		String contrasenia = JOptionPane.showInputDialog("Ingrese su contraseña:");

		if (mail.equals(operador.getMail()) && contrasenia.equals(operador.getContrasenia())) {
			JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso...");

			int opcion;
			
			//OPERADOR...
			do {
				opcion = JOptionPane.showOptionDialog(null, "Bienvenido " + mail, "Menú Operador",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
						opcionesOperador, opcionesOperador[0]);

				switch (opcion) {
					case 0:
						//VER PEDIDOS
							JOptionPane.showMessageDialog(null, "Viendo pedidos.");
						break;
					case 1:			
						///EXPLOTAR MINERAL
						 String[] elegirOpcion = {"Oro", "Cobre", "Plata", "Salir"};
		                    int elegir;
		                    do {
		                    	elegir = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Ver minerales para explotar", 0, JOptionPane.INFORMATION_MESSAGE,
		                        		null, elegirOpcion, elegirOpcion[0]);
		                        switch (elegir) {
		                            case 0:
		                                JOptionPane.showMessageDialog(null, "Explotando Oro..." );
		                                break;
		                            case 1:
		                                JOptionPane.showMessageDialog(null, "Explotando Cobre..." );
		                                break;
		                            case 2:
		                                JOptionPane.showMessageDialog(null, "Explotando Plata..." );
		                                break;
		                            case 3:
		                                JOptionPane.showMessageDialog(null, "Saliendo..." );
		                                break;
		                            default:
		                                break;
		                        }
		                    } while (elegir != 3);
		                    break;						
					case 2:
						//AGREGAR STOCK
						JOptionPane.showMessageDialog(null, "Selecione el mineral que desea agregar.");

						String[] elegirMineral = {"Oro", "Cobre", "Plata", "Salir"};
	                    int elegirM;
	                    do {
	                    	elegirM = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Selecione el mineral que desea explotar.", 
	                    			0, JOptionPane.INFORMATION_MESSAGE, null, elegirMineral, elegirMineral[0]);
	                    	
	                        switch (elegirM) {
	                            case 0:
	                                JOptionPane.showMessageDialog(null, "Agregando Oro..." );
	                                break;
	                            case 1:
	                                JOptionPane.showMessageDialog(null, "Agregando Cobre..." );
	                                break;
	                            case 2:
	                                JOptionPane.showMessageDialog(null, "Agregando Plata..." );
	                                break;
	                            case 3:
	                                JOptionPane.showMessageDialog(null, "Agregando..." );
	                                break;
	                            default:
	                                break;
	                        }
	                    } while (elegirM != 3);
						break;
					case 3:
						//SALIR
						JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
						break;
				}
			} while (opcion != 3);

		} else if (mail.equals(cliente.getMail()) && contrasenia.equals(cliente.getContrasenia())) {
			JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso...");


			//CLIENTE...
			int opcion;
			do {
				opcion = JOptionPane.showOptionDialog(null, "Bienvenido " + mail, "Menú Cliente",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
						opcionesCliente, opcionesCliente[0]);

				switch (opcion) {
					case 0:
						//VER CATALOGO
						JOptionPane.showMessageDialog(null, "Viendo catalogo...");
						break;
						
					case 1:
						//HACER PEDIDO
                        JOptionPane.showMessageDialog(null, "En caso de que el mineral no este en stock inmediato, recibira un 10% de descuanto :)" );
						String[] elegirMineral = {"Oro", "Cobre", "Plata", "Salir"};
	                    int elegirM;
	                    do {
	                    	elegirM = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Selecione el mineral que desea pedir.", 
	                    			0, JOptionPane.INFORMATION_MESSAGE, null, elegirMineral, elegirMineral[0]);
	                    	
	                        switch (elegirM) {
	                            case 0:
	                            	int agregarOro = Integer.parseInt(JOptionPane.showInputDialog("¿Cuánto oro desea agregar?"));
	                                JOptionPane.showMessageDialog(null, "Se agrego " + agregarOro +" de oro al pedido" );
	                                break;
	                                
	                            case 1:
	                            	int agregarCobre = Integer.parseInt(JOptionPane.showInputDialog("¿Cuánto cobre desea agregar?"));
	                                JOptionPane.showMessageDialog(null, "Se agrego " + agregarCobre +" de cobre al pedido" );
	                            	break;
	                            case 2:
	                            	int agregarPlata = Integer.parseInt(JOptionPane.showInputDialog("¿Cuánto plata desea agregar?"));
	                                JOptionPane.showMessageDialog(null, "Se agrego " + agregarPlata +" de plata al pedido" );
	                                break;
	                            case 3:
	                                JOptionPane.showMessageDialog(null, "Saliendo..." );
	                                break;
	                            default:
	                                break;
	                        }
	                    } while (elegirM != 3);						
	                    break;
	                    
					case 2:
						//FINALIZAR PEDIDO
						JOptionPane.showMessageDialog(null, "Resumen del pedido: ");
						//Y en esta linea mostraria un resumen del pedido y preg si hay que agregar una opcion de pago
						
						break;
					case 3:
						
						//SALIR
						JOptionPane.showMessageDialog(null, "Salir");
						break;
				}
			} while (opcion != 3);

		} else if (mail.equals(encargado.getMail()) && contrasenia.equals(encargado.getContrasenia())) {
			JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso...");

			
			
			
			
			//ENCARGADO... :V
			
			int opcion;
			do {
				opcion = JOptionPane.showOptionDialog(null, "Bienvenido " + mail, "Menú Encargado",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
						opcionesEncargado, opcionesEncargado[0]);

				switch (opcion) {
					case 0:
						//VER PEDIDOS
						JOptionPane.showMessageDialog(null, "Viendo pedidos");
						break;
						
					case 1:
						//REVISAR STOCK
						String[] elegirMineral = {"Oro", "Cobre", "Plata", "Salir"};
	                    int elegirM;
	                    do {
	                    	elegirM = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Revisar stock", 
	                    			0, JOptionPane.INFORMATION_MESSAGE, null, elegirMineral, elegirMineral[0]);
	                    	
	                        switch (elegirM) {
	                            case 0:
	                                JOptionPane.showMessageDialog(null, "Cantidad de oro: " );
	                                break;
	                            case 1:
	                                JOptionPane.showMessageDialog(null, "Cantidad de cobre: " );
	                                break;
	                            case 2:
	                                JOptionPane.showMessageDialog(null, "Cantidad de plata: ." );
	                                break;
	                            case 3:
	                                JOptionPane.showMessageDialog(null, "Saliendo..." );
	                                break;
	                            default:
	                                break;
	                        }
	                    } while (elegirM != 3);
						break;	
						
					case 2:
						//ELIMINAR PEDIDOS
						JOptionPane.showMessageDialog(null, "Eliminar pedidos");
						break;
						
					case 3:
						//SALIR
						JOptionPane.showMessageDialog(null, "Salir");
						break;
				}
			} while (opcion != 3);

		} else {
			JOptionPane.showMessageDialog(null, "Cuenta no registrada");
		}
	}
}
