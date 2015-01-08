package com.app.vo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="grupo_evento")
public class GrupoEvento {

	@DatabaseField(columnName = "id", id = true, generatedId = true)
	private Long id;
	
	@DatabaseField(columnName = "grupo_id", canBeNull = false, uniqueCombo = true)
	private Grupo grupo;

	@DatabaseField(columnName = "evento_id", canBeNull = false, uniqueCombo = true)
	private Evento evento;

	public GrupoEvento() {
	}
 
}
