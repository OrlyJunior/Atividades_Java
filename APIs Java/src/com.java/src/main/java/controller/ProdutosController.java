package controller;

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
					return pegaCategoria.getId(produto.getCategoriaId());
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
}
