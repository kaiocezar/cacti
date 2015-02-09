package com.app.vo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "grupo")
public class Grupo {

	@DatabaseField(id = true)
	private int id;

	@DatabaseField
	private String nome;

	@DatabaseField
	private String descricao;

	/*
	 * @DatabaseField private byte[] imagem;
	 */

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

	/*
	 * public byte[] getImagem() { return imagem; }
	 * 
	 * public void setImagem(byte[] imagem) { this.imagem = imagem; }
	 */
}
