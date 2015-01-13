package com.app.vo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "jogador")
public class Jogador {

	@DatabaseField(id = true, columnName = "id")
	private Integer id;

	@DatabaseField(columnName = "nome", canBeNull = false)
	private String nome;

	@DatabaseField(columnName = "senha", canBeNull = false)
	private String senha;

	@DatabaseField(columnName = "email", canBeNull = false)
	private String email;

	@DatabaseField(columnName = "foto", canBeNull = true)
	private byte[] foto;

	@DatabaseField(columnName = "foto", canBeNull = false)
	private String numeroTelefone;

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

	public Jogador() {
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

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	public int getQtdGol() {
		return qtdGol;
	}

	public void setQtdGol(int gol) {
		this.qtdGol = gol;
	}

	public int getQtdCartaoVermelho() {
		return qtdCartaoVermelho;
	}

	public void setQtdCartaoVermelho(int cartaoVermelho) {
		this.qtdCartaoVermelho = cartaoVermelho;
	}

	public int getQtdCartaoAmarelo() {
		return qtdCartaoAmarelo;
	}

	public void setQtdCartaoAmarelo(int cartaoAmarelo) {
		this.qtdCartaoAmarelo = cartaoAmarelo;
	}

	public int getQtdVitoria() {
		return qtdVitoria;
	}

	public void setQtdVitoria(int vitoria) {
		this.qtdVitoria = vitoria;
	}

	public int getQtdJogos() {
		return qtdJogo;
	}

	public void setQtdJogos(int jogos) {
		this.qtdJogo = jogos;
	}
}
