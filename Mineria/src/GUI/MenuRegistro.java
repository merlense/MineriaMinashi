package GUI;

import BLL.Cliente;
import BLL.Usuario;



import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import java.awt.Color;

public class MenuRegistro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField IngNombreReg;
	private JTextField IngApellidoReg;
	private JTextField IngEmailReg;
	private JPasswordField ingConReg;

	public MenuRegistro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 468, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel TituloRegistro = new JLabel("REGISTRO");
		TituloRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		TituloRegistro.setFont(new Font("Segoe UI Semibold", Font.BOLD, 25));
		TituloRegistro.setBounds(160, 10, 125, 58);
		contentPane.add(TituloRegistro);
		
		JLabel NombreReg = new JLabel("Nombre:");
		NombreReg.setFont(new Font("Tahoma", Font.PLAIN, 16));
		NombreReg.setBounds(37, 79, 75, 17);
		contentPane.add(NombreReg);
		
		IngNombreReg = new JTextField();
		IngNombreReg.setBounds(37, 99, 168, 37);
		contentPane.add(IngNombreReg);
		IngNombreReg.setColumns(10);
		
		JLabel ApellidoReg = new JLabel("Apellido:");
		ApellidoReg.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ApellidoReg.setBounds(249, 79, 75, 17);
		contentPane.add(ApellidoReg);
		
		IngApellidoReg = new JTextField();
		IngApellidoReg.setColumns(10);
		IngApellidoReg.setBounds(249, 99, 168, 37);
		contentPane.add(IngApellidoReg);
		
		JLabel EmailReg = new JLabel("Email:");
		EmailReg.setFont(new Font("Tahoma", Font.PLAIN, 16));
		EmailReg.setBounds(37, 162, 75, 17);
		contentPane.add(EmailReg);
		
		IngEmailReg = new JTextField();
		IngEmailReg.setColumns(10);
		IngEmailReg.setBounds(37, 182, 258, 37);
		contentPane.add(IngEmailReg);
		
		JLabel ContraseniaReg = new JLabel("Contraseña:");
		ContraseniaReg.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ContraseniaReg.setBounds(37, 246, 107, 17);
		contentPane.add(ContraseniaReg);
		
		JButton RegistrarBtn = new JButton("Registrar");
		RegistrarBtn.setForeground(new Color(0, 0, 0));
		RegistrarBtn.setBackground(new Color(192, 192, 192));
		RegistrarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = IngNombreReg.getText();
				String apellido = IngApellidoReg.getText();
				String email = IngEmailReg.getText();
				String contrasenia = new String(ingConReg.getPassword());

				Usuario nuevoUsuario = new Usuario();
				nuevoUsuario.setNombre(nombre);
				nuevoUsuario.setApellido(apellido);
				nuevoUsuario.setEmail(email);
				nuevoUsuario.setContrasenia(contrasenia);
				nuevoUsuario.setTipo("Cliente");

				if (nuevoUsuario.registrarUsuario()) {
					dispose(); // cerrar ventana actual si se desea
				}
			}
		});

		RegistrarBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		RegistrarBtn.setBounds(160, 342, 125, 48);
		contentPane.add(RegistrarBtn);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MenuRegistro.class.getResource("/IMG/steve.png")));
		lblNewLabel.setBounds(321, 181, 107, 122);
		contentPane.add(lblNewLabel);
		
		ingConReg = new JPasswordField();
		ingConReg.setBounds(37, 273, 184, 37);
		contentPane.add(ingConReg);
	}
}
