package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import BLL.Usuario;
import BLL.Cliente;
import BLL.Operador;
import BLL.Encargado_Venta;

public class ControllerUsuario {

    private Connection con;

    public ControllerUsuario() {
        this.con = Conexion.getInstance().getConnection();
    }

    public Usuario login(String email, String contrasenia) {
        Usuario usuario = null;
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM usuario WHERE email = ? AND contrasenia = ?");
            Usuario temp = new Usuario();
            stmt.setString(1, email);
            stmt.setString(2, temp.encriptar(contrasenia));

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String tipo = rs.getString("tipo");

                switch (tipo.toLowerCase()) {
                    case "cliente":
                        usuario = new Cliente(id, nombre, apellido, tipo, email, contrasenia);

                        ControllerPedido controllerPedido = new ControllerPedido();
                        int idPedido = controllerPedido.obtenerPedidoActivo(id);
                        if (idPedido == -1) {
                            controllerPedido.crearPedido(id);
                        }
                        break;

                    case "operador":
                        usuario = new Operador(id, nombre, apellido, tipo, email, contrasenia);
                        break;

                    case "encargado":
                        usuario = new Encargado_Venta(id, nombre, apellido, tipo, email, contrasenia);
                        break;

                    default:
                        System.out.println("Tipo de usuario desconocido: " + tipo);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public void agregarUsuario(Usuario usuario) {
        if (usuario.getEmail() == null || !usuario.getEmail().toLowerCase().endsWith("@gmail.com")) {
            throw new IllegalArgumentException("El email no es valido");
        }

        try {
            PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO usuario (nombre, apellido, tipo, email, contrasenia) VALUES (?, ?, ?, ?, ?)"
            );
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido());
            stmt.setString(3, usuario.getTipo());
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, usuario.encriptar(usuario.getContrasenia()));

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Usuario agregado correctamente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean existeEmail(String email) {
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT id FROM usuario WHERE email = ?");
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public LinkedList<Usuario> mostrarUsuarios() {
        LinkedList<Usuario> usuarios = new LinkedList<>();
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM usuario");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String tipo = rs.getString("tipo");
                String email = rs.getString("email");
                String contrasenia = rs.getString("contrasenia");

                Usuario usuario = null;
                switch (tipo.toLowerCase()) {
                    case "cliente":
                        usuario = new Cliente(id, nombre, apellido, tipo, email, contrasenia);
                        break;
                    case "operador":
                        usuario = new Operador(id, nombre, apellido, tipo, email, contrasenia);
                        break;
                    case "encargado":
                        usuario = new Encargado_Venta(id, nombre, apellido, tipo, email, contrasenia);
                        break;
                    default:
                        System.out.println("Tipo desconocido: " + tipo);
                        break;
                }

                if (usuario != null) {
                    usuarios.add(usuario);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuarios;
    }
}
