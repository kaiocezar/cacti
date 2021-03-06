package com.app.vo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "administra")
public class Administra {

	@DatabaseField(columnName = "jogador_id", foreign = true, uniqueCombo = true)
	private Jogador jogador;

	@DatabaseField(columnName = "grupo_id", foreign = true, uniqueCombo = true)
	private Grupo grupo;

	public Administra() {
	}

	public Administra(Jogador jogador, Grupo grupo) {
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
}
