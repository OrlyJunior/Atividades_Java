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
			
			while(retorno.next()) {
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
				}catch (InterruptedException | ExecutionException e) {
				    System.out.println(e.getMessage());
				}
				
				System.out.println(produto.escrever());
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			fecharConexao(con);
		}
		
		return con;
	}
}
