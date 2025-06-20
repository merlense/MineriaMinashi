package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedList;
import BLL.Mineral;
import BLL.Usuario;
import DLL.ControllerMineral;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CatalogoMinerales extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Mineral mineralSeleccionado = null;


    public CatalogoMinerales(Usuario  usuario) {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(CatalogoMinerales.class.getResource("/IMG/diamante-super-chico.png")));
        setTitle("Catálogo de Minerales");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 753, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        String[] columnas = {"Nombre", "Cantidad", "Peso", "Precio", "Pureza", "Descuento"};

        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
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
        tabla.setRowSelectionAllowed(true);
        tabla.getTableHeader().setReorderingAllowed(false); 
        contentPane.setLayout(null);
        
        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int fila = tabla.getSelectedRow();
                if (fila != -1) {
                    mineralSeleccionado = minerales.get(fila); 
                }
            }
        });          

        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBounds(10, 55, 717, 203);
        contentPane.add(scrollPane);
        
        JButton btnNewButton = new JButton("SALIR");
        btnNewButton.setBounds(573, 320, 154, 30);
        contentPane.add(btnNewButton);
        
        JButton btnAgregarAlCarrito = new JButton("AGREGAR AL CARRITO");
        btnAgregarAlCarrito.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        	}
        });
        
        btnAgregarAlCarrito.addActionListener(e -> {
            if (mineralSeleccionado != null) {
                JOptionPane.showMessageDialog(null, "Agregado al carrito: " + mineralSeleccionado.getTipo());               
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un mineral primero");
            }
        });

        
   
        
        
        btnAgregarAlCarrito.setBounds(10, 269, 201, 30);
        contentPane.add(btnAgregarAlCarrito);
        
        JLabel lblNewLabel = new JLabel("CATALOGO DE MINERALES");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
        lblNewLabel.setBounds(204, 9, 320, 35);
        contentPane.add(lblNewLabel);
        
        JButton btnNewButton_1 = new JButton("FINALIZAR PEDIDO");
        btnNewButton_1.setBounds(223, 269, 137, 30);
        contentPane.add(btnNewButton_1);
        
        JButton btnNewButton_3 = new JButton("FILTRO");
        btnNewButton_3.setBounds(626, 269, 89, 30);
        contentPane.add(btnNewButton_3);
        
        
        
//        if (usuario.getTipo().equals("Cliente")) {
//        	btnAgregarAlCarrito.setVisible(true);
//
//		} else {
//   		btnAgregarAlCarrito.setVisible(false);
//
//		}
////        JCheckBox chckbxNewCheckBox = new JCheckBox("Ocultar/Mostrar");
////        chckbxNewCheckBox.setBounds(23, 331, 138, 23);
////        contentPane.add(chckbxNewCheckBox);
////        chckbxNewCheckBox.addActionListener(e->{
////        	if (chckbxNewCheckBox.isSelected()) {
////        		btnAgregarAlCarrito.setVisible(true);
////			} else {
////        		btnAgregarAlCarrito.setVisible(false);
////
////			}
////        });
    }
}
