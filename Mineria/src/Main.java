import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		
		Conexion.getInstance();

		Operador operador = new Operador("mora@gmail.com", "1234567");
		Cliente cliente = new Cliente("bruno@gmail.com", "12345678");
		Encargado_Venta encargado = new Encargado_Venta("martina@gmail.com", "123456789");
//agus labura
		JOptionPane.showMessageDialog(null, "Bienvenido a la Mineria Minashi :)");

		String[] opcionesOperador = {"Ver pedidos", "Explotar mineral", "Agregar stock", "Salir"};
		String[] opcionesCliente = {"Ver catalogo", "Hacer pedido", "Finalizar pedido", "Salir"};  //VER ESTADO, VENTA...
		String[] opcionesEncargado = {"Ver pedidos", "Revisar stock", "Eliminar pedidos", "Salir"};
		
		//SE PODRIA AGREGAR A FUTURO UNA OPCION DE "RESUMEN DE VENTAS", ETC. PARA DARLE MAS ENTIDAD AL ENCARGADO.

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
						JOptionPane.showMessageDialog(null,
							    "Mensaje del Encargado:\n\n" +
							    "Se detectó bajo stock en el sistema.\n" +
							    "Por favor reponer:\n" +
							    "- 10 unidades de Oro\n" +
							    "- 5 unidades de Plata\n\n" +
							    "Gracias.",
							    "Reposición Solicitada",
							    JOptionPane.WARNING_MESSAGE);
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
		                                //JOptionPane.showMessageDialog(null, "Saliendo..." );
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
	                    	elegirM = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Selecione el mineral que desea agregar.", 
	                    			0, JOptionPane.INFORMATION_MESSAGE, null, elegirMineral, elegirMineral[0]);
	                    	
	                        switch (elegirM) {
	                            case 0:
	                            	int agregarOro = Integer.parseInt(JOptionPane.showInputDialog("¿Cuánto oro desea agregar?"));
	                                JOptionPane.showMessageDialog(null, "Se agrego " + agregarOro +" de oro al inventario" );
	                                break;
	                            case 1:
	                            	int agregarCobre = Integer.parseInt(JOptionPane.showInputDialog("¿Cuánto cobre desea agregar?"));
	                                JOptionPane.showMessageDialog(null, "Se agrego " + agregarCobre +" de oro al inventario" );
	                                break;
	                            case 2:
	                            	int agregarPlata = Integer.parseInt(JOptionPane.showInputDialog("¿Cuánta plata desea agregar?"));
	                                JOptionPane.showMessageDialog(null, "Se agrego " + agregarPlata +" de oro al inventario" );
	                                break;
	                            case 3:
	                                //JOptionPane.showMessageDialog(null, "Agregando..." );
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
						JOptionPane.showMessageDialog(null,
							    "Catálogo disponible:\n" +
							    "- Oro: $1500/u\n" +
							    "- Plata: $800/u\n" +
							    "- Cobre: $400/u",
							    "Catálogo de Minerales",
							    JOptionPane.INFORMATION_MESSAGE);						break;
					
					case 1:
						//HACER PEDIDO
                        JOptionPane.showMessageDialog(null, "En caso de que el mineral no este en stock inmediato, recibira un 10% de descuento :)" );
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
	                                //JOptionPane.showMessageDialog(null, "Saliendo..." );
	                                break;
	                            default:
	                                break;
	                        }
	                    } while (elegirM != 3);						
	                    break;
	                    
					case 2:
						//FINALIZAR PEDIDO
						JOptionPane.showMessageDialog(null,
							    "Resumen de su pedido:\n" +
							    "- 3 unidades de Oro ($1500 c/u)\n" +
							    "- 2 unidades de Plata ($800 c/u)\n" +
							    "------------------------------\n" +
							    "Total: $6100\n",
							    "Resumen de Pedido",
							    JOptionPane.INFORMATION_MESSAGE);
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
						JOptionPane.showMessageDialog(null,
							    "Pedidos recientes:\n" +
							    "1. Cliente: juan@mail.com - 5 Oro\n" +
							    "2. Cliente: ana@mail.com - 3 Plata\n" +
							    "3. Cliente: leo@mail.com - 2 Cobre",
							    "Historial de Pedidos",
							    JOptionPane.INFORMATION_MESSAGE);
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
						String pedidoAEliminar = JOptionPane.showInputDialog("Ingrese el ID del pedido que desea eliminar:");
						JOptionPane.showMessageDialog(null,
						    "Pedido " + pedidoAEliminar + " eliminado correctamente.",
						    "Eliminación Exitosa",
						    JOptionPane.INFORMATION_MESSAGE);						
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
