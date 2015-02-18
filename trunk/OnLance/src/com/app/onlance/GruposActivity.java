package com.app.onlance;

import java.util.ArrayList;
import java.util.List;

import Utils.UtilsConstants;
import Utils.UtilsMetodos;
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
	private List<GrupoFacade> gruposFacade; 

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

				GrupoFacade facade = gruposFacade.get(position);
				
				Intent intent = new Intent(GruposActivity.this,
						GrupoPerfilActivity.class);
				
				intent.putExtra(UtilsConstants.ID_GRUPO, facade.getId());
				startActivity(intent);

			}
		});
	}

	private void init() {

		List<Grupo> grupoList = bo.getGrupoByJogador(jogador);
		
		gruposFacade = new ArrayList<GrupoFacade>();
		
		if (grupoList != null && grupoList.size() > 0) {

			for (Grupo grupo : grupoList) {
				parseEntityInFacade(gruposFacade, grupo);
			}

		}


		BaseAdapterGrupos adapter = new BaseAdapterGrupos(this, gruposFacade);
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