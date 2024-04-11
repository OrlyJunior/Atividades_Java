package ConexaoAoBancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ConexaoProd extends FechaConexao {
	public Connection get() {
		Conexao pegaCategoria = new Conexao();

		Connection con = null;

		String url = "jdbc:mysql://localhost:3306/testejava?user=root&password=1234561";

		try {
			con = DriverManager.getConnection(url);

			String cm = "select * from produto";

			Statement comando = con.prepareStatement(cm);

			ResultSet retorno = comando.executeQuery(cm);

			while (retorno.next()) {
				Produto produto = new Produto();

				produto.setCategoriaId(retorno.getInt("categoriaId"));
				produto.setId(retorno.getInt("id"));
				produto.setDescricao(retorno.getString("descricao"));
				produto.setQuantidade(retorno.getDouble("quantidade"));
				produto.setUnidadeDeMedida(retorno.getString("unidadeDeMedida"));

				try {
					CompletableFuture<Categoria> categoria = CompletableFuture.supplyAsync(() -> {
						return pegaCategoria.getId(produto.getCategoriaId());
					});

					produto.setCateogoria(categoria.get());
				} catch (InterruptedException | ExecutionException e) {
					System.out.println(e.getMessage());
				}

				System.out.println(produto.escrever());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			fecharConexao(con);
		}

		return con;
	}

	public Connection getId() {
		Scanner inserir = new Scanner(System.in);

		Conexao pegaCategoria = new Conexao();

		Connection con = null;

		System.out.println("Insira o ID do produto que deseja consultar:");

		int id = inserir.nextInt();

		String url = "jdbc:mysql://localhost:3306/testejava?user=root&password=1234561";

		try {
			con = DriverManager.getConnection(url);

			String cm = "select * from produto where id = ?";

			PreparedStatement comando = con.prepareStatement(cm);

			comando.setInt(1, id);

			ResultSet retorno = comando.executeQuery();

			if (retorno.next()) {
				Produto produto = new Produto();

				produto.setCategoriaId(retorno.getInt("categoriaId"));
				produto.setId(retorno.getInt("id"));
				produto.setDescricao(retorno.getString("descricao"));
				produto.setQuantidade(retorno.getDouble("quantidade"));
				produto.setUnidadeDeMedida(retorno.getString("unidadeDeMedida"));

				try {
					CompletableFuture<Categoria> categoria = CompletableFuture.supplyAsync(() -> {
						return pegaCategoria.getId(produto.getCategoriaId());
					});

					produto.setCateogoria(categoria.get());
				} catch (InterruptedException | ExecutionException e) {
					System.out.println(e.getMessage());
				}

				System.out.println(produto.escrever());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			fecharConexao(con);
		}

		return con;
	}

	public Connection post() {
		Scanner inserir = new Scanner(System.in);

		Produto produto = new Produto();

		Connection con = null;

		System.out.println("Qual a descrição do produto que deseja inserir?");

		produto.setDescricao(inserir.nextLine());

		System.out.println("Qual a quantidade do produto que deseja inserir?");

		produto.setQuantidade(inserir.nextDouble());

		inserir.nextLine();

		System.out.println("Qual a unidade de medida do produto que deseja inserir?");

		produto.setUnidadeDeMedida(inserir.nextLine());

		System.out.println("Qual o ID da categoria do produto que deseja inserir?");

		produto.setCategoriaId(inserir.nextInt());

		String url = "jdbc:mysql://localhost:3306/testejava?user=root&password=1234561";

		try {
			con = DriverManager.getConnection(url);

			String cm = "insert into produto(descricao, quantidade, unidadeDeMedida, categoriaId)values(?, ?, ?, ?)";

			PreparedStatement comando = con.prepareStatement(cm);

			comando.setString(1, produto.getDescricao());
			comando.setDouble(2, produto.getQuantidade());
			comando.setString(3, produto.getUnidadeDeMedida());
			comando.setInt(4, produto.getCategoriaId());

			comando.execute();

			System.out.println("Produto inserido com sucesso!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			fecharConexao(con);
		}

		return con;
	}

	public Connection delete() {
		Scanner inserir = new Scanner(System.in);

		Connection con = null;

		System.out.println("Qual a descrição do produto que deseja deletar?");

		int id = inserir.nextInt();

		String url = "jdbc:mysql://localhost:3306/testejava?user=root&password=1234561";

		try {
			con = DriverManager.getConnection(url);

			String cm = "delete from produto where id = ?";

			PreparedStatement comando = con.prepareStatement(cm);

			comando.setInt(1, id);

			comando.execute();

			System.out.println("Produto deletado com sucesso!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			fecharConexao(con);
		}

		return con;
	}

	public Connection put() {
		Scanner inserir = new Scanner(System.in);

		Produto produto = new Produto();

		Connection con = null;

		System.out.println("Insira o ID do produto que deseja editar:");

		int id = inserir.nextInt();

		inserir.nextLine();

		System.out.println("Insira a nova descrição do produto que deseja editar:");

		produto.setDescricao(inserir.nextLine());

		System.out.println("Insira a nova unidade de medida do produto que deseja editar:");

		produto.setUnidadeDeMedida(inserir.nextLine());

		System.out.println("Insira a nova quantidade do produto que deseja editar:");

		produto.setQuantidade(inserir.nextDouble());

		inserir.nextLine();

		System.out.println("Insira o novo ID da categoria do produto que deseja editar:");

		produto.setCategoriaId(inserir.nextInt());

		String url = "jdbc:mysql://localhost:3306/testejava?user=root&password=1234561";

		try {
			con = DriverManager.getConnection(url);

			String cm = "update produto set descricao = ?, unidadeDeMedida = ?, quantidade = ?, categoriaId = ? where id = ?";

			PreparedStatement comando = con.prepareStatement(cm);

			comando.setString(1, produto.getDescricao());
			comando.setString(2, produto.getUnidadeDeMedida());
			comando.setDouble(3, produto.getQuantidade());
			comando.setInt(4, produto.getCategoriaId());
			comando.setInt(5, id);

			comando.execute();

			System.out.println("Produto editado com sucesso!");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			fecharConexao(con);
		}

		return con;
	}
}
