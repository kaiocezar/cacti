package com.app.onlance;

import java.util.ArrayList;
import java.util.List;

import Utils.UtilsConstants;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.app.adapter.BaseAdapterAmigos;
import com.app.adapter.BaseAdapterGrupos;
import com.app.bo.GrupoBo;
import com.app.bo.JogadorBo;
import com.app.facade.GrupoFacade;
import com.app.facade.JogadorFacade;
import com.app.vo.Grupo;
import com.app.vo.Jogador;

public class AddJogadorInGruposActivity extends Activity {

	private ListView listJogador;
	private JogadorBo bo;
	private List<JogadorFacade> gruposFacade; 
	private BaseAdapterAmigos adpter;
	private int idGrupo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jogadores);

		bo = new JogadorBo(this);

		idGrupo = getIntent().getIntExtra(UtilsConstants.ID_GRUPO, 0);
		
		init();
		bind();
	}

	
	@Override
	protected void onResume() {
		super.onResume();
		init();
	}
	
	private void bind() {
		
		listJogador.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				JogadorFacade item = adpter.getItem(position);
				
				bo.participarGrupo(idGrupo,item.getId());
				
				finish();
			}
		});
	
	
	}

	private void init() {
		
		listJogador = (ListView) findViewById(R.id.listViewJogadores);
		gruposFacade = new ArrayList<JogadorFacade>();
		
		List<Jogador> jogadorList = bo.getAmigos();
		for (Jogador jogador : jogadorList) {
			jogadorParseFacade(gruposFacade, jogador);
		}

		adpter = new BaseAdapterAmigos(this, gruposFacade);
		listJogador.setAdapter(adpter);

	}
	
	public void jogadorParseFacade(List<JogadorFacade> facadeList, Jogador jogador){
		
		JogadorFacade facade = new JogadorFacade();
		facade.setEmail(jogador.getEmail());
		facade.setFone(jogador.getNumeroTelefone());
		facade.setId(jogador.getId());
		facade.setLogin(jogador.getEmail());
		facade.setNome(jogador.getNome());
		
		facadeList.add(facade);
		
	}

}