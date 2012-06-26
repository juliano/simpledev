package br.com.simpledev.spring.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping(value = "/novo", method = GET)
	public ModelAndView novo() {
		return new ModelAndView("carro/novo", "carro", new Carro());
	}

	@RequestMapping(value = "/novo", method = POST)
	public String novo(final Carro carro) {
		dao.adiciona(carro);
		return "redirect:lista";
	}

	@RequestMapping(value = "/editar/{id}", method = GET)
	public ModelAndView editar(@PathVariable Long id) {
		Carro carro = dao.busca(id);
		return new ModelAndView("carro/editar", "carro", carro);
	}

	@RequestMapping(value = "/editar", method = PUT)
	public String editar(final Carro carro) {
		dao.atualiza(carro);
		return "redirect:lista";
	}
}
