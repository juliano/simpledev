package br.com.simpledev.springhibernate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.simpledev.springhibernate.dao.CarroDao;
import br.com.simpledev.springhibernate.model.Carro;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CarroDao dao = context.getBean(CarroDao.class);
		
		Carro carro = new Carro("Ferrari", 2013);
		
		dao.adiciona(carro);
		System.out.println(dao.lista());
	}
}
