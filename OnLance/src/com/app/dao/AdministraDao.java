package com.app.dao;

import java.sql.SQLException;
import com.app.vo.Administra;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class AdministraDao extends BaseDaoImpl<Administra, Integer> {
	
	public AdministraDao(ConnectionSource cs) throws SQLException{
		super(Administra.class);
		setConnectionSource(cs);
		initialize();
	}

}
