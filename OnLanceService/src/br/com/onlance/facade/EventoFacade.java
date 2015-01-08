package br.com.onlance.facade;

import java.util.Date;
import java.util.List;

public class EventoFacade {

	private int id;

	private String local;

	private String nome;

	private Date data;
	
	private List<GrupoFacade> grupo;

	private List<JogadorFacade> jogador;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public List<GrupoFacade> getGrupo() {
		return grupo;
	}

	public void setGrupo(List<GrupoFacade> grupo) {
		this.grupo = grupo;
	}

	public List<JogadorFacade> getJogador() {
		return jogador;
	}

	public void setJogador(List<JogadorFacade> jogador) {
		this.jogador = jogador;
	}	
}
