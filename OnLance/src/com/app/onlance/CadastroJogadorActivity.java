package com.app.onlance;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class CadastroJogadorActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastro_usuario);
		
		TextView termo_uso = (TextView) findViewById(R.id.termos_uso);
		termo_uso.setText(Html.fromHtml("Ao continuar, você também aceita as <u><b>Condições de serviço</b></u>, a <u><b>Política de privacidade</b></u> e as <u><b>Condições de serviço</b></u> para celular do Olho no Lance."));
	}
	
}
