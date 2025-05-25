import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

public class ControllerUsuario<T extends Usuario> {

    private static Connection con = Conexion.getInstance().getConnection();

    public T login(String email, String password) {
        T usuario = null;
        try {
            PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM usuario WHERE email = ? AND password = ?"
            );
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String tipo = rs.getString("tipo");

                switch (tipo.toLowerCase()) {
                    case "cliente":
                        usuario = (T) new Cliente(id, nombre, apellido, tipo, email, password);
                        break;
                    case "operador":
                        usuario = (T) new Operador(id, nombre, apellido, tipo, email, password);
                        break;
                    case "encargado":
                        usuario = (T) new Encargado_Venta(id, nombre, apellido, tipo, email, password);
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
        try {
            PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO usuario (nombre, apellido, tipo, email, password) VALUES (?, ?, ?, ?, ?)"
            );
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setString(3, usuario.getTipo());
            statement.setString(4, usuario.getEmail());
            statement.setString(5, usuario.getContrasenia());

            int filas = statement.executeUpdate();
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
                String password = rs.getString("password");

                switch (tipo.toLowerCase()) {
                    case "cliente":
                        usuarios.add((T) new Cliente(id, nombre, apellido, tipo, email, password));
                        break;
                    case "operador":
                        usuarios.add((T) new Operador(id, nombre, apellido, tipo, email, password));
                        break;
                    case "encargado":
                        usuarios.add((T) new Encargado_Venta(id, nombre, apellido, tipo, email, password));
                        break;
                    default:
                        System.out.println("Tipo desconocido: " + tipo);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuarios;
    }
}
