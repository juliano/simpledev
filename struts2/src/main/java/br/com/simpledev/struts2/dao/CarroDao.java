package br.com.simpledev.struts2.dao;

import java.util.List;

import br.com.simpledev.struts2.model.Carro;

public interface CarroDao {

	List<Carro> lista();

	void adiciona(Carro carro);
}
