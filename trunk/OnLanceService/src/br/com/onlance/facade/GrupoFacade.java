package br.com.onlance.facade;

import java.util.List;

public class GrupoFacade {

	private Integer id;
	
	private String nome;
	
	private String descricao;
	
	private List<Integer> participa;
	
	private List<Integer> evento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Integer> getParticipa() {
		return participa;
	}

	public void setParticipa(List<Integer> participa) {
		this.participa = participa;
	}

	public List<Integer> getEvento() {
		return evento;
	}

	public void setEvento(List<Integer> evento) {
		this.evento = evento;
	}

	
}
