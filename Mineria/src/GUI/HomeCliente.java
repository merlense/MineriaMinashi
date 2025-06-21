package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Usuario;

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

	
	public HomeCliente(Usuario  usuario) {
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
		CatalogoCLBT.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        CatalogoMinerales catalogo = new CatalogoMinerales(usuario);
		        catalogo.setVisible(true);
		    }
		});
		
		CatalogoCLBT.setBounds(164, 118, 131, 47);
		contentPane.add(CatalogoCLBT);
		
		JButton VerEstadoPedidoCLBT_1 = new JButton("Ver estado");
		VerEstadoPedidoCLBT_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ACCION VER PEDIDO
			}
		});
		VerEstadoPedidoCLBT_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		VerEstadoPedidoCLBT_1.setBounds(336, 118, 146, 47);
		contentPane.add(VerEstadoPedidoCLBT_1);
		
		JButton SalirCLBT = new JButton("Salir");
		SalirCLBT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ACCION SALIR
			}
		});
		SalirCLBT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		SalirCLBT.setBounds(248, 177, 131, 47);
		contentPane.add(SalirCLBT);
		
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
