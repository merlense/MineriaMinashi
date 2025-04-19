
public interface Validacion {

	default boolean ValidarMail(String data) {
		if (data.contains("@gmail.edu.ar")) {
			return true;
		}else {
			return false;
		}
	}
	
	boolean validar(String data);
	}



