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
import com.app.onlance.JogadorForList;
import com.app.onlance.R;

public class BaseAdapterEventos extends BaseAdapter {

	private List<EventoFacade> jogadores;
	private Context context;
	private LayoutInflater mInflater;

	public BaseAdapterEventos(Context contexto, List<EventoFacade> jogadores) {
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
	public EventoFacade getItem(int arg0) {
		return jogadores.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View view, ViewGroup arg2) {

		EventoFacade item = jogadores.get(position);
		// infla o layout para podermos preencher os dados
		if (view == null) {
			view = mInflater.inflate(R.layout.layout_list_evento, null);
		}

		TextView data = (TextView) view.findViewById(R.id.dataValue);
		TextView local = (TextView) view.findViewById(R.id.localValue);
		TextView quant = (TextView) view.findViewById(R.id.quantValue);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		data.setText(sdf.format(item.getData()));
		local.setText(item.getLocal());
		if (item.getJogador() != null) {
			quant.setText(String.valueOf(item.getJogador().size()));
		}

		return view;
	}

}
