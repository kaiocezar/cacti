package br.com.onlance.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.onlance.bean.Endereco;
import br.com.onlance.bean.Jogador;
import br.com.onlance.bean.Pessoa;
import br.com.onlance.dao.DaoImp;
import br.com.onlance.facade.JogadorFacade;
import br.com.onlance.utils.HibernateUtils;
import br.com.onlance.utils.JPAUtil;

import com.google.gson.Gson;

@Path("/service")
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
		
		Gson g = new Gson();
		JogadorFacade joga = g.fromJson(parametro, JogadorFacade.class);
		
		
		JogadorFacade retorno = DaoImp.getInstance().persistOrMerge(joga);
		
		
		
		return g.toJson(retorno);
		
		
	}
	
	@GET
	@Path("/idade")
	public String getIdade() throws ServletException {

		
		Session session = HibernateUtils.openSession();
		Transaction tras = null;
		Jogador jogar = null;
		
		try {

			tras = session.beginTransaction();
			
			jogar = (Jogador) session.get(Jogador.class, 1);
			tras.commit();
			
			
		} catch (Throwable t) {
			try {
				if (tras.isActive()) {
					tras.rollback();
				}
			} catch (Throwable ex) {
				ex.printStackTrace();
			}

			throw new ServletException(t);
		}
		
		
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
