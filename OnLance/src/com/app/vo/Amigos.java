package com.app.vo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "amigos")
public class Amigos {

	@DatabaseField(columnName = "jogador_id", foreign = true, uniqueCombo = true)
	private Jogador jogador;

	@DatabaseField(columnName = "amigo_id", foreign = true, uniqueCombo = true)
	private Jogador amigo;

	public Amigos() {
	}

	public Amigos(Jogador jogador, Jogador amigo) {
		this.jogador = jogador;
		this.amigo = amigo;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public Jogador getAmigo() {
		return amigo;
	}

	public void setAmigo(Jogador amigo) {
		this.amigo = amigo;
	}
}
