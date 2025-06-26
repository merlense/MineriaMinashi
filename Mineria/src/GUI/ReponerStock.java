package GUI;

import java.awt.Font;
import java.awt.Toolkit;
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

    public ReponerStock(Usuario usuario) {
        setIconImage(Toolkit.getDefaultToolkit().getImage(ReponerStock.class.getResource("/IMG/diamante-super-chico.png")));
        setTitle("Reposición de Stock");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 343);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Minerales disponibles para reposición");
        lblTitulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
        lblTitulo.setBounds(200, 10, 400, 30);
        contentPane.add(lblTitulo);

        // Se eliminó "Descuento" de las columnas
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

        JButton btnReponerStock = new JButton("Reponer Stock");
        btnReponerStock.setBounds(154, 255, 134, 30);
        contentPane.add(btnReponerStock);

        btnReponerStock.addActionListener(e -> {
            // lógica futura
        });

        JButton btnIngresarMineral = new JButton("Ingresar minerales");
        btnIngresarMineral.setBounds(10, 255, 134, 30);
        contentPane.add(btnIngresarMineral);

        btnIngresarMineral.addActionListener(e -> {
            IngresarMinerales ventana = new IngresarMinerales();
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
