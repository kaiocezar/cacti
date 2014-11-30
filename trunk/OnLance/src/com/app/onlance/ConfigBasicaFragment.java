package com.app.onlance;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ConfigBasicaFragment extends Fragment {

	String[] quantGols = new String[] { "01", "02", "03", "04", "05" };
	String[] quantTempo = new String[] { "05", "10", "15", "20", "25", "30",
			"35", "40", "45" };
	Spinner spinnerQuantGols;
	Spinner spinnerQuantTemp;

	public Spinner getSpinnerQuantGols() {
		return spinnerQuantGols;
	}

	public void setSpinnerQuantGols(Spinner spinnerQuantGols) {
		this.spinnerQuantGols = spinnerQuantGols;
	}

	public Spinner getSpinnerQuantTemp() {
		return spinnerQuantTemp;
	}

	public void setSpinnerQuantTemp(Spinner spinnerQuantTemp) {
		this.spinnerQuantTemp = spinnerQuantTemp;
	}

	View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.config_basica_fragment, container,
				false);

		init();
		return rootView;
	}

	private void init() {

		ArrayAdapter<String> adapterGols = new ArrayAdapter<String>(
				getActivity(), android.R.layout.simple_spinner_dropdown_item,
				quantGols);
		ArrayAdapter<String> adapterTemp = new ArrayAdapter<String>(
				getActivity(), android.R.layout.simple_spinner_dropdown_item,
				quantTempo);

		spinnerQuantGols = (Spinner) rootView
				.findViewById(R.id.quantGolSpnieer);
		spinnerQuantTemp = (Spinner) rootView
				.findViewById(R.id.quantTempoSpnieer);

		spinnerQuantGols.setAdapter(adapterGols);
		spinnerQuantTemp.setAdapter(adapterTemp);
		
		spinnerQuantGols.setSelection(1);
		spinnerQuantTemp.setSelection(1);
	}

}
