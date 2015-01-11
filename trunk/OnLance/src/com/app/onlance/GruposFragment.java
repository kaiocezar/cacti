package com.app.onlance;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GruposFragment extends Fragment {
	
	private View rootView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.grupos_fragment, container, false);
		init();
		return rootView;
	}

	private void init() {
		List<String> grupos = new ArrayList<String>();
		grupos.add("Awake");
		grupos.add("Inex");
		grupos.add("BuidIT");
		grupos.add("Bonafide");
		grupos.add("Tinycorp");
		grupos.add("Awake");
		grupos.add("Inex");
		grupos.add("BuidIT");
		grupos.add("Bonafide");
		grupos.add("Tinycorp");
		grupos.add("Awake");
		grupos.add("Inex");
		grupos.add("BuidIT");
		grupos.add("Bonafide");
		grupos.add("Tinycorp");
		ListView viewGrupos = (ListView) rootView.findViewById(R.id.listViewGrupos);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, grupos);
		viewGrupos.setAdapter(adapter);
	}

}
