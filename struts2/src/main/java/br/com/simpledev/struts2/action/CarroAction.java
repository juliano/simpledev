package br.com.simpledev.struts2.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import br.com.simpledev.struts2.dao.CarroDao;
import br.com.simpledev.struts2.dao.jdbc.JdbcCarroDao;
import br.com.simpledev.struts2.model.Carro;

@Namespace(value = "/carro")
public class CarroAction {

	private final CarroDao dao;

	private List<Carro> carros;

	private Carro carro;

	public CarroAction() {
		this.dao = new JdbcCarroDao();
	}

	public CarroAction(CarroDao dao) {
		this.dao = dao;
	}

	@Action(value = "lista", results = @Result(name = "ok", location = "/carro/lista.jsp"))
	public String lista() {
		carros = dao.lista();
		return "ok";
	}

	@Action(value = "novo", results = @Result(name = "ok", location = "/carro/adiciona.jsp"))
	public String novo() {
		return "ok";
	}

	@Action(value = "adiciona", results = @Result(name = "ok", type = "redirectAction", params = {
			"actionName", "lista" }))
	public String adiciona() {
		dao.adiciona(carro);
		return "ok";
	}

	public List<Carro> getCarros() {
		return carros;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(final Carro carro) {
		this.carro = carro;
	}
}
