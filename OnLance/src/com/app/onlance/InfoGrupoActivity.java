package com.app.onlance;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.app.adapter.BaseAdapterAmigos;
import com.app.facade.JogadorFacade;

public class InfoGrupoActivity extends Activity{


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.infor_grupo);
		init();
	}
	
	
	private void init() {
		ListView list = (ListView) findViewById(R.id.listInfGrupo);

		List<JogadorFacade> amigos = new ArrayList<JogadorFacade>();
		JogadorFacade kaio = new JogadorFacade();
		JogadorFacade geyson = new JogadorFacade();
		JogadorFacade helton = new JogadorFacade();
		JogadorFacade joao = new JogadorFacade();
		JogadorFacade ze = new JogadorFacade();

		kaio.setNome("Kaio");
		kaio.getHistorico().setGol(10);
		kaio.getHistorico().setJogos(10);

		geyson.setNome("Geyson");
		geyson.getHistorico().setGol(12);
		geyson.getHistorico().setJogos(20);

		helton.setNome("helton");
		helton.getHistorico().setGol(1);
		helton.getHistorico().setJogos(50);

		joao.setNome("joao");
		joao.getHistorico().setGol(1);
		joao.getHistorico().setJogos(10);

		ze.setNome("ze");
		ze.getHistorico().setGol(1000);
		ze.getHistorico().setJogos(1);

		amigos.add(kaio);
		amigos.add(geyson);
		amigos.add(helton);
		amigos.add(joao);
		amigos.add(ze);

		BaseAdapterAmigos adpter = new BaseAdapterAmigos(this, amigos);

		list.setAdapter(adpter);

	}
	
}
