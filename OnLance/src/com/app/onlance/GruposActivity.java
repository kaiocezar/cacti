package com.app.onlance;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.app.adapter.BaseAdapterGrupos;
import com.app.facade.GrupoFacade;

public class GruposActivity extends Activity {

	private ListView listGrupos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grupos);

		ActionBar actionBar = getActionBar();
		actionBar.setTitle(R.string.grupos_UPPER);

		init();
		bind();
	}

	private void bind() {
		listGrupos.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				Intent intent = new Intent(GruposActivity.this, GrupoPerfilActivity.class);
				startActivity(intent);

			}
		});
	}

	private void init() {
		List<GrupoFacade> grupos = new ArrayList<GrupoFacade>();

		GrupoFacade gfInex = new GrupoFacade();
		gfInex.setNome("Inex");
		gfInex.setDescricao("Top jogadores de SI");

		GrupoFacade gfAwake = new GrupoFacade();
		gfAwake.setNome("Awake");
		gfAwake.setDescricao("Top jogadores de CC");

		GrupoFacade gfBuildIt = new GrupoFacade();
		gfBuildIt.setNome("BuildIt");
		gfBuildIt.setDescricao("Top jogadores de EC");

		grupos.add(gfInex);
		grupos.add(gfAwake);
		grupos.add(gfBuildIt);

		BaseAdapterGrupos adapter = new BaseAdapterGrupos(this, grupos);
		listGrupos = (ListView) findViewById(R.id.listViewGrupos);
		listGrupos.setAdapter(adapter);
	}

	public void onClickNewGroup(View view) {
		Intent intent = new Intent(this, AddNewGroupActivity.class);
		startActivity(intent);
	}

}