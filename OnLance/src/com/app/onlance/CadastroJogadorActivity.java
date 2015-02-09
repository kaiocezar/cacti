package com.app.onlance;

import java.sql.SQLException;

import Utils.UtilsMetodos;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
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

	private Button btnCriarConta;
	private EditText emailEditText;
	private EditText senhaEditText;
	private EditText confirmarSenhaEditText;
	private EditText numTelefoneEditText;
	private TextView termo_uso;
	private JogadorBo bo;
	private Jogador jogador;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastro_usuario);

		// Recuperação dos Widgets
		btnCriarConta = (Button) findViewById(R.id.criar_conta_cadastro);
		emailEditText = (EditText) findViewById(R.id.email_cadastro);
		senhaEditText = (EditText) findViewById(R.id.senha_cadastro);
		confirmarSenhaEditText = (EditText) findViewById(R.id.confirmar_senha_cadastro);
		numTelefoneEditText = (EditText) findViewById(R.id.number_phone_cadastro);
		termo_uso = (TextView) findViewById(R.id.termos_uso);


		// Listeners
		OnClickListener oclBtnCriarConta = new OnClickListener() {
			public void onClick(View v) {
				try {

					jogador = new Jogador();
					if (senhaEditText
							.getText()
							.toString()
							.equals(confirmarSenhaEditText.getText().toString())) {
						jogador.setEmail(emailEditText.getText().toString());
						jogador.setNome(emailEditText.getText().toString()
								.split("@")[0]);
						jogador.setSenha(senhaEditText.getText().toString());
						jogador.setNumeroTelefone(numTelefoneEditText.getText()
								.toString());
						bo = new JogadorBo(CadastroJogadorActivity.this);
						int result = bo.save(jogador);

						if (result == 1) {
							Context context = getApplicationContext();
							UtilsMetodos
									.getInscace()
									.toast(context,
											context.getString(R.string.msg_salvar_jogador));

						} else {
							throw new SQLException();
						}
					} else {
						// Lancar exceção
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				bo.closeDb();
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
