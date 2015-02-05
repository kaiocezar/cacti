package com.app.dao;

import java.sql.SQLException;
import com.app.vo.Cria;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class CriaDao extends BaseDaoImpl<Cria, Integer> {

	public CriaDao(ConnectionSource cs) throws SQLException{
		super(Cria.class);
		setConnectionSource(cs);
		initialize();
	}
}
