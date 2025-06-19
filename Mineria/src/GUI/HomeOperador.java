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
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class HomeOperador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public HomeOperador() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(HomeOperador.class.getResource("/IMG/steve.png")));
		setTitle("MENU OPERADOR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 633, 308);
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
		SalirCLBT.setBounds(259, 198, 95, 52);
		contentPane.add(SalirCLBT);
		
		JLabel HomeTituloCL = new JLabel("MINERIA MINASHI");
		HomeTituloCL.setHorizontalAlignment(SwingConstants.CENTER);
		HomeTituloCL.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
		HomeTituloCL.setBounds(204, 38, 192, 30);
		contentPane.add(HomeTituloCL);
		
		JButton VerPedidosOPBT = new JButton("Ver pedidos");
		VerPedidosOPBT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ACCION VER PEDIDOS 
			}
		});
		VerPedidosOPBT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		VerPedidosOPBT.setBounds(60, 123, 132, 52);
		contentPane.add(VerPedidosOPBT);
		
		JButton btnExplotarMineral = new JButton("Explotar mineral");
		btnExplotarMineral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ACCION EXPLOTAR MINERAL
			}
		});
		btnExplotarMineral.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnExplotarMineral.setBounds(219, 123, 168, 52);
		contentPane.add(btnExplotarMineral);
		
		JButton btnActualizarStock = new JButton("Actualizar stock");
		btnActualizarStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ACCION EXPLOTAR MINERAL
			}
		});
		btnActualizarStock.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnActualizarStock.setBounds(414, 123, 145, 52);
		contentPane.add(btnActualizarStock);
		
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
