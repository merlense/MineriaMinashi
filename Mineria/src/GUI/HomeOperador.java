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

public class HomeOperador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public HomeOperador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 633, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton SalirCLBT = new JButton("Salir");
		SalirCLBT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ACCION SALIR
			}
		});
		SalirCLBT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		SalirCLBT.setBounds(259, 189, 95, 52);
		contentPane.add(SalirCLBT);
		
		JLabel HomeTituloCL = new JLabel("MINERIA MINASHI");
		HomeTituloCL.setHorizontalAlignment(SwingConstants.CENTER);
		HomeTituloCL.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
		HomeTituloCL.setBounds(208, 50, 192, 30);
		contentPane.add(HomeTituloCL);
		
		JButton VerPedidosOPBT = new JButton("Ver pedidos");
		VerPedidosOPBT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ACCION VER PEDIDOS 
			}
		});
		VerPedidosOPBT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		VerPedidosOPBT.setBounds(63, 107, 123, 52);
		contentPane.add(VerPedidosOPBT);
		
		JButton btnExplotarMineral = new JButton("Explotar mineral");
		btnExplotarMineral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ACCION EXPLOTAR MINERAL
			}
		});
		btnExplotarMineral.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnExplotarMineral.setBounds(222, 107, 168, 52);
		contentPane.add(btnExplotarMineral);
		
		JButton btnActualizarStock = new JButton("Actualizar stock");
		btnActualizarStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ACCION EXPLOTAR MINERAL
			}
		});
		btnActualizarStock.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnActualizarStock.setBounds(417, 107, 145, 52);
		contentPane.add(btnActualizarStock);
	}

}
