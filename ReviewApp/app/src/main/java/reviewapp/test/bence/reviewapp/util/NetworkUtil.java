package reviewapp.test.bence.reviewapp.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {

    public static boolean isNetworkAvailable(Context context) {
        boolean networkAvailable = false;

        if (context == null) {
            networkAvailable = true;
        } else {
            final Object systemService = context.getSystemService(Context.CONNECTIVITY_SERVICE);

            if (systemService instanceof ConnectivityManager) {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) systemService).getActiveNetworkInfo();
                if (activeNetworkInfo != null) {
                    networkAvailable = activeNetworkInfo.isConnected();
                }
            }
        }

        return networkAvailable;
    }
}
