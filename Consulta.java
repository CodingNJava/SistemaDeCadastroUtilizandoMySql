package tela;

//import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.sql.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Consulta extends JFrame {

	private JPanel contentPane;

//	private final static String url = "jdbc:mysql://localhost:3306/impacta";
//
//	private final static String username = "root";
//	private final static String password = "Imp@ct@";
//
//	private Connection con;
//	private Statement stmt;
//	private ResultSet rs;

	JLabel lblNome = new JLabel("");
	JLabel lblTelefone = new JLabel("");
	JTextField txtCodigo = new JTextField();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Consulta frame = new Consulta();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Consulta() {
		setTitle("Consulta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 344, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCod = new JLabel("C\u00F3digo:");
		lblCod.setBounds(56, 40, 46, 14);
		contentPane.add(lblCod);

		JLabel lblNo = new JLabel("Nome:");
		lblNo.setBounds(56, 93, 60, 14);
		contentPane.add(lblNo);

		JLabel lblTel = new JLabel("Telefone:");
		lblTel.setBounds(56, 147, 60, 14);
		contentPane.add(lblTel);

		// txtCodigo = new JTextField();
		txtCodigo.setBounds(150, 37, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);

		// lblNome = new JLabel("");
		lblNome.setBounds(150, 93, 84, 14);
		contentPane.add(lblNome);

		// lblTelefone = new JLabel("");
		lblTelefone.setBounds(150, 147, 86, 14);
		contentPane.add(lblTelefone);

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Utilidades u = new Utilidades();
				u.abrirDB();

				try {
					u.getDados(Integer.parseInt(txtCodigo.getText()));
					lblTelefone.setText(u.telefone);
					lblNome.setText(u.nome);
					u.fecharDB();
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Digite um código!",
							"Atenção", JOptionPane.INFORMATION_MESSAGE);
					limparCampos();
				}

			}
		});
		btnConsultar.setBounds(51, 195, 91, 23);
		contentPane.add(btnConsultar);

		JButton btnSair = new JButton("Sair");
		btnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		btnSair.setBounds(152, 195, 91, 23);
		contentPane.add(btnSair);
	}

	void limparCampos() {
		txtCodigo.setText(null);
		lblNome.setText(null);
		lblTelefone.setText(null);
		txtCodigo.requestFocus();
	}

}
