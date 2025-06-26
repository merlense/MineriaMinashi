package GUI;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.Mineral;
import BLL.Usuario;
import DLL.ControllerMineral;

import java.util.LinkedList;

public class ReponerStock extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tablaMinerales;
    private Mineral mineralSeleccionado = null;
    private Usuario usuario;

    public ReponerStock(Usuario usuario) {
        if (usuario == null) {
            System.out.println("Advertencia: Usuario es null, creando usuario vacío para evitar errores.");
            this.usuario = new Usuario();
        } else {
            this.usuario = usuario;
        }

        setIconImage(Toolkit.getDefaultToolkit().getImage(ReponerStock.class.getResource("/IMG/diamante-super-chico.png")));
        setTitle("Stock Minerales");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 332);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Stock Minerales");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
        lblTitulo.setBounds(281, 10, 160, 30);
        contentPane.add(lblTitulo);

        String[] columnas = {"ID", "Nombre", "Cantidad", "Peso", "Precio", "Pureza"};
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
                m.getIdMineria(),
                m.getTipo(),
                m.getUnidades(),
                m.getPeso(),
                m.getPrecio(),
                m.getPureza() + "%"
            };
            modelo.addRow(fila);
        }

        tablaMinerales = new JTable(modelo);
        tablaMinerales.setRowSelectionAllowed(true);
        tablaMinerales.getTableHeader().setReorderingAllowed(false);

        tablaMinerales.getSelectionModel().addListSelectionListener(e -> {
            int fila = tablaMinerales.getSelectedRow();
            if (fila != -1) {
                int idSeleccionado = (int) tablaMinerales.getValueAt(fila, 0);
                for (Mineral m : minerales) {
                    if (m.getIdMineria() == idSeleccionado) {
                        mineralSeleccionado = m;
                        break;
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(tablaMinerales);
        scrollPane.setBounds(10, 50, 760, 179);
        contentPane.add(scrollPane);

        JTextField txtCantidad = new JTextField();
        txtCantidad.setBounds(330, 255, 80, 30);
        contentPane.add(txtCantidad);

        JButton btnReponerStock = new JButton("Reponer Stock");
        btnReponerStock.setBounds(420, 255, 134, 30);
        contentPane.add(btnReponerStock);

        btnReponerStock.addActionListener(e -> {
            if (mineralSeleccionado == null) {
                JOptionPane.showMessageDialog(null, "Por favor seleccione un mineral de la tabla.");
                return;
            }
            String cantidadStr = txtCantidad.getText().trim();
            if (cantidadStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese una cantidad para reponer.");
                return;
            }
            int cantidad = 0;
            try {
                cantidad = Integer.parseInt(cantidadStr);
                if (cantidad <= 0) {
                    JOptionPane.showMessageDialog(null, "La cantidad debe ser un número positivo.");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ingrese un número válido en cantidad.");
                return;
            }

            // Sumar cantidad y actualizar en BD
            int nuevaCantidad = mineralSeleccionado.getUnidades() + cantidad;

            boolean exito = controlador.actualizarCantidad(mineralSeleccionado.getIdMineria(), nuevaCantidad);

            if (exito) {
                JOptionPane.showMessageDialog(null, "Stock actualizado correctamente.");
                mineralSeleccionado.setUnidades(nuevaCantidad);

                int filaSeleccionada = tablaMinerales.getSelectedRow();
                if (filaSeleccionada != -1) {
                    tablaMinerales.setValueAt(nuevaCantidad, filaSeleccionada, 2);
                }

                txtCantidad.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar el stock.");
            }
        });

        JButton btnIngresarMineral = new JButton("Ingresar minerales");
        btnIngresarMineral.setBounds(10, 255, 160, 30);
        contentPane.add(btnIngresarMineral);

        btnIngresarMineral.addActionListener(e -> {
            IngresarMinerales ventana = new IngresarMinerales(usuario);
            ventana.setVisible(true);
            dispose();
        });

        JButton btnVolverAtras = new JButton("Volver atrás");
        btnVolverAtras.setBounds(570, 255, 200, 30);
        contentPane.add(btnVolverAtras);

        btnVolverAtras.addActionListener(e -> {
            HomeOperador home = new HomeOperador(usuario);
            home.setVisible(true);
            dispose();
        });
    }
}
