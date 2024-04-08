package ConexaoAoBancoDeDados;

public class Categoria {
	public int Id = 0;
	public String Nome;
	
	public String escrever() {
		String retorno = String.format("%d - %s", this.Id, this.Nome);
		
		return retorno;
	}
}