package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import JWT.GeraToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import metodosUteis.Metodos;
import model.Usuario;

@RestController
@Api(tags = "CRUD de usuários")
public class UsuariosController {
	Metodos metodos = new Metodos();

	GeraToken geradorDeTokens = new GeraToken();

	@GetMapping("/usuarios")
	@ApiOperation(value = "Retorna todos os usuários")
	public ArrayList<Usuario> get() {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

		Connection con = null;

		String url = "jdbc:mysql://localhost:3306/testejava?user=root&password=1234561";

		try {
			con = DriverManager.getConnection(url);

			String cm = "select * from usuario";

			Statement comando = con.prepareStatement(cm);

			ResultSet retorno = comando.executeQuery(cm);

			while (retorno.next()) {
				Usuario usuario = new Usuario();

				usuario.setId(retorno.getInt("id"));
				usuario.setUsuario(retorno.getString("user"));
				usuario.setPassword(retorno.getString("password"));
				usuario.setRole(retorno.getString("role"));

				usuarios.add(usuario);
			}
		} catch (Exception e) {
			return usuarios;
		} finally {
			metodos.fecharConexao(con);
		}

		return usuarios;
	}

	@GetMapping("/usuarios/id")
	@ApiOperation(value = "Retorna um usuário")
	public Usuario getId(int id) {
		Usuario usuario = new Usuario();

		Connection con = null;

		String url = "jdbc:mysql://localhost:3306/testejava?user=root&password=1234561";

		try {
			con = DriverManager.getConnection(url);

			String cm = "select * from usuario where id = ?";

			PreparedStatement comando = con.prepareStatement(cm);

			comando.setInt(1, id);

			ResultSet retorno = comando.executeQuery();

			if (retorno.next()) {
				usuario.setId(retorno.getInt("id"));
				usuario.setUsuario(retorno.getString("user"));
				usuario.setPassword(retorno.getString("password"));
				usuario.setRole(retorno.getString("role"));
			}
		} catch (Exception e) {
			return usuario;
		} finally {
			metodos.fecharConexao(con);
		}

		return usuario;
	}

	@PostMapping("/usuarios")
	@ApiOperation(value = "Insere um usuário na tablea")
	public Usuario post(String user, String password, String role) {
		Usuario usuario = new Usuario();

		Connection con = null;

		String url = "jdbc:mysql://localhost:3306/testejava?user=root&password=1234561";

		try {
			con = DriverManager.getConnection(url);

			usuario.setPassword(password);
			usuario.setRole(role);
			usuario.setUsuario(user);

			String cm = "insert into usuario(user, password, role)values(?, ?, ?)";

			PreparedStatement comando = con.prepareStatement(cm);

			comando.setString(1, usuario.getUsuario());
			comando.setString(2, usuario.getPassword());
			comando.setString(3, usuario.getRole());

			comando.execute();
		} catch (Exception e) {
			return usuario;
		} finally {
			metodos.fecharConexao(con);
		}

		return usuario;
	}

	@PostMapping("/usuarios/login")
	@ApiOperation(value = "Faz o login do usuário")
	public String login(String user, String password) {
		Usuario usuario = new Usuario();

		Connection con = null;

		String token = "";

		String url = "jdbc:mysql://localhost:3306/testejava?user=root&password=1234561";

		try {
			con = DriverManager.getConnection(url);

			usuario.setPassword(password);
			usuario.setUsuario(user);

			String cm = "select * from usuario where user = ? and password = ?";

			PreparedStatement comando = con.prepareStatement(cm);

			comando.setString(1, usuario.getUsuario());
			comando.setString(2, usuario.getPassword());

			ResultSet retorno = comando.executeQuery();

			if (retorno.next()) {
				token = geradorDeTokens.retornaToken(retorno.getString("role"));

				return token;
			}
		} catch (Exception e) {
			return e.getMessage();
		} finally {
			metodos.fecharConexao(con);
		}

		return token;
	}

	@PutMapping("/usuarios")
	@ApiOperation(value = "Altera um usuário da tablea")
	public Usuario put(String user, String password, String role, int id) {
		Usuario usuario = new Usuario();

		Connection con = null;

		String url = "jdbc:mysql://localhost:3306/testejava?user=root&password=1234561";

		try {
			con = DriverManager.getConnection(url);

			usuario.setId(id);
			usuario.setPassword(password);
			usuario.setRole(role);
			usuario.setUsuario(user);

			String cm = "update usuario set user = ?, password = ?, role = ? where id = ?";

			PreparedStatement comando = con.prepareStatement(cm);

			comando.setString(1, usuario.getUsuario());
			comando.setString(2, usuario.getPassword());
			comando.setString(3, usuario.getRole());
			comando.setInt(4, usuario.getId());

			comando.execute();
		} catch (Exception e) {
			return usuario;
		} finally {
			metodos.fecharConexao(con);
		}

		return usuario;
	}

	@DeleteMapping("/usuarios")
	@ApiOperation(value = "Deleta um usuário da tablea")
	public void delete(int id) {
		Connection con = null;

		String url = "jdbc:mysql://localhost:3306/testejava?user=root&password=1234561";

		try {
			con = DriverManager.getConnection(url);

			String cm = "delete from usuario where id = ?";

			PreparedStatement comando = con.prepareStatement(cm);

			comando.setInt(1, id);

			comando.execute();
		} catch (Exception e) {
			System.out.println("Erro no deletamento de usuário!");
		} finally {
			metodos.fecharConexao(con);
		}
	}
}