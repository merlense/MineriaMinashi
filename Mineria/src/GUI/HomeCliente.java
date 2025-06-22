package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import BLL.Usuario;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeCliente extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public HomeCliente(Usuario usuario) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 658, 301);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel HomeTituloCL = new JLabel("MINERIA MINASHI");
        HomeTituloCL.setHorizontalAlignment(SwingConstants.CENTER);
        HomeTituloCL.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
        HomeTituloCL.setBounds(214, 39, 196, 47);
        contentPane.add(HomeTituloCL);

        JButton CatalogoCLBT = new JButton("Ver catalogo");
        CatalogoCLBT.setFont(new Font("Tahoma", Font.PLAIN, 16));
        CatalogoCLBT.setBounds(164, 118, 131, 47);
        contentPane.add(CatalogoCLBT);

        CatalogoCLBT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CatalogoMinerales catalogo = new CatalogoMinerales(usuario);
                catalogo.setVisible(true);
                dispose(); 
            }
        });

        JButton VerEstadoPedidoCLBT_1 = new JButton("Ver estado");
        VerEstadoPedidoCLBT_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        VerEstadoPedidoCLBT_1.setBounds(336, 118, 146, 47);
        contentPane.add(VerEstadoPedidoCLBT_1);
        
        VerEstadoPedidoCLBT_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ResumenPedido resumen = new ResumenPedido(usuario);
                resumen.setVisible(true);
            }
        });



        JButton SalirCLBT = new JButton("Salir");
        SalirCLBT.setFont(new Font("Tahoma", Font.PLAIN, 16));
        SalirCLBT.setBounds(248, 177, 131, 47);
        contentPane.add(SalirCLBT);

        SalirCLBT.addActionListener(e -> {
            dispose(); 
        });

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(HomeCliente.class.getResource("/IMG/diamante-super-chico.png")));
        lblNewLabel.setBounds(156, 26, 48, 60);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(HomeCliente.class.getResource("/IMG/diamante-super-chico.png")));
        lblNewLabel_1.setBounds(420, 26, 48, 60);
        contentPane.add(lblNewLabel_1);
    }
}
