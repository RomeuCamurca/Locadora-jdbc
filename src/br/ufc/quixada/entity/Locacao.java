package br.ufc.quixada.entity;

public class Locacao {
	private int id_filme;
	private String cpf_cliente;
	private String data_locacao;
	private String data_devolucao;
	
	public Locacao(int id_filme, String cpf_cliente, String data_locacao, String data_devolucao) {
		super();
		this.id_filme = id_filme;
		this.cpf_cliente = cpf_cliente;
		this.data_locacao = data_locacao;
		this.data_devolucao = data_devolucao;
	}
	
	public Locacao() {
		super();
	}

	public int getId_filme() {
		return id_filme;
	}
	public void setId_filme(int id_filme) {
		this.id_filme = id_filme;
	}
	public String getCpf_cliente() {
		return cpf_cliente;
	}
	public void setCpf_cliente(String cpf_cliente) {
		this.cpf_cliente = cpf_cliente;
	}
	public String getData_locacao() {
		return data_locacao;
	}
	public void setData_locacao(String data_locacao) {
		this.data_locacao = data_locacao;
	}
	public String getData_devolucao() {
		return data_devolucao;
	}
	public void setData_devolucao(String data_devolucao) {
		this.data_devolucao = data_devolucao;
	}

	@Override
	public String toString() {
		return "Locação [id Do Filme = " + id_filme + ", CPF Do Cliente = " + cpf_cliente + ", Data Da Locação = " + data_locacao
				+ ", Data Da Devolução = " + data_devolucao + "]\n";
	}

}
