package tela;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

//import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import java.sql.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Cadastro extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNome;
	private JTextField txtTelefone;

	// private final static String url = "jdbc:mysql://localhost:3306/impacta";
	//
	// private final static String username = "root";
	// private final static String password = "Imp@ct@";
	//
	// private Connection con;
	// private Statement stmt;
	// private ResultSet rs;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// Cadastro frame = new Cadastro();
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the frame.
	 */
	public Cadastro() {
		setTitle("Cadastro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 303, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblC = new JLabel("C\u00F3digo:");
		lblC.setBounds(50, 41, 46, 14);
		contentPane.add(lblC);

		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setBounds(50, 94, 62, 14);
		contentPane.add(lblNewLabel);

		JLabel lblT = new JLabel("Telefone:");
		lblT.setBounds(50, 156, 62, 14);
		contentPane.add(lblT);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(158, 38, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);

		txtNome = new JTextField();
		txtNome.setBounds(158, 91, 86, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		txtTelefone = new JTextField();
		txtTelefone.setBounds(158, 153, 86, 20);
		contentPane.add(txtTelefone);
		txtTelefone.setColumns(10);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Utilidades u = new Utilidades();
				u.abrirDB();

				try {
					u.setCampos(Integer.parseInt(txtCodigo.getText()),
							txtNome.getText(), txtTelefone.getText());
					u.inserirDados();
					u.fecharDB();
					limparCampos();

				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Digite um cÃ³digo!",
							"Atenção", JOptionPane.INFORMATION_MESSAGE);
					limparCampos();
				}
			}
		});
		btnCadastrar.setBounds(50, 207, 91, 23);
		contentPane.add(btnCadastrar);

		JButton btnSair = new JButton("Sair");
		btnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// System.exit(0);
				setVisible(false);
			}
		});
		btnSair.setBounds(153, 207, 91, 23);
		contentPane.add(btnSair);
	}

	// public void openDB() {
	// try {
	// con = DriverManager.getConnection(url, username, password);
	// stmt = con.createStatement();
	// System.out.println("\nConexão estabelecida com sucesso!\n");
	// } catch (SQLException e) {
	// System.out.println("\nNão foi possível estabelecer conexão " + e
	// + "\n");
	// System.exit(1);
	// }
	// }

	// public void closeDB() {
	// try {
	// con.close();
	// } catch (SQLException e) {
	// System.out.println("\nNão foi possível fechar conexão " + e + "\n");
	// System.exit(1);
	// }
	// }

	// public void cadastrar() {
	// // montagem do comando SQL Insert:
	// try {
	// String comandoSQL = "INSERT INTO alunos(CÓDIGO,NOME,TELEFONE)"
	// + " VALUES ('" + txtCodigo.getText() + "','"
	// + txtNome.getText() + "','" + txtTelefone.getText() + "')";
	// System.out.println("fez aqui");
	//
	// // executando o comando de gravação:
	//
	// stmt.executeUpdate(comandoSQL);
	// JOptionPane.showMessageDialog(null,
	// "Dados cadastrados com sucesso!", "Parabéns",
	// JOptionPane.INFORMATION_MESSAGE);
	// limparCampos();
	//
	// } catch (SQLException e) {
	// System.out.println("Não cadastrou...");
	// JOptionPane.showMessageDialog(null, "Este registro já existe",
	// "Atenção!", JOptionPane.INFORMATION_MESSAGE);
	// limparCampos();
	// e.printStackTrace();
	// }
	// }

	void limparCampos() {
		txtCodigo.setText(null);
		txtNome.setText(null);
		txtTelefone.setText(null);
		txtCodigo.requestFocus();
	}

}
