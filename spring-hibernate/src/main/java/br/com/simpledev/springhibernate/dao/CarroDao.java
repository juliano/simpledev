package br.com.simpledev.springhibernate.dao;

import java.util.List;

import br.com.simpledev.springhibernate.model.Carro;

public interface CarroDao {

    List<Carro> lista();

    void adiciona(Carro carro);
}