package com.app.vo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "jogador_evento")
public class JogadorEvento {

	@DatabaseField(columnName = "id", id = true, generatedId = true)
	private Long id;

	@DatabaseField(columnName = "jogador_id", canBeNull = false, uniqueCombo = true)
	private Jogador jogador;

	@DatabaseField(columnName = "evento_id", canBeNull = false, uniqueCombo = true)
	private Evento evento;

	public JogadorEvento() {
	}
}
