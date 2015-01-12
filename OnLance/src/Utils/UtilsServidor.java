package Utils;

import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.app.facade.JogadorFacade;
import com.google.gson.Gson;

public class UtilsServidor {

	static UtilsServidor servidor;
	private static String serverOnlance;
	private static JogadorFacade jogador;

	private UtilsServidor() {
	}

	public static UtilsServidor getInstace(String serverOn) {
		if (servidor == null) {
			servidor = new UtilsServidor();
		}
		serverOnlance = serverOn;
		jogador = null;
		return servidor;
	}

	public boolean validarUsuario(final String login, final String senha) {
		boolean retorno = false;
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					JogadorFacade joga = new JogadorFacade();
					joga.setLogin(login);
					joga.setSenha(senha);

					Gson g = new Gson();
					String url = serverOnlance
							+ "/validar/Jogador/"
							+ java.net.URLEncoder.encode(g.toJson(joga),
									"UTF-8");

					HttpClient httpclient = new DefaultHttpClient();
					HttpResponse httpResponse = null;
					httpResponse = httpclient.execute(new HttpGet(url));

					// receive response as inputStream
					InputStream inputStream = httpResponse.getEntity()
							.getContent();

					String result = null;
					// convert inputstream to string
					if (inputStream != null) {
						result = UtilsMetodos.getInscace()
								.convertInputStreamToString(inputStream);
						jogador = g.fromJson(result, JogadorFacade.class);

					}

				} catch (Exception e) {
					e.printStackTrace();
					jogador = new JogadorFacade();
				}
			}
		});

		thread.start();

		while (jogador == null) {

		}

		if (jogador.getId() != null) {
			retorno = true;
		}

		return retorno;
	}

	public JogadorFacade persistOrMerge(final JogadorFacade facade) {

		final Gson g = new Gson();
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String url = serverOnlance
							+ "/persistOrMerge/Jogador/"
							+ java.net.URLEncoder.encode(g.toJson(facade),
									"UTF-8");

					HttpClient httpclient = new DefaultHttpClient();
					HttpResponse httpResponse = null;
					httpResponse = httpclient.execute(new HttpGet(url));

					// receive response as inputStream
					InputStream inputStream = httpResponse.getEntity()
							.getContent();

					String result = null;
					// convert inputstream to string
					if (inputStream != null) {
						result = UtilsMetodos.getInscace()
								.convertInputStreamToString(inputStream);
						jogador = g.fromJson(result,
								JogadorFacade.class);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		thread.start();

		while (jogador == null) {

		}
		
		facade.setId(jogador.getId());

		return facade;
	}

}
