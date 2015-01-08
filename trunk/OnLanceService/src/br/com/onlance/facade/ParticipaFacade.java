package br.com.onlance.facade;


public class ParticipaFacade {

	private int id;
	
	private JogadorFacade jogador;

	private GrupoFacade grupo;

	private HistoricoFacade historico;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public JogadorFacade getJogador() {
		return jogador;
	}

	public void setJogador(JogadorFacade jogador) {
		this.jogador = jogador;
	}

	public GrupoFacade getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoFacade grupo) {
		this.grupo = grupo;
	}

	public HistoricoFacade getHistorico() {
		return historico;
	}

	public void setHistorico(HistoricoFacade historico) {
		this.historico = historico;
	}
	
}
