
public class Usuario implements Encriptador{

	protected int id;
    protected String nombre;
    protected String apellido;
    protected String tipo;
    protected String email;
	protected String Contrasenia;
	
	public Usuario(int id, String nombre, String apellido, String tipo, String email, String contrasenia) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipo = tipo;
		this.email = email;
		Contrasenia = contrasenia;
	}
	
	public Usuario(String nombre, String apellido, String tipo, String email, String contrasenia) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipo = tipo;
		this.email = email;
		Contrasenia = contrasenia;
	}
	
	 public Usuario() {
	      
	    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasenia() {
		return Contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		Contrasenia = contrasenia;
	}
	

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", tipo=" + tipo + ", email="
				+ email + ", Contrasenia=" + Contrasenia + "]";
	}


	
	
	
	
	
	
	
}
