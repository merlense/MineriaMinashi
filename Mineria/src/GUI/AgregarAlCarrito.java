package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

import BLL.Usuario;
import DLL.ControllerMineral;
import DLL.ControllerPedido;

public class AgregarAlCarrito extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNombre, txtCantidad, txtPeso, txtPrecio, txID;
    private int idMineral;
    private Runnable onFinalizarCallback;
    private Usuario usuario;

    public AgregarAlCarrito(Usuario usuario) {
        this.usuario = usuario;
        initialize();
    }

    private void initialize() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 377, 572);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("SELECCIONE CANTIDAD");
        lblTitulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
        lblTitulo.setBounds(62, 33, 244, 40);
        contentPane.add(lblTitulo);

        JLabel lblId = new JLabel("ID");
        lblId.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblId.setBounds(30, 93, 69, 31);
        contentPane.add(lblId);

        txID = new JTextField();
        txID.setEditable(false);
        txID.setBounds(30, 122, 152, 31);
        contentPane.add(txID);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNombre.setBounds(30, 163, 69, 31);
        contentPane.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setEditable(false);
        txtNombre.setBounds(30, 192, 152, 31);
        contentPane.add(txtNombre);

        JLabel lblCantidad = new JLabel("Cantidad");
        lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblCantidad.setBounds(30, 233, 69, 31);
        contentPane.add(lblCantidad);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(30, 262, 152, 31);
        contentPane.add(txtCantidad);

        JLabel lblPeso = new JLabel("Peso");
        lblPeso.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPeso.setBounds(30, 303, 69, 31);
        contentPane.add(lblPeso);

        txtPeso = new JTextField();
        txtPeso.setEditable(false);
        txtPeso.setBounds(30, 332, 152, 31);
        contentPane.add(txtPeso);

        JLabel lblPrecio = new JLabel("Precio");
        lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPrecio.setBounds(30, 373, 69, 31);
        contentPane.add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setEditable(false);
        txtPrecio.setBounds(30, 402, 152, 31);
        contentPane.add(txtPrecio);

        JButton btnFinalizar = new JButton("FINALIZAR");
        btnFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnFinalizar.setBounds(116, 471, 136, 40);
        contentPane.add(btnFinalizar);

        btnFinalizar.addActionListener(e -> {
            try {
                int cantidadSeleccionada = Integer.parseInt(txtCantidad.getText());
                if (cantidadSeleccionada <= 0) {
                    JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor a cero.");
                    return;
                }

                ControllerMineral controladorMineral = new ControllerMineral();
                int stockActual = controladorMineral.getStock(idMineral);

                ControllerPedido controllerPedido = new ControllerPedido();
                int idPedido = controllerPedido.obtenerPedidoActivo(usuario.getId());
                if (idPedido == -1) {
                    idPedido = controllerPedido.crearPedido(usuario.getId());
                }

                if (cantidadSeleccionada <= stockActual && stockActual > 0) {
                    boolean exitoResta = controladorMineral.restarCantidad(idMineral, cantidadSeleccionada);
                    if (!exitoResta) {
                        JOptionPane.showMessageDialog(null, "Error al actualizar stock.");
                        return;
                    }
                    boolean agregado = controllerPedido.agregarMineralAPedido(idPedido, idMineral, cantidadSeleccionada);
                    if (agregado) {
                        JOptionPane.showMessageDialog(null, "Mineral agregado al pedido correctamente.");
                        dispose();
                        if (onFinalizarCallback != null) onFinalizarCallback.run();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al agregar mineral al pedido.");
                    }
                } else {
                    // Mostrar mensaje de descuento
                    JOptionPane.showMessageDialog(null, "10% de descuento por bajo stock.");

                    // Agregar mineral igual, sin manejar descuento en la BD
                    boolean agregado = controllerPedido.agregarMineralAPedido(idPedido, idMineral, cantidadSeleccionada);
                    if (agregado) {
                        dispose();
                        if (onFinalizarCallback != null) onFinalizarCallback.run();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al agregar mineral al pedido.");
                    }
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ingrese una cantidad válida.");
            }
        });
    }
    
    public void setDatosMineral(int idMineral, String nombre, int cantidad, double peso, double precio) {
        this.idMineral = idMineral;
        txID.setText(String.valueOf(idMineral));
        txtNombre.setText(nombre);
        txtCantidad.setText(String.valueOf(cantidad));
        txtPeso.setText(String.valueOf(peso));
        txtPrecio.setText(String.valueOf(precio));
    }

    public void setOnFinalizar(Runnable callback) {
        this.onFinalizarCallback = callback;
    }

}