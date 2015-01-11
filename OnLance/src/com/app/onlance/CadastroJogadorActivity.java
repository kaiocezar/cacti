package com.app.onlance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import Utils.Mask;
import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.app.facade.JogadorFacade;
import com.google.gson.Gson;

public class CadastroJogadorActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastro_usuario);

		EditText textPhone = (EditText) findViewById(R.id.textPhone);
		textPhone.addTextChangedListener(Mask.insert("(##)####-#####", textPhone));
		
		TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		String number = tm.getLine1Number();
	}

	public void cadastrar(View view) {

		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					
					JogadorFacade joga = new JogadorFacade();
					joga.setEmail("teste android");
					joga.setFone("teste android");
					joga.setLogin("teste android");
					joga.setNome("teste android");
					joga.setSenha("teste android");
					
					Gson g = new Gson();
					String url = getString(R.string.servidor_desenvolvimento)+ "/persistOrMerge/Jogador/" + java.net.URLEncoder.encode(g.toJson(joga), "UTF-8");
					
					HttpClient httpclient = new DefaultHttpClient();
					HttpResponse httpResponse = null;
					httpResponse = httpclient.execute(new HttpGet(url));

					// receive response as inputStream
					InputStream inputStream = httpResponse.getEntity()
							.getContent();

					String result = null;
					// convert inputstream to string
					if (inputStream != null) {
						result = convertInputStreamToString(inputStream);
						JogadorFacade jogador = g.fromJson(result, JogadorFacade.class);

						
						//Contigo joão
						
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		thread.start();

		// create HttpClient

		// make GET request to the given URL

	}

	private static String convertInputStreamToString(InputStream inputStream)
			throws IOException {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while ((line = bufferedReader.readLine()) != null)
			result += line;

		inputStream.close();
		return result;

	}

}
