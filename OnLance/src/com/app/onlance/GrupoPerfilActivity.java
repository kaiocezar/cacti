package com.app.onlance;

import java.util.ArrayList;
import java.util.List;

import com.app.adapter.BaseAdapterAmigos;
import com.app.bo.GrupoBo;
import com.app.facade.JogadorFacade;
import com.app.vo.Jogador;

import Utils.UtilsConstants;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

public class GrupoPerfilActivity extends Activity {

	private GrupoBo grupoBo;
	private int idGrupo;
	private List<JogadorFacade> amigos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grupo_perfil);
		grupoBo = new GrupoBo(this);
		idGrupo = getIntent().getIntExtra(UtilsConstants.ID_GRUPO, 0);
		
		init();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		init();
	}
	private void init() {
		ListView list = (ListView) findViewById(R.id.listPerfilGrupo);
		amigos = new ArrayList<JogadorFacade>();
		
		List<Jogador> jogadorList = grupoBo.getJogadorListByGrupo(idGrupo);
		for (Jogador jogador : jogadorList) {
			jogadorParseFacade(amigos, jogador);
		}

		BaseAdapterAmigos adpter = new BaseAdapterAmigos(this, amigos);
		list.setAdapter(adpter);

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.grupos_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_add_member:
			
			Intent in =  new Intent(GrupoPerfilActivity.this,AddJogadorInGruposActivity.class);
			in.putExtra(UtilsConstants.ID_GRUPO, idGrupo);
			startActivity(in);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

}