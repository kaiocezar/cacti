package com.app.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.facade.GrupoFacade;
import com.app.onlance.R;

public class BaseAdapterGrupos extends BaseAdapter {

	private Context context;
	private List<GrupoFacade> grupos;

	public BaseAdapterGrupos(Context context, List<GrupoFacade> grupos) {
		this.context = context;
		this.grupos = grupos;
	}

	@Override
	public int getCount() {
		return grupos.size();
	}

	@Override
	public Object getItem(int position) {
		return grupos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Recupera o estado da posição atual
		GrupoFacade grupo = grupos.get(position);

		// Cria uma instância do layout XML para os objetos correspondentes na
		// View
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.grupos_list, null);

		// Nome do grupo
		TextView textName = (TextView) view.findViewById(R.id.nomeGrupo);
		textName.setText(grupo.getNome());

		// Descrição
		TextView textDesc = (TextView) view.findViewById(R.id.descricaoGrupo);
		textDesc.setText(grupo.getDescricao());

		// Imagem
		// ImageView img = (ImageView) view.findViewById(R.id.imageGrupo);
		// img.setImageResource(grupo.getImage());

		return view;
	}

}