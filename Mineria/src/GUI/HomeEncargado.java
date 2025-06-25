package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import BLL.Usuario;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class HomeEncargado extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Usuario usuario;

    public HomeEncargado(Usuario usuario) {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(HomeEncargado.class.getResource("/IMG/aldeano.jpg")));
    	setTitle("Home - Encargado");
        this.usuario = usuario;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 636, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel HomeTituloCL = new JLabel("MINERIA MINASHI");
        HomeTituloCL.setBounds(209, 52, 192, 30);
        HomeTituloCL.setHorizontalAlignment(SwingConstants.CENTER);
        HomeTituloCL.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
        contentPane.add(HomeTituloCL);

        JButton RevisarStockENBT = new JButton("Revisar stock");
        RevisarStockENBT.setFont(new Font("Tahoma", Font.PLAIN, 16));
        RevisarStockENBT.setBounds(311, 122, 192, 52);
        contentPane.add(RevisarStockENBT);
        RevisarStockENBT.addActionListener(e -> {
            RevisarStock revisar = new RevisarStock(null, usuario);
            revisar.setVisible(true);
            dispose();
        });

        JButton GestionarPedidoENBT = new JButton("Gestionar pedido");
        GestionarPedidoENBT.setFont(new Font("Tahoma", Font.PLAIN, 16));
        GestionarPedidoENBT.setBounds(109, 122, 192, 52);
        contentPane.add(GestionarPedidoENBT);
        GestionarPedidoENBT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GestionarPedido gestionar = new GestionarPedido(usuario);
                gestionar.setVisible(true);
                dispose(); 
            }
        });

        JButton SalirCLBT = new JButton("Salir");
        SalirCLBT.setFont(new Font("Tahoma", Font.PLAIN, 16));
        SalirCLBT.setBounds(232, 201, 145, 52);
        contentPane.add(SalirCLBT);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(HomeEncargado.class.getResource("/IMG/diamante-super-chico.png")));
        lblNewLabel.setBounds(409, 35, 51, 59);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(HomeEncargado.class.getResource("/IMG/diamante-super-chico.png")));
        lblNewLabel_1.setBounds(148, 35, 51, 59);
        contentPane.add(lblNewLabel_1);
        SalirCLBT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}