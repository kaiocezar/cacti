package com.app.onlance;

import java.io.Serializable;

public class JogadorForList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8433437776337558195L;
	
	
	private String nome;
	private String tipoTela;

	public JogadorForList(){
		
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
	
}
