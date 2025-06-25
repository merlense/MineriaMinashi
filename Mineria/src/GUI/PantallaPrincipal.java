package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class PantallaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField InputEmail;
	private JPasswordField passwordField;

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

	public PantallaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 421, 324);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel CorreoElectronico = new JLabel("Ingrese su correo electronico:");
		CorreoElectronico.setFont(new Font("Tahoma", Font.PLAIN, 14));
		CorreoElectronico.setBounds(24, 62, 219, 22);
		contentPane.add(CorreoElectronico);
		
		InputEmail = new JTextField();
		InputEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		InputEmail.setBounds(24, 89, 219, 30);
		contentPane.add(InputEmail);
		InputEmail.setColumns(10);
		
		JLabel Contrasenia = new JLabel("Ingrese su contraseña:");
		Contrasenia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Contrasenia.setBounds(24, 140, 174, 13);
		contentPane.add(Contrasenia);
		
		JLabel Mineria = new JLabel("MINERIA MINASHI");
		Mineria.setHorizontalAlignment(SwingConstants.CENTER);
		Mineria.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
		Mineria.setBounds(106, 30, 203, 22);
		contentPane.add(Mineria);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(23, 163, 217, 30);
		contentPane.add(passwordField);
	
		JButton LoginBtn = new JButton("Login");

		LoginBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String email = InputEmail.getText().trim();
		        String password = new String(passwordField.getPassword()).trim();

		        if (email.isEmpty() && password.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacíos");
		            return;
		        }

		        if (email.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Debe ingresar el email");
		            return;
		        }

		        if (password.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Debe ingresar la contraseña");
		            return;
		        }

		        DLL.ControllerUsuario controller = new DLL.ControllerUsuario();
		        BLL.Usuario usuario = controller.login(email, password);

		        if (usuario != null) {
		            JOptionPane.showMessageDialog(null, "Bienvenido " + usuario.getTipo() + " " + usuario.getNombre());

		            dispose(); 

		            switch (usuario.getTipo().toLowerCase()) {
		                case "cliente":
		                    new HomeCliente(usuario).setVisible(true);
		                    break;
		                case "operador":
		                    new HomeOperador(usuario).setVisible(true);
		                    break;
		                case "encargado":
		                    new HomeEncargado(usuario).setVisible(true);
		                    break;
		                default:
		                    JOptionPane.showMessageDialog(null, "Tipo de usuario no reconocido.");
		                    break;
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
		        }
		    }
		});

		LoginBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LoginBtn.setBounds(86, 223, 114, 40);
		contentPane.add(LoginBtn);

		
		JButton RegistroBtn = new JButton("Registrate");
		RegistroBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				MenuRegistro registro = new MenuRegistro();
				registro.setVisible(true);
				dispose();
			}
		});
		RegistroBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		RegistroBtn.setBounds(209, 223, 114, 40);
		contentPane.add(RegistroBtn);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/IMG/picoDiamante.png")));
		lblNewLabel.setBounds(269, 80, 114, 113);
		contentPane.add(lblNewLabel);
	}
}