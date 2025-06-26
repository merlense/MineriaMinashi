package DLL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

            String insertPedidoSQL = "INSERT INTO pedido (fechaPedido, fechaEntrega, estado) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(insertPedidoSQL, PreparedStatement.RETURN_GENERATED_KEYS);
            Date hoy = new Date(System.currentTimeMillis());
            ps.setDate(1, hoy);
            ps.setDate(2, null);
            ps.setString(3, "pendiente");
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
            // Solo actualizar el estado, la fechaEntrega se puede setear aparte si querés
            String sql = "UPDATE pedido SET estado = 'en proceso' WHERE idPedido = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idPedido);
            int rows = ps.executeUpdate();
            ps.close();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int obtenerPedidoActivo(int idUsuario) {
        try {
            String sql = "SELECT p.idPedido FROM pedido p " +
                         "JOIN usuario_tiene_pedido utp ON p.idPedido = utp.idPedido " +
                         "WHERE utp.idUsuario = ? AND p.fechaEntrega IS NULL";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            int idPedido = -1;
            if (rs.next()) {
                idPedido = rs.getInt("idPedido");
            }
            rs.close();
            ps.close();
            return idPedido;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int obtenerUltimoPedido(int idUsuario) {
        try {
            String sql = "SELECT p.idPedido FROM pedido p " +
                         "JOIN usuario_tiene_pedido utp ON p.idPedido = utp.idPedido " +
                         "WHERE utp.idUsuario = ? ORDER BY p.idPedido DESC LIMIT 1";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            int idPedido = -1;
            if (rs.next()) {
                idPedido = rs.getInt("idPedido");
            }
            rs.close();
            ps.close();
            return idPedido;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public DefaultTableModel obtenerResumenPedido(int idPedido) {
        String[] columnas = {"ID Mineral", "Tipo", "Cantidad", "Peso", "Precio", "Descuento", "Pureza", "Subtotal"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);

        try {
            String sql = "SELECT m.idMineral, m.tipo, ptm.cantidad, m.peso, m.precio, m.descuento, m.pureza, " +
                         "(m.precio * ptm.cantidad * (1 - m.descuento / 100)) AS subtotal " +
                         "FROM pedido_tiene_mineral ptm " +
                         "JOIN mineral m ON ptm.idMineral = m.idMineral " +
                         "WHERE ptm.idPedido = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idPedido);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[8];
                fila[0] = rs.getInt("idMineral");
                fila[1] = rs.getString("tipo");
                fila[2] = rs.getInt("cantidad");
                fila[3] = rs.getDouble("peso");
                fila[4] = rs.getDouble("precio");
                fila[5] = rs.getDouble("descuento") + "%";
                fila[6] = rs.getInt("pureza") + "%";
                fila[7] = rs.getDouble("subtotal");
                modelo.addRow(fila);
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelo;
    }

    public String[] obtenerFechasPedido(int idPedido) {
        try {
            String sql = "SELECT fechaPedido, fechaEntrega FROM pedido WHERE idPedido = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idPedido);
            ResultSet rs = ps.executeQuery();
            String[] fechas = new String[]{"", ""};
            if (rs.next()) {
                fechas[0] = rs.getDate("fechaPedido") != null ? rs.getDate("fechaPedido").toString() : "";
                fechas[1] = rs.getDate("fechaEntrega") != null ? rs.getDate("fechaEntrega").toString() : "";
            }
            rs.close();
            ps.close();
            return fechas;
        } catch (SQLException e) {
            e.printStackTrace();
            return new String[]{"", ""};
        }
    }

    public boolean actualizarFechaEntrega(int idPedido, Date fechaEntrega) {
        try {
            String sql = "UPDATE pedido SET fechaEntrega = ? WHERE idPedido = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, fechaEntrega);
            ps.setInt(2, idPedido);
            int filas = ps.executeUpdate();
            ps.close();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarEstadoPedido(int idPedido, String estado) {
        try {
            String sql = "UPDATE pedido SET estado = ? WHERE idPedido = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, estado);
            ps.setInt(2, idPedido);
            int filas = ps.executeUpdate();
            ps.close();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String obtenerEstadoPedido(int idPedido) {
        String estado = "";
        try {
            String sql = "SELECT estado FROM pedido WHERE idPedido = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idPedido);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                estado = rs.getString("estado");
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estado;
    }

    public DefaultTableModel obtenerPedidosConFiltros(Integer idPedidoFiltro, String estadoFiltro) {
        String[] columnas = {"ID Pedido", "Cliente", "Tipo", "Pureza", "Cantidad", "Subtotal", "Estado", "Entrega"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);

        try {
            StringBuilder sql = new StringBuilder("SELECT p.idPedido, u.nombre, m.tipo, m.pureza, ptm.cantidad, " +
                    "(m.precio * ptm.cantidad * (1 - m.descuento / 100)) AS subtotal, p.estado, p.fechaEntrega " +
                    "FROM pedido p " +
                    "JOIN usuario_tiene_pedido utp ON p.idPedido = utp.idPedido " +
                    "JOIN usuario u ON u.id = utp.idUsuario " +
                    "JOIN pedido_tiene_mineral ptm ON p.idPedido = ptm.idPedido " +
                    "JOIN mineral m ON ptm.idMineral = m.idMineral WHERE 1=1 ");

            if (idPedidoFiltro != null) {
                sql.append(" AND p.idPedido = ").append(idPedidoFiltro);
            }
            if (estadoFiltro != null) {
                sql.append(" AND p.estado = '").append(estadoFiltro).append("'");
            }

            sql.append(" ORDER BY p.idPedido DESC");

            PreparedStatement ps = con.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[8];
                fila[0] = rs.getInt("idPedido");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("tipo");
                fila[3] = rs.getInt("pureza") + "%";
                fila[4] = rs.getInt("cantidad");
                fila[5] = rs.getDouble("subtotal");
                fila[6] = rs.getString("estado");
                fila[7] = rs.getDate("fechaEntrega");
                modelo.addRow(fila);
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelo;
    }

    public ArrayList<int[]> obtenerListaPedidosDeUsuario(int idUsuario) {
        ArrayList<int[]> lista = new ArrayList<>();
        try {
            String sql = "SELECT p.idPedido, " +
                         "CASE p.estado " +
                         "WHEN 'pendiente' THEN 1 " +
                         "WHEN 'en proceso' THEN 2 " +
                         "WHEN 'finalizado' THEN 3 " +
                         "WHEN 'cancelado' THEN 4 ELSE 0 END as estadoNum " +
                         "FROM pedido p " +
                         "JOIN usuario_tiene_pedido utp ON p.idPedido = utp.idPedido " +
                         "WHERE utp.idUsuario = ? ORDER BY p.idPedido DESC";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new int[]{rs.getInt("idPedido"), rs.getInt("estadoNum")});
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public DefaultTableModel obtenerPedidosConDetalles(String estadoFiltro) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[] {
            "ID Pedido", "Cliente", "Tipo Mineral", "Pureza", "Cantidad", "Subtotal", "Estado", "Fecha Entrega"
        });

        try {
            String sql = "SELECT p.idPedido, u.nombre, m.tipo, m.pureza, ptm.cantidad, " +
                         "(m.precio * ptm.cantidad) AS subtotal, p.estado, p.fechaEntrega " +
                         "FROM pedido p " +
                         "JOIN usuario_tiene_pedido utp ON p.idPedido = utp.idPedido " +
                         "JOIN usuario u ON utp.idUsuario = u.id " +
                         "JOIN pedido_tiene_mineral ptm ON p.idPedido = ptm.idPedido " +
                         "JOIN mineral m ON ptm.idMineral = m.idMineral ";

            if (estadoFiltro != null && !estadoFiltro.isEmpty() && !estadoFiltro.equals("Todos")) {
                sql += "WHERE p.estado = ? ";
            }
            sql += "ORDER BY p.idPedido DESC";

            PreparedStatement ps = con.prepareStatement(sql);

            if (estadoFiltro != null && !estadoFiltro.isEmpty() && !estadoFiltro.equals("Todos")) {
                ps.setString(1, estadoFiltro);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[8];
                fila[0] = rs.getInt("idPedido");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("tipo");
                fila[3] = rs.getDouble("pureza");
                fila[4] = rs.getInt("cantidad");
                fila[5] = rs.getDouble("subtotal");
                String estado = rs.getString("estado");
                fila[6] = estado;

                Date fechaEntrega = rs.getDate("fechaEntrega");
                if ((estado.equals("pendiente") || estado.equals("en proceso")) && fechaEntrega == null) {
                    fila[7] = "A despachar";
                } else {
                    fila[7] = fechaEntrega != null ? fechaEntrega.toString() : "";
                }

                modelo.addRow(fila);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelo;
    }

    public DefaultTableModel obtenerResumenPedidoConEstadoYFechaEspecial(int idPedido) {
        String[] columnas = {"ID Mineral", "Tipo", "Cantidad", "Peso", "Precio", "Descuento", "Pureza", "Estado Pedido", "Fecha Entrega", "Subtotal"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);

        try {
            String sql = "SELECT m.idMineral, m.tipo, ptm.cantidad, m.peso, m.precio, m.descuento, m.pureza, p.estado, p.fechaEntrega, " +
                         "(m.precio * ptm.cantidad * (1 - m.descuento / 100)) AS subtotal " +
                         "FROM pedido_tiene_mineral ptm " +
                         "JOIN mineral m ON ptm.idMineral = m.idMineral " +
                         "JOIN pedido p ON ptm.idPedido = p.idPedido " +
                         "WHERE ptm.idPedido = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idPedido);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[10];
                fila[0] = rs.getInt("idMineral");
                fila[1] = rs.getString("tipo");
                fila[2] = rs.getInt("cantidad");
                fila[3] = rs.getDouble("peso");
                fila[4] = rs.getDouble("precio");
                fila[5] = rs.getDouble("descuento") + "%";
                fila[6] = rs.getInt("pureza") + "%";
                fila[7] = rs.getString("estado");
                String estado = rs.getString("estado");
                Date fechaEntrega = rs.getDate("fechaEntrega");
                if ((estado.equalsIgnoreCase("pendiente") || estado.equalsIgnoreCase("en proceso")) && fechaEntrega == null) {
                    fila[8] = "A despachar";
                } else {
                    fila[8] = fechaEntrega != null ? fechaEntrega.toString() : "-";
                }
                fila[9] = rs.getDouble("subtotal");

                modelo.addRow(fila);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelo;
    }

    public DefaultTableModel obtenerPedidosPorTipoUsuario(String tipoUsuario, String estadoFiltro) {
        DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{"ID Pedido", "Usuario", "Tipo Mineral", "Pureza", "Cantidad", "Estado", "Entrega"}, 0);

        try {
            StringBuilder sql = new StringBuilder(
                "SELECT p.idPedido, u.nombre AS usuario, m.tipo, m.pureza, ptm.cantidad, p.estado, p.fechaEntrega " +
                "FROM pedido p " +
                "JOIN usuario_tiene_pedido utp ON p.idPedido = utp.idPedido " +
                "JOIN usuario u ON u.id = utp.idUsuario " +
                "JOIN pedido_tiene_mineral ptm ON p.idPedido = ptm.idPedido " +
                "JOIN mineral m ON ptm.idMineral = m.idMineral " +
                "WHERE u.tipo = ? "
            );

            if (estadoFiltro != null && !estadoFiltro.isEmpty()) {
                sql.append(" AND p.estado = ? ");
            }

            sql.append(" ORDER BY p.idPedido DESC");

            PreparedStatement ps = con.prepareStatement(sql.toString());
            ps.setString(1, tipoUsuario);

            if (estadoFiltro != null && !estadoFiltro.isEmpty()) {
                ps.setString(2, estadoFiltro);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[7];
                fila[0] = rs.getInt("idPedido");
                fila[1] = rs.getString("usuario");
                fila[2] = rs.getString("tipo");
                fila[3] = rs.getInt("pureza") + "%";
                fila[4] = rs.getInt("cantidad");
                fila[5] = rs.getString("estado");
                Date fechaEntrega = rs.getDate("fechaEntrega");
                fila[6] = fechaEntrega != null ? fechaEntrega.toString() : "A despachar";

                modelo.addRow(fila);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelo;
    }

}
