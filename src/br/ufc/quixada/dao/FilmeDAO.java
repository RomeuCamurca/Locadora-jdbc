package br.ufc.quixada.dao;

import java.util.List;
import br.ufc.quixada.entity.Filme;

public interface FilmeDAO {
	public boolean adicionar(Filme filme);
	public boolean remover(int id);
	public List <Filme> find();
	public Filme find(int id);
	public boolean atualizar(int id, Filme filme);
}
