package Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionDetector {

	private static Context context;
	private static ConnectionDetector instace;

	public static ConnectionDetector getInstance(Context contextParam){
		context = contextParam;
		if(instace == null){
			instace = new ConnectionDetector();
		}
		return instace;
	}
	
	private ConnectionDetector() {}

	public boolean isConnectingToInternet() {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null)
				for (int i = 0; i < info.length; i++)
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
		}
		return false;
	}
}
