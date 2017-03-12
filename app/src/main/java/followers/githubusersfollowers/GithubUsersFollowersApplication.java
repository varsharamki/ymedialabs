package followers.githubusersfollowers;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;

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
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }
}