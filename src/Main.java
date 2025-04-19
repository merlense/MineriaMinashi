import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
  
		JOptionPane.showMessageDialog(null, "Bienvenido a la Mineria Minashi :)");
		
		String[] opciones = {"Iniciar sesion", "Registrarte", "Salir"};
	    int opcion;

	    do {
	    	opcion = JOptionPane.showOptionDialog(null, "Selecione una opcion", null, 0, 0, null, opciones, opciones[0]);
	    	
	    	switch (opcion) {
			case 0:
				mail.ValidarMail();

				JOptionPane.showMessageDialog(null, opciones);
				break;
			case 1:
				break;
			case 2:
				break;
			default:
				break;
			}

	    }while(opcion!=3);
		
	}

}
