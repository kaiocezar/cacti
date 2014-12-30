package com.app.onlance;

import java.util.List;

import Utils.UtilsMetodos;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BaseAdapterOnLance extends BaseAdapter {

	List<JogadorForList> jogadores;
	Context contexto;

	public BaseAdapterOnLance(Context contexto, List<JogadorForList> jogadores) {
		super();
		this.contexto = contexto;
		this.jogadores = jogadores;
	}

	@Override
	public int getCount() {
		return jogadores.size();
	}

	@Override
	public Object getItem(int arg0) {
		return jogadores.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGrup) {

		final JogadorForList jogador = jogadores.get(position);
		View viewLine;
		if (view == null) {
			LayoutInflater inflate = (LayoutInflater) contexto
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			viewLine = inflate.inflate(R.layout.layout_list_time, null);

		} else {
			viewLine = view;
		}

		TextView text = (TextView) viewLine.findViewById(R.id.textPartida);
		text.setText(jogador.getNome());
		
		ImageView img1 = (ImageView) viewLine.findViewById(R.id.imageView1);
		ImageView img2 = (ImageView) viewLine.findViewById(R.id.imageView2);
		final LinearLayout lay = (LinearLayout) viewLine.findViewById(R.id.content);
		
		
		img2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				lay.setBackgroundColor(Color.parseColor("#90CAF9")); // blue
				jogador.setTipoTela("2");
				UtilsMetodos.getInscace().toast(contexto, "clico na 2");
			}
		});
		
		img1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				jogador.setTipoTela("1");
				lay.setBackgroundColor(Color.parseColor("#EF9A9A")); // red
			}
		});

	

		if (jogador.getTipoTela().equals("0")) {
			lay.setBackgroundColor(Color.parseColor("#BDBDBD")); // gray
		} else if (jogador.getTipoTela().equals("1")) {
			lay.setBackgroundColor(Color.parseColor("#EF9A9A")); // red
		} else {
			lay.setBackgroundColor(Color.parseColor("#90CAF9")); // blue
		}
		return viewLine;
	}

}
