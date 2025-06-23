package GUI;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import BLL.Pedido;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeEncargado extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public HomeEncargado(Pedido pedido) {
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 647, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel HomeTituloCL = new JLabel("MINERIA MINASHI");
        HomeTituloCL.setBounds(209, 47, 192, 30);
        HomeTituloCL.setHorizontalAlignment(SwingConstants.CENTER);
        HomeTituloCL.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
        contentPane.add(HomeTituloCL);

        JButton VerPedidoENBT = new JButton("Ver pedido");
        VerPedidoENBT.setFont(new Font("Tahoma", Font.PLAIN, 16));
        VerPedidoENBT.setBounds(50, 104, 123, 52);
        contentPane.add(VerPedidoENBT);
        VerPedidoENBT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VerPedido ver = new VerPedido();
                ver.setVisible(true);
                dispose();
            }
        });

        JButton RevisarStockENBT = new JButton("Revisar stock");
        RevisarStockENBT.setFont(new Font("Tahoma", Font.PLAIN, 16));
        RevisarStockENBT.setBounds(232, 104, 145, 52);
        contentPane.add(RevisarStockENBT);
        RevisarStockENBT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	RevisarStock revisar = new RevisarStock(pedido);
            	  revisar.setVisible(true);
                  dispose();
            }
        });

        JButton EnviarPedidoENBT = new JButton("Enviar pedido");
        EnviarPedidoENBT.setFont(new Font("Tahoma", Font.PLAIN, 16));
        EnviarPedidoENBT.setBounds(426, 104, 145, 52);
        contentPane.add(EnviarPedidoENBT);
        EnviarPedidoENBT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
            }
        });

        JButton SalirCLBT = new JButton("Salir");
        SalirCLBT.setFont(new Font("Tahoma", Font.PLAIN, 16));
        SalirCLBT.setBounds(260, 186, 95, 52);
        contentPane.add(SalirCLBT);
        SalirCLBT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); 
            }
        });
    }
}
