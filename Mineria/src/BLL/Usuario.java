package BLL;

import javax.swing.JOptionPane;

import DLL.ControllerUsuario;
import Repository.Encriptador;

public class Usuario implements Encriptador {

    protected int id;
    protected String nombre;
    protected String apellido;
    protected String tipo;
    protected String email;
    protected String Contrasenia;

    // Constructor completo
    public Usuario(int id, String nombre, String apellido, String tipo, String email, String contrasenia) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipo = tipo;
        this.email = email;
        this.Contrasenia = contrasenia;
    }

    // Constructor sin ID
    public Usuario(String nombre, String apellido, String tipo, String email, String contrasenia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipo = tipo;
        this.email = email;
        this.Contrasenia = contrasenia;
    }

    // Constructor vacío
    public Usuario() {}

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { 
    	this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { 
    	this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { 
    	this.apellido = apellido; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { 
    	this.tipo = tipo; }

    public String getEmail() { return email; }
    public void setEmail(String email) { 
    	this.email = email; }

    public String getContrasenia() { 
    	return Contrasenia; }
    
    public void setContrasenia(String contrasenia) { 
    	this.Contrasenia = contrasenia; }

    // toString
    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido +
               ", tipo=" + tipo + ", email=" + email + ", Contrasenia=" + Contrasenia + "]";
    }

    public boolean login() {
        if (this.email == null || this.email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El correo no puede estar vacío");
            return false;
        }
        if (this.Contrasenia == null || this.Contrasenia.isEmpty()) {
            JOptionPane.showMessageDialog(null, "La contraseña no puede estar vacía");
            return false;
        }
        return true;
    }

    public void agregarUsuario() {
    	if (this.getNombre().isEmpty() || this.getContrasenia().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Nombre o contraseña no pueden estar vacíos");
    	} else {
    		ControllerUsuario controller = new ControllerUsuario();
    		controller.agregarUsuario(this);
    	}
    }
    
    public boolean validarRegistro() {
        if (
            (this.nombre == null || this.nombre.trim().isEmpty()) &&
            (this.apellido == null || this.apellido.trim().isEmpty()) &&
            (this.email == null || this.email.trim().isEmpty()) &&
            (this.Contrasenia == null || this.Contrasenia.trim().isEmpty())
        ) {
            JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacíos");
            return false;
        } else if (this.nombre == null || this.nombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nombre inválido");
            return false;
        } else if (this.apellido == null || this.apellido.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Apellido inválido");
            return false;
        } else if (this.email == null || this.email.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Email inválido");
            return false;
        } else if (this.Contrasenia == null || this.Contrasenia.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Contraseña inválida");
            return false;
        } else {
            return true;
        }
    }

    
    public boolean registrarUsuario() {
        if (!validarRegistro()) {
            return false;
        }

        ControllerUsuario controller = new ControllerUsuario();

        if (controller.existeEmail(this.email)) {
            JOptionPane.showMessageDialog(null, "El email ya está registrado");
            return false;
        }

        controller.agregarUsuario(this);
        JOptionPane.showMessageDialog(null, "Usuario registrado correctamente");
        return true;
    }

    
}

