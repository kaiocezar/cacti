package com.app.onlance;

import java.io.Serializable;

public class JogadorForList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8433437776337558195L;
	
	
	private int idJogador;
	private int idGrupo;
	private String nome;
	private String tipoTela;

	public JogadorForList(){
		tipoTela = "0";
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipoTela() {
		return tipoTela;
	}
	public void setTipoTela(String tipoTela) {
		this.tipoTela = tipoTela;
	}

	public int getIdJogador() {
		return idJogador;
	}

	public void setIdJogador(int idJogador) {
		this.idJogador = idJogador;
	}

	public int getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(int idGrupo) {
		this.idGrupo = idGrupo;
	}
}