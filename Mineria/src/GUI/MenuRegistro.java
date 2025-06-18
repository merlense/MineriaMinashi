package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuRegistro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField IngNombreReg;
	private JTextField IngApellidoReg;
	private JTextField IngEmailReg;
	private JTextField textField;

	public MenuRegistro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel TituloRegistro = new JLabel("Registro");
		TituloRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		TituloRegistro.setFont(new Font("Tahoma", Font.PLAIN, 25));
		TituloRegistro.setBounds(102, 10, 195, 58);
		contentPane.add(TituloRegistro);
		
		JLabel NombreReg = new JLabel("Nombre:");
		NombreReg.setFont(new Font("Tahoma", Font.PLAIN, 16));
		NombreReg.setBounds(10, 95, 75, 17);
		contentPane.add(NombreReg);
		
		IngNombreReg = new JTextField();
		IngNombreReg.setBounds(10, 115, 168, 37);
		contentPane.add(IngNombreReg);
		IngNombreReg.setColumns(10);
		
		JLabel ApellidoReg = new JLabel("Apellido:");
		ApellidoReg.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ApellidoReg.setBounds(222, 95, 75, 17);
		contentPane.add(ApellidoReg);
		
		IngApellidoReg = new JTextField();
		IngApellidoReg.setColumns(10);
		IngApellidoReg.setBounds(222, 115, 168, 37);
		contentPane.add(IngApellidoReg);
		
		JLabel EmailReg = new JLabel("Email:");
		EmailReg.setFont(new Font("Tahoma", Font.PLAIN, 16));
		EmailReg.setBounds(10, 178, 75, 17);
		contentPane.add(EmailReg);
		
		IngEmailReg = new JTextField();
		IngEmailReg.setColumns(10);
		IngEmailReg.setBounds(10, 198, 287, 37);
		contentPane.add(IngEmailReg);
		
		JLabel ContraseniaReg = new JLabel("Contraseña:");
		ContraseniaReg.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ContraseniaReg.setBounds(10, 262, 107, 17);
		contentPane.add(ContraseniaReg);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(10, 282, 218, 37);
		contentPane.add(textField);
		
		JButton RegistrarBtn = new JButton("Registrar");
		RegistrarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Accion de registro
			}
		});
		RegistrarBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		RegistrarBtn.setBounds(160, 348, 125, 48);
		contentPane.add(RegistrarBtn);
	}

	// HOLA HOLA 2.0
}
