package Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.facebook.Session;
import com.facebook.Session.NewPermissionsRequest;

public class UtilsMetodos {

	private static UtilsMetodos utils;

	private UtilsMetodos() {
	}

	public static UtilsMetodos getInscace() {

		if (utils == null) {
			utils = new UtilsMetodos();
		}

		return utils;
	}
	
	public int getId(){
		
		Random gerador = new Random();
		 
        int numero = gerador.nextInt(1000) + 1;
 
       return numero;
	}

	public boolean isConectadoFacebook() {
		boolean retorno = false;

		Session session = Session.getActiveSession();
		if (session != null && session.isOpened()) {
			retorno = true;
		}

		return retorno;
	}

	public boolean validarUsuario(Activity contexto) {
		boolean retorno = false;

		Session session = Session.getActiveSession();
		List<String> permission = session.getPermissions();
		List<String> newPermission = UtilsConstants.permissions_app;

		if (hasNotPermission(permission, newPermission)) {
			Session.NewPermissionsRequest newPermiRequest = new NewPermissionsRequest(
					contexto, newPermission);
			session.requestNewPublishPermissions(newPermiRequest);
		} else {
			retorno = true;
		}

		return retorno;
	}

	private boolean hasNotPermission(List<String> permission,
			List<String> newPermission) {

		boolean retorno = true;

		for (String thisPermission : permission) {

			for (String newPer : newPermission) {

				if (!thisPermission.equals(newPer)) {
					retorno = false;
				}

			}
		}

		return retorno;
	}

	public void toast(final Context context, final String valor) {
		Toast.makeText(context, valor, Toast.LENGTH_LONG).show();
	}
	
	public  String convertInputStreamToString(InputStream inputStream)
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
