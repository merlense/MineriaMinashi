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
    private JDateChooser dateChooser;
    private JButton btnGuardarFecha;

    private int idPedidoSeleccionado = -1;

    public GestionarPedido() {
        setTitle("Gestionar pedidos - Encargado");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 900, 550);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10,10,10,10));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("GESTIONAR PEDIDOS");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
        lblTitulo.setBounds(250, 10, 400, 30);
        contentPane.add(lblTitulo);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 60, 860, 350);
        contentPane.add(scrollPane);

        tabla = new JTable();
        tabla.setModel(new DefaultTableModel());
        tabla.setFillsViewportHeight(true);
        tabla.getTableHeader().setReorderingAllowed(false);
        scrollPane.setViewportView(tabla);

        filtroEstadoCombo = new JComboBox<>(new String[]{"Todos", "pendiente", "en proceso", "finalizado", "cancelado"});
        filtroEstadoCombo.setBounds(600, 20, 130, 25);
        contentPane.add(filtroEstadoCombo);

        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setBounds(10, 420, 150, 25);
        contentPane.add(dateChooser);

        btnGuardarFecha = new JButton("Guardar fecha");
        btnGuardarFecha.setBounds(180, 420, 130, 30);
        contentPane.add(btnGuardarFecha);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnSalir.setBounds(390, 470, 100, 30);
        contentPane.add(btnSalir);

        btnSalir.addActionListener(e -> dispose());

        filtroEstadoCombo.addActionListener(e -> cargarPedidos());

        tabla.getSelectionModel().addListSelectionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila != -1) {
                idPedidoSeleccionado = (int) tabla.getValueAt(fila, 0);
                Object fechaObj = tabla.getValueAt(fila, 7);
                if (fechaObj != null) {
                    if (fechaObj instanceof Date) {
                        dateChooser.setDate((Date) fechaObj);
                    } else if (fechaObj instanceof java.util.Date) {
                        dateChooser.setDate((java.util.Date) fechaObj);
                    } else {
                        dateChooser.setDate(null);
                    }
                } else {
                    dateChooser.setDate(null);
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
            boolean actualizado = cp.actualizarFechaEntrega(idPedidoSeleccionado, sqlDate);
            if (actualizado) {
                JOptionPane.showMessageDialog(null, "Fecha de entrega actualizada correctamente.");
                cargarPedidos(); 
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar la fecha de entrega.");
            }
        });

        cargarPedidos();
    }

    private void cargarPedidos() {
        ControllerPedido cp = new ControllerPedido();
        String estado = Objects.requireNonNull(filtroEstadoCombo.getSelectedItem()).toString();
        DefaultTableModel modelo = cp.obtenerPedidosConDetalles(
            estado.equals("Todos") ? null : estado
        );
        tabla.setModel(modelo);
        idPedidoSeleccionado = -1;
        dateChooser.setDate(null);
    }
}
