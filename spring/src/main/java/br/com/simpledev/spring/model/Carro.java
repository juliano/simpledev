package br.com.simpledev.spring.model;

public class Carro {

	private Long id;
	private String nome;
	private Integer ano;

	public Carro() {
	}

	public Carro(final Long id, final String nome, final Integer ano) {
		this.id = id;
		this.nome = nome;
		this.ano = ano;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}
}
