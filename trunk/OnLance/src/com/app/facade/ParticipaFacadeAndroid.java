package com.app.facade;
public class ParticipaFacadeAndroid {

	private JogadorFacade jogador;

	private GrupoFacade grupo;
	
	private int qtdGol;

	private int qtdCartaoVermelho;

	private int qtdCartaoAmarelo;

	private int qtdVitoria;

	private int qtdJogo;

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

	public int getQtdGol() {
		return qtdGol;
	}

	public void setQtdGol(int qtdGol) {
		this.qtdGol = qtdGol;
	}

	public int getQtdCartaoVermelho() {
		return qtdCartaoVermelho;
	}

	public void setQtdCartaoVermelho(int qtdCartaoVermelho) {
		this.qtdCartaoVermelho = qtdCartaoVermelho;
	}

	public int getQtdCartaoAmarelo() {
		return qtdCartaoAmarelo;
	}

	public void setQtdCartaoAmarelo(int qtdCartaoAmarelo) {
		this.qtdCartaoAmarelo = qtdCartaoAmarelo;
	}

	public int getQtdVitoria() {
		return qtdVitoria;
	}

	public void setQtdVitoria(int qtdVitoria) {
		this.qtdVitoria = qtdVitoria;
	}

	public int getQtdJogo() {
		return qtdJogo;
	}

	public void setQtdJogo(int qtdJogo) {
		this.qtdJogo = qtdJogo;
	}
	
}