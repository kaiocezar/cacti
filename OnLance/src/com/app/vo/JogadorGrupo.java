package com.app.vo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "jogador_grupo")
public class JogadorGrupo {

	@DatabaseField(columnName = "id", id = true, generatedId = true)
	private Long id;

	@DatabaseField(columnName = "jogador_id", canBeNull = false, uniqueCombo = true)
	private Jogador jogador;

	@DatabaseField(columnName = "grupo_id", canBeNull = false, uniqueCombo = true)
	private Grupo grupo;

	@DatabaseField(columnName = "qtdGol", canBeNull = true)
	private int qtdGol;

	@DatabaseField(columnName = "qtdCartaoVermelho", canBeNull = true)
	private int qtdCartaoVermelho;

	@DatabaseField(columnName = "qtdCartaoAmarelo", canBeNull = true)
	private int qtdCartaoAmarelo;

	@DatabaseField(columnName = "qtdVitoria", canBeNull = true)
	private int qtdVitoria;

	@DatabaseField(columnName = "qtdJogo", canBeNull = true)
	private int qtdJogo;
}
