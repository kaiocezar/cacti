package com.app.vo;

import com.j256.ormlite.field.DatabaseField;

import android.provider.ContactsContract.Contacts.Data;

public class Evento {

	@DatabaseField(id = true, generatedId = true, columnName = "id")
	private int id;

	@DatabaseField(columnName = "localizacao", canBeNull = true)
	private String localizacao;

	@DatabaseField(columnName = "data", canBeNull = false)
	private Data data;

	@DatabaseField(columnName = "nome", canBeNull = false)
	private String nome;

	public Evento() {
	}
}
