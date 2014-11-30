package com.app.onlance;

import java.util.List;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Dashboard extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);

	}

	public void onClickAmigo(View view) {
		
	}

	public void onClickTime(View view) {

	}

	public void onClickConfig(View view) {

	}

	public void onClickPartida(View view) {
		Intent intent = new Intent(this, ModoJogoActivity.class);
		startActivity(intent);
	}
}
