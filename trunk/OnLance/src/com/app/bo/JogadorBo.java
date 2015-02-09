package com.app.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Utils.ConnectionDetector;
import Utils.UtilsMetodos;
import Utils.UtilsServidor;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.Toast;

import com.app.dao.AdministraDao;
import com.app.dao.DatabaseHelper;
import com.app.dao.GrupoDao;
import com.app.dao.JogadorDao;
import com.app.dao.MembroDao;
import com.app.dao.ParticipaDao;
import com.app.facade.JogadorFacade;
import com.app.onlance.R;
import com.app.vo.Evento;
import com.app.vo.Grupo;
import com.app.vo.Jogador;
import com.app.vo.Membro;
import com.app.vo.Participa;

public class JogadorBo {

	public static final String MyPREFERENCES = "MyPrefs";
	public static final String UserId = "UserId";
	private MembroDao menbroDao;
	private GrupoDao grupoDao;
	private Context context;
	private DatabaseHelper dh;
	private JogadorDao jogadorDao;
	private SharedPreferences sharedpreferences;

	public JogadorBo(Context context) {
		this.context = context;
	}

	public int save(Jogador jogador) throws SQLException {
		
		sharedpreferences = context.getSharedPreferences(MyPREFERENCES,
				Context.MODE_PRIVATE);

		// if (ConnectionDetector.getInstance(context).isConnectingToInternet())
		// {
		/*
		 * if (jogador .getEmail() .matches(
		 * "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$;"
		 * )) {
		 */
		/*
		 * if (!UtilsServidor.getInstace(
		 * context.getString(R.string.servidor_desenvolvimento))
		 * .findByEmail(jogador.getEmail())) {
		 * 
		 * JogadorFacade jogadorF = new JogadorFacade();
		 * jogadorF.setEmail(jogador.getEmail());
		 * jogadorF.setNome(jogador.getNome());
		 * jogadorF.setSenha(jogador.getSenha());
		 * jogadorF.setQtdCartaoAmarelo(0); // jogadorF.setQtdCartaoVermelho(0);
		 */
		// jogadorF.setQtdGol(0);
		// jogadorF.setQtdJogos(0);
		// jogadorF.setQtdVitoria(0);

		/*
		 * jogadorF = UtilsServidor.getInstace(
		 * context.getString(R.string.servidor_desenvolvimento))
		 * .persistOrMerge(jogadorF); jogador.setId(jogadorF.getId());
		 * 
		 * dh = DatabaseHelper.getInstance(context); jogadorDao = new
		 * JogadorDao(dh.getConnectionSource());
		 * 
		 * int result = jogadorDao.create(jogador); if (result == 1) {
		 * 
		 * UtilsMetodos.getInscace().toast(context,
		 * context.getString(R.string.msg_salvar_jogador));
		 * 
		 * } else { throw new SQLException(); }
		 */
		// } else { // Valida campos Intacio um Jogador Facade Procuro no
		// Bd se
		// já existe aquele email Salvo facade Recebe o Id e //
		// persiste localmente
		// }
		/*
		 * } else { // Exxception de Validacao // }
		 */
		// } else {
		// UtilsMetodos.getInscace().toast(context,
		// context.getString(R.string.msg_falha_conexao_com_internet));
		// }
		 dh = DatabaseHelper.getInstance(context); 
		 jogadorDao = new JogadorDao(dh.getConnectionSource());
		 
		jogador.setId(10);
		jogadorDao.createOrUpdate(jogador);
		 Editor editor = sharedpreferences.edit();
		 editor.putInt(UserId, jogador.getId());
		 editor.commit();
		 
		 
		
		return 1;
	}
	
	public Jogador findById(int id){
		Jogador j = null;
		
		 try {
			 dh = DatabaseHelper.getInstance(context); 
			jogadorDao = new JogadorDao(dh.getConnectionSource());
			j = jogadorDao.queryForId(id);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return j;
		
	}
	
	public Jogador findByIdPreferences(){
		Jogador j = null;
		
		sharedpreferences = context.getSharedPreferences(JogadorBo.MyPREFERENCES,
				Context.MODE_PRIVATE);
		
		int id = sharedpreferences.getInt(JogadorBo.UserId, 0);
		
		try {
			dh = DatabaseHelper.getInstance(context); 
			jogadorDao = new JogadorDao(dh.getConnectionSource());
			j = jogadorDao.queryForId(id);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return j;
		
	}
	
	public List<Grupo> getGrupoByJogador(Jogador jogador){
		List<Grupo> lisGrupo = new ArrayList<Grupo>();
		
		 try {
			 dh = DatabaseHelper.getInstance(context); 
			menbroDao = new MembroDao(dh.getConnectionSource());
			grupoDao = new GrupoDao(dh.getConnectionSource());
			List<Membro> listMebro = menbroDao.queryBuilder().where().eq("jogador_id", jogador).query();
			if(listMebro != null && listMebro.size() > 0){
				for (Membro membro : listMebro) {
					
					lisGrupo.add(grupoDao.queryForId(membro.getGrupo().getId()));
					
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return lisGrupo;
	}

	public void remove(Jogador jogador) throws SQLException {
		if (ConnectionDetector.getInstance(context).isConnectingToInternet()) {
			if (!UtilsServidor.getInstace(
					context.getString(R.string.servidor_desenvolvimento))
					.findByEmail(jogador.getEmail())) {

				JogadorFacade jogadorF = new JogadorFacade();
				jogadorF.setEmail(jogador.getEmail());
				jogadorF.setNome(jogador.getNome());
				jogadorF.setSenha(jogador.getSenha());
				// jogadorF.setQtdCartaoAmarelo(jogador.getQtdCartaoAmarelo());
				// jogadorF.setQtdCartaoVermelho(jogador.getQtdCartaoVermelho());
				// jogadorF.setQtdGol(jogador.getQtdGol());
				// jogadorF.setQtdJogos(jogador.getQtdJogos());
				// jogadorF.setQtdVitoria(jogador.getQtdVitoria());

				/*
				 * jogadorF = UtilsServidor.getInstace(
				 * context.getString(R.string.servidor_desenvolvimento)) Kaio tu
				 * tem que fazer o remove .persistOrMerge(jogadorF);
				 */

				dh = DatabaseHelper.getInstance(context);
				jogadorDao = new JogadorDao(dh.getConnectionSource());
				int result = jogadorDao.delete(jogador);
				if (result == 1) {

					UtilsMetodos.getInscace().toast(context,
							context.getString(R.string.msg_deletar_jogador));

				} else {
					throw new SQLException();
				}
			} else {

			}
		} else {

		}
	}

	public Jogador findByEmail(String email) throws SQLException {
		Jogador jogador = null;
		if (ConnectionDetector.getInstance(context).isConnectingToInternet()) {

			dh = DatabaseHelper.getInstance(context);
			jogadorDao = new JogadorDao(dh.getConnectionSource());
			Map<String, Object> values = new HashMap<String, Object>();
			values.put("email", email + " - ANDROID CLASS");
			jogador = (Jogador) jogadorDao.queryForFieldValues(values);

		} else {

		}
		return jogador;
	}

	public void update(Jogador jogador) throws SQLException {
		if (ConnectionDetector.getInstance(context).isConnectingToInternet()) {
			if (!UtilsServidor.getInstace(
					context.getString(R.string.servidor_desenvolvimento))
					.findByEmail(jogador.getEmail())) {

				JogadorFacade jogadorF = new JogadorFacade();
				jogadorF.setEmail(jogador.getEmail());
				jogadorF.setNome(jogador.getNome());
				jogadorF.setSenha(jogador.getSenha());
				// jogadorF.setQtdCartaoAmarelo(jogador.getQtdCartaoAmarelo());
				// jogadorF.setQtdCartaoVermelho(jogador.getQtdCartaoVermelho());
				// jogadorF.setQtdGol(jogador.getQtdGol());
				// jogadorF.setQtdJogos(jogador.getQtdJogos());
				// jogadorF.setQtdVitoria(jogador.getQtdVitoria());

				jogadorF = UtilsServidor.getInstace(
						context.getString(R.string.servidor_desenvolvimento))
						.persistOrMerge(jogadorF);

				dh = DatabaseHelper
						.getInstance(context.getApplicationContext());

				jogadorDao = new JogadorDao(dh.getConnectionSource());
				int result = jogadorDao.update(jogador);
				if (result == 1) {

					UtilsMetodos.getInscace().toast(context,
							context.getString(R.string.msg_atualizar_jogador));

				} else {
					throw new SQLException();
				}
			} else {

			}
		} else {

		}
	}

	public List<Jogador> getAll() throws SQLException {
		List<Jogador> allJogador = null;
		if (ConnectionDetector.getInstance(context).isConnectingToInternet()) {

			allJogador = jogadorDao.queryForAll();

		}
		return allJogador;
	}

	public void validarJogador(Jogador jogador) {
		if (ConnectionDetector.getInstance(context).isConnectingToInternet()) {
			if (!UtilsServidor.getInstace(
					context.getString(R.string.servidor_desenvolvimento))
					.findByEmail(jogador.getEmail())) {

			}
		}
	}

	public void closeDb() {
		if (dh != null) {
			dh.close(); // Fecha a conexão
		}
	}

	public void teste() throws SQLException{
		/*dh = DatabaseHelper.getInstance(context);
		ParticipaDao pd;
		pd = new ParticipaDao(dh.getConnectionSource());
		
		Evento evento = new Evento();
		evento.setId(2);
		evento.setLocalizacao("Teste");
		evento.setData(new Date());
		evento.setNome("teste");

		Grupo grupo = new Grupo();
		grupo.setDescricao("teste");
		grupo.setId(1);
		// grupo.setImagem(new byte[] { (byte) 0xe0 });
		grupo.setNome("teste");

		Jogador jogador = new Jogador();
		jogador.setEmail("teste");
		jogador.setId(1);
		
		Participa p = new Participa(jogador, evento, grupo);
		int result = pd.create(p);
		if(result==1){
			UtilsMetodos.getInscace().toast(context,
					context.getString(R.string.msg_atualizar_jogador));
			
		} else {
			UtilsMetodos.getInscace().toast(context,"We have a problem!");
		}*/
	}
}
