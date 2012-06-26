package br.com.simpledev.struts2spring.dao;

import java.util.List;

import br.com.simpledev.struts2spring.model.Carro;

public interface CarroDao {

	List<Carro> lista();

	void adiciona(Carro carro);
}
