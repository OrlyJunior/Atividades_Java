package model;

public class Produto {
	private int Id;
	private String Descricao;
	private double Quantidade;
	private String UnidadeDeMedida;
	private int CategoriaId;
	private Categoria Cateogoria;

	public double getQuantidade() {
		return Quantidade;
	}

	public void setQuantidade(double quantidade) {
		Quantidade = quantidade;
	}

	public String getUnidadeDeMedida() {
		return UnidadeDeMedida;
	}

	public void setUnidadeDeMedida(String unidadeDeMedida) {
		UnidadeDeMedida = unidadeDeMedida;
	}

	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String descricao) {
		Descricao = descricao;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Categoria getCateogoria() {
		return Cateogoria;
	}

	public void setCateogoria(Categoria cateogoria) {
		Cateogoria = cateogoria;
	}

	public int getCategoriaId() {
		return CategoriaId;
	}

	public void setCategoriaId(int categoriaId) {
		CategoriaId = categoriaId;
	}
}
