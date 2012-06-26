package br.com.simpledev.struts2.model;

public class Carro {

	private final Long id;
	private final String nome;
	private final Integer ano;

	public Carro(final Long id, final String nome, final Integer ano) {
		this.id = id;
		this.nome = nome;
		this.ano = ano;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Integer getAno() {
		return ano;
	}
}
