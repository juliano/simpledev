package br.com.simpledev.struts2spring.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.simpledev.struts2spring.dao.CarroDao;
import br.com.simpledev.struts2spring.model.Carro;

@Repository
public class JdbcCarroDao implements CarroDao {

	private final List<Carro> database = new ArrayList<Carro>();

	public JdbcCarroDao() {
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
