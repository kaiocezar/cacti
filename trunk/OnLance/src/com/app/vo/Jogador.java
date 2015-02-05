package com.app.vo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "jogador")
public class Jogador {

	@DatabaseField(id = true)
	private Integer id;

	@DatabaseField
	private String nome;

	@DatabaseField
	private String senha;

	@DatabaseField(uniqueIndex = true)
	private String email;

	// @DatabaseField
	// private byte[] foto;

	@DatabaseField(uniqueIndex = true)
	private String numeroTelefone;

	public Jogador() {
	}

	public Jogador(Integer id, String nome, String senha, String email,
			byte[] foto, String numeroTelefone) {
		this.id = id;
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		//this.foto = foto;
		this.numeroTelefone = numeroTelefone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/*
	 * public byte[] getFoto() { return foto; }
	 * 
	 * public void setFoto(byte[] foto) { this.foto = foto; }
	 */

	public String getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}
}
