package GUI;

import java.awt.Toolkit;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.Mineral;
import BLL.Pedido;
import DLL.ControllerMineral;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;

public class RevisarStock extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Mineral mineralSeleccionado = null;
    private JTable tabla;
    private JButton alertaStock;
    private JButton alertaStock_1;

    public RevisarStock(Pedido pedido) {
        setIconImage(Toolkit.getDefaultToolkit().getImage(RevisarStock.class.getResource("/IMG/diamante-super-chico.png")));
        setTitle("Revisar stock");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 747, 430);
        setLocationRelativeTo(null); // Centra la ventana

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(null);  // importante para colocar el scrollPane
        setContentPane(contentPane);

        // Columnas de la tabla
        String[] columnas = { "ID", "Nombre", "Cantidad", "Peso", "Precio", "Pureza", "Descuento" };
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Cargar minerales desde la base de datos
        ControllerMineral controlador = new ControllerMineral();
        LinkedList<Mineral> minerales = controlador.mostrarMinerales();

        for (Mineral m : minerales) {
            Object[] fila = {
                m.getIdMineria(),
                m.getTipo(),
                m.getUnidades(),
                m.getPeso(),
                m.getPrecio(),
                m.getPureza() + "%",
                m.getDescuento() + "%"
            };
            modelo.addRow(fila);
        }

        // Crear tabla y scrollPane
        tabla = new JTable(modelo);
        tabla.setRowSelectionAllowed(true);
        tabla.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBounds(10, 10, 715, 222); // ajustá tamaño si querés
        contentPane.add(scrollPane);
        
        alertaStock = new JButton("Pedir stock");
        alertaStock.setFont(new Font("Tahoma", Font.PLAIN, 16));
        alertaStock.setBounds(165, 285, 121, 46);
        contentPane.add(alertaStock);
        
        alertaStock_1 = new JButton("Pedir stock");
        alertaStock_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        alertaStock_1.setBounds(428, 285, 121, 46);
        contentPane.add(alertaStock_1);

        // Evento al seleccionar una fila
        tabla.getSelectionModel().addListSelectionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila != -1) {
                int idSeleccionado = (int) tabla.getValueAt(fila, 0);
                for (Mineral m : minerales) {
                    if (m.getIdMineria() == idSeleccionado) {
                        mineralSeleccionado = m;
                        break;
                    }
                }
            }
        });
    }
}
