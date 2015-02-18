package com.app.dao;

import java.sql.SQLException;

import com.app.vo.Amigos;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class AmigosDao extends BaseDaoImpl<Amigos,Integer> {

	public AmigosDao(ConnectionSource cs) throws SQLException{
		super(Amigos.class);
		setConnectionSource(cs);
		initialize();
	}
}
