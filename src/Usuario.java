
public class Usuario {

	private String Mail;
	private String Contrasenia;
	
	
	public Usuario(String mail, String contrasenia) {
		super();
		Mail = mail;
		Contrasenia = contrasenia;
	}


	public String getMail() {
		return Mail;
	}


	public void setMail(String mail) {
		Mail = mail;
	}


	public String getContrasenia() {
		return Contrasenia;
	}


	public void setContrasenia(String contrasenia) {
		Contrasenia = contrasenia;
	}  
	
	public void login() {
		
	}
	
}
