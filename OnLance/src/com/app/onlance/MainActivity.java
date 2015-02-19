package com.app.onlance;

import java.sql.SQLException;
import java.util.List;

import Utils.UtilsConstants;
import Utils.UtilsMetodos;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.bo.JogadorBo;
import com.app.vo.Jogador;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

public class MainActivity extends Activity {

	
	private EditText login;
	private EditText senha;
	private Button entrar;
	private JogadorBo jogadorBo;
	
	UiLifecycleHelper uiHelper;
	SharedPreferences sharedpreferences;
	private Session.StatusCallback callback = new StatusCallback() {

		@Override
		public void call(Session session, SessionState state,
				Exception exception) {

			onSessionStateChaged(session, state, exception);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		LoginButton lb = (LoginButton) findViewById(R.id.login_face);
		lb.setPublishPermissions(UtilsConstants.permissions_app);
		sharedpreferences = getSharedPreferences(JogadorBo.MyPREFERENCES,
				Context.MODE_PRIVATE);
		uiHelper = new UiLifecycleHelper(this, callback);
		uiHelper.onCreate(savedInstanceState);
		
		jogadorBo = new JogadorBo(this);
		
		login = (EditText) findViewById(R.id.login);
		senha =  (EditText)  findViewById(R.id.senha);
		entrar = (Button ) findViewById(R.id.entrar);

		validarEntrada();
		bind();
	}
	

	private void bind() {
		entrar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				try {
				Jogador jog = jogadorBo.findByEmail(login.getText().toString());
				
				if(jog.getSenha().equals(senha.getText().toString())){
					jogadorBo.setIdJogadorSharePreference(jog.getId());
					validarEntrada();
				}else{
					UtilsMetodos.getInscace().toast(MainActivity.this, "Jogador não esta no clube");
				}
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
	}


	private void validarEntrada() {

		if (sharedpreferences.getInt(JogadorBo.UserId, 0) != 0
				|| (UtilsMetodos.getInscace().isConectadoFacebook() && UtilsMetodos
						.getInscace().validarUsuario(this))) {
			proximaTelaEvent();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();

		Session session = Session.getActiveSession();
		if (session != null && (session.isOpened() || session.isClosed())) {
			onSessionStateChaged(session, session.getState(), null);
		}

		validarEntrada();

		uiHelper.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		uiHelper.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		uiHelper.onDestroy();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		// outState.putBoolean(KEY, reauth);
		uiHelper.onSaveInstanceState(outState);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	public void onSessionStateChaged(final Session session, SessionState state,
			Exception exception) {

		if (session != null && session.isOpened()) {

			Log.i("Script", "Usuario conectado");
			Request.newMeRequest(session, new Request.GraphUserCallback() {

				@Override
				public void onCompleted(GraphUser user, Response response) {

					if (user != null) {
						Log.i("Script", "Nome" + user.getName());
						Log.i("Script", "NomeFirst" + user.getFirstName());
						Log.i("Script", "Email" + user.getProperty("email"));
						Log.i("Script", "ID" + user.getId());
						getMyFriends(session);

					}

				}
			}).executeAsync();

		} else {
			Log.i("Script", "Usuario Não conectado");
		}

	}

	public void getMyFriends(Session session) {
		Request.newMyFriendsRequest(session,
				new Request.GraphUserListCallback() {

					@Override
					public void onCompleted(List<GraphUser> users,
							Response response) {
						if (users != null) {
							Log.i("Script", "NUmero de amigos" + users.size());
						}

						Log.i("Script", "Response" + response);
					}
				}).executeAsync();
	}

	public void proximaTela(View view) {

		// shareContent();

		proximaTelaEvent();
	}

	public void proximaTelaEvent() {
		Intent intent = new Intent(this, Dashboard.class);

		startActivity(intent);
	}

	public void proximaTelaCadastro(View view) {
		Intent intent = new Intent(this, CadastroJogadorActivity.class);

		startActivity(intent);
	}

	public void shareContent() {
		if (UtilsMetodos.getInscace().isConectadoFacebook()
				&& UtilsMetodos.getInscace().validarUsuario(this)) {

			Session session = Session.getActiveSession();
			Bundle paramns = new Bundle();
			paramns.putString("name", "TesteNome");
			paramns.putString("caption", "Teste SubTitulo");
			paramns.putString("description", "Teste descrição");
			paramns.putString("link", "http://google.com.br");
			paramns.putString("picture",
					"http://www.informatoz.com/imagens/ico_cobertura/bola.png");

			Request.Callback call = new Request.Callback() {

				@Override
				public void onCompleted(Response response) {
					if (response.getError() == null) {
						Toast.makeText(MainActivity.this, "Sucesso",
								Toast.LENGTH_LONG).show();
					} else {
						Toast.makeText(MainActivity.this, "Falha",
								Toast.LENGTH_LONG).show();
					}
				}
			};

			Request re = new Request(session, "/me/feed", paramns,
					HttpMethod.POST, call);
			re.executeAsync();
		}

	}

}
