package com.app.facade;


public class ParticipaFacadeAndroid {

	private JogadorFacade jogador;

	private GrupoFacade grupo;

	private boolean confirma;

	public JogadorFacade getJogador() {
		return jogador;
	}

	public void setJogador(JogadorFacade jogador) {
		this.jogador = jogador;
	}

	public GrupoFacade getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoFacade grupo) {
		this.grupo = grupo;
	}

	public boolean isConfirma() {
		return confirma;
	}

	public void setConfirma(boolean confirma) {
		this.confirma = confirma;
	}
	
}