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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField InputEmail;
	private JPasswordField passwordField;

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
		setBounds(100, 100, 421, 324);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel CorreoElectronico = new JLabel("Ingrese su correo electronico:");
		CorreoElectronico.setFont(new Font("Tahoma", Font.PLAIN, 14));
		CorreoElectronico.setBounds(11, 68, 219, 22);
		contentPane.add(CorreoElectronico);
		
		InputEmail = new JTextField();
		InputEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		InputEmail.setBounds(11, 95, 219, 30);
		contentPane.add(InputEmail);
		InputEmail.setColumns(10);
		
		JLabel Contrasenia = new JLabel("Ingrese su contraseña:");
		Contrasenia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Contrasenia.setBounds(11, 146, 174, 13);
		contentPane.add(Contrasenia);
		
		JLabel Mineria = new JLabel("Mineria minashi");
		Mineria.setHorizontalAlignment(SwingConstants.CENTER);
		Mineria.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Mineria.setBounds(116, 24, 160, 22);
		contentPane.add(Mineria);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(10, 169, 217, 30);
		contentPane.add(passwordField);
	
		JButton LoginBtn = new JButton("Login");
		LoginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Aca se pondria la accion del login
				
				
			}
		});
		LoginBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LoginBtn.setBounds(9, 214, 106, 28);
		contentPane.add(LoginBtn);
		
		JButton RegistroBtn = new JButton("Registrate");
		RegistroBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Aca se pondria la accion del registro
				
				
			}
		});
		RegistroBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		RegistroBtn.setBounds(120, 214, 106, 28);
		contentPane.add(RegistroBtn);
		int cliente = (int)(Math.random()*20);
		
	//	JOptionPane.showMessageDialog(null, cliente==0?"Es admin":"Es cliente");
		

		}
		
		
	}

