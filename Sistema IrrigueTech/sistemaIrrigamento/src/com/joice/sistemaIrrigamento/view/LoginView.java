package com.joice.sistemaIrrigamento.view;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.joice.sistemaIrrigamento.model.LoginModel;
import javax.swing.ImageIcon;

public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField tUsuario;
	private JPasswordField pSenha;	
	private JButton bRealizarLogin;
	private JLabel lblSenha;
	private JLabel lUsuario;
	private JLabel lLogin;
	
	PrincipalView chView = new PrincipalView();

	public LoginView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tUsuario = new JTextField();
		tUsuario.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		tUsuario.setBounds(187, 117, 213, 31);
		contentPane.add(tUsuario);
		tUsuario.setColumns(10);
		
		bRealizarLogin = new JButton("Login");
		bRealizarLogin.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		bRealizarLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginModel logModel = new LoginModel();
				//executa bot�o login				
				//verifica se nos campos usuario e senha possuem informa��es digitadas
				if (tUsuario.getText().length() == 0 && pSenha.getPassword().length == 0){
					JOptionPane.showMessageDialog(null, "Favor Preencher os campos acima!!");
				} else if (pSenha.getPassword().length == 0){
					JOptionPane.showMessageDialog(null, "Favor Preencher o campo SENHA!!");
				} else if (tUsuario.getText().length() == 0){
					JOptionPane.showMessageDialog(null, "Favor Preencher o campo USUARIO!!"); 
				} else {
					String usuario = tUsuario.getText();
					char[] senhaChar = pSenha.getPassword();
					String senha = String.copyValueOf(senhaChar); // Converte de array para String
					//valida o login
					try {
						if(logModel.validarLogin(usuario, senha)){ 
							setVisible(false);
							chView.setVisible(true);					
						} else {
							JOptionPane.showMessageDialog(null, "USUARIO OU SENHA INCORRETOS!!");
							tUsuario.setText("");
							pSenha.setText("");
						}
					} catch (HeadlessException | ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		bRealizarLogin.setBounds(246, 294, 89, 23);
		contentPane.add(bRealizarLogin);
		
		lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblSenha.setBounds(187, 170, 70, 24);
		contentPane.add(lblSenha);
		
		pSenha = new JPasswordField();
		pSenha.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		pSenha.setBounds(187, 195, 213, 31);
		contentPane.add(pSenha);
		
		lUsuario = new JLabel("Usuario:");
		lUsuario.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lUsuario.setBounds(187, 93, 55, 24);
		contentPane.add(lUsuario);
		
		lLogin = new JLabel("");
		lLogin.setIcon(new ImageIcon("C:\\Users\\joice\\OneDrive\\Documentos\\desafio indra\\sistemaIrrigamento\\src\\com\\joice\\sistemaIrrigamento\\imagem\\logo.jpg"));
		lLogin.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lLogin.setBounds(161, 26, 271, 56);
		contentPane.add(lLogin);
	}
}
