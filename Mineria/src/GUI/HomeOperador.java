package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import BLL.Usuario;

public class HomeOperador extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Usuario operador;

    public HomeOperador(Usuario operador) {
        this.operador = operador;

        setIconImage(Toolkit.getDefaultToolkit().getImage(HomeOperador.class.getResource("/IMG/steve.png")));
        setTitle("MENÚ OPERADOR");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 633, 308);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); 
            }
        });
        btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnSalir.setBounds(259, 198, 95, 52);
        contentPane.add(btnSalir);

        JLabel lblTitulo = new JLabel("MINERÍA MINASHI");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
        lblTitulo.setBounds(204, 38, 192, 30);
        contentPane.add(lblTitulo);

        JButton btnVerPedidos = new JButton("Ver pedidos");
        btnVerPedidos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VerPedidoOperador(operador).setVisible(true);
                dispose();
            }
        });
        btnVerPedidos.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnVerPedidos.setBounds(315, 121, 207, 52);
        contentPane.add(btnVerPedidos);

        JButton btnExplotarMineral = new JButton("Explotar mineral");
        btnExplotarMineral.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ReponerStock(operador).setVisible(true);
                dispose();
            }
        });
        btnExplotarMineral.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnExplotarMineral.setBounds(98, 121, 207, 52);
        contentPane.add(btnExplotarMineral);

        JLabel lblIcon1 = new JLabel("");
        lblIcon1.setIcon(new ImageIcon(HomeOperador.class.getResource("/IMG/diamante-super-chico.png")));
        lblIcon1.setBounds(411, 20, 53, 64);
        contentPane.add(lblIcon1);

        JLabel lblIcon2 = new JLabel("");
        lblIcon2.setIcon(new ImageIcon(HomeOperador.class.getResource("/IMG/diamante-super-chico.png")));
        lblIcon2.setBounds(141, 20, 53, 64);
        contentPane.add(lblIcon2);
    }
}
