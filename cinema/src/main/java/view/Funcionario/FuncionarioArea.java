package view.Funcionario;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.FuncionarioDAO;
import model.ClsRedimensionarImagem;
import model.Funcionario;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import net.miginfocom.swing.MigLayout;
import view.JFrameMain;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class FuncionarioArea extends JFrame {

	private JPanel contentPane;
	private JTextField textLogin;
	private JTextField textSenha;
	private FuncionarioDAO funcionarioDAO = FuncionarioDAO.getInstancia();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					// LAF Windows não encontrado, usar o LAF padrão do sistema
				}
				try {
					FuncionarioArea frame = new FuncionarioArea();
					frame.setVisible(true);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FuncionarioArea() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(FuncionarioArea.class.getResource("/Images/0609b1d7-4a7d-41be-bd18-081ecb35eb9e.png")));
		setBackground(Color.WHITE);
		setTitle("Sistema de Cinema");
		Dimension tamanhoMinimo = new Dimension(860, 500);
		 setMinimumSize(tamanhoMinimo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 860, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[133.00px,grow 50][134px,grow][97.00px,grow][328.00][134px,grow][115px,grow 50]", "[66px,grow][68.00px][96.00px][][96.00px][36.00,grow][32.00][66px,grow]"));

		JLabel lblNewLabel = new JLabel("Área de funcionários");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 66));
		contentPane.add(lblNewLabel, "cell 1 0 4 2,growx,aligny center");
		
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // fecha tela atual
				JFrameMain jMain = new JFrameMain();
				jMain.setVisible(true);
				jMain.setExtendedState(JFrame.MAXIMIZED_BOTH);

			}
		});
		contentPane.add(btnNewButton, "cell 0 0,alignx left,aligny top");
		

		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Yu Gothic Light", Font.BOLD, 30));
		contentPane.add(lblNewLabel_1, "cell 2 2,growx,aligny center");
		
		textLogin = new JTextField();
		contentPane.add(textLogin, "cell 3 2,growx,aligny center");
		
		textLogin.setColumns(10);
		
		// IMG 1
			JLabel imgLogin = new JLabel();
			imgLogin.setForeground(new Color(255, 255, 255));
			contentPane.add(imgLogin, "cell 1 2,width 60px!,alignx right,height 60px!,aligny center");
	
			ImageIcon imageIcon1 = new ImageIcon(FuncionarioArea.class.getResource("/Images/login.png"));
			imgLogin.setIcon(imageIcon1);
	
			// Ajusta o tamanho do JLabel para corresponder ao tamanho da imagem redimensionada
			Image img = imageIcon1.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
			ImageIcon imageResized = new ImageIcon(img);
			imgLogin.setIcon(imageResized);
			imgLogin.setSize(60, 60);

		// IMG 2
			JLabel imgSenha = new JLabel("");
			contentPane.add(imgSenha, "cell 1 4,alignx right,aligny center");

			ImageIcon imageIcon2 = new ImageIcon(FuncionarioArea.class.getResource("/Images/trancar.png"));
			imgSenha.setIcon(imageIcon2);

			// Ajusta o tamanho do JLabel para corresponder ao tamanho da imagem redimensionada
			Image img2 = imageIcon2.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
			ImageIcon imageResized2 = new ImageIcon(img2);
			imgSenha.setIcon(imageResized2);
			imgSenha.setSize(60, 60);

			JLabel lblNewLabel_1_1 = new JLabel("CPF:");
			lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
			lblNewLabel_1_1.setFont(new Font("Yu Gothic Light", Font.BOLD, 30));
			contentPane.add(lblNewLabel_1_1, "cell 2 4,growx,aligny center");
			MaskFormatter cpfFormatter = null;
			
		
				JButton btnLogin = new JButton("Login");
				btnLogin.setBackground(Color.WHITE);
				btnLogin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
				btnLogin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String txtNome = textLogin.getText();
					    String txtCPF = textSenha.getText();
					    txtCPF = txtCPF.replaceAll("[^0-9]", ""); // Remove todos os caracteres não numéricos
					    Funcionario funcionarioLogin = funcionarioDAO.verificarFuncionarioAdmin(new Funcionario(Long.parseLong(txtCPF), txtNome));
					    if(funcionarioLogin != null) {
					    	dispose();
					    	CadastrarFuncionario func;
							try {
								func = new CadastrarFuncionario();
								func.setVisible(true);
								func.setExtendedState(JFrame.MAXIMIZED_BOTH);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					    }
					    else {
					        JOptionPane.showMessageDialog(null, "O Funcionario informado não existe ou não tem permissão de admin", "Aviso", JOptionPane.WARNING_MESSAGE);
					        txtNome = null;
					        txtCPF= null;
					    }
					}
				});
				try {
					cpfFormatter = new MaskFormatter("###.###.###-##");

				} catch (ParseException e) {
					e.printStackTrace();
				}
				textSenha = new JFormattedTextField(cpfFormatter);
				textSenha.setColumns(10);
				contentPane.add(textSenha, "cell 3 4,growx,aligny center");
				contentPane.add(btnLogin, "cell 2 6 2 1,grow");
	}
}
