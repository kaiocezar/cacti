package com.app.onlance;

import java.util.ArrayList;
import java.util.List;

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

import com.app.adapter.BaseAdapterGrupos;
import com.app.bo.JogadorBo;
import com.app.facade.GrupoFacade;
import com.app.vo.Grupo;
import com.app.vo.Jogador;

public class GruposActivity extends Activity {

	private ListView listGrupos;
	SharedPreferences sharedpreferences;
	private JogadorBo bo;
	private Jogador jogador;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grupos);

		ActionBar actionBar = getActionBar();
		actionBar.setTitle(R.string.grupos_UPPER);
		bo = new JogadorBo(this);
		sharedpreferences = getSharedPreferences(JogadorBo.MyPREFERENCES,
				Context.MODE_PRIVATE);

		jogador = bo.findById(sharedpreferences.getInt(JogadorBo.UserId, 0));

		init();
		bind();
	}

	
	@Override
	protected void onResume() {
		super.onResume();
		init();
	}
	
	private void bind() {
		listGrupos.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent intent = new Intent(GruposActivity.this,
						GrupoPerfilActivity.class);
				startActivity(intent);

			}
		});
	}

	private void init() {

		List<Grupo> grupoList = bo.getGrupoByJogador(jogador);
		
		List<GrupoFacade> grupos = new ArrayList<GrupoFacade>();
		GrupoFacade g1 = new GrupoFacade();
		g1.setNome("SIFC");
		g1.setDescricao("Galera de SI");
		grupos.add(g1);

		GrupoFacade g2 = new GrupoFacade();
		g2.setNome("CCFC");
		g2.setDescricao("Galera de CC");
		grupos.add(g2);
		
		GrupoFacade g3 = new GrupoFacade();
		g3.setNome("ECFC");
		g3.setDescricao("Galera de EC");
		grupos.add(g3);
		
		if (grupoList != null && grupoList.size() > 0) {

			for (Grupo grupo : grupoList) {
				parseEntityInFacade(grupos, grupo);
			}

		}


		BaseAdapterGrupos adapter = new BaseAdapterGrupos(this, grupos);
		listGrupos = (ListView) findViewById(R.id.listViewGrupos);
		listGrupos.setAdapter(adapter);
	}

	public void parseEntityInFacade(List<GrupoFacade> list, Grupo entity) {

		if (list != null && entity != null) {
			GrupoFacade facade = new GrupoFacade();
			facade.setDescricao(entity.getDescricao());
			facade.setId(entity.getId());
			facade.setNome(entity.getNome());
			list.add(facade);
		}

	}

	public void onClickNewGroup(View view) {
		Intent intent = new Intent(this, AddNewGroupActivity.class);
		startActivity(intent);
	}

}