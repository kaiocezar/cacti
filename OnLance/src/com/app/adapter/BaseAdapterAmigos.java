package com.app.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.facade.JogadorFacade;
import com.app.onlance.JogadorForList;
import com.app.onlance.R;

public class BaseAdapterAmigos extends BaseAdapter {

	private List<JogadorFacade> jogadores;
	private Context context;
	private LayoutInflater mInflater;

	public BaseAdapterAmigos(Context contexto, List<JogadorFacade> jogadores) {
		super();
		this.context = contexto;
		this.jogadores = jogadores;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return jogadores.size();
	}

	@Override
	public JogadorFacade getItem(int arg0) {
		return jogadores.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View view, ViewGroup arg2) {

		JogadorFacade item = jogadores.get(position);
		// infla o layout para podermos preencher os dados
		view = mInflater.inflate(R.layout.layout_list_amigo, null);

		TextView nome = (TextView) view.findViewById(R.id.nomeJogador);
		TextView quantGols = (TextView) view.findViewById(R.id.quantGols);
		TextView quantJogos = (TextView) view.findViewById(R.id.quantJogos);

		nome.setText(item.getNome());
		quantJogos.setText(String.valueOf(item.getHistorico().getJogos()));
		quantGols.setText(String.valueOf(item.getHistorico().getGol()));
		
		
		return view;
	}

}
