package com.app.onlance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Dashboard extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);

	}

	public void onClickComunidade(View view) {
		Intent intent = new Intent(this, AmigosGruposActivity.class);
		startActivity(intent);
	}

	public void onClickTime(View view) {

	}

	public void onClickConfig(View view) {
		Intent intent = new Intent(this, ConfigGeralActivity.class);
		startActivity(intent);
	}

	public void onClickPartida(View view) {
		Intent intent = new Intent(this, ModoJogoActivity.class);
		startActivity(intent);
	}
}
