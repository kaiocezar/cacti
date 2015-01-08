import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.onlance.bean.Jogador;
import br.com.onlance.utils.HibernateUtils;



public class Gerador {

	public static void main(String[] args) {
				
		Session session = HibernateUtils.openSession();
		Transaction t = session.beginTransaction();
		
		
		Jogador joga = new Jogador();
		
		joga.setEmail("kcpo@cin.ufpe.br");
		joga.setLogin("kcpo");
		joga.setNome("Kaio");
		joga.setSenha("kcpo");
		
		session.saveOrUpdate(joga);
		t.commit();
	

	}
	
}
