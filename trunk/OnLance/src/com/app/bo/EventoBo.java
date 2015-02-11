package com.app.bo;

import java.sql.SQLException;

import Utils.UtilsMetodos;
import android.content.Context;

import com.app.dao.CriaDao;
import com.app.dao.DatabaseHelper;
import com.app.dao.EventoDao;
import com.app.vo.Cria;
import com.app.vo.Evento;
import com.app.vo.Jogador;

public class EventoBo {

	private Context context;
	private DatabaseHelper dh;
	private EventoDao daoEvento;
	private CriaDao criaEvento;

	public EventoBo(Context context) {
		this.context = context;
	}
	
	public void criarEvento(Evento evento, Jogador jogador){
		dh = DatabaseHelper.getInstance(context);
		evento.setId(UtilsMetodos.getInscace().getId());
		try {
			daoEvento = new EventoDao(dh.getConnectionSource());
			criaEvento = new CriaDao(dh.getConnectionSource());
			daoEvento.create(evento);
			Cria cria = new Cria(jogador,evento);
			criaEvento.create(cria);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}
}
