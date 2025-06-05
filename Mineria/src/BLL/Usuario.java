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
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getContrasenia() { return Contrasenia; }
    public void setContrasenia(String contrasenia) { this.Contrasenia = contrasenia; }

    // toString
    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido +
               ", tipo=" + tipo + ", email=" + email + ", Contrasenia=" + Contrasenia + "]";
    }

    public Usuario login(String email, String contrasenia) {
        if (email.isEmpty() || contrasenia.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Correo o contraseña vacíos");
            return null;
        } else {
            ControllerUsuario controller = new ControllerUsuario();
            return controller.login(email, contrasenia);
        }
    }

    public void agregarUsuario() {
        if (this.getNombre().isEmpty() || this.getContrasenia().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nombre o contraseña no pueden estar vacíos");
        } else {
            ControllerUsuario controller = new ControllerUsuario();
            controller.agregarUsuario(this);
        }
    }
}

