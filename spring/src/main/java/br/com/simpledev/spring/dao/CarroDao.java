package br.com.simpledev.spring.dao;

import java.util.List;

import br.com.simpledev.spring.model.Carro;

public interface CarroDao {

	List<Carro> lista();

	void adiciona(Carro carro);

	Carro busca(Long id);

	void atualiza(Carro carro);
}
