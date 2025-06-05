package BLL;
import javax.swing.JOptionPane;

public class Cliente extends Usuario{

	public Cliente(int id, String nombre, String apellido, String tipo, String email, String contrasenia) {
		super(id, nombre, apellido, tipo, email, contrasenia);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", tipo=" + tipo + ", email="
				+ email + ", Contrasenia=" + Contrasenia + "]";
	}

	

	
}
