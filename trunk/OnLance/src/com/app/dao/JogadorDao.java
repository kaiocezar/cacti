package com.app.dao;

import java.sql.SQLException;

import com.app.vo.Jogador;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class JogadorDao extends BaseDaoImpl<Jogador, Integer> {

	protected JogadorDao(ConnectionSource cs) throws SQLException {
		super(Jogador.class);
		setConnectionSource(cs);
		initialize();
	}

}
