package com.app.dao;

import java.sql.SQLException;

import com.app.vo.Evento;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class EventoDao extends BaseDaoImpl<Evento, Integer> {

	protected EventoDao(ConnectionSource cs) throws SQLException {
		super(Evento.class);
		setConnectionSource(cs);
		initialize();
	}

}
