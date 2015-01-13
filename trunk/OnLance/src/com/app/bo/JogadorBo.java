package com.app.bo;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Utils.ConnectionDetector;
import Utils.UtilsMetodos;
import Utils.UtilsServidor;
import android.content.Context;

import com.app.dao.DatabaseHelper;
import com.app.dao.JogadorDao;
import com.app.facade.JogadorFacade;
import com.app.onlance.R;
import com.app.vo.Jogador;

public class JogadorBo {

	private Context context;
	private DatabaseHelper dh;
	private JogadorDao jogadorDao;

	public JogadorBo(Context context) {
		this.context = context;
	}

	public void save(Jogador jogador) throws SQLException {
		if (ConnectionDetector.getInstance(context).isConnectingToInternet()) {
//			if (jogador.getEmail().matches("\\w+@\\w+\\.\\w{2,3}\\.\\w{2,3}")) {
				if (!UtilsServidor.getInstace(
						context.getString(R.string.servidor_desenvolvimento))
						.findByEmail(jogador.getEmail())) {

					JogadorFacade jogadorF = new JogadorFacade();
					jogadorF.setEmail(jogador.getEmail());
					jogadorF.setFone(jogador.getNumeroTelefone());
					jogadorF.setLogin(jogador.getEmail());
					jogadorF.setNome(jogador.getNome());
					jogadorF.setSenha(jogador.getSenha());

					jogadorF = UtilsServidor
							.getInstace(
									context.getString(R.string.servidor_desenvolvimento))
							.persistOrMerge(jogadorF);

					jogador.setId(jogadorF.getId());

					dh = DatabaseHelper.getBdInstance(context); // Recupera a
																// instancia do
																// BD
					jogadorDao = new JogadorDao(dh.getConnectionSource());
					
					int result = jogadorDao.create(jogador);
					if (result == 1) {

						UtilsMetodos.getInscace().toast(context,
								context.getString(R.string.msg_salvar_jogador));
					} else {
						throw new SQLException();
					}
				} else {
					// Valida campos Intacio um Jogador Facade Procuro no Bd se
					// já existe aquele email Salvo facade Recebe o Id e
					// persiste localmente
				}
//			} else {
//					//Exception de Validacao
//			}
		} else {
			UtilsMetodos.getInscace().toast(context,
					context.getString(R.string.msg_falha_conexao_com_internet));
		}
	}

	public void remove(Jogador jogador) throws SQLException {
		dh = DatabaseHelper.getBdInstance(context); // Recupera a instancia do
													// BD
		jogadorDao = new JogadorDao(dh.getConnectionSource());
		jogadorDao.delete(jogador);
	}

	public Jogador findByEmail(String email) throws SQLException {
		dh = DatabaseHelper.getBdInstance(context); // Recupera a instancia do
													// BD
		jogadorDao = new JogadorDao(dh.getConnectionSource());
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("email", email + " - ANDROID CLASS");
		return (Jogador) jogadorDao.queryForFieldValues(values);
	}

	public void update(Jogador jogador) throws SQLException {
		dh = DatabaseHelper.getBdInstance(context); // Recupera a instancia do
													// BD
		jogadorDao = new JogadorDao(dh.getConnectionSource());
		jogadorDao.update(jogador);
	}

	public List<Jogador> getAll() throws SQLException {
		return jogadorDao.queryForAll();
	}

	// Fecha conexão com o banco
	public void closeDb() {
		dh.close();
	}
}
