package com.app.onlance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Utils.UtilsConstants;
import Utils.UtilsInformation;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class LogActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.log_partida);
		
		List<Map> dados = UtilsInformation.getInscace().getListaHistorico();
		
		List<Map<String,String>> hasParaLayout = new ArrayList<Map<String,String>>();
		
		for (Map<String,String> map : dados) {
			
			Map<String, String> mapaLayout = new HashMap<String, String>();
			
			String evento = (String) map.get(UtilsConstants.EVENTO);
			String descricao;
			
			if(evento.equals(UtilsConstants.GOL)){
				
				if(map.get(UtilsConstants.TIME1) != null){
					descricao = map.get(UtilsConstants.TIME1) + " do Time Vermelho";
				}else{
					descricao = map.get(UtilsConstants.TIME2) + " do Time Azul";
				}
			}else if(evento.equals(UtilsConstants.TERMINO)){
				descricao = "Time Vermelho "+  map.get(UtilsConstants.PLACAR_TIME1) +
						" X " + map.get(UtilsConstants.PLACAR_TIME2) + "Time Azul"; 
				
			}else if(evento.equals(UtilsConstants.CARTAO)){
				descricao = "O jogador "+ map.get(UtilsConstants.JOGADOR) + " recebeu um "+
						map.get(UtilsConstants.CARTAO_MSG) ;
				
			}
			else{
				
				descricao = map.get(UtilsConstants.PLACAR_TIME1) + " Para o Time Vermelho "
				+ map.get(UtilsConstants.PLACAR_TIME2) + " Para o Time Azul";
				
			}
			
			
			mapaLayout.put(UtilsConstants.EVENTO, (String) map.get(UtilsConstants.EVENTO));
			mapaLayout.put(UtilsConstants.TEMPO, (String) map.get(UtilsConstants.TEMPO));
			mapaLayout.put(UtilsConstants.DESCRICAO, descricao);
			
			hasParaLayout.add(mapaLayout);
		}
		
		
		String[] from = new String[]{UtilsConstants.EVENTO, UtilsConstants.TEMPO,
			UtilsConstants.DESCRICAO};
		
		int[] to = new int[]{R.id.eventoList,R.id.tempoList,R.id.descricaList};
		
		int layout = R.layout.log_layout;
		
		ListView lv = (ListView)findViewById(R.id.listLog);
		lv.setAdapter(new SimpleAdapter(this, hasParaLayout, layout, from, to));
		
	}

	public void irParaMenuPrincipal(View view) {
		Intent intent = new Intent(this, Dashboard.class);
		startActivity(intent);
	}
	public void irParaOutraPartida(View view) {
		Intent intent = new Intent(this, DefinirTimesActivity.class);
		startActivity(intent);
	}

}
