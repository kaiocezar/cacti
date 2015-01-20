package com.app.dao;

import java.sql.SQLException;

import com.app.vo.Grupo;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class GrupoDao extends BaseDaoImpl<Grupo, Integer> {

	public GrupoDao(ConnectionSource cs) throws SQLException {
		super(Grupo.class);
		setConnectionSource(cs);
		initialize();
	}

}
