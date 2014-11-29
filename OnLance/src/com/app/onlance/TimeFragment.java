package com.app.onlance;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class TimeFragment extends Fragment {

	String[] quantJogadores = new String[] { "04", "05", "06", "07", "08", "09",
			"10", "11", "12" };
	Spinner spinnerQuantJog;


	View rootView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.config_avancada_fragment, container, false);
		
		
		init();
		return rootView;
	}
	
	private void init() {
		
		ArrayAdapter<String> adapterJog = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_dropdown_item, quantJogadores);

		spinnerQuantJog = (Spinner) rootView.findViewById(R.id.quantJogSpnieer);

		spinnerQuantJog.setAdapter(adapterJog);
	}
	
	public Spinner getSpinnerQuantJog() {
		return spinnerQuantJog;
	}

	public void setSpinnerQuantJog(Spinner spinnerQuantJog) {
		this.spinnerQuantJog = spinnerQuantJog;
	}
	
	public String[] getQuantJogadores() {
		return quantJogadores;
	}

	public void setQuantJogadores(String[] quantJogadores) {
		this.quantJogadores = quantJogadores;
	}

}
