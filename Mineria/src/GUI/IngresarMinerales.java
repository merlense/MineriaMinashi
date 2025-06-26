package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import DLL.ControllerMineral;
import BLL.Mineral;

public class IngresarMinerales extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtTipo, txtUnidades, txtPeso, txtPureza, txtPrecio;

    public IngresarMinerales() {
        setTitle("Ingresar Mineral");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 420);  // Altura reducida
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

        JButton btnGuardar = new JButton("GUARDAR");
        btnGuardar.setBounds(130, y + 20, 120, 35);
        contentPane.add(btnGuardar);

        btnGuardar.addActionListener(e -> {
            try {
                String tipo = txtTipo.getText();
                int unidades = Integer.parseInt(txtUnidades.getText());
                double peso = Double.parseDouble(txtPeso.getText());
                double pureza = Double.parseDouble(txtPureza.getText());
                double precio = Double.parseDouble(txtPrecio.getText());

                // Creamos el mineral sin campo descuento (usamos 0 o cambiá tu constructor)
                Mineral mineral = new Mineral(0, tipo, unidades, peso, pureza, precio, 0);
                ControllerMineral controller = new ControllerMineral();

                boolean exito = controller.insertarNuevoMineral(mineral);
                if (exito) {
                    JOptionPane.showMessageDialog(null, "Mineral guardado correctamente.");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar el mineral.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error en los datos ingresados. Verifique los campos.");
            }
        });
    }
}
