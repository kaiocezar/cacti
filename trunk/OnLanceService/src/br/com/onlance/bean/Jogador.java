package br.com.onlance.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Jogador{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String login;

	private String nome;

	private String senha;

	private String email;

	private String fone;
	
	public Jogador(){
		historico = new Historico();
	}

	@Embedded
	private Historico historico;

	@OneToMany(mappedBy="jogador",cascade=CascadeType.ALL)
	private List<Participa> participa;
	
	@ManyToMany(cascade=CascadeType.ALL)
	private List<Evento> evento;
	

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public Historico getHistorico() {
		return historico;
	}

	public void setHistorico(Historico historico) {
		this.historico = historico;
	}
	
	public List<Participa> getParticipa() {
		return participa;
	}

	public void setParticipa(List<Participa> participa) {
		this.participa = participa;
	}

	public List<Evento> getEvento() {
		return evento;
	}

	public void setEvento(List<Evento> evento) {
		this.evento = evento;
	}

}
