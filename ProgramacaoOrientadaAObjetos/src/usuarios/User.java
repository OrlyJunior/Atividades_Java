package usuarios;

public class User {
	private int id;
	private String nome;
	private String senha;
	private boolean ativo;
	
	public int getId() {
		return this.id;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getSenha() {
		return this.senha;
	}
	
	public boolean getAtivo() {
		return this.ativo;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public String toString() {
		String texto = "Id: " + id + " Nome: " + nome + " Senha: " + senha + " Ativo: " + ativo;
		
		return texto;
	}
}