package BLL;
public class Operador extends Usuario {

	
	public Operador(int id, String nombre, String apellido, String tipo, String email, String contrasenia) {
		super(id, nombre, apellido, tipo, email, contrasenia);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Operador [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", tipo=" + tipo + ", email="
				+ email + ", Contrasenia=" + Contrasenia + "]";
	}


	//@Override

	}
