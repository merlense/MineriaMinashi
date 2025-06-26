package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import DLL.ControllerMineral;
import BLL.Mineral;
import BLL.Usuario;

public class IngresarMinerales extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtTipo, txtUnidades, txtPeso, txtPureza, txtPrecio;
    private Usuario usuario;

    public IngresarMinerales(Usuario usuario) {
        this.usuario = usuario;

        setTitle("Ingresar Mineral");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 480); 
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("DATOS DEL MINERAL");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setBounds(90, 10, 250, 30);
        contentPane.add(lblTitulo);

        JLabel[] labels = {
            new JLabel("Tipo:"),
            new JLabel("Unidades:"),
            new JLabel("Peso:"),
            new JLabel("Pureza (%):"),
            new JLabel("Precio:")
        };

        JTextField[] fields = {
            txtTipo = new JTextField(),
            txtUnidades = new JTextField(),
            txtPeso = new JTextField(),
            txtPureza = new JTextField(),
            txtPrecio = new JTextField()
        };

        int y = 60;
        for (int i = 0; i < labels.length; i++) {
            labels[i].setBounds(30, y, 150, 25);
            contentPane.add(labels[i]);

            fields[i].setBounds(180, y, 150, 25);
            contentPane.add(fields[i]);
            y += 40;
        }

        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(40, y + 20, 120, 35);
        contentPane.add(btnIngresar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(200, y + 20, 120, 35);
        contentPane.add(btnVolver);

        btnIngresar.addActionListener(e -> {
            try {
                String tipo = txtTipo.getText();
                int unidades = Integer.parseInt(txtUnidades.getText());
                double peso = Double.parseDouble(txtPeso.getText());
                double pureza = Double.parseDouble(txtPureza.getText());
                double precio = Double.parseDouble(txtPrecio.getText());

                Mineral mineral = new Mineral(0, tipo, unidades, peso, pureza, precio, 0);
                ControllerMineral controller = new ControllerMineral();

                boolean exito = controller.insertarNuevoMineral(mineral);
                if (exito) {
                    JOptionPane.showMessageDialog(null, "Mineral guardado correctamente.");
                    new ReponerStock(usuario).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar el mineral.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error en los datos ingresados. Verifique los campos.");
            }
        });

        btnVolver.addActionListener(e -> {
            new ReponerStock(usuario).setVisible(true);
            dispose();
        });
    }
}
