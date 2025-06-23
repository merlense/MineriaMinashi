package DLL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

public class ControllerPedido {

    private Connection con;

    public ControllerPedido() {
        con = Conexion.getInstance().getConnection();
    }

    public int crearPedido(int idUsuario) {
        try {
            String consulta = "SELECT p.idPedido FROM pedido p " +
                              "JOIN usuario_tiene_pedido utp ON p.idPedido = utp.idPedido " +
                              "WHERE utp.idUsuario = ? AND p.fechaEntrega IS NULL";
            PreparedStatement checkStmt = con.prepareStatement(consulta);
            checkStmt.setInt(1, idUsuario);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                int pedidoExistente = rs.getInt("idPedido");
                rs.close();
                checkStmt.close();
                return pedidoExistente;  
            }
            rs.close();
            checkStmt.close();

            String insertPedidoSQL = "INSERT INTO pedido (fechaPedido, fechaEntrega) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(insertPedidoSQL, PreparedStatement.RETURN_GENERATED_KEYS);
            Date hoy = new Date(System.currentTimeMillis());
            ps.setDate(1, hoy);
            ps.setDate(2, null);
            ps.executeUpdate();

            ResultSet rs2 = ps.getGeneratedKeys();
            int idPedido = -1;
            if (rs2.next()) {
                idPedido = rs2.getInt(1);
            }
            rs2.close();
            ps.close();

            String insertRelacion = "INSERT INTO usuario_tiene_pedido (idUsuario, idPedido) VALUES (?, ?)";
            PreparedStatement psRel = con.prepareStatement(insertRelacion);
            psRel.setInt(1, idUsuario);
            psRel.setInt(2, idPedido);
            psRel.executeUpdate();
            psRel.close();

            return idPedido;

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean agregarMineralAPedido(int idPedido, int idMineral, int cantidad) {
        try {
            String sql = "INSERT INTO pedido_tiene_mineral (idPedido, idMineral, cantidad) VALUES (?, ?, ?) " +
                         "ON DUPLICATE KEY UPDATE cantidad = cantidad + VALUES(cantidad)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idPedido);
            ps.setInt(2, idMineral);
            ps.setInt(3, cantidad);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean finalizarPedido(int idPedido) {
        try {
            String sql = "UPDATE pedido SET fechaEntrega = ? WHERE idPedido = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            Date fechaEntrega = new Date(System.currentTimeMillis());
            ps.setDate(1, fechaEntrega);
            ps.setInt(2, idPedido);
            int rows = ps.executeUpdate();
            ps.close();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int obtenerPedidoActivo(int idUsuario) {
        int idPedido = -1;
        try {
            String sql = "SELECT p.idPedido FROM pedido p " +
                         "JOIN usuario_tiene_pedido utp ON p.idPedido = utp.idPedido " +
                         "WHERE utp.idUsuario = ? AND p.fechaEntrega IS NULL LIMIT 1";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                idPedido = rs.getInt("idPedido");
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idPedido;
    }

    public DefaultTableModel obtenerResumenPedido(int idPedido) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[] {
            "ID Pedido", "Fecha Pedido", "ID Mineral", "Nombre Mineral", "Cantidad", "Precio Unitario", "Pureza", "Subtotal"
        });

        String sql = "SELECT p.idPedido, p.fechaPedido, m.idMineral, m.tipo, ptm.cantidad, m.precio, m.pureza, " +
                     "(ptm.cantidad * m.precio) AS subtotal " +
                     "FROM Pedido p " +
                     "JOIN pedido_tiene_mineral ptm ON p.idPedido = ptm.idPedido " +
                     "JOIN Mineral m ON ptm.idMineral = m.idMineral " +
                     "WHERE p.idPedido = ?";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idPedido);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[] {
                    rs.getInt("idPedido"),
                    rs.getDate("fechaPedido"),
                    rs.getInt("idMineral"),
                    rs.getString("tipo"),
                    rs.getInt("cantidad"),
                    rs.getDouble("precio"),
                    rs.getDouble("pureza") + "%",
                    rs.getDouble("subtotal")
                };
                modelo.addRow(fila);
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelo;
    }

    public String[] obtenerFechasPedido(int idPedido) {
        String[] fechas = new String[2]; 
        try {
            String sql = "SELECT fechaPedido, fechaEntrega FROM pedido WHERE idPedido = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idPedido);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                fechas[0] = String.valueOf(rs.getDate("fechaPedido"));
                fechas[1] = String.valueOf(rs.getDate("fechaEntrega"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fechas;
    }

    public int obtenerUltimoPedido(int idUsuario) {
        int idPedido = -1;
        try {
            String sql = "SELECT p.idPedido FROM pedido p " +
                         "JOIN usuario_tiene_pedido utp ON p.idPedido = utp.idPedido " +
                         "WHERE utp.idUsuario = ? " +
                         "ORDER BY p.fechaPedido DESC LIMIT 1";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                idPedido = rs.getInt("idPedido");
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idPedido;
    }

    // Método para obtener pedidos con estado específico
    public DefaultTableModel obtenerPedidosConDetalles(String estadoFiltro) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[] {
            "ID Pedido", "Cliente", "Mineral", "Pureza", "Cantidad", "Subtotal", "Estado", "Fecha Entrega"
        });

        String sql = "SELECT p.idPedido, u.nombre AS cliente, m.tipo AS mineral, m.pureza, ptm.cantidad, " +
                     "(ptm.cantidad * m.precio) AS subtotal, p.estado, p.fechaEntrega " +
                     "FROM pedido p " +
                     "JOIN usuario_tiene_pedido utp ON p.idPedido = utp.idPedido " +
                     "JOIN usuario u ON utp.idUsuario = u.id " +
                     "JOIN pedido_tiene_mineral ptm ON p.idPedido = ptm.idPedido " +
                     "JOIN mineral m ON ptm.idMineral = m.idMineral";

        if (estadoFiltro != null && !estadoFiltro.equalsIgnoreCase("Todos")) {
            sql += " WHERE p.estado = ?";
        }

        sql += " ORDER BY p.idPedido";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            if (estadoFiltro != null && !estadoFiltro.equalsIgnoreCase("Todos")) {
                stmt.setString(1, estadoFiltro);
            }
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                modelo.addRow(new Object[] {
                    rs.getInt("idPedido"),
                    rs.getString("cliente"),
                    rs.getString("mineral"),
                    rs.getDouble("pureza") + "%",
                    rs.getInt("cantidad"),
                    rs.getDouble("subtotal"),
                    rs.getString("estado"),
                    rs.getDate("fechaEntrega")
                });
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelo;
    }


    // Método para actualizar el estado de un pedido
    public boolean actualizarEstadoPedido(int idPedido, String nuevoEstado) {
        try {
            String sql = "UPDATE pedido SET estado = ? WHERE idPedido = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nuevoEstado);
            ps.setInt(2, idPedido);
            int rows = ps.executeUpdate();
            ps.close();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
