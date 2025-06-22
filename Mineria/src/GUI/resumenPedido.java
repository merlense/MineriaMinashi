package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DLL.ControllerPedido;
import BLL.Usuario;

import java.awt.*;

public class resumenPedido extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;

    public resumenPedido(Usuario usuario) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 750, 500);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("RESUMEN DE PEDIDO");
        lblTitulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
        lblTitulo.setBounds(250, 10, 300, 40);
        contentPane.add(lblTitulo);

        ControllerPedido controllerPedido = new ControllerPedido();
        int idPedidoActivo = controllerPedido.obtenerPedidoActivo(usuario.getId());
        DefaultTableModel modelo = controllerPedido.obtenerResumenPedido(idPedidoActivo);

        JTable table = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 70, 680, 260);
        contentPane.add(scrollPane);

        double total = 0;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            Object valor = modelo.getValueAt(i, 6); 
            if (valor instanceof Number) {
                total += ((Number) valor).doubleValue();
            }
        }
        JLabel lblTotal = new JLabel("TOTAL GENERAL: $" + total);
        lblTotal.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTotal.setBounds(30, 340, 400, 30);
        contentPane.add(lblTotal);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(600, 400, 110, 34);
        contentPane.add(btnVolver);
        btnVolver.addActionListener(e -> dispose());
    }
}

