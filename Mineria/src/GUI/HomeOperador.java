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
        setTitle("MENU OPERADOR");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 633, 308);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Botón Salir
        JButton SalirCLBT = new JButton("Salir");
        SalirCLBT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // O volver al login si querés
            }
        });
        SalirCLBT.setFont(new Font("Tahoma", Font.PLAIN, 16));
        SalirCLBT.setBounds(259, 198, 95, 52);
        contentPane.add(SalirCLBT);

        // Título
        JLabel HomeTituloCL = new JLabel("MINERIA MINASHI");
        HomeTituloCL.setHorizontalAlignment(SwingConstants.CENTER);
        HomeTituloCL.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
        HomeTituloCL.setBounds(204, 38, 192, 30);
        contentPane.add(HomeTituloCL);

        // Botón Ver Pedidos
        JButton VerPedidosOPBT = new JButton("Ver pedidos");
        VerPedidosOPBT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VerPedidoOperador(operador).setVisible(true);
                dispose();
            }
        });
        VerPedidosOPBT.setFont(new Font("Tahoma", Font.PLAIN, 16));
        VerPedidosOPBT.setBounds(315, 121, 207, 52);
        contentPane.add(VerPedidosOPBT);

        // Botón Explotar Mineral (corregido)
        JButton btnExplotarMineral = new JButton("Explotar mineral");
        btnExplotarMineral.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ReponerStock(operador).setVisible(true); // CORREGIDO
                dispose();
            }
        });
        btnExplotarMineral.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnExplotarMineral.setBounds(98, 121, 207, 52);
        contentPane.add(btnExplotarMineral);

        // Iconos
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(HomeOperador.class.getResource("/IMG/diamante-super-chico.png")));
        lblNewLabel.setBounds(411, 20, 53, 64);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(HomeOperador.class.getResource("/IMG/diamante-super-chico.png")));
        lblNewLabel_1.setBounds(141, 20, 53, 64);
        contentPane.add(lblNewLabel_1);
    }
}
