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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import java.sql.*;

import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class Exclusao extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	JLabel lblNome = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Exclusao frame = new Exclusao();
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
	public Exclusao() {
		setTitle("Exclus\u00E3o");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 309, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCd = new JLabel("C\u00F3digo:");
		lblCd.setBounds(67, 57, 46, 14);
		contentPane.add(lblCd);

		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setBounds(67, 111, 46, 14);
		contentPane.add(lblNewLabel);

		txtCodigo = new JTextField();
		txtCodigo.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {

				Utilidades u = new Utilidades();
				u.abrirDB();
				try {
					u.getDados(Integer.parseInt(txtCodigo.getText()));
					lblNome.setText(u.nome);
					u.fecharDB();
				} catch (java.lang.NumberFormatException n) {

				}

			}
		});
		txtCodigo.setBounds(153, 54, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);

		// JLabel lblNome = new JLabel("");
		lblNome.setBounds(153, 111, 85, 14);
		contentPane.add(lblNome);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Utilidades u = new Utilidades();
				u.abrirDB();
				try {
					int escolha = JOptionPane.showConfirmDialog(null,
							"Deseja realmente excluir?");
					if (escolha == JOptionPane.YES_OPTION) {
						u.setCodigo(Integer.parseInt(txtCodigo.getText()));
						u.excluirRegistro();
						txtCodigo.setText("");
						lblNome.setText("");
					} else
						txtCodigo.setText("");
					lblNome.setText("");
					txtCodigo.requestFocus();

					u.fecharDB();
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Digite um código!",
							"Atenção", JOptionPane.INFORMATION_MESSAGE);
					txtCodigo.requestFocus();
				}
			}
		});
		btnExcluir.setBounds(67, 186, 91, 23);
		contentPane.add(btnExcluir);

		JButton btnSair = new JButton("Sair");
		btnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		btnSair.setBounds(168, 186, 91, 23);
		contentPane.add(btnSair);
	}

	void limparCampos() {
		txtCodigo.setText(null);
		lblNome.setText(null);
		txtCodigo.requestFocus();
	}
}
