package com.app.onlance;

import com.facebook.Session;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ConfigGeralActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.config_geral);
	}

	public void onClickSair(View view)
	{
		if(Session.getActiveSession()!=null)
		{
			Session.getActiveSession().closeAndClearTokenInformation();
			Session.setActiveSession(null);
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		}
		
	}
}
