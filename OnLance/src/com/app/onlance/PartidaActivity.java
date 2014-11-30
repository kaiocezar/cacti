package com.app.onlance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Utils.UtilsConstants;
import Utils.UtilsInformation;
import Utils.UtilsMetodos;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;

public class PartidaActivity extends Activity implements OnItemClickListener,
		OnClickListener {
	private static String limite = "00:20";

	private ActionBar actionBar;

	Chronometer cronometro;
	private long milliseconds;
	private boolean isStart;
	private boolean isGol = false;

	List<String> jogadores1;
	List<String> jogadores2;
	ListView listViewJogadores1;
	ListView listViewJogadores2;

	TextView placar1;
	TextView placar2;
	
	String nomeParaEvento;

	Button gol;
	private AlertDialog alertDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.partida);

		actionBar = getActionBar();
		actionBar.setTitle(R.string.partida_UPPER);

		init();
		bind();
	}

	public void init() {
		this.alertDialog = criaAlertDialog();
		jogadores1 = new ArrayList<String>();
		jogadores2 = new ArrayList<String>();
		milliseconds = 0;
		isStart = true;

		if (UtilsInformation.getInscace().getTime() != null) {
			limite = UtilsInformation.getInscace().getTime() + ":00";
		}

		List<JogadorForList> lista1 = UtilsInformation.getInscace().getTime1();
		List<JogadorForList> lista2 = UtilsInformation.getInscace().getTime2();

		for (JogadorForList jogadorForList : lista1) {
			jogadores1.add(jogadorForList.getNome());
		}

		for (JogadorForList jogadorForList : lista2) {
			jogadores2.add(jogadorForList.getNome());
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, jogadores1);
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, jogadores2);

		listViewJogadores1 = (ListView) findViewById(R.id.time1);
		listViewJogadores2 = (ListView) findViewById(R.id.time2);

		listViewJogadores1.setAdapter(adapter);
		listViewJogadores2.setAdapter(adapter2);

		cronometro = (Chronometer) findViewById(R.id.chronometerPartida);
		gol = (Button) findViewById(R.id.gol);
		placar1 = (TextView) findViewById(R.id.placar1);
		placar2 = (TextView) findViewById(R.id.placar2);
	}

	public void bind() {

		cronometro
				.setOnChronometerTickListener(new OnChronometerTickListener() {

					@Override
					public void onChronometerTick(Chronometer arg0) {

						String valorCronometro = arg0.getText().toString();
						if (valorCronometro.equals(limite)) {
							fim();
						}
					}

				});

		listViewJogadores1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				if (isGol) {
					Integer gol = Integer
							.parseInt(placar1.getText().toString());
					gol++;
					placar1.setText(gol.toString());
					modificarGol();
					
					
					historicoGol(jogadores1.get(arg2), UtilsConstants.TIME1);
					shareContent(jogadores1.get(arg2));
					
					isFinishFromGol();
				}

			}
		});

		listViewJogadores1
				.setOnItemLongClickListener(new OnItemLongClickListener() {

					@Override
					public boolean onItemLongClick(AdapterView<?> arg0,
							View arg1, int positionList, long arg3) {

						alertDialog.show();
						nomeParaEvento =  jogadores1.get(positionList);
						return false;
					}

				});

		listViewJogadores2.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				if (isGol) {
					Integer gol = Integer
							.parseInt(placar2.getText().toString());
					gol++;
					placar2.setText(gol.toString());
					modificarGol();
					historicoGol(jogadores2.get(arg2), UtilsConstants.TIME2);
					shareContent(jogadores2.get(arg2));
					fim();
				}

			}
		});

		listViewJogadores2
				.setOnItemLongClickListener(new OnItemLongClickListener() {

					@Override
					public boolean onItemLongClick(AdapterView<?> arg0,
							View arg1, int positionList, long arg3) {

						alertDialog.show();
						nomeParaEvento =  jogadores2.get(positionList);
						return false;
					}

				});

	}

	private AlertDialog criaAlertDialog() {
		final CharSequence[] items = { getString(R.string.cartao_amarelo),
				getString(R.string.cartao_vermelho) };

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.opcoes);
		builder.setItems(items, this);
		return builder.create();
	}

	private void fim() {

		Utils.UtilsMetodos.getInscace().toast(PartidaActivity.this, "fim");
		String valorCronometro = cronometro.getText().toString();
		cronometro.stop();
		milliseconds = 0;
		isStart = true;
		
		int placarTime1 = Integer.parseInt(placar1.getText().toString());
		int placarTime2 = Integer.parseInt(placar2.getText().toString());
		
		
		Map<String,String> dados = new HashMap<String,String>();
		dados.put(UtilsConstants.EVENTO, UtilsConstants.TERMINO);
		dados.put(UtilsConstants.TEMPO, valorCronometro);
		dados.put(UtilsConstants.PLACAR_TIME1, String.valueOf(placarTime1));
		dados.put(UtilsConstants.PLACAR_TIME2, String.valueOf(placarTime2));
		
		UtilsInformation.getInscace().addHistorico(dados);
		
		MediaPlayer media = MediaPlayer.create(
				PartidaActivity.this.getApplicationContext(),
				R.raw.apitodefutebol);
		
		media.start();
		
		proximaTela();
		
	}
	
	private void proximaTela() {
		Intent intent = new Intent(this, LogActivity.class);

		startActivity(intent);
		
	}

	public void historicoGol(String nome,String time){
	
		String valorCronometro = cronometro.getText().toString();
		Map<String,String> dados = new HashMap<String,String>();
		dados.put(UtilsConstants.EVENTO, UtilsConstants.GOL);
		dados.put(UtilsConstants.TEMPO, valorCronometro);
		dados.put(time, nome);
		
		UtilsInformation.getInscace().addHistorico(dados);
	}

	private void isFinishFromGol() {
		
		int gol = Integer.parseInt(UtilsInformation.getInscace().getGol());
		int placarTime1 = Integer.parseInt(placar1.getText().toString());
		int placarTime2 = Integer.parseInt(placar2.getText().toString());
		
		
		if(gol == placarTime1 || gol == placarTime2 ){
			fim();
		}
		
		
	}

	public void clickGol(View view) {
		if (!isStart) {
			modificarGol();
		}
	}

	public void modificarGol() {
		if (isGol) {
			gol.setBackgroundResource(R.drawable.gol);
			isGol = false;
			
		} else {
			Toast.makeText(this, "Selecione o jogador que marcou o GOL",
					Toast.LENGTH_SHORT).show();
			gol.setBackgroundResource(R.drawable.gol_ativo);
			isGol = true;
		}

	}

	public void start(View view) {
		if (isStart) {
			
			MediaPlayer media = MediaPlayer.create(
					PartidaActivity.this.getApplicationContext(),
					R.raw.apitodefutebol);
			media.start();
			
			int placarTime1 = Integer.parseInt(placar1.getText().toString());
			int placarTime2 = Integer.parseInt(placar2.getText().toString());
			
			String valorCronometro = cronometro.getText().toString();
			Map<String,String> dados = new HashMap<String,String>();
			dados.put(UtilsConstants.EVENTO, "Inicio");
			dados.put(UtilsConstants.TEMPO, valorCronometro);
			dados.put(UtilsConstants.PLACAR_TIME1, String.valueOf(placarTime1));
			dados.put(UtilsConstants.PLACAR_TIME2, String.valueOf(placarTime2));
			
			UtilsInformation.getInscace().addHistorico(dados);
			
			cronometro.setBase(SystemClock.elapsedRealtime() - milliseconds);
			cronometro.start();
			Button b = (Button) findViewById(R.id.playPartida);
			Chronometer c = (Chronometer) findViewById(R.id.chronometerPartida);
			b.setVisibility(View.INVISIBLE);
			c.setVisibility(View.VISIBLE);
			isStart = false;
		} else {
			
			MediaPlayer media = MediaPlayer.create(
					PartidaActivity.this.getApplicationContext(),
					R.raw.apitodefutebol);
			media.start();
			milliseconds = SystemClock.elapsedRealtime() - cronometro.getBase();
			cronometro.stop();
			isStart = true;
		}
	}

	public void shareContent(String nome) {
		if (UtilsMetodos.getInscace().isConectado()
				&& UtilsMetodos.getInscace().validarUsuario(this)) {

			Session session = Session.getActiveSession();
			Bundle paramns = new Bundle();
			paramns.putString("name", "Gollllll");
			paramns.putString("caption", "Baixe agora o ONLance");
			paramns.putString("description", "Numa jogada espetacular, " + nome
					+ " faz um gol magnífico!");
			paramns.putString("link", "http://google.com.br");
			paramns.putString("picture",
					"http://www.informatoz.com/imagens/ico_cobertura/bola.png");

			Request.Callback call = new Request.Callback() {

				@Override
				public void onCompleted(Response response) {
					if (response.getError() == null) {
						Toast.makeText(PartidaActivity.this, "Sucesso",
								Toast.LENGTH_LONG).show();
					} else {
						Toast.makeText(PartidaActivity.this, "Falha",
								Toast.LENGTH_LONG).show();
					}
				}
			};

			Request re = new Request(session, "/me/feed", paramns,
					HttpMethod.POST, call);
			re.executeAsync();
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(DialogInterface arg0, int item) {

		switch (item){ 
		
		case 0:
			
			break;
		case 1:
			
			break;
		}
		
	}
}
