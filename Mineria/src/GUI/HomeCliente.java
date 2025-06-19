package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.ImageIcon;

public class HomeCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
	public HomeCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 658, 301);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel HomeTituloCL = new JLabel("MINERIA MINASHI");
		HomeTituloCL.setHorizontalAlignment(SwingConstants.CENTER);
		HomeTituloCL.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
		HomeTituloCL.setBounds(220, 39, 192, 47);
		contentPane.add(HomeTituloCL);
		
		JButton CatalogoCLBT = new JButton("Ver catalogo");
		CatalogoCLBT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		CatalogoCLBT.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        CatalogoMinerales catalogo = new CatalogoMinerales();
		        catalogo.setVisible(true);
		    }
		});
		
		CatalogoCLBT.setBounds(26, 118, 131, 47);
		contentPane.add(CatalogoCLBT);
		
		JButton HacerPedidoCLBT = new JButton("Hacer pedido");
		HacerPedidoCLBT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ACCION HACER PEDIDO
			}
		});
		HacerPedidoCLBT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		HacerPedidoCLBT.setBounds(167, 118, 131, 47);
		contentPane.add(HacerPedidoCLBT);
		
		JButton FinalizarPedidoCLBT = new JButton("Finalizar pedido");
		FinalizarPedidoCLBT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ACCION FINALIZAR PEDIDO
			}
		});
		FinalizarPedidoCLBT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		FinalizarPedidoCLBT.setBounds(312, 118, 151, 47);
		contentPane.add(FinalizarPedidoCLBT);
		
		JButton VerEstadoPedidoCLBT_1 = new JButton("Ver pedido");
		VerEstadoPedidoCLBT_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ACCION VER PEDIDO
			}
		});
		VerEstadoPedidoCLBT_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		VerEstadoPedidoCLBT_1.setBounds(481, 118, 131, 47);
		contentPane.add(VerEstadoPedidoCLBT_1);
		
		JButton SalirCLBT = new JButton("Salir");
		SalirCLBT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ACCION SALIR
			}
		});
		SalirCLBT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		SalirCLBT.setBounds(257, 189, 131, 47);
		contentPane.add(SalirCLBT);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(HomeCliente.class.getResource("/IMG/diamante-super-chico.png")));
		lblNewLabel.setBounds(156, 26, 48, 60);
		contentPane.add(lblNewLabel);
	}
}
