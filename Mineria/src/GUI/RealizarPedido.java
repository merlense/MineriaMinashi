package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedList;
import BLL.Mineral;
import DLL.ControllerMineral;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RealizarPedido extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public RealizarPedido() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(CatalogoCliente.class.getResource("/IMG/diamante-super-chico.png")));
        setTitle("Catálogo de Minerales");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        String[] columnas = {"Nombre", "Cantidad", "Peso", "Precio", "Pureza", "Descuento"};

        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        ControllerMineral controlador = new ControllerMineral();
        LinkedList<Mineral> minerales = controlador.mostrarMinerales();

        for (Mineral m : minerales) {
            Object[] fila = {
                m.getTipo(),
                m.getUnidades(),
                m.getPeso(),
                m.getPrecio(),
                m.getPureza() + "%",
                m.getDescuento() + "%"
            };
            modelo.addRow(fila);
        }

        JTable tabla = new JTable(modelo);
        tabla.setEnabled(false); 
        tabla.getTableHeader().setReorderingAllowed(false); 
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBounds(10, 56, 566, 210);
        contentPane.add(scrollPane);
        
        JButton btnNewButton = new JButton("SALIR");
        btnNewButton.setBounds(398, 276, 154, 30);
        contentPane.add(btnNewButton);
        
        JButton btnAgregarAlCarrito = new JButton("AGREGAR AL CARRITO");
        btnAgregarAlCarrito.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        	}
        });
        btnAgregarAlCarrito.setBounds(36, 276, 201, 30);
        contentPane.add(btnAgregarAlCarrito);
        
        JLabel lblNewLabel = new JLabel("CATALOGO");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
        lblNewLabel.setBounds(215, 10, 145, 35);
        contentPane.add(lblNewLabel);
    }
}
