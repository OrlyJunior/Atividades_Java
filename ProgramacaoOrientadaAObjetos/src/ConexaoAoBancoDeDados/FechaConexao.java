package ConexaoAoBancoDeDados;

import java.sql.Connection;

public class FechaConexao {
	protected void fecharConexao(Connection con) {
		try {
			con.close();
		} catch (final Exception e) {
			System.out.println("Impossível fechar conexão!");
		}
	}
}