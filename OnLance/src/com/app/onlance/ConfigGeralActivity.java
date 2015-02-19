package com.app.onlance;

import com.app.bo.JogadorBo;
import com.facebook.Session;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
			SharedPreferences sharedpreferences = getSharedPreferences(JogadorBo.MyPREFERENCES,
					Context.MODE_PRIVATE);
			
			 Editor editor = sharedpreferences.edit();
			 editor.putInt(JogadorBo.UserId, 0);
			 editor.commit();
			
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		}
		
	}
}
