package com.app.onlance;

import java.sql.SQLException;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.app.bo.JogadorBo;
import com.app.vo.Jogador;

public class CadastroJogadorActivity extends Activity {

	Button btnCriarConta;
	EditText emailEditText;
	EditText senhaEditText;
	EditText confirmarSenhaEditText;
	EditText numTelefoneEditText;
	TextView termo_uso;
	JogadorBo bo;
	Jogador jogador;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastro_usuario);

		// Recupera��o dos Widgets
		btnCriarConta = (Button) findViewById(R.id.criar_conta_cadastro);
		emailEditText = (EditText) findViewById(R.id.email_cadastro);
		senhaEditText = (EditText) findViewById(R.id.senha_cadastro);
		confirmarSenhaEditText = (EditText) findViewById(R.id.confirmar_senha_cadastro);
		numTelefoneEditText = (EditText) findViewById(R.id.number_phone_cadastro);
		termo_uso = (TextView) findViewById(R.id.termos_uso);
		termo_uso
				.setText(Html
						.fromHtml("Ao continuar, voc� tamb�m aceita as <u><b>Condi��es de servi�o</b></u>, a <u><b>Pol�tica de privacidade</b></u> e as <u><b>Condi��es de servi�o</b></u> para celular do Olho no Lance."));

		// Listeners
		OnClickListener oclBtnCriarConta = new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {

					jogador = new Jogador();
					if (senhaEditText.getText().toString().equals(
							confirmarSenhaEditText.getText().toString())) {
						jogador.setEmail(emailEditText.getText().toString());
						jogador.setNome(emailEditText.getText().toString()
								.split("@")[0]);
						jogador.setSenha(senhaEditText.getText().toString());
						jogador.setNumeroTelefone(numTelefoneEditText.getText()
								.toString());
						bo = new JogadorBo(CadastroJogadorActivity.this);
						new JogadorBo(CadastroJogadorActivity.this)
								.save(jogador);
					} else {
						// Lancar exce��o
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				onDestroy();
			}
		};

		btnCriarConta.setOnClickListener(oclBtnCriarConta);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		bo.closeDb(); // Destroy o BD
	}

}
