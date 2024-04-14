package controller;

import org.springframework.context.annotation.ComponentScan;
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

@RestController
@ComponentScan(basePackages = { "com.java", "Controllers", "Configurations, model" })
@Api(tags = "Contorller para testes")
public class testeController {
	Metodos metodos = new Metodos();

	@GetMapping
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
	
	@GetMapping("/id")
	@ApiOperation(value = "Retorna todas as categorias")
	public Categoria getId(int id) {
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
			return categoria;
		} finally {
			metodos.fecharConexao(con);
		}

		return categoria;
	}

	@PostMapping
	@ApiOperation(value = "Adiciona categorias")
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

	@PutMapping
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

	@DeleteMapping
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