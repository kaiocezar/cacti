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
		List<String> amigos = new ArrayList<String>();
		amigos.add("Kaio");
		amigos.add("Geyson");
		amigos.add("João Vitor");
		amigos.add("Zé Carlos");
		amigos.add("Helton");
		amigos.add("Kaio");
		amigos.add("Geyson");
		amigos.add("João Vitor");
		amigos.add("Zé Carlos");
		amigos.add("Helton");
		amigos.add("Kaio");
		amigos.add("Geyson");
		amigos.add("João Vitor");
		amigos.add("Zé Carlos");
		amigos.add("Helton");
		ListView viewAmigos = (ListView) rootView.findViewById(R.id.listViewAmigos);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, amigos);
		viewAmigos.setAdapter(adapter);
	}

}