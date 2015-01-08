package br.com.onlance.utils;

import java.util.ArrayList;
import java.util.List;

import br.com.onlance.bean.Evento;
import br.com.onlance.bean.Grupo;
import br.com.onlance.bean.Historico;
import br.com.onlance.bean.Jogador;
import br.com.onlance.facade.EventoFacade;
import br.com.onlance.facade.GrupoFacade;
import br.com.onlance.facade.HistoricoFacade;
import br.com.onlance.facade.JogadorFacade;

public class ParserAdapter {

	private ParserAdapter adapter;
	
	private ParserAdapter(){
		
	}
	
	public ParserAdapter getInstance(){
		if(adapter == null){
			adapter = new ParserAdapter();
		}
		
		return adapter;
	}
	
	
	
	public JogadorFacade getFacade(Jogador jogador){
		JogadorFacade retorno = null;
		
		if(jogador != null){
			retorno = new JogadorFacade();
			
			retorno.setEmail(jogador.getEmail());
			retorno.setFone(jogador.getFone());
			retorno.setId(jogador.getId());
			retorno.setLogin(jogador.getLogin());
			retorno.setNome(jogador.getNome());
			retorno.setSenha(jogador.getSenha());
			retorno.setHistorico(getFacade(jogador.getHistorico()));
			retorno.setEvento(getFacadeEvento(jogador.getEvento()));
			
		}
		
		
		return retorno;
	}

	private List<EventoFacade> getFacadeEvento(List<Evento> evento) {

		List<EventoFacade> retorno = null;
		
		
		if(evento != null && evento.size() >0){
			
			
			retorno = new ArrayList<EventoFacade>();
			for (Evento var : evento) {
				retorno.add(getFacade(var));
				
			}
		}
		
		
		return retorno;
	}

	private EventoFacade getFacade(Evento var) {

		EventoFacade retorno = null;
		
		if(var != null){
			retorno = new EventoFacade();
			retorno.setData(var.getData());
			retorno.setGrupo(getFacadeGrupo(var.getGrupo()));
			retorno.setId(var.getId());
		}
		
		return retorno;
	}

	private List<GrupoFacade> getFacadeGrupo(List<Grupo> grupo) {

		
		
		return null;
	}

	private HistoricoFacade getFacade(Historico historico) {
		HistoricoFacade retorno = null;
		
		if(historico != null){
			retorno = new HistoricoFacade();
			retorno.setCartaoAmarelo(historico.getCartaoAmarelo());
			retorno.setCartaoVermelho(historico.getCartaoVermelho());
			retorno.setGol(historico.getGol());
			retorno.setJogos(historico.getJogos());
			retorno.setVitoria(historico.getVitoria());
		}
		return retorno;
	}
	
}
