package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DLL.ControllerPedido;

import java.awt.*;
import java.util.Objects;

public class VerPedido extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tabla;
    private JComboBox<String> filtroEstadoCombo;

    public VerPedido() {
        setTitle("Ver Pedidos - Encargado");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 900, 500);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("LISTADO DE PEDIDOS");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
        lblTitulo.setBounds(250, 10, 400, 30);
        contentPane.add(lblTitulo);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 60, 860, 330);
        contentPane.add(scrollPane);

        tabla = new JTable();
        tabla.setModel(new DefaultTableModel());
        tabla.setFillsViewportHeight(true);
        tabla.getTableHeader().setReorderingAllowed(false);
        scrollPane.setViewportView(tabla);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnSalir.setBounds(390, 410, 100, 30);
        contentPane.add(btnSalir);

        btnSalir.addActionListener(e -> {
            dispose();
        });

        filtroEstadoCombo = new JComboBox<>();
        filtroEstadoCombo.setModel(new DefaultComboBoxModel<>(new String[] {
            "Todos", "pendiente", "en proceso", "finalizado", "cancelado"
        }));
        filtroEstadoCombo.setBounds(700, 20, 150, 25);
        contentPane.add(filtroEstadoCombo);

        filtroEstadoCombo.addActionListener(e -> {
            String estado = Objects.requireNonNull(filtroEstadoCombo.getSelectedItem()).toString();
            if (estado.equals("Todos")) {
                cargarPedidos(null);
            } else {
                cargarPedidos(estado);
            }
        });

        cargarPedidos(null); 
    }

    private void cargarPedidos(String estado) {
        ControllerPedido cp = new ControllerPedido();
        DefaultTableModel modelo = cp.obtenerPedidosConDetalles(estado);
        tabla.setModel(modelo);
    }
}
