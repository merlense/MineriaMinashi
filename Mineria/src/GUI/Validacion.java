package GUI;

public interface Validacion {

	default boolean ValidarMail(String mail) {
		if (mail.equals("@gmail.com.ar")) {
			return true;
		}else {
			return false;
		}
	}
	
	boolean validar(String data);
	}



