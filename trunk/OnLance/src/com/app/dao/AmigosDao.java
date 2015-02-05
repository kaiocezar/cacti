package com.app.dao;

import java.sql.SQLException;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class AmigosDao extends BaseDaoImpl<AmigosDao,Integer> {

	public AmigosDao(ConnectionSource cs) throws SQLException{
		super(AmigosDao.class);
		setConnectionSource(cs);
		initialize();
	}
}
