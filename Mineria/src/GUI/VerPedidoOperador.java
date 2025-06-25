package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import DLL.ControllerPedido;
import BLL.Usuario;
import java.awt.event.ActionEvent;
import java.sql.Date;

public class VerPedidoOperador extends JFrame {

    private JPanel contentPane;
    private JTable tablePedidos;
    private JButton btnExplotarMineral;
    private JDateChooser dateChooser;
    private ControllerPedido controllerPedido;
    private JLabel lblNewLabel;
    private JButton btnVolver;
    private Usuario operador;

    public VerPedidoOperador(Usuario operador) {
        setIconImage(Toolkit.getDefaultToolkit().getImage(VerPedidoOperador.class.getResource("/IMG/steve.png")));
        this.operador = operador;
        controllerPedido = new ControllerPedido();

        setTitle("PEDIDOS PENDIENTES");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 820, 551);
        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Tabla
        tablePedidos = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablePedidos);
        scrollPane.setBounds(10, 10, 780, 350);
        contentPane.add(scrollPane);

        // Label fecha explotación
        lblNewLabel = new JLabel("Seleccione fecha de explotación:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel.setBounds(10, 375, 250, 37);
        contentPane.add(lblNewLabel);

        // Calendario
        dateChooser = new JDateChooser();
        dateChooser.setBounds(10, 410, 160, 37);
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setPreferredSize(new Dimension(150, 25));
        contentPane.add(dateChooser);

        // Botón Confirmar Explotación
        btnExplotarMineral = new JButton("Confirmar Explotación");
        btnExplotarMineral.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnExplotarMineral.setBounds(180, 410, 220, 37);
        contentPane.add(btnExplotarMineral);

        // Etiqueta "Estado:"
        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblEstado.setBounds(10, 467, 80, 25);
        contentPane.add(lblEstado);

        // ComboBox con los estados posibles
        JComboBox<String> estadoCombo = new JComboBox<>(new String[]{"pendiente", "en proceso", "finalizado", "cancelado"});
        estadoCombo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        estadoCombo.setBounds(80, 467, 150, 25);
        contentPane.add(estadoCombo);

        // Botón para guardar el estado seleccionado
        JButton btnGuardarEstado = new JButton("Guardar estado");
        btnGuardarEstado.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnGuardarEstado.setBounds(250, 467, 150, 30);
        contentPane.add(btnGuardarEstado);

        // Botón Volver
        btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnVolver.setBounds(680, 460, 110, 37);
        contentPane.add(btnVolver);

        // Cargar datos
        cargarPedidos();

        // Acción botón Volver
        btnVolver.addActionListener(e -> {
            new HomeOperador(operador).setVisible(true);
            dispose();
        });

        // Acción botón Confirmar Explotación
        btnExplotarMineral.addActionListener((ActionEvent e) -> {
            int fila = tablePedidos.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione un pedido primero.", "Atención", JOptionPane.WARNING_MESSAGE);
                return;
            }

            java.util.Date fechaSeleccionada = dateChooser.getDate();
            if (fechaSeleccionada == null) {
                JOptionPane.showMessageDialog(this, "Seleccione una fecha estimada de entrega.", "Atención", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int idPedido = (int) tablePedidos.getValueAt(fila, 0);
            Date fechaEntrega = new Date(fechaSeleccionada.getTime());

            boolean fechaOK = controllerPedido.actualizarFechaEntrega(idPedido, fechaEntrega);
            boolean estadoOK = controllerPedido.actualizarEstadoPedido(idPedido, "en proceso");

            if (fechaOK && estadoOK) {
                JOptionPane.showMessageDialog(this, "Fecha y estado actualizados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarPedidos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar el pedido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Acción botón Guardar Estado con combo
        btnGuardarEstado.addActionListener((ActionEvent e) -> {
            int fila = tablePedidos.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione un pedido primero.", "Atención", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int idPedido = (int) tablePedidos.getValueAt(fila, 0);
            String nuevoEstado = estadoCombo.getSelectedItem().toString();

            boolean ok = controllerPedido.actualizarEstadoPedido(idPedido, nuevoEstado);
            if (ok) {
                JOptionPane.showMessageDialog(this, "Estado actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarPedidos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar estado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void cargarPedidos() {
        DefaultTableModel modelo = controllerPedido.obtenerPedidosPorTipoUsuario("Encargado", null);

        DefaultTableModel modeloFiltrado = new DefaultTableModel(
                new Object[]{"ID Pedido", "Operador", "Tipo", "Pureza", "Cantidad", "Estado", "Entrega"}, 0);

        for (int i = 0; i < modelo.getRowCount(); i++) {
            String estado = modelo.getValueAt(i, 5).toString().toLowerCase();
            if (estado.equals("pendiente") || estado.equals("en proceso")) {
                Object[] fila = new Object[7];
                for (int c = 0; c < 7; c++) {
                    fila[c] = modelo.getValueAt(i, c);
                }
                modeloFiltrado.addRow(fila);
            }
        }

        tablePedidos.setModel(modeloFiltrado);

        tablePedidos.getColumnModel().getColumn(0).setPreferredWidth(60);
        tablePedidos.getColumnModel().getColumn(1).setPreferredWidth(120);
        tablePedidos.getColumnModel().getColumn(2).setPreferredWidth(100);
        tablePedidos.getColumnModel().getColumn(3).setPreferredWidth(80);
        tablePedidos.getColumnModel().getColumn(4).setPreferredWidth(80);
        tablePedidos.getColumnModel().getColumn(5).setPreferredWidth(100);
        tablePedidos.getColumnModel().getColumn(6).setPreferredWidth(120);
    }
}
