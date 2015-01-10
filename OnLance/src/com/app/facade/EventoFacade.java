package com.app.facade;

import java.util.Date;
import java.util.List;

public class EventoFacade {

	private Integer id;

	private String local;

	private String nome;

	private Date data;
	
	private List<Integer> grupo;

	private List<Integer> jogador;

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<Integer> getGrupo() {
		return grupo;
	}

	public void setGrupo(List<Integer> grupo) {
		this.grupo = grupo;
	}

	public List<Integer> getJogador() {
		return jogador;
	}

	public void setJogador(List<Integer> jogador) {
		this.jogador = jogador;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

}
