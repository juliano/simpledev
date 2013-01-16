package br.com.simpledev.springhibernate.dao.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.simpledev.springhibernate.dao.CarroDao;
import br.com.simpledev.springhibernate.model.Carro;

@Repository
public class HibernateCarroDao implements CarroDao {

	private final SessionFactory factory;

	@Autowired
	public HibernateCarroDao(final SessionFactory factory) {
		this.factory = factory;
	}

	@Transactional(readOnly = true)
	public List<Carro> lista() {
		return factory.getCurrentSession().createCriteria(Carro.class).list();
	}

	@Transactional
	public void adiciona(final Carro carro) {
		factory.getCurrentSession().save(carro);
	}
}
