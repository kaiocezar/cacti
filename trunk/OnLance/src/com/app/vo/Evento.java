package com.app.vo;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "evento")
public class Evento {

	@DatabaseField(id = true)
	private int id;

	@DatabaseField
	private String localizacao;

	@DatabaseField
	private Date data;

	@DatabaseField
	private String nome;

	public Evento() {
	}

	public Evento(String localizacao, Date data, String nome) {
		this.localizacao = localizacao;
		this.data = data;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
