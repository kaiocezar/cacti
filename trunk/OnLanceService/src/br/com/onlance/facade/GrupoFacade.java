package br.com.onlance.facade;

import java.util.List;

public class GrupoFacade {

	private int id;
	
	private String nome;
	
	private String descricao;
	
	private List<ParticipaFacade> participa;
	
	private List<EventoFacade> evento;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public List<ParticipaFacade> getParticipa() {
		return participa;
	}

	public void setParticipa(List<ParticipaFacade> participa) {
		this.participa = participa;
	}

	public List<EventoFacade> getEvento() {
		return evento;
	}

	public void setEvento(List<EventoFacade> evento) {
		this.evento = evento;
	}
	
}
