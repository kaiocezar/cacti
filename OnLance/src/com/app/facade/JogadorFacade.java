package com.app.facade;

import java.util.List;

public class JogadorFacade {

	private Integer id;

	private String login;

	private String nome;

	private String senha;

	private String email;

	private String fone;

	public JogadorFacade(){
		historico = new HistoricoFacade();
	}
	
	private HistoricoFacade historico;

	private List<Integer> participa;
	
	private List<Integer> evento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public HistoricoFacade getHistorico() {
		return historico;
	}

	public void setHistorico(HistoricoFacade historico) {
		this.historico = historico;
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
