package metodosUteis;

import java.sql.Connection;

public class Metodos {
	public void fecharConexao(Connection con) {
		try {
			con.close();
		} catch (final Exception e) {
			System.out.println("Impossível fechar conexão!");
		}
	}
}
