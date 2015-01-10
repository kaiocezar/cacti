package br.com.onlance.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.onlance.bean.Jogador;
import br.com.onlance.facade.HistoricoFacade;
import br.com.onlance.facade.JogadorFacade;
import br.com.onlance.utils.HibernateUtils;

public class DaoImp {
	
	private static DaoImp daoImp;
	private static Session session;
	
	private DaoImp(){
		
	}
	
	public static DaoImp getInstance(){
		if(daoImp == null){
			daoImp = new DaoImp();
		}
		session = HibernateUtils.openSession();
		return daoImp;
	}
	
	public JogadorFacade persistOrMerge(JogadorFacade jogadorFacade){
		
		if(jogadorFacade.getHistorico() == null){
			jogadorFacade.setHistorico(new HistoricoFacade());
		} 
		if(jogadorFacade.getId() != null){
		
			Transaction trans = session.beginTransaction();
			Jogador entity = (Jogador) session.get(Jogador.class, jogadorFacade.getId());
			entity.setEmail(jogadorFacade.getEmail());
			entity.setFone(jogadorFacade.getFone());
			entity.setLogin(jogadorFacade.getLogin());
			entity.setSenha(jogadorFacade.getSenha());
			entity.getHistorico().setCartaoAmarelo(jogadorFacade.getHistorico().getCartaoAmarelo());
			entity.getHistorico().setVitoria(jogadorFacade.getHistorico().getVitoria());
			entity.getHistorico().setCartaoVermelho(jogadorFacade.getHistorico().getCartaoVermelho());
			entity.getHistorico().setGol(jogadorFacade.getHistorico().getGol());
			entity.getHistorico().setJogos(jogadorFacade.getHistorico().getJogos());
			entity.getHistorico().setVitoria(jogadorFacade.getHistorico().getVitoria());
			session.saveOrUpdate(entity);
			trans.commit();
			
		}else{
			Transaction trans = session.beginTransaction();
			Jogador entity = new Jogador();
			
			entity.setEmail(jogadorFacade.getEmail());
			entity.setFone(jogadorFacade.getFone());
			entity.setLogin(jogadorFacade.getLogin());
			entity.setSenha(jogadorFacade.getSenha());
			entity.getHistorico().setCartaoAmarelo(jogadorFacade.getHistorico().getCartaoAmarelo());
			entity.getHistorico().setVitoria(jogadorFacade.getHistorico().getVitoria());
			entity.getHistorico().setCartaoVermelho(jogadorFacade.getHistorico().getCartaoVermelho());
			entity.getHistorico().setGol(jogadorFacade.getHistorico().getGol());
			entity.getHistorico().setJogos(jogadorFacade.getHistorico().getJogos());
			entity.getHistorico().setVitoria(jogadorFacade.getHistorico().getVitoria());
			session.saveOrUpdate(entity);
			trans.commit();
			jogadorFacade.setId(entity.getId());
			
			
		}
		
		session.close();
		return jogadorFacade;
	}
	
	

}
