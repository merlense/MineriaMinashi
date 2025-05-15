import javax.swing.JOptionPane;

public class Cliente extends Usuario{

	
	public Cliente(String mail, String contrasenia) {
		super(mail, contrasenia);
	}

	@Override
	public void login() {
		JOptionPane.showConfirmDialog(null, "Usted esta por ingresar sesion...");
		JOptionPane.showMessageDialog(null, "Ingrese su mail");
			String mail = JOptionPane.showInputDialog("");
			JOptionPane.showMessageDialog(null, "Ingrese su contraseña");
			String contrasenia = JOptionPane.showInputDialog("");
		}
	
	
	public void registrarte() {
		JOptionPane.showConfirmDialog(null, "Usted esta por registrarse...");
		JOptionPane.showMessageDialog(null, "Ingrese su mail");
			String mail = JOptionPane.showInputDialog("");
			JOptionPane.showMessageDialog(null, "Ingrese su contraseña");
			String contrasenia = JOptionPane.showInputDialog("");
	}
	
}
