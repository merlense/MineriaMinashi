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
}
