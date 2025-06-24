package GUI;

import DLL.ControllerPedido;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.Objects;

public class GestionarPedido extends JFrame {

    private JPanel contentPane;
    private JTable tabla;
    private JComboBox<String> filtroEstadoCombo;
    private JTextField filtroIdPedidoText;
    private JComboBox<String> estadoCombo;
    private JDateChooser dateChooser;
    private JButton btnGuardarFecha, btnGuardarEstado, btnFiltrar;

    private int idPedidoSeleccionado = -1;

    public GestionarPedido() {
        setTitle("Gestionar pedidos - Encargado");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1000, 639);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("GESTIONAR PEDIDOS");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
        lblTitulo.setBounds(300, 10, 400, 30);
        contentPane.add(lblTitulo);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 90, 960, 350);
        contentPane.add(scrollPane);

        tabla = new JTable();
        tabla.setModel(new DefaultTableModel());
        tabla.setFillsViewportHeight(true);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setDefaultEditor(Object.class, null);
        scrollPane.setViewportView(tabla);

        JLabel lblFiltroId = new JLabel("ID Pedido:");
        lblFiltroId.setBounds(10, 50, 70, 25);
        contentPane.add(lblFiltroId);

        filtroIdPedidoText = new JTextField();
        filtroIdPedidoText.setBounds(80, 50, 100, 25);
        contentPane.add(filtroIdPedidoText);

        JLabel lblFiltroEstado = new JLabel("Estado:");
        lblFiltroEstado.setBounds(200, 50, 60, 25);
        contentPane.add(lblFiltroEstado);

        filtroEstadoCombo = new JComboBox<>(new String[]{"Todos", "pendiente", "en proceso", "finalizado", "cancelado"});
        filtroEstadoCombo.setBounds(260, 50, 130, 25);
        contentPane.add(filtroEstadoCombo);

        btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setBounds(410, 50, 100, 25);
        contentPane.add(btnFiltrar);

        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setBounds(10, 460, 150, 25);
        contentPane.add(dateChooser);

        btnGuardarFecha = new JButton("Guardar fecha");
        btnGuardarFecha.setBounds(180, 460, 150, 30);
        contentPane.add(btnGuardarFecha);

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(10, 510, 80, 25);
        contentPane.add(lblEstado);

        estadoCombo = new JComboBox<>(new String[]{"pendiente", "en proceso", "finalizado", "cancelado"});
        estadoCombo.setBounds(80, 510, 150, 25);
        contentPane.add(estadoCombo);

        btnGuardarEstado = new JButton("Guardar estado");
        btnGuardarEstado.setBounds(250, 510, 150, 30);
        contentPane.add(btnGuardarEstado);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnSalir.setBounds(420, 560, 100, 30);
        contentPane.add(btnSalir);
        btnSalir.addActionListener(e -> dispose());

        btnFiltrar.addActionListener(e -> cargarPedidos());

        filtroEstadoCombo.addActionListener(e -> cargarPedidos());

        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int fila = tabla.getSelectedRow();
                if (fila != -1) {
                    idPedidoSeleccionado = Integer.parseInt(tabla.getValueAt(fila, 0).toString());
                    Object fechaObj = tabla.getValueAt(fila, 7);
                    if (fechaObj instanceof java.sql.Date) {
                        dateChooser.setDate((java.sql.Date) fechaObj);
                    } else if (fechaObj instanceof java.util.Date) {
                        dateChooser.setDate((java.util.Date) fechaObj);
                    } else {
                        dateChooser.setDate(null);
                    }
                    String estadoActual = tabla.getValueAt(fila, 6).toString();
                    estadoCombo.setSelectedItem(estadoActual);
                }
            }
        });

        btnGuardarFecha.addActionListener(e -> {
            if (idPedidoSeleccionado == -1) {
                JOptionPane.showMessageDialog(null, "Seleccione un pedido para modificar la fecha.");
                return;
            }
            java.util.Date nuevaFecha = dateChooser.getDate();
            if (nuevaFecha == null) {
                JOptionPane.showMessageDialog(null, "Seleccione una fecha válida.");
                return;
            }
            Date sqlDate = new Date(nuevaFecha.getTime());
            ControllerPedido cp = new ControllerPedido();
            if (cp.actualizarFechaEntrega(idPedidoSeleccionado, sqlDate)) {
                JOptionPane.showMessageDialog(null, "Fecha actualizada correctamente.");
                cargarPedidos();
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar la fecha.");
            }
        });

        btnGuardarEstado.addActionListener(e -> {
            if (idPedidoSeleccionado == -1) {
                JOptionPane.showMessageDialog(null, "Seleccione un pedido para modificar el estado.");
                return;
            }
            String nuevoEstado = Objects.requireNonNull(estadoCombo.getSelectedItem()).toString();
            ControllerPedido cp = new ControllerPedido();
            if (cp.actualizarEstadoPedido(idPedidoSeleccionado, nuevoEstado)) {
                JOptionPane.showMessageDialog(null, "Estado actualizado correctamente.");
                cargarPedidos();
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar el estado.");
            }
        });

        cargarPedidos();
    }

    private void cargarPedidos() {
        ControllerPedido cp = new ControllerPedido();
        String estado = Objects.requireNonNull(filtroEstadoCombo.getSelectedItem()).toString();
        String idPedidoText = filtroIdPedidoText.getText().trim();

        Integer idPedidoFiltro = null;
        if (!idPedidoText.isEmpty()) {
            try {
                idPedidoFiltro = Integer.parseInt(idPedidoText);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "El ID de pedido debe ser un número válido.");
                return;
            }
        }

        DefaultTableModel modelo = cp.obtenerPedidosConFiltros(idPedidoFiltro, estado.equals("Todos") ? null : estado);
        tabla.setModel(modelo);
        tabla.setDefaultEditor(Object.class, null);
        idPedidoSeleccionado = -1;
        dateChooser.setDate(null);
        estadoCombo.setSelectedIndex(0);
    }
}
