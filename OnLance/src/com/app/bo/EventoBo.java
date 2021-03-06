package com.app.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Utils.UtilsMetodos;
import android.content.Context;

import com.app.dao.CriaDao;
import com.app.dao.DatabaseHelper;
import com.app.dao.EventoDao;
import com.app.dao.GrupoDao;
import com.app.dao.JogadorDao;
import com.app.dao.ParticipaDao;
import com.app.vo.Cria;
import com.app.vo.Evento;
import com.app.vo.Jogador;
import com.app.vo.Participa;

public class EventoBo {

	private Context context;
	private DatabaseHelper dh;
	private EventoDao daoEvento;
	private JogadorDao jogadorDao;
	private GrupoDao grupoDao;
	private CriaDao criaEvento;
	private ParticipaDao participaDao;

	public EventoBo(Context context) {
		this.context = context;
	}

	public void criarEvento(Evento evento, Jogador jogador) {
		dh = DatabaseHelper.getInstance(context);
		evento.setId(UtilsMetodos.getInscace().getId());
		try {
			daoEvento = new EventoDao(dh.getConnectionSource());
			criaEvento = new CriaDao(dh.getConnectionSource());
			participaDao = new ParticipaDao(dh.getConnectionSource());
			daoEvento.create(evento);
			Cria cria = new Cria(jogador, evento);
			criaEvento.create(cria);

			Participa participa = new Participa();
			participa.setJogador(jogador);
			participa.setEvento(evento);
			participaDao.create(participa);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Participa> getParticaEvento(int idEvento) {
		List<Participa> participa = null;
		dh = DatabaseHelper.getInstance(context);
		try {
			participaDao = new ParticipaDao(dh.getConnectionSource());
			daoEvento = new EventoDao(dh.getConnectionSource());

			participa = participaDao.queryBuilder().where()
					.eq("evento_id", daoEvento.queryForId(idEvento)).query();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return participa;
	}

	public void addGol(int idJogador, int idGrupo, int idEvento) {
		dh = DatabaseHelper.getInstance(context);

		try {
			jogadorDao = new JogadorDao(dh.getConnectionSource());
			grupoDao = new GrupoDao(dh.getConnectionSource());
			participaDao = new ParticipaDao(dh.getConnectionSource());
			daoEvento = new EventoDao(dh.getConnectionSource());
			List<Participa> participaList = participaDao.queryBuilder().where()
					.eq("jogador_id", jogadorDao.queryForId(idJogador)).and()
					.eq("grupo_id", grupoDao.queryForId(idGrupo)).and()
					.eq("evento_id", daoEvento.queryForId(idEvento)).query();
			
			if(participaList != null && participaList.size() > 0){
				Participa p = participaList.get(0);
				p.setQtdGol(p.getQtdGol()+1);
				participaDao.update(p);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
