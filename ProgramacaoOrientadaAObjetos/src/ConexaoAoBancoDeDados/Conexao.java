package ConexaoAoBancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Conexao {
	public Connection get() {
		Connection con = null;

		String url = "jdbc:mysql://localhost:3306/testejava?user=root&password=1234561";

		try {
			con = DriverManager.getConnection(url);

			String cm = "select * from categoria";

			Statement comando = con.prepareStatement(cm);

			ResultSet retorno = comando.executeQuery(cm);

			while (retorno.next()) {
				System.out.println(String.format("%d - %s", retorno.getInt("id"), retorno.getString("nome")));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			fecharConexao(con);
		}

		return con;
	}

	public Categoria getId() {
		Connection con = null;

		Categoria categoria = new Categoria();

		Scanner inserir = new Scanner(System.in);

		int idConsultar = inserir.nextInt();

		String url = "jdbc:mysql://localhost:3306/testejava?user=root&password=1234561";

		System.out.println("Qual o ID da categoria que deseja consultar?");

		try {
			con = DriverManager.getConnection(url);

			String cm = "select * from categoria where id = ?";

			PreparedStatement comando = con.prepareStatement(cm);

			comando.setInt(1, idConsultar);

			ResultSet retorno = comando.executeQuery();

			if (retorno.next()) {
				System.out.println(String.format("$d - %s", retorno.getInt("id"), retorno.getString("nome")));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			fecharConexao(con);
		}

		return categoria;
	}

	public void post() {
		Scanner inserir = new Scanner(System.in);

		Categoria categoria = new Categoria();

		System.out.println("Qual o nome da categoria que deseja inserir?");

		categoria.Nome = inserir.nextLine();

		Connection con = null;

		try {
			String url = "jdbc:mysql://localhost:3306/testejava?user=root&password=1234561";

			con = DriverManager.getConnection(url);

			String cm = "insert into categoria(nome)values(?)";

			PreparedStatement comando = con.prepareStatement(cm);

			comando.setString(1, categoria.Nome);

			comando.executeUpdate();

			System.out.println("Categoria inserida com sucesso!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			fecharConexao(con);
		}
	}

	public void delete() {
		Connection con = null;
		ResultSet retorno = null;
		PreparedStatement comando = null;

		Scanner inserir = new Scanner(System.in);

		System.out.println("Insira o id da categoria que deseja deletar");

		int idDelete = inserir.nextInt();

		try {
			String url = "jdbc:mysql://localhost:3306/testejava?user=root&password=1234561";

			con = DriverManager.getConnection(url);

			String cm = "delete from categoria where id = ?";

			comando = con.prepareStatement(cm);

			comando.setInt(1, idDelete);

			comando.executeUpdate();

			System.out.println("Categoria deletada com sucesso!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			fecharConexao(con);
		}
	}

	public void put() {
		Scanner inserir = new Scanner(System.in);

		Categoria categoria = new Categoria();

		System.out.println("Qual o ID da categoria que deseja editar?");

		categoria.Id = inserir.nextInt();

		inserir.nextLine();

		System.out.println("Qual você deseja que seja o novo nome desta categoria?");

		categoria.Nome = inserir.nextLine();

		Connection con = null;

		try {
			String url = "jdbc:mysql://localhost:3306/testejava?user=root&password=1234561";

			con = DriverManager.getConnection(url);

			String cm = "update categoria set nome = ? where id = ?";

			PreparedStatement comando = con.prepareStatement(cm);

			comando.setString(1, categoria.Nome);

			comando.setInt(2, categoria.Id);

			comando.executeUpdate();

			System.out.println("Categoria editada com sucesso!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			fecharConexao(con);
		}
	}

	private void fecharConexao(Connection con) {
		try {
			con.close();
		} catch (final Exception e) {
			System.out.println("Impossível fechar conexão!");
		}
	}
}
