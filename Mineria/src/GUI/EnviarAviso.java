package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionEvent;

import BLL.Usuario;
import DLL.ControllerMineral;
import DLL.ControllerPedido;

public class EnviarAviso extends JFrame {

    private JPanel contentPane;
    private JTextField txtID, txtNombre, txtCantidad, txtPeso, txtPrecio;
    private int idMineral;
    private Usuario usuario;

    public EnviarAviso(Usuario usuario, int idMineral, String nombre, double peso, double precio) {
        this.usuario = usuario;
        this.idMineral = idMineral;
        setTitle("Pedido Stock");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 370, 405);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("PEDIDO DE STOCK");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setBounds(90, 20, 200, 30);
        contentPane.add(lblTitulo);

        JLabel lblID = new JLabel("ID Mineral:");
        lblID.setBounds(30, 70, 100, 20);
        contentPane.add(lblID);

        txtID = new JTextField(String.valueOf(idMineral));
        txtID.setEditable(false);
        txtID.setBounds(140, 70, 180, 25);
        contentPane.add(txtID);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 110, 100, 20);
        contentPane.add(lblNombre);

        txtNombre = new JTextField(nombre);
        txtNombre.setEditable(false);
        txtNombre.setBounds(140, 110, 180, 25);
        contentPane.add(txtNombre);

        JLabel lblPeso = new JLabel("Peso:");
        lblPeso.setBounds(30, 150, 100, 20);
        contentPane.add(lblPeso);

        txtPeso = new JTextField(String.valueOf(peso));
        txtPeso.setEditable(false);
        txtPeso.setBounds(140, 150, 180, 25);
        contentPane.add(txtPeso);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(30, 190, 100, 20);
        contentPane.add(lblPrecio);

        txtPrecio = new JTextField(String.valueOf(precio));
        txtPrecio.setEditable(false);
        txtPrecio.setBounds(140, 190, 180, 25);
        contentPane.add(txtPrecio);

        JLabel lblCantidad = new JLabel("Cantidad a pedir:");
        lblCantidad.setBounds(30, 230, 120, 20);
        contentPane.add(lblCantidad);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(160, 230, 160, 25);
        contentPane.add(txtCantidad);

        JButton btnPedir = new JButton("Confirmar Pedido");
        btnPedir.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnPedir.setBounds(90, 300, 180, 40);
        contentPane.add(btnPedir);

        btnPedir.addActionListener((ActionEvent e) -> {
            try {
                int cantidad = Integer.parseInt(txtCantidad.getText());

                if (cantidad <= 0) {
                    JOptionPane.showMessageDialog(this, "Ingrese una cantidad válida mayor a cero.");
                    return;
                }

                ControllerPedido controllerPedido = new ControllerPedido();
                int idPedido = controllerPedido.obtenerPedidoActivo(usuario.getId());
                if (idPedido == -1) {
                    idPedido = controllerPedido.crearPedido(usuario.getId());
                }

                boolean agregado = controllerPedido.agregarMineralAPedido(idPedido, idMineral, cantidad);

                if (agregado) {
                    JOptionPane.showMessageDialog(this, "Pedido enviado correctamente.");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al registrar el pedido.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Cantidad inválida.");
            }
        });
    }
}
