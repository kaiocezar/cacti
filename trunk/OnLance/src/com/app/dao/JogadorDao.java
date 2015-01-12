package com.app.dao;

import java.sql.SQLException;
import java.util.Map;

import android.content.ContentValues;

import com.app.vo.Jogador;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class JogadorDao extends BaseDaoImpl<Jogador, Integer> {

	public JogadorDao(ConnectionSource cs) throws SQLException {
		super(Jogador.class);
		setConnectionSource(cs);
		initialize();
	}
}
