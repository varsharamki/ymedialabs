package followers.githubusersfollowers;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.support.v4.content.ContextCompat;

public class GithubUsersFollowersApplication extends Application{
 private static Context context;

    public void onCreate() {
        super.onCreate();
       GithubUsersFollowersApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return GithubUsersFollowersApplication.context;
    }

    public static boolean isNetworkAvailable(Context context) {
        int network_permission= ContextCompat.checkSelfPermission(getAppContext(), Manifest.permission.INTERNET);
        int network_state_permission= ContextCompat.checkSelfPermission(getAppContext(), Manifest.permission.INTERNET);

        if(network_permission== PackageManager.PERMISSION_GRANTED && network_state_permission==PackageManager.PERMISSION_GRANTED) {
            ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            return connectivityManager.getActiveNetworkInfo() != null;
        }
        return false;
    }
}