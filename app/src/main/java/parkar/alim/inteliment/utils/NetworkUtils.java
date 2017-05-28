package parkar.alim.inteliment.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;


/**
 * Helper class containing Network related utils
 */
public class NetworkUtils {

  /**
   * Check whether network is available on the device.
   * @param context Context to fetch the system service.
   * @return <code>true</code> if connected to a network <code>false</code> otherwise
   */
  public static boolean isNetworkAvailable(@NonNull Context context) {
    ConnectivityManager connectivityManager =
      (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
  }
}
