package br.ufc.quixada.entity;

public class Cliente {
	private String cpf;
	private String nome;
	private int idade;
	private char sexo;
	
	public Cliente() {
		super();
	}

	public Cliente(String cpf, String nome, int idade, char sexo) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.idade = idade;
		this.sexo = sexo;
		
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		return "Cliente [CPF = " + cpf + ", Nome = " + nome + ", Idade = " + idade + ", Sexo = " + sexo + "]";
	}

}
