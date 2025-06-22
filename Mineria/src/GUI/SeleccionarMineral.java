package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

public class SeleccionarMineral extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    // Atributos opcionales para que puedas acceder desde fuera
    private JTextField txtNombre;
    private JTextField txtCantidad;
    private JTextField txtPeso;
    private JTextField txtPrecio;
    private int idMineral;
    private Runnable onFinalizarCallback;
    


    
    
    public SeleccionarMineral() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 377, 504);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        
        
        JLabel lblTitulo = new JLabel("SELECCIONE CANTIDAD");
        lblTitulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
        lblTitulo.setBounds(62, 33, 244, 40);
        contentPane.add(lblTitulo);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNombre.setBounds(24, 102, 69, 31);
        contentPane.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(24, 131, 152, 31);
        txtNombre.setEditable(false); // Solo lectura
        contentPane.add(txtNombre);

        JLabel lblCantidad = new JLabel("Cantidad");
        lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblCantidad.setBounds(24, 172, 69, 31);
        contentPane.add(lblCantidad);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(24, 201, 152, 31);
        txtCantidad.setEditable(true); // Único campo editable
        contentPane.add(txtCantidad);

        JLabel lblPeso = new JLabel("Peso");
        lblPeso.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPeso.setBounds(24, 242, 69, 31);
        contentPane.add(lblPeso);

        txtPeso = new JTextField();
        txtPeso.setBounds(24, 271, 152, 31);
        txtPeso.setEditable(false); // Solo lectura
        contentPane.add(txtPeso);

        JLabel lblPrecio = new JLabel("Precio");
        lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPrecio.setBounds(24, 312, 69, 31);
        contentPane.add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(24, 341, 152, 31);
        txtPrecio.setEditable(false); // Solo lectura
        contentPane.add(txtPrecio);

        JButton btnFinalizar = new JButton("FINALIZAR");
        
        btnFinalizar.addActionListener(e -> {
            try {
                int cantidadSeleccionada = Integer.parseInt(txtCantidad.getText());

                if (cantidadSeleccionada <= 0) {
                    JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor a cero.");
                    return;
                }

                DLL.ControllerMineral controlador = new DLL.ControllerMineral();
                boolean exito = controlador.restarCantidad(idMineral, cantidadSeleccionada);

                if (exito) {
                    JOptionPane.showMessageDialog(null, "Pedido finalizado. Stock actualizado.");
                    dispose(); 
                    if (onFinalizarCallback != null) {
                        onFinalizarCallback.run(); 
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No hay suficiente stock disponible.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ingrese una cantidad válida.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al finalizar pedido: " + ex.getMessage());
            }
        });


        
        btnFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnFinalizar.setBounds(109, 398, 136, 40);
        contentPane.add(btnFinalizar);

        
        JLabel lblIcono = new JLabel("");
        lblIcono.setIcon(new ImageIcon(SeleccionarMineral.class.getResource("/IMG/diamante-chico.png")));
        lblIcono.setBounds(248, 212, 69, 86);
        contentPane.add(lblIcono);
    }

    public void setDatosMineral(int idMineral, String nombre, int cantidad, double peso, double precio) {
        this.idMineral = idMineral;
        txtNombre.setText(nombre);
        txtCantidad.setText(String.valueOf(cantidad));
        txtPeso.setText(String.valueOf(peso));
        txtPrecio.setText(String.valueOf(precio));
    }

    public int getCantidad() {
        try {
            return Integer.parseInt(txtCantidad.getText());
        } catch (NumberFormatException e) {
            return -1; // o podrías lanzar una excepción personalizada
        }
    }
    
    public void setOnFinalizar(Runnable callback) {
        this.onFinalizarCallback = callback;
    }

}
