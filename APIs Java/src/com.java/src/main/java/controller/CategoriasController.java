package controller;

import org.springframework.http.HttpStatus;
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
import java.util.Collections;

@RestController
@Api(tags = "CRUD de categorias")
public class CategoriasController {
	Metodos metodos = new Metodos();

	@GetMapping("/categorias")
	@ApiOperation(value = "Retorna todas as categorias")
	public ArrayList<Categoria> get() {
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();

		Connection con = null;

		String url = "jdbc:mysql://localhost:3306/testejava?user=root&password=1234561";

		try {
			con = DriverManager.getConnection(url);

			String cm = "select * from categoria";

			Statement comando = con.prepareStatement(cm);

			ResultSet retorno = comando.executeQuery(cm);

			while (retorno.next()) {
				Categoria categoria = new Categoria();

				categoria.setId(retorno.getInt("id"));
				categoria.setNome(retorno.getString("nome"));

				categorias.add(categoria);
			}
		} catch (Exception e) {
			return categorias;
		} finally {
			metodos.fecharConexao(con);
		}

		return categorias;
	}

	@GetMapping("/categorias/id")
	@ApiOperation(value = "Retorna uma categoria")
	public ResponseEntity<?> getId(int id) {
		Connection con = null;

		Categoria categoria = new Categoria();

		String url = "jdbc:mysql://localhost:3306/testejava?user=root&password=1234561";

		try {
			con = DriverManager.getConnection(url);

			String cm = "select * from categoria where id = ?";

			PreparedStatement comando = con.prepareStatement(cm);

			comando.setInt(1, id);

			ResultSet retorno = comando.executeQuery();

			if (retorno.next()) {
				categoria.setId(retorno.getInt("id"));
				categoria.setNome(retorno.getString("nome"));
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Collections.singletonMap("erro", e.getMessage()));
		} finally {
			metodos.fecharConexao(con);
		}

		return ResponseEntity.ok(categoria);
	}

	@PostMapping("/categorias")
	@ApiOperation(value = "Adiciona uma categoria na tabela")
	public Categoria post(Categoria categoria) {
		Connection con = null;

		categoria.setId(0);

		String url = "jdbc:mysql://localhost:3306/testejava?user=root&password=1234561";

		try {
			con = DriverManager.getConnection(url);

			String cm = "insert into categoria(nome)values(?)";

			PreparedStatement comando = con.prepareStatement(cm);

			comando.setString(1, categoria.getNome());

			comando.execute();
		} catch (Exception e) {
			return categoria;
		} finally {
			metodos.fecharConexao(con);
		}

		return categoria;
	}

	@PutMapping("/categorias")
	@ApiOperation(value = "Altera uma categoria da tabela")
	public Categoria put(Categoria categoria) {
		Connection con = null;

		String url = "jdbc:mysql://localhost:3306/testejava?user=root&password=1234561";

		try {
			con = DriverManager.getConnection(url);

			String cm = "update categoria set nome = ? where id = ?";

			PreparedStatement comando = con.prepareStatement(cm);

			comando.setString(1, categoria.getNome());
			comando.setInt(2, categoria.getId());

			comando.execute();
		} catch (Exception e) {
			return categoria;
		} finally {
			metodos.fecharConexao(con);
		}
		return categoria;
	}

	@DeleteMapping("/categorias")
	@ApiOperation(value = "Deleta uma categoria da tabela")
	public void delete(int id) {
		Connection con = null;

		String url = "jdbc:mysql://localhost:3306/testejava?user=root&password=1234561";

		try {
			con = DriverManager.getConnection(url);

			String cm = "delete from categoria where id = ?";

			PreparedStatement comando = con.prepareStatement(cm);

			comando.setInt(1, id);

			comando.execute();
		} catch (Exception e) {

		} finally {
			metodos.fecharConexao(con);
		}
	}
}