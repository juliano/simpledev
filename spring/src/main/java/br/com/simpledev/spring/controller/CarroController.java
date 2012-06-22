package br.com.simpledev.spring.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.simpledev.spring.dao.CarroDao;
import br.com.simpledev.spring.model.Carro;

@Controller
@RequestMapping("/carro")
public class CarroController {

	private final CarroDao dao;

	@Autowired
	public CarroController(final CarroDao dao) {
		this.dao = dao;
	}

	@RequestMapping(value = "/lista", method = GET)
	public List<Carro> lista() {
		return dao.lista();
	}
	
	@RequestMapping(value="/novo", method = GET)
	public ModelAndView novo() {
		return new ModelAndView("carro/novo", "carro", new Carro());
	}
}
