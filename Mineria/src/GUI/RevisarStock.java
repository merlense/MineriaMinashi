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
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RevisarStock extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Mineral mineralSeleccionado = null;
    private JTable tabla;
    private JButton alertaStock;
    private JButton VolverBTN;
    private JLabel RevisarStock;
    private JButton btnFiltrar;
    private JTextField textField;
    private JLabel lblNewLabel;
    private LinkedList<Mineral> minerales; 

    public RevisarStock(Pedido pedido) {
        setIconImage(Toolkit.getDefaultToolkit().getImage(RevisarStock.class.getResource("/IMG/diamante-super-chico.png")));
        setTitle("Revisar stock");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 747, 430);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        String[] columnas = { "ID", "Nombre", "Cantidad", "Peso", "Precio", "Pureza", "Descuento" };
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        ControllerMineral controlador = new ControllerMineral();
        minerales = controlador.mostrarMinerales();

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

        tabla = new JTable(modelo);
        tabla.setRowSelectionAllowed(true);
        tabla.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBounds(10, 40, 715, 222);
        contentPane.add(scrollPane);

        alertaStock = new JButton("Pedir stock");
        alertaStock.setFont(new Font("Tahoma", Font.PLAIN, 16));
        alertaStock.setBounds(10, 272, 121, 46);
        contentPane.add(alertaStock);

        VolverBTN = new JButton("Volver");
        VolverBTN.setFont(new Font("Tahoma", Font.PLAIN, 16));
        VolverBTN.setBounds(604, 337, 121, 46);
        contentPane.add(VolverBTN);

        VolverBTN.addActionListener(e -> {
            HomeEncargado home = new HomeEncargado(pedido); 
            dispose();
        });

        btnFiltrar = new JButton("Filtrar");
        btnFiltrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String texto = textField.getText().trim();
                if (texto.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese una cantidad para filtrar.");
                    return;
                }

                int cantidadFiltro;
                try {
                    cantidadFiltro = Integer.parseInt(texto);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un número válido.");
                    return;
                }

                DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
                modelo.setRowCount(0); // limpia

                for (Mineral m : minerales) {
                    if (m.getUnidades() <= cantidadFiltro) {
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
                }
            }
        });
        btnFiltrar.setBounds(116, 352, 85, 21);
        contentPane.add(btnFiltrar);

        textField = new JTextField();
        textField.setBounds(10, 353, 96, 19);
        contentPane.add(textField);
        textField.setColumns(10);

        lblNewLabel = new JLabel("Filtro");
        lblNewLabel.setBounds(10, 337, 45, 13);
        contentPane.add(lblNewLabel);

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
