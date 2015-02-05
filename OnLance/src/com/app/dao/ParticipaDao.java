package com.app.dao;

import java.sql.SQLException;

import com.app.vo.Participa;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class ParticipaDao extends BaseDaoImpl<Participa, Integer> {

	public ParticipaDao(ConnectionSource cs) throws SQLException {
		super(Participa.class);
		setConnectionSource(cs);
		initialize();
	}
}
