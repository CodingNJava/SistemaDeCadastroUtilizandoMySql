/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tela;

/**
 *
 * @author rita
 */

import java.sql.*;
import javax.swing.*;

/**
 * @author Rita
 */
public class Utilidades {
	Connection con;
	Statement stmt;
	ResultSet rs;
	static String driver = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/impacta";

	String strSQL;
	int codigo;
	String nome;
	String telefone;

	public void abrirDB() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "root", "Imp@ct@");
			stmt = con.createStatement();

		} catch (ClassNotFoundException e) {
			System.out.println("Classe não encontrada. ");
		} catch (SQLException e) {
			System.out.println("Não foi Possivel estabelecer a conexão. ");
		}
	}

	public void fecharDB() {
		try {
			con.close();
			stmt.close();

		} catch (SQLException e) {
		}
	}

	public void getDados(int codigo) {
		try {
			strSQL = "SELECT * FROM alunos WHERE Codigo = " + codigo;
			rs = stmt.executeQuery(strSQL);

			boolean nRegistros = false;

			while (rs.next()) {
				nRegistros = true;

				codigo = rs.getInt("Codigo");
				nome = rs.getString("Nome");
				telefone = rs.getString("telefone");
			}

			if (nRegistros == false) {

				JOptionPane.showMessageDialog(null, "Registro não existe!",
						"Atenção!", JOptionPane.INFORMATION_MESSAGE);

			}

		} catch (SQLException e) {
			System.out.println("Problemas ao recuperar dados...");
		}
	}

	public void setCampos(int codigo, String nome, String telefone) {

		this.codigo = codigo;
		this.nome = nome;
		this.telefone = telefone;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void inserirDados() {

		try {

			strSQL = "INSERT INTO alunos(CODIGO,NOME,TELEFONE)" + " VALUES ('"
					+ codigo + "','" + nome + "','" + telefone + "')";

			stmt.executeUpdate(strSQL);
			JOptionPane.showMessageDialog(null,
					"Dados cadastrados com sucesso!", "Atenção!",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Este registro ja existe",
					"Atenção!", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	void excluirRegistro() {

		try {
			strSQL = "DELETE FROM alunos WHERE codigo = '" + codigo + "'";
			stmt.executeUpdate(strSQL);

			JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!",
					"Parabéns", JOptionPane.INFORMATION_MESSAGE);

		}

		catch (SQLException e) {
			System.out.println("não excluiu");
			System.err.print(e);
			e.printStackTrace();
		}
	}

}
