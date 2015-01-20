package com.app.vo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "jogador_grupo")
public class JogadorGrupo {

	@DatabaseField(columnName = "jogador_id", foreign = true, uniqueCombo = true)
	private Jogador jogador;

	@DatabaseField(columnName = "grupo_id", foreign = true, uniqueCombo = true)
	private Grupo grupo;

	@DatabaseField
	private int qtdGol;

	@DatabaseField
	private int qtdCartaoVermelho;

	@DatabaseField
	private int qtdCartaoAmarelo;

	@DatabaseField
	private int qtdVitoria;

	@DatabaseField
	private int qtdJogo;

	public JogadorGrupo() {
	}

	public JogadorGrupo(Jogador jogador, Grupo grupo) {
		this.jogador = jogador;
		this.grupo = grupo;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
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
