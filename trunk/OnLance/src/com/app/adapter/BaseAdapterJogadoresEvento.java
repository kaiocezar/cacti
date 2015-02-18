package com.app.adapter;

import java.text.SimpleDateFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.facade.EventoFacade;
import com.app.facade.JogadorFacade;
import com.app.facade.ParticipaFacadeAndroid;
import com.app.onlance.JogadorForList;
import com.app.onlance.R;

public class BaseAdapterJogadoresEvento extends BaseAdapter {

	private List<ParticipaFacadeAndroid> jogadores;
	private Context context;
	private LayoutInflater mInflater;

	public BaseAdapterJogadoresEvento(Context contexto, List<ParticipaFacadeAndroid> jogadores) {
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
	public ParticipaFacadeAndroid getItem(int arg0) {
		return jogadores.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View view, ViewGroup arg2) {

		ParticipaFacadeAndroid item = jogadores.get(position);
		// infla o layout para podermos preencher os dados
		if (view == null) {
			view = mInflater.inflate(R.layout.layout_list_jogador_evento, null);
		}

		TextView data = (TextView) view.findViewById(R.id.nomeJogador);

		data.setText(item.getJogador().getNome());
		
		return view;
	}

}
