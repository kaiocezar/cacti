package com.app.onlance;

import java.util.ArrayList;
import java.util.List;

import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;

import Utils.UtilsInformation;
import Utils.UtilsMetodos;
import android.app.ActionBar;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PartidaActivity extends Activity {
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
	
	Button gol;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.partida);

		actionBar = getActionBar();
		actionBar.setTitle(R.string.partida_UPPER);

		init();
		bind();
	}
	
	public void init(){
		
		jogadores1 = new ArrayList<String>();
		jogadores2 = new ArrayList<String>();
		milliseconds = 0;
		isStart = true;
		
		if(UtilsInformation.getInscace().getTime() != null){
			limite = UtilsInformation.getInscace().getTime() +":00";
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
		
		cronometro = (Chronometer) findViewById(R.id.chronometer1);
		gol = (Button) findViewById(R.id.gol);
		placar1 = (TextView) findViewById(R.id.placar1);
		placar2 = (TextView) findViewById(R.id.placar2);
	}
	
	
	public void bind(){
		
		cronometro
				.setOnChronometerTickListener(new OnChronometerTickListener() {

			@Override
			public void onChronometerTick(Chronometer arg0) {

				String valorCronometro = arg0.getText().toString();
				if (valorCronometro.equals(limite) || isFinishFromGol()) {
					fim();
				}
			}

		});
		
		
		
		listViewJogadores1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				if(isGol){
					Integer gol = Integer
							.parseInt(placar1.getText().toString());
					gol++;
					placar1.setText(gol.toString());
					modificarGol();
					
					shareContent(jogadores1.get(arg2));
					fim();
				}
				
			}
		});
		
		listViewJogadores2.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				if(isGol){
					Integer gol = Integer
							.parseInt(placar2.getText().toString());
					gol++;
					placar2.setText(gol.toString());
					modificarGol();
					shareContent(jogadores2.get(arg2));
					fim();
				}
				
			}
		});
		
		
	}
	
	private void fim() {
		
		Utils.UtilsMetodos.getInscace().toast(PartidaActivity.this, "fim");
		cronometro.stop();
		milliseconds = 0;
		isStart = true;
		MediaPlayer media = MediaPlayer.create(PartidaActivity.this.getApplicationContext(),
				R.raw.apitodefutebol);
		media.start();
	}
	
	private boolean isFinishFromGol() {
		
		boolean retono = false;
		int gol = Integer.parseInt(UtilsInformation.getInscace().getGol());
		int placarTime1 = Integer.parseInt(placar1.getText().toString());
		int placarTime2 = Integer.parseInt(placar2.getText().toString());
		
		
		if(gol == placarTime1 || gol == placarTime2 ){
			retono = true;
		}
		
		
		return retono;
	}
	
	public void clickGol(View view){
		modificarGol();
	}
	
	public void modificarGol(){
		if(isGol){
			gol.setBackgroundResource(R.drawable.gol);
			isGol = false;
		}else{
			Toast.makeText(this, "Selecione o jogador que marcou o GOL",
					Toast.LENGTH_SHORT).show();
			gol.setBackgroundResource(R.drawable.gol_ativo);
			isGol = true;
		}
		
	}

	public void start(View view) {
		if(isStart){
			cronometro.setBase(SystemClock.elapsedRealtime() - milliseconds);
			cronometro.start();
			isStart = false;
		}else{
			 milliseconds = SystemClock.elapsedRealtime() -cronometro.getBase();
			 cronometro.stop();
			 isStart = true;
		}
	}
	
	
	public void shareContent(String nome){
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
					if(response.getError() == null){
						Toast.makeText(PartidaActivity.this, "Sucesso",
								Toast.LENGTH_LONG).show();
					}else{
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
}
