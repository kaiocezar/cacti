package br.com.onlance.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.onlance.bean.Endereco;
import br.com.onlance.bean.Jogador;
import br.com.onlance.bean.Pessoa;
import br.com.onlance.utils.HibernateUtils;
import br.com.onlance.utils.JPAUtil;

import com.google.gson.Gson;

@Path("/kaio")
public class ServiceFactory {


	@GET
	@Path("/findById/Jogador/{id}")
	public String getJogadorById(@PathParam("id") int parametro) {

		Gson g = new Gson();
		
		EntityManager em = JPAUtil.getEntityManager(); 

		Jogador res = em.find(Jogador.class,parametro);
		
		res.getParticipa();
		res.getEvento();
		
		String retorno = g.toJson(res);
		
		
		return retorno;
	}
	
	@GET
	@Path("/persistOrMerge/Jogador/{jogador}")
	public String getJogador(@PathParam("jogador") String parametro) {
		
		
		// inicia a transação antes de processar o request
		EntityManager em = JPAUtil.getEntityManager();
		Jogador res = null;
		EntityTransaction tx = em.getTransaction(); 
		try { tx.begin();
		
		
		Gson g = new Gson();
		
		res = g.fromJson(parametro,Jogador.class);
		if(res != null){
			
			if(res.getId() == 0){
				em.persist(res);
			}else{
				em.merge(res);
				
			}
			
		}
		tx.commit(); 
		} catch (Exception e){ 
			// ou em caso de erro faz o rollback 
			if(tx != null && tx.isActive()){ tx.rollback(); } }

		finally { 
			em.close(); 
		}
		
		
		return res.getNome();
		
		
	}
	
	@GET
	@Path("/idade")
	public String getIdade() {

		
		Session session = HibernateUtils.openSession();
		Transaction tras = session.beginTransaction();
		
		Jogador jogar = (Jogador) session.get(Jogador.class, 1);
		tras.commit();
		
		
		return jogar.getEmail();
	}
	
	@GET
	@Path("/{parametro}")
	public String getIdade(@PathParam("parametro") String parametro) {

		Gson g = new Gson();
		Pessoa res;
		
		res = g.fromJson(parametro,Pessoa.class);
		
		return res.getNome();
	}

}
