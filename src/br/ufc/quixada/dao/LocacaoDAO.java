package br.ufc.quixada.dao;

import java.util.List;

import br.ufc.quixada.entity.Cliente;
import br.ufc.quixada.entity.Filme;
import br.ufc.quixada.entity.Locacao;

@SuppressWarnings("unused")
public interface LocacaoDAO {
	public boolean alugarFilme(Locacao locacao); //ok
	public boolean removerFilme(int id_filme, String cpf_cliente);//ok
	public List <Filme> find(); //FILMES DE LOCACAO //ok
	public List <Filme> find(String cpf_cliente); // mostrar todos os filmes alugados por um cliente ok
	public Filme find(int id);//ok
	
}
