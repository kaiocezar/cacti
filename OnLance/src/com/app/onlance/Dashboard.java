package com.app.onlance;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Dashboard extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);
	}

	public void onClickVenda(View v) {
		trace("Venda");
	}

	public void onClickConsulta(View v) {
		trace("Consulta");
	}

	public void onClickConfig(View v) {
		trace("Configurações");
	}

	public void onClickSobre(View v) {
		trace("Sobre");
	}

	public void onClickHome(View v) {
		trace("Home");
	}

	public void toast(String msg) {
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
	}

	private void trace(String msg) {
		toast(msg);
	}
}
