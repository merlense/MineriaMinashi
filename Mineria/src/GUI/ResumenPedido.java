package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.Usuario;
import DLL.ControllerPedido;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResumenPedido extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private Usuario usuario;
    private JComboBox<String> comboPedidos;
    private JLabel lblTotal, lblFechaPedido, lblFechaEntrega;

    // Mapa para guardar idPedido de cada opción del combo
    private Map<String, Integer> pedidoMap = new HashMap<>();

    public ResumenPedido(Usuario usuario) {
        this.usuario = usuario;
        setTitle("Resumen de Pedido");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 900, 500);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("RESUMEN DE PEDIDOS");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(200, 10, 450, 30);
        contentPane.add(lblTitulo);

        comboPedidos = new JComboBox<>();
        comboPedidos.setBounds(10, 50, 350, 25);
        contentPane.add(comboPedidos);

        comboPedidos.addActionListener(e -> {
            String seleccion = (String) comboPedidos.getSelectedItem();
            if (seleccion != null && pedidoMap.containsKey(seleccion)) {
                cargarPedidoPorId(pedidoMap.get(seleccion));
            }
        });

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 90, 864, 270);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        lblTotal = new JLabel("Total: $0");
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTotal.setBounds(10, 370, 300, 30);
        contentPane.add(lblTotal);

        lblFechaPedido = new JLabel("Fecha de pedido: -");
        lblFechaPedido.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblFechaPedido.setBounds(10, 410, 300, 25);
        contentPane.add(lblFechaPedido);

        lblFechaEntrega = new JLabel("Fecha de entrega: -");
        lblFechaEntrega.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblFechaEntrega.setBounds(10, 440, 300, 25);
        contentPane.add(lblFechaEntrega);

        JButton btnCerrar = new JButton("CERRAR");
        btnCerrar.setBounds(760, 420, 100, 30);
        contentPane.add(btnCerrar);
        btnCerrar.addActionListener(e -> dispose());

        cargarListaPedidos();
    }

    private void cargarListaPedidos() {
        ControllerPedido controller = new ControllerPedido();
        ArrayList<int[]> lista = controller.obtenerListaPedidosDeUsuario(usuario.getId());

        comboPedidos.removeAllItems();
        pedidoMap.clear();

        if (lista.isEmpty()) {
            int nuevo = controller.crearPedido(usuario.getId());
            if (nuevo != -1) {
                String itemNuevo = "Pedido " + nuevo + " (nuevo)";
                comboPedidos.addItem(itemNuevo);
                pedidoMap.put(itemNuevo, nuevo);
                comboPedidos.setSelectedItem(itemNuevo);
                cargarPedidoPorId(nuevo);
            }
        } else {
            for (int[] datos : lista) {
                int id = datos[0];
                String estado = datos[1] == 1 ? "pendiente" :
                                datos[1] == 2 ? "en proceso" :
                                datos[1] == 3 ? "finalizado" : "cancelado";
                String item = "Pedido " + id + " (" + estado + ")";
                comboPedidos.addItem(item);
                pedidoMap.put(item, id);
            }
            comboPedidos.setSelectedIndex(0);
            String seleccion = (String) comboPedidos.getSelectedItem();
            if (seleccion != null && pedidoMap.containsKey(seleccion)) {
                cargarPedidoPorId(pedidoMap.get(seleccion));
            }
        }
    }

    private void cargarPedidoPorId(int idPedido) {
        ControllerPedido controller = new ControllerPedido();

        // Obtener resumen con estado y fecha entrega personalizada
        DefaultTableModel modelo = controller.obtenerResumenPedidoConEstadoYFechaEspecial(idPedido);
        table.setModel(modelo);

        // Calcular total (asumiendo subtotal en columna 8)
        double total = 0.0;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            Object valor = modelo.getValueAt(i, 8);
            if (valor instanceof Number) {
                total += ((Number) valor).doubleValue();
            }
        }
        lblTotal.setText("Total: $" + total);

        // Obtener fechas
        String[] fechas = controller.obtenerFechasPedido(idPedido);
        String fechaPedidoStr = (fechas[0] != null && !fechas[0].isEmpty()) ? fechas[0] : "Sin fecha";

        // Obtener estado para decidir qué mostrar en fechaEntrega
        String estado = controller.obtenerEstadoPedido(idPedido);

        String fechaEntregaStr;
        if ((estado.equalsIgnoreCase("pendiente") || estado.equalsIgnoreCase("en proceso"))
            && (fechas[1] == null || fechas[1].isEmpty())) {
            fechaEntregaStr = "A despachar";
        } else {
            fechaEntregaStr = (fechas[1] != null && !fechas[1].isEmpty()) ? fechas[1] : "-";
        }

        lblFechaPedido.setText("Fecha de pedido: " + fechaPedidoStr);
        lblFechaEntrega.setText("Fecha de entrega: " + fechaEntregaStr);
    }
}
