package br.com.simpledev.struts2.dao;

import java.util.List;

public interface LocalidadeDao {

	List<String> listaEstados();

	List<String> listaCidades(String estado);
}
