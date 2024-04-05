package ConexaoAoBancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao{
	public Connection conecta() {
		Connection con = null;
		
		try {
			String url = "jdbc:mysql://localhost:3306/testejava?user=root&password=1234561";
			
			con = DriverManager.getConnection(url);
			
			String cm = "select * from categoria";
			
			Statement comando = con.prepareStatement(cm);
			
			ResultSet retorno = comando.executeQuery(cm);
			
			while(retorno.next()) {
				System.out.println(retorno.getString("nome"));
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return con;
	}
}

