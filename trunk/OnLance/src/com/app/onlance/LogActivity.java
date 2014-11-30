package com.app.onlance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LogActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.log_partida);
	}

	public void irParaMenuPrincipal(View view) {
		Intent intent = new Intent(this, Dashboard.class);
		startActivity(intent);
	}

}
