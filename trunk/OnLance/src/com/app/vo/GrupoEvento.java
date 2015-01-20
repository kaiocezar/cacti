package com.app.vo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "grupo_evento")
public class GrupoEvento {

	@DatabaseField(columnName = "grupo_id", foreign = true, uniqueCombo = true)
	private Grupo grupo;

	@DatabaseField(columnName = "evento_id", foreign = true, uniqueCombo = true)
	private Evento evento;

	public GrupoEvento() {
	}
}
