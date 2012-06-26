package br.com.simpledev.struts2.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import br.com.simpledev.struts2.dao.CarroDao;
import br.com.simpledev.struts2.model.Carro;

public class JdbcCarroDao implements CarroDao {

	private static final List<Carro> database = new ArrayList<Carro>();

	static {
		database.add(new Carro(1L, "Camaro", 2012));
		database.add(new Carro(2L, "Mustang", 2011));
	}

	public List<Carro> lista() {
		return database;
	}

	public void adiciona(final Carro carro) {
		carro.setId((long) database.size() + 1);
		database.add(carro);
	}
}
