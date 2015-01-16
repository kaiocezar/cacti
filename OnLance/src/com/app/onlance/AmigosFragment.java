package com.app.onlance;

import java.util.ArrayList;
import java.util.List;

import com.app.adapter.BaseAdapterAmigos;
import com.app.facade.JogadorFacade;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class AmigosFragment extends Fragment {

	private View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.amigos_fragment, container, false);
		init();
		return rootView;
	}

	private void init() {
		List<JogadorFacade> amigos = new ArrayList<JogadorFacade>();
		JogadorFacade kaio = new JogadorFacade();
		JogadorFacade geyson = new JogadorFacade();
		JogadorFacade helton = new JogadorFacade();
		JogadorFacade joao = new JogadorFacade();
		JogadorFacade ze = new JogadorFacade();
		
		kaio.setNome("Kaio");
		kaio.getHistorico().setGol(10);
		kaio.getHistorico().setJogos(10);
		
		geyson.setNome("Gesyon");
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
		
		BaseAdapterAmigos adapter = new BaseAdapterAmigos(getActivity(), amigos);
		
		
		ListView viewAmigos = (ListView) rootView.findViewById(R.id.listViewAmigos);
		
		viewAmigos.setAdapter(adapter);
	}

}