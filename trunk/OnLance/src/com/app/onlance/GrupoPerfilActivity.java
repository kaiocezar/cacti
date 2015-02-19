package com.app.onlance;

import java.util.ArrayList;
import java.util.List;

import com.app.adapter.BaseAdapterAmigos;
import com.app.bo.GrupoBo;
import com.app.facade.JogadorFacade;
import com.app.vo.Jogador;

import Utils.UtilsConstants;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;

public class GrupoPerfilActivity extends Activity {

	private GrupoBo grupoBo;
	private int idGrupo;
	private ListView list;
	private BaseAdapterAmigos adpter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grupo_perfil);
		grupoBo = new GrupoBo(this);
		idGrupo = getIntent().getIntExtra(UtilsConstants.ID_GRUPO, 0);
		
		init();
		bind();
	
	}
	
	private void bind() {
		list.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				final Dialog dialog = new Dialog(GrupoPerfilActivity.this);
				dialog.setContentView(R.layout.layout_dialog);
				dialog.setTitle("Confirmação...");
	 
				Button simButon = (Button) dialog.findViewById(R.id.confirmarDialog);
				Button naoButon = (Button) dialog.findViewById(R.id.cancelarDialog);
				// if button is clicked, close the custom dialog
				simButon.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						
						
					}
				});

				naoButon.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.hide();
						
					}
				});
	 
				dialog.show();
				
				return false;
			}
		});
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		init();
	}
	private void init() {
		 list = (ListView) findViewById(R.id.listPerfilGrupo);
		List<JogadorFacade> amigos = new ArrayList<JogadorFacade>();
		
		List<Jogador> jogadorList = grupoBo.getJogadorListByGrupo(idGrupo);
		for (Jogador jogador : jogadorList) {
			jogadorParseFacade(amigos, jogador);
		}

		 adpter = new BaseAdapterAmigos(this, amigos,grupoBo,idGrupo);
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