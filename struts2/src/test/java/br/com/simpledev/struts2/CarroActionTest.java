package br.com.simpledev.struts2;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.simpledev.struts2.action.CarroAction;
import br.com.simpledev.struts2.dao.CarroDao;
import br.com.simpledev.struts2.model.Carro;

public class CarroActionTest {

	private CarroAction action;

	@Mock
	private CarroDao dao;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		action = new CarroAction(dao);
	}

	@Test
	public void deveListarTodosOsCarros() {
		when(dao.lista()).thenReturn(Collections.singletonList(new Carro(1L, "Camaro", 2012)));
		action.lista();
		List<Carro> carros = action.getCarros();
		assertFalse(carros.isEmpty());
	}

	@Test
	public void deveAdicionarCarroValido() {
		action.setCarro(new Carro(1L, "Camaro", 2012));
		action.adiciona();
	}
}
