package com.app.onlance;

import java.sql.SQLException;

import com.app.bo.JogadorBo;
import com.app.vo.Jogador;

import Utils.UtilsMetodos;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Dashboard extends Activity {

	private Button btnComunidade;
	private Button btnEvento;
	private Button btnPartidaRapida;
	private Button btnConfiguracoes;
	private JogadorBo jogadorBo;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);

		btnComunidade = (Button) findViewById(R.id.comunidade_dashboard);
		btnEvento = (Button) findViewById(R.id.estatisticas_dashboard);
		btnPartidaRapida = (Button) findViewById(R.id.partidas_dashboard);
		btnConfiguracoes = (Button) findViewById(R.id.configuracoes_dashboard);

		jogadorBo = new JogadorBo(this);
		Jogador joga = jogadorBo.findByIdPreferences();
		if(joga !=null){
			UtilsMetodos.getInscace().toast(this,
					joga.getEmail() + " - " + joga.getSenha());
			
		}

		OnClickListener oclBtnComunidade = new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Dashboard.this, GruposActivity.class);
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

		btnEvento.setOnClickListener(oclBtnEstatisticas);

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
						DefinirTimesActivity.class);
				startActivity(intent);
			}
		};

		btnPartidaRapida.setOnClickListener(oclBtnPartidas);
	}
}
