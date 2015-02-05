package com.app.dao;

import java.sql.SQLException;
import com.app.vo.Membro;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class MembroDao extends BaseDaoImpl<Membro, Integer> {

	public MembroDao(ConnectionSource cs) throws SQLException{
		super(Membro.class);
		setConnectionSource(cs);
		initialize();
	}
}
