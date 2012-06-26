package br.com.simpledev.struts2.dao.jdbc;

import java.util.Arrays;
import java.util.List;

import br.com.simpledev.struts2.dao.CarroDao;
import br.com.simpledev.struts2.model.Carro;

public class JdbcCarroDao implements CarroDao {

	public List<Carro> lista() {
		return Arrays.asList(new Carro(1L, "Corsa", 2012), new Carro(2L, "Montana", 2011));
	}

	public void adiciona(final Carro carro) {
	}
}
