package com.app.onlance;

import java.util.ArrayList;
import java.util.List;

import Utils.UtilsInformation;
import Utils.UtilsMetodos;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.model.GraphUser;

public class DefinirTimesActivity extends Activity {

	List<JogadorForList> jogadores;
	private ActionBar actionBar;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.definir_times_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case R.id.action_config_partida:
			openConfigPartida();
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}

	private void openConfigPartida() {
		Intent intent = new Intent(this, TimesPartidaActivity.class);
		startActivityForResult(intent, 1);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.definir_times);

		actionBar = getActionBar();
		actionBar.setTitle(R.string.selecao_jogadores_UPPER);

		jogadores = new ArrayList<JogadorForList>();

		if (UtilsMetodos.getInscace().isConectadoFacebook()) {
			getMyFriends(Session.getActiveSession());
		}

	}

	public void showLog(View view) {
		Intent intent = new Intent(this, LogActivity.class);
		startActivity(intent);
	}

	public void carregarListJogadores() {

		if (true) {
			for (int i = 0; i < 20; i++) {
				JogadorForList mapa = new JogadorForList();
				mapa.setNome("kaio" + i);
				mapa.setTipoTela("0");

				jogadores.add(mapa);
			}
		}

		ListView view = (ListView) findViewById(R.id.jogadores);

		view.setAdapter(new BaseAdapterOnLance(this, jogadores));

		view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				LinearLayout lay = (LinearLayout) arg1
						.findViewById(R.id.content);
				
				
				JogadorForList joga = jogadores.get(arg2);
				lay.setBackgroundColor(Color.parseColor("#BDBDBD")); // gray
				joga.setTipoTela("0");
//				if (joga.getTipoTela().equals("0")) {
//					lay.setBackgroundColor(Color.parseColor("#EF9A9A")); // red
//					joga.setTipoTela("1");
//
//				} else if (joga.getTipoTela().equals("1")) {
//					lay.setBackgroundColor(Color.parseColor("#90CAF9")); // blue
//					joga.setTipoTela("2");
//				} else {
//					lay.setBackgroundColor(Color.parseColor("#BDBDBD")); // gray
//					joga.setTipoTela("0");
//				}

			}
		});
	}

	public void prosseguir(View view) {

		if (validarTime()) {

			Intent intent = new Intent(this, PartidaActivity.class);
			UtilsInformation.getInscace().cleanListAll();
			extractTime();
			startActivity(intent);
		}
	}

	private void extractTime() {
		for (JogadorForList jogador : jogadores) {

			if (jogador.getTipoTela().equals("1")) {
				UtilsInformation.getInscace().addTime1(jogador);
			} else if (jogador.getTipoTela().equals("2")) {
				UtilsInformation.getInscace().addTime2(jogador);
			}
		}

	}

	private boolean validarTime() {

		return jogadores.size() > 0 && isJoogadoresValid()
				&& isQuantTimeValid();

	}

	private boolean isQuantTimeValid() {
		boolean retorno = true;

		int quantJogTime = Integer.parseInt(UtilsInformation.getInscace()
				.getPlay());
		int quantTimeValido = 0;

		for (JogadorForList jogador : jogadores) {
			if (jogador.getTipoTela().equals("1")) {
				quantTimeValido++;
			}
		}

		if (quantTimeValido != quantJogTime) {

			UtilsMetodos
					.getInscace()
					.toast(this,
							"A quantidade de jogadores difere da quantidade configurada");
			retorno = false;
		}

		return retorno;
	}

	private boolean isJoogadoresValid() {

		boolean retorno = true;

		int quatTime1 = 0;
		int quatTime2 = 0;

		for (JogadorForList jogador : jogadores) {

			if (jogador.getTipoTela().equals("1")) {
				quatTime1++;
			} else if (jogador.getTipoTela().equals("2")) {
				quatTime2++;
			}
		}

		if (quatTime1 != quatTime2) {
			UtilsMetodos.getInscace().toast(this, "O time não está balanceado");
			retorno = false;
		}

		return retorno;
	}

	public void getMyFriends(Session session) {
		Request.newMyFriendsRequest(session,
				new Request.GraphUserListCallback() {

					@Override
					public void onCompleted(List<GraphUser> users,
							Response response) {
						if (response.getError() == null & users != null
								&& users.size() > 0) {
							Log.i("Script", "NUmero de amigos " + users.size());

							for (GraphUser amigos : users) {
								JogadorForList mapa = new JogadorForList();
								mapa.setNome(amigos.getName());
								mapa.setTipoTela("0");
								jogadores.add(mapa);
							}
							carregarListJogadores();
						}

						Log.i("Script", "Response" + response);
					}
				}).executeAsync();
	}

}
