package br.ufc.quixada.dao;

import java.util.List;

import br.ufc.quixada.entity.Cliente;

public interface ClienteDAO {
	public boolean adicionar(Cliente cliente);
	public boolean remover(String cpf);
	public List <Cliente> find();
	public Cliente find(String cpf);
	public boolean atualizar(String cpf, Cliente cliente);
}
