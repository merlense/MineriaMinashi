package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.Usuario;
import DLL.ControllerPedido;

import java.awt.*;

public class ResumenPedido extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;

    public ResumenPedido(Usuario usuario) {
        setTitle("Resumen de Pedido");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 850, 500); 
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("RESUMEN DEL PEDIDO");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(200, 10, 450, 30);
        contentPane.add(lblTitulo);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 50, 814, 250);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        ControllerPedido controller = new ControllerPedido();
        int idPedido = controller.obtenerUltimoPedido(usuario.getId()); 
        
        if (idPedido != -1) {
            DefaultTableModel modelo = controller.obtenerResumenPedido(idPedido);
            table.setModel(modelo);

            double total = 0.0;
            for (int i = 0; i < modelo.getRowCount(); i++) {
                Object valor = modelo.getValueAt(i, 7);
                if (valor instanceof Number) {
                    total += ((Number) valor).doubleValue();
                }
            }

            JLabel lblTotal = new JLabel("Total: $" + total);
            lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 16));
            lblTotal.setBounds(10, 320, 300, 30);
            contentPane.add(lblTotal);

            String[] fechas = controller.obtenerFechasPedido(idPedido);
            JLabel lblFechaPedido = new JLabel("Fecha de pedido: " + fechas[0]);
            lblFechaPedido.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            lblFechaPedido.setBounds(10, 360, 300, 25);
            contentPane.add(lblFechaPedido);

            JLabel lblFechaEntrega = new JLabel("Fecha de entrega: " + fechas[1]);
            lblFechaEntrega.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            lblFechaEntrega.setBounds(10, 390, 300, 25);
            contentPane.add(lblFechaEntrega);

        } else {
            JOptionPane.showMessageDialog(this, "No hay un pedido activo para mostrar.");
            dispose();
        }

        JButton btnCerrar = new JButton("CERRAR");
        btnCerrar.setBounds(670, 410, 100, 30);
        contentPane.add(btnCerrar);

        btnCerrar.addActionListener(e -> dispose());
    }
}
