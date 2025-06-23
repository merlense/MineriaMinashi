package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeEncargado extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
	//HOLA
	
	public HomeEncargado() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 647, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton SalirCLBT = new JButton("Salir");
		SalirCLBT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		SalirCLBT.setBounds(260, 186, 95, 52);
		SalirCLBT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(SalirCLBT);
		
		JLabel HomeTituloCL = new JLabel("MINERIA MINASHI");
		HomeTituloCL.setBounds(209, 47, 192, 30);
		HomeTituloCL.setHorizontalAlignment(SwingConstants.CENTER);
		HomeTituloCL.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
		contentPane.add(HomeTituloCL);
		
		JButton VerPedidoENBT = new JButton("Ver pedido");
		VerPedidoENBT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		VerPedidoENBT.setBounds(50, 104, 123, 52);
		VerPedidoENBT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(VerPedidoENBT);
		
		JButton RevisarStockENBT = new JButton("Revisar stock");
		RevisarStockENBT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		RevisarStockENBT.setBounds(232, 104, 145, 52);
		RevisarStockENBT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(RevisarStockENBT);
		
		JButton EnviarPedidoENBT = new JButton("Enviar pedido");
		EnviarPedidoENBT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		EnviarPedidoENBT.setBounds(426, 104, 145, 52);
		EnviarPedidoENBT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(EnviarPedidoENBT);
	}

}
