package br.ufc.quixada.entity;

public class Filme {
	private int id;
	private String nome;
	private String genero;
	
	public Filme (int id, String nome, String genero) {
		this.id = id;
		this.nome = nome;
		this.genero = genero;
	}
	
	public Filme() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	@Override
	public String toString() {
		return "Filme [Id = " + id + ", Nome = " + nome + ", Gênero = " + genero + "]";
	}
	
}
