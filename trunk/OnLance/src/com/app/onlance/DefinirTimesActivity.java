package com.app.onlance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Utils.UtilsConstants;
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

import com.app.adapter.BaseAdapterOnLance;
import com.app.bo.EventoBo;
import com.app.bo.JogadorBo;
import com.app.vo.Jogador;
import com.app.vo.Participa;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.model.GraphUser;

public class DefinirTimesActivity extends Activity {

	List<JogadorForList> jogadores;
	private BaseAdapterOnLance adpter;
	private EventoBo eventoBo;
	private int idEvento;
	private JogadorBo jogadorBo;
	private ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.definir_times);

		eventoBo = new EventoBo(this);
		jogadorBo = new JogadorBo(this);

		idEvento = getIntent().getIntExtra(UtilsConstants.ID_EVENTO, 0);
		actionBar = getActionBar();
		actionBar.setTitle(R.string.selecao_jogadores_UPPER);

		jogadores = new ArrayList<JogadorForList>();

		carregarListJogadores();
		if (UtilsMetodos.getInscace().isConectadoFacebook()) {
			getMyFriends(Session.getActiveSession());
		}

	}

	private void openConfigPartida() {
		Intent intent = new Intent(this, TimesPartidaActivity.class);
		startActivityForResult(intent, 1);
	}

	public void showLog(View view) {
		Intent intent = new Intent(this, LogActivity.class);
		startActivity(intent);
	}

	public void carregarListJogadores() {
		if(idEvento != 0){
			List<Participa> list = eventoBo.getParticaEvento(idEvento);
			jogadores.addAll(getJogadorForListByParticipa(list));
		}else{
			jogadores.addAll(getAmigosUsuario());
		}

		for (int i = 0; i < 20; i++) {
			JogadorForList mapa = new JogadorForList();
			mapa.setNome("kaio" + i);
			jogadores.add(mapa);
		}
		
		int i = 1;
		
		while(i <= 8){
			int position = UtilsMetodos.getInscace().gerarJogadorAleatorio(jogadores.size());
			if(jogadores.get(position).getTipoTela().equals("0")){
				if(i <= 4){
					jogadores.get(position).setTipoTela("1");
				}else{
					jogadores.get(position).setTipoTela("2");
				}
				i++;
			}
			
			
		}
			

		ListView view = (ListView) findViewById(R.id.jogadores);

		adpter = new BaseAdapterOnLance(this, jogadores);

		view.setAdapter(adpter);

		view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				LinearLayout lay = (LinearLayout) arg1
						.findViewById(R.id.content);

				JogadorForList joga = jogadores.get(arg2);
				lay.setBackgroundColor(Color.parseColor("#BDBDBD")); // gray
				joga.setTipoTela("0");
				// if (joga.getTipoTela().equals("0")) {
				// lay.setBackgroundColor(Color.parseColor("#EF9A9A")); // red
				// joga.setTipoTela("1");
				//
				// } else if (joga.getTipoTela().equals("1")) {
				// lay.setBackgroundColor(Color.parseColor("#90CAF9")); // blue
				// joga.setTipoTela("2");
				// } else {
				// lay.setBackgroundColor(Color.parseColor("#BDBDBD")); // gray
				// joga.setTipoTela("0");
				// }

			}
		});
	}

	private List<JogadorForList> getAmigosUsuario() {
		List<JogadorForList> retorno = new ArrayList<JogadorForList>();
		
		List<Jogador> amigos = jogadorBo.getAmigos();
		
		for (Jogador jogador : amigos) {
			JogadorForList jog = new JogadorForList();
			jog.setIdJogador(jogador.getId());
			jog.setNome(jogador.getNome());
			retorno.add(jog);
		}
		
		return retorno;
	}

	private List<JogadorForList> getJogadorForListByParticipa(List<Participa> list) {

		List<JogadorForList> retorno = new ArrayList<JogadorForList>();
		if (list != null && list.size() > 0) {
			for (Participa p : list) {
				if (p.isConfirma()) {
					JogadorForList jog = new JogadorForList();
					Jogador joga = jogadorBo.findById(p.getJogador().getId());
					jog.setNome(joga.getNome());
					jog.setIdJogador(p.getJogador().getId());
					jog.setTipoTela("0");
					if (p.getGrupo() != null && p.getGrupo().getId() > 0) {
						jog.setIdGrupo(p.getGrupo().getId());
					}
					retorno.add(jog);

				}
			}

		}
		
		return retorno;

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

//		int quantJogTime = Integer.parseInt(UtilsInformation.getInscace()
//				.getPlay());
//		int quantTimeValido = 0;
//
//		for (JogadorForList jogador : jogadores) {
//			if (jogador.getTipoTela().equals("1")) {
//				quantTimeValido++;
//			}
//		}
//
//		if (quantTimeValido != quantJogTime) {
//
//			UtilsMetodos
//					.getInscace()
//					.toast(this,
//							"A quantidade de jogadores difere da quantidade configurada");
//			retorno = false;
//		}

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
							adpter.notifyDataSetChanged();
						}

						Log.i("Script", "Response" + response);
					}
				}).executeAsync();
	}

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

}
