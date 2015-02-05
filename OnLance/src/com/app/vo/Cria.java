package com.app.vo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "jogador_evento")
public class Cria {

	@DatabaseField(columnName = "jogador_id", foreign = true, uniqueCombo = true)
	private Jogador jogador;

	@DatabaseField(columnName = "evento_id", foreign = true, uniqueCombo = true)
	private Evento evento;

	public Cria() {
	}

	public Cria(Jogador jogador, Evento evento) {
		this.jogador = jogador;
		this.evento = evento;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
}
