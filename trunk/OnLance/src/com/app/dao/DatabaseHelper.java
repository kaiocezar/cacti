package com.app.dao;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.app.vo.Evento;
import com.app.vo.Grupo;
import com.app.vo.Jogador;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String NOME_BD = "onlance";
	private static final int VERSAO_BD = 1;
	private static DatabaseHelper dh;

	public DatabaseHelper(Context ctx) {
		super(ctx, NOME_BD, null, VERSAO_BD);
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource cs) {
		// Método para Criação do DB
		try {
			TableUtils.createTable(cs, Jogador.class);
			TableUtils.createTable(cs, Grupo.class);
			TableUtils.createTable(cs, Evento.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource cs,
			int oldVersion, int newVersion) {
		// Método para Atualização do Esquema do DB
		try {
			TableUtils.dropTable(cs, Jogador.class, true);
			TableUtils.dropTable(cs, Grupo.class, true);
			TableUtils.dropTable(cs, Evento.class, true);
			onCreate(db, cs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static DatabaseHelper getBdInstance(Context context) {

		if (dh == null) {
			dh = new DatabaseHelper(context.getApplicationContext());
		}
		return dh;
	}

	@Override
	public void close() {

		super.close();
	}

}
