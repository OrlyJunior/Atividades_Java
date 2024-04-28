package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.Categoria;
import metodosUteis.Metodos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import model.Produto;

@RestController
@Api(tags = "CRUD de produtos")
public class ProdutosController {
	CategoriasController pegaCategoria = new CategoriasController();
	Metodos metodos = new Metodos();

	@GetMapping("/produtos")
	@ApiOperation(value = "Retorna todos os produtos")
	public ArrayList<Produto> get() {
		ArrayList<Produto> produtos = new ArrayList<Produto>();

		Connection con = null;

		String url = "jdbc:mysql://localhost:3306/testejava?user=root&password=1234561";

		try {
			con = DriverManager.getConnection(url);

			String cm = "select * from produto";

			Statement comando = con.prepareStatement(cm);

			ResultSet retorno = comando.executeQuery(cm);

			while (retorno.next()) {
				Produto produto = new Produto();

				produto.setId(retorno.getInt("id"));
				produto.setDescricao(retorno.getString("descricao"));
				produto.setQuantidade(retorno.getDouble("quantidade"));
				produto.setUnidadeDeMedida(retorno.getString("unidadeDeMedida"));
				produto.setCategoriaId(retorno.getInt("categoriaId"));

				CompletableFuture<Categoria> categoria = CompletableFuture.supplyAsync(() -> {
					ResponseEntity<Categoria> categoriaEntity = (ResponseEntity<Categoria>) pegaCategoria.getId(produto.getCategoriaId());
				
					return categoriaEntity.getBody();
				});

				produto.setCateogoria(categoria.get());

				produtos.add(produto);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

			return produtos;
		} finally {
			metodos.fecharConexao(con);
		}

		return produtos;
	}

	@GetMapping("/produtos/id")
	@ApiOperation(value = "Retorna um produto")
	public Produto getId(int id) {
		Produto produto = new Produto();

		Connection con = null;

		String url = "jdbc:mysql://localhost:3306/testejava?user=root&password=1234561";

		try {
			con = DriverManager.getConnection(url);

			String cm = "select * from produto where id = ?";

			PreparedStatement comando = con.prepareStatement(cm);

			comando.setInt(1, id);

			ResultSet retorno = comando.executeQuery();

			if (retorno.next()) {
				produto.setId(retorno.getInt("id"));
				produto.setDescricao(retorno.getString("descricao"));
				produto.setQuantidade(retorno.getDouble("quantidade"));
				produto.setUnidadeDeMedida(retorno.getString("unidadeDeMedida"));
				produto.setCategoriaId(retorno.getInt("categoriaId"));

				CompletableFuture<Categoria> categoria = CompletableFuture.supplyAsync(() -> {
					ResponseEntity<Categoria> categoriaEntity = (ResponseEntity<Categoria>) pegaCategoria.getId(produto.getCategoriaId());
				
					return categoriaEntity.getBody();
				});
				
				produto.setCateogoria(categoria.get());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

			return produto;
		} finally {
			metodos.fecharConexao(con);
		}

		return produto;
	}

	@PostMapping("/produtos")
	@ApiOperation(value = "Insere um produto na tabela")
	public Produto post(String descricao, String unidadeDeMedida, double quantidade, int categoriaId) {
		Produto produto = new Produto();

		produto.setDescricao(descricao);
		produto.setCategoriaId(categoriaId);
		produto.setQuantidade(quantidade);
		produto.setUnidadeDeMedida(unidadeDeMedida);

		Connection con = null;

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
		} catch (Exception e) {
			System.out.println(e.getMessage());

			return produto;
		} finally {
			metodos.fecharConexao(con);
		}

		return produto;
	}

	@PutMapping("/produtos")
	@ApiOperation(value = "Altera um produto da tabela")
	public Produto put(String descricao, String unidadeDeMedida, double quantidade, int categoriaId, int id) {
		Produto produto = new Produto();

		produto.setDescricao(descricao);
		produto.setCategoriaId(categoriaId);
		produto.setQuantidade(quantidade);
		produto.setUnidadeDeMedida(unidadeDeMedida);
		produto.setId(id);

		Connection con = null;

		String url = "jdbc:mysql://localhost:3306/testejava?user=root&password=1234561";

		try {
			con = DriverManager.getConnection(url);

			String cm = "update produto set descricao = ?, quantidade = ?, unidadeDeMedida = ?, categoriaId = ? where id = ?";

			PreparedStatement comando = con.prepareStatement(cm);

			comando.setString(1, produto.getDescricao());
			comando.setDouble(2, produto.getQuantidade());
			comando.setString(3, produto.getUnidadeDeMedida());
			comando.setInt(4, produto.getCategoriaId());
			comando.setInt(5, produto.getId());

			comando.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());

			return produto;
		} finally {
			metodos.fecharConexao(con);
		}

		return produto;
	}

	@DeleteMapping("/produtos")
	@ApiOperation(value = "Deleta um produto da tabela")
	public void delete(int id) {
		Connection con = null;

		String url = "jdbc:mysql://localhost/testejava?user=root&password=1234561";

		try {
			con = DriverManager.getConnection(url);

			String cm = "delete from produto where id = ?";

			PreparedStatement comando = con.prepareStatement(cm);

			comando.setInt(1, id);

			comando.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			metodos.fecharConexao(con);
		}
	}
}