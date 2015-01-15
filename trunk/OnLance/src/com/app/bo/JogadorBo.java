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
			/*
			 * if (jogador .getEmail() .matches(
			 * "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$;"
			 * )) {
			 */
			if (!UtilsServidor.getInstace(
					context.getString(R.string.servidor_desenvolvimento))
					.findByEmail(jogador.getEmail())) {

				JogadorFacade jogadorF = new JogadorFacade();
				jogadorF.setEmail(jogador.getEmail());
				jogadorF.setNome(jogador.getNome());
				jogadorF.setSenha(jogador.getSenha());
				jogadorF.setQtdCartaoAmarelo(0);
				// jogadorF.setQtdCartaoVermelho(0);
				// jogadorF.setQtdGol(0);
				// jogadorF.setQtdJogos(0);
				// jogadorF.setQtdVitoria(0);

				jogadorF = UtilsServidor.getInstace(
						context.getString(R.string.servidor_desenvolvimento))
						.persistOrMerge(jogadorF);
				jogador.setId(jogadorF.getId());

				dh = DatabaseHelper.getInstance(context);
				jogadorDao = new JogadorDao(dh.getConnectionSource());

				int result = jogadorDao.create(jogador);
				if (result == 1) {

					UtilsMetodos.getInscace().toast(context,
							context.getString(R.string.msg_salvar_jogador));
					
				} else {
					throw new SQLException();
				}

			} else { // Valida campos Intacio um Jogador Facade Procuro no
						// Bd se
				// já existe aquele email Salvo facade Recebe o Id e //
				// persiste localmente
			}
			/*
			 * } else { // Exxception de Validacao // }
			 */
		} else {
			UtilsMetodos.getInscace().toast(context,
					context.getString(R.string.msg_falha_conexao_com_internet));
		}
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
		dh = null;
	}
}
