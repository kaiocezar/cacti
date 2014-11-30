package com.app.onlance;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class ModoJogoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.modo_jogo);

		MediaPlayer media = MediaPlayer.create(this.getApplicationContext(),
				R.raw.apitodefutebol);
		media.start();
	}

	public void proximaTela(View view) {

		Intent intent = new Intent(this, TimesPartidaActivity.class);

		startActivity(intent);

	}

}
