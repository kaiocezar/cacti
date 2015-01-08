package com.app.vo;

import com.j256.ormlite.field.DatabaseField;

public class Grupo {

	@DatabaseField(id = true, generatedId = true, columnName = "id")
	private int id;

	@DatabaseField(columnName = "nome", canBeNull = false)
	private String nome;

	@DatabaseField(columnName = "descricao", canBeNull = true)
	private String descricao;

	@DatabaseField(columnName = "imagem", canBeNull = true)
	private byte[] imagem;

	public Grupo() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
}
