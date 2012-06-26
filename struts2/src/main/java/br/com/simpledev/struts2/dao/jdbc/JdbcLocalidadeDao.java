package br.com.simpledev.struts2.dao.jdbc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.simpledev.struts2.dao.LocalidadeDao;

public class JdbcLocalidadeDao implements LocalidadeDao {

	private List<String> estados;
	private Map<String, List<String>> cidades;

	public JdbcLocalidadeDao() {
		estados = Arrays.asList("São Paulo", "Minas Gerais");
		cidades = new HashMap<String, List<String>>();
		cidades.put("São Paulo",
				Arrays.asList("Sorocaba", "Guarulhos", "Santos"));
		cidades.put("Minas Gerais",
				Arrays.asList("Varginha", "Belo Horizonte", "Pouso Alegre"));
	}

	public List<String> listaEstados() {
		return estados;
	}

	public List<String> listaCidades(String estado) {
		return cidades.get(estado);
	}
}
