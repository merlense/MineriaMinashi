package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class PantallaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField PaginaPrincipal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaPrincipal frame = new PantallaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PantallaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(134, 12, 5, 22);
		contentPane.add(textArea);
		
		PaginaPrincipal = new JTextField();
		PaginaPrincipal.setBounds(134, 28, 156, 26);
		PaginaPrincipal.setHorizontalAlignment(SwingConstants.CENTER);
		PaginaPrincipal.setFont(new Font("Tahoma", Font.BOLD, 16));
		PaginaPrincipal.setText("Mineria Minashi");
		contentPane.add(PaginaPrincipal);
		PaginaPrincipal.setColumns(10);
		JButton btnComprar = new JButton("comprar");
		btnComprar.setBounds(34, 152, 89, 23);
		contentPane.add(btnComprar);
		int cliente = (int)(Math.random()*20);
		JOptionPane.showMessageDialog(null, cliente==0?"Es admin":"Es cliente");
		if (cliente==0) {
			//admin
			btnComprar.setVisible(false);
			
			HomeAdmin admin = new HomeAdmin();
			admin.setVisible(true);
		}else {
			//cliente
			btnComprar.setVisible(true);
			HomeCliente pantCliente = new HomeCliente();
			pantCliente.setVisible(true);

		}
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(54, 36, 46, 14);
		contentPane.add(lblNewLabel);
		
		
	}
}
