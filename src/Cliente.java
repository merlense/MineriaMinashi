import javax.swing.JOptionPane;

public class Cliente extends Usuario{

	
	public Cliente(String mail, String contrasenia) {
		super(mail, contrasenia);
	}

	@Override
	public void login() {
			String mail = JOptionPane.showInputDialog("");
		}
	
}
