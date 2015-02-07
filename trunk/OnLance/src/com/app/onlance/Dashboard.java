package com.app.onlance;

import java.sql.SQLException;

import com.app.bo.JogadorBo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Dashboard extends Activity {

	private Button btnComunidade;
	private Button btnEstatisticas;
	private Button btnPartidas;
	private Button btnConfiguracoes;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);

		btnComunidade = (Button) findViewById(R.id.comunidade_dashboard);
		btnEstatisticas = (Button) findViewById(R.id.estatisticas_dashboard);
		btnPartidas = (Button) findViewById(R.id.partidas_dashboard);
		btnConfiguracoes = (Button) findViewById(R.id.configuracoes_dashboard);

		OnClickListener oclBtnComunidade = new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Dashboard.this,
						GruposActivity.class);
				startActivity(intent);
			}
		};

		btnComunidade.setOnClickListener(oclBtnComunidade);

		OnClickListener oclBtnEstatisticas = new OnClickListener() {
			public void onClick(View v) {
				
				Intent intent = new Intent(Dashboard.this,
						EventosActivity.class);
				startActivity(intent);
			}
		};

		btnEstatisticas.setOnClickListener(oclBtnEstatisticas);

		OnClickListener oclBtnConf = new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Dashboard.this,
						ConfigGeralActivity.class);
				startActivity(intent);
			}
		};

		btnConfiguracoes.setOnClickListener(oclBtnConf);

		OnClickListener oclBtnPartidas = new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Dashboard.this,
						ModoJogoActivity.class);
				startActivity(intent);
			}
		};

		btnPartidas.setOnClickListener(oclBtnPartidas);
	}
}
