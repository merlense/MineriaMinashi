package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import BLL.Mineral;

public class ControllerMineral {
    private Connection con;

    public ControllerMineral() {
        this.con = Conexion.getInstance().getConnection();
    }

    public LinkedList<Mineral> mostrarMinerales() {
        LinkedList<Mineral> minerales = new LinkedList<>();
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM mineral");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int idMineria = rs.getInt("idMineral");
                String tipo = rs.getString("tipo");
                int unidades = rs.getInt("unidades");
                double peso = rs.getDouble("peso");
                double pureza = rs.getDouble("pureza");
                double precio = rs.getDouble("precio");
                int descuento = rs.getInt("descuento");

                Mineral mineral = new Mineral(idMineria, tipo, unidades, peso, pureza, precio, descuento);
                minerales.add(mineral);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return minerales;
    }

    public boolean restarCantidad(int idMineral, int cantidadARestar) {
        try {
            PreparedStatement ps1 = con.prepareStatement("SELECT unidades, descuento FROM mineral WHERE idMineral = ?");
            ps1.setInt(1, idMineral);
            ResultSet rs = ps1.executeQuery();

            if (rs.next()) {
                int cantidadActual = rs.getInt("unidades");
                int descuentoActual = rs.getInt("descuento");

                if (cantidadARestar > cantidadActual) {
                    if (descuentoActual < 10) {
                        PreparedStatement psDesc = con.prepareStatement("UPDATE mineral SET descuento = 10 WHERE idMineral = ?");
                        psDesc.setInt(1, idMineral);
                        psDesc.executeUpdate();
                        psDesc.close();
                    }
                    rs.close();
                    ps1.close();
                    return false;
                }

                int nuevaCantidad = cantidadActual - cantidadARestar;

                PreparedStatement ps2 = con.prepareStatement("UPDATE mineral SET unidades = ? WHERE idMineral = ?");
                ps2.setInt(1, nuevaCantidad);
                ps2.setInt(2, idMineral);
                ps2.executeUpdate();

                rs.close();
                ps1.close();
                ps2.close();

                return true;
            }

            rs.close();
            ps1.close();
            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean insertarNuevoMineral(Mineral m) {
        try {
            String query = "INSERT INTO mineral (tipo, unidades, peso, pureza, precio, descuento) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, m.getTipo());
            ps.setInt(2, m.getUnidades());
            ps.setDouble(3, m.getPeso());
            ps.setDouble(4, m.getPureza());
            ps.setDouble(5, m.getPrecio());
            ps.setInt(6, m.getDescuento());

            int filas = ps.executeUpdate();
            ps.close();
            return filas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean actualizarCantidad(int idMineral, int nuevaCantidad) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE mineral SET unidades = ? WHERE idMineral = ?");
            ps.setInt(1, nuevaCantidad);
            ps.setInt(2, idMineral);
            int filas = ps.executeUpdate();
            ps.close();
            return filas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public int getStock(int idMineral) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT unidades FROM mineral WHERE idMineral = ?");
            ps.setInt(1, idMineral);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("unidades");
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean actualizarDescuento(int idMineral, int nuevoDescuento) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE mineral SET descuento = ? WHERE idMineral = ?");
            ps.setInt(1, nuevoDescuento);
            ps.setInt(2, idMineral);
            int filas = ps.executeUpdate();
            ps.close();
            return filas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    
    
}
