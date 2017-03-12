package followers.githubusersfollowers.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.util.Log;
import android.widget.ImageView;

import followers.githubusersfollowers.data.injection.utils.GithubRepoHelper;
import followers.githubusersfollowers.model.GithubUser;
import followers.githubusersfollowers.view.activities.GithubUserDetailActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetailsViewModel extends BaseObservable {

    public static ImageView iView;
    private Context ctx;
    private GithubUser user;
    private String avatarUrl;

    public UserDetailsViewModel(Context ctx, GithubUser user) {
        this.ctx = ctx;
        this.user = user;
    }

    public static ImageView getiView() {
        return iView;
    }

    public static void setiView(ImageView iView) {
        UserDetailsViewModel.iView = iView;
    }

    @Bindable
    public String getAvatarUrl() {
        avatarUrl = user.getAvatarUrl();
        return avatarUrl;
    }

    public void gitUserDetails(final ImageView iView) {
        Call<GithubUser> githubUser = GithubRepoHelper.getGithubRepoService().getUser(user.getLogin());
        githubUser.enqueue(new Callback<GithubUser>() {


            @Override
            public void onResponse(Call<GithubUser> call, Response<GithubUser> response) {


                if (response.isSuccessful()) {
                    user = response.body();
                    Log.d("VARSHAVARSHA", response.code() + " user " + response.body().toString());
                    Intent intent = new Intent(ctx, GithubUserDetailActivity.class);
                    intent.putExtra("user", user);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) ctx, iView, "profile");
                        ctx.startActivity(intent, options.toBundle());

                    }
                    ctx.startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<GithubUser> call, Throwable t) {

            }
        });


    }

    @Bindable
    public String getLogin() {
        return user.getLogin();
    }

    @Bindable
    public String getName() {
        return user.getName();
    }

    @Bindable
    public String getLocation() {
        return user.getLocation();
    }

    @Bindable
    public String getEmail() {
        return user.getEmail();
    }

    @Bindable
    public String getPublicRepos() {
        return String.valueOf(user.getPublicRepos());
    }

    @Bindable
    public String getFollowers() {

        return String.valueOf(user.getFollowers());
    }

    @Bindable
    public String getFollowing() {
        return String.valueOf(user.getFollowing());
    }

}