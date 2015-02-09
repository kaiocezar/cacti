package com.app.bo;

import java.sql.SQLException;

import Utils.ConnectionDetector;
import Utils.UtilsMetodos;
import android.content.Context;

import com.app.dao.DatabaseHelper;
import com.app.dao.GrupoDao;
import com.app.dao.MembroDao;
import com.app.facade.GrupoFacade;
import com.app.onlance.R;
import com.app.vo.Grupo;
import com.app.vo.Jogador;
import com.app.vo.Membro;
import com.j256.ormlite.dao.Dao.CreateOrUpdateStatus;

public class GrupoBo {

	private Context context;
	private DatabaseHelper dh;
	private GrupoDao grupoDao;
	private MembroDao menbroDao;

	public GrupoBo(Context context) {
		this.context = context;
	}

	public void saveInMembro(Grupo grupo, Jogador jogador) {

		dh = DatabaseHelper.getInstance(context);
		try {
			grupoDao = new GrupoDao(dh.getConnectionSource());
			grupo.setId(14);
			 int result = grupoDao.create(grupo);

			Membro m = new Membro(); 
			m.setGrupo(grupo);
			m.setJogador(jogador);;
			
			menbroDao = new MembroDao(dh.getConnectionSource());
			menbroDao.create(m);
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void save(Grupo grupo) throws SQLException {
		if (ConnectionDetector.getInstance(context).isConnectingToInternet()) {
			/*
			 * if (jogador .getEmail() .matches(
			 * "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$;"
			 * )) {
			 */
			/*
			 * if (!UtilsServidor.getInstace(
			 * context.getString(R.string.servidor_desenvolvimento))
			 * .findById(jogador.getEmail())) {
			 */

			GrupoFacade grupoF = new GrupoFacade();
			// Setar os atributos no grupo

			/*
			 * grupoF = UtilsServidor.getInstace(
			 * context.getString(R.string.servidor_desenvolvimento))
			 * .persistOrMerge(grupoF); jogador.setId(grupoF.getId());
			 */

			dh = DatabaseHelper.getInstance(context);
			grupoDao = new GrupoDao(dh.getConnectionSource());

			int result = grupoDao.create(grupo);
			if (result == 1) {

				UtilsMetodos.getInscace().toast(context,
						context.getString(R.string.msg_salvar_jogador));

			} else {
				throw new SQLException();
			}

			// } else { // Valida campos Intacio um Jogador Facade Procuro no
			// Bd se
			// já existe aquele email Salvo facade Recebe o Id e //
			// persiste localmente
			// }
			/*
			 * } else { // Exxception de Validacao // }
			 */
		} else {
			UtilsMetodos.getInscace().toast(context,
					context.getString(R.string.msg_falha_conexao_com_internet));
		}
	}
}
