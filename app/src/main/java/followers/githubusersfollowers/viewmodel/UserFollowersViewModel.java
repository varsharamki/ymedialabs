package followers.githubusersfollowers.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.regex.Pattern;

import followers.githubusersfollowers.data.injection.utils.GithubRepoHelper;
import followers.githubusersfollowers.model.GithubUserFollowers;
import followers.githubusersfollowers.view.activities.GithubUserFollowersActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserFollowersViewModel extends BaseObservable {

    private Context ctx;
    private GithubUserFollowers followers;
    private ArrayList<GithubUserFollowers> list;
    private String avatarUrl;

    public UserFollowersViewModel(Context ctx, GithubUserFollowers followers) {
        this.ctx = ctx;
        this.followers = followers;
    }

    public String getAvatarUrl() {
        avatarUrl = followers.getAvatarUrl();
        ;
        return avatarUrl;
    }


    public String getLogin() {
        return followers.getLogin();
    }

    public void onClickButton(View view) {

        Call<ArrayList<GithubUserFollowers>> githubUserFollowers = GithubRepoHelper.getGithubRepoService().getUserFollowers(followers.getLogin());
        githubUserFollowers.enqueue(new Callback<ArrayList<GithubUserFollowers>>() {
            @Override
            public void onResponse(Call<ArrayList<GithubUserFollowers>> call, Response<ArrayList<GithubUserFollowers>> response) {
                if (response.code() == 200) {
                    list = response.body();
                    Intent followIntent = new Intent(ctx, GithubUserFollowersActivity.class);
                    followIntent.putParcelableArrayListExtra("mylist", list);
                    ctx.startActivity(followIntent);

                }
            }

            @Override
            public void onFailure(Call<ArrayList<GithubUserFollowers>> call, Throwable t) {
                Log.d("VARSHAVARSHA", "FAILIUE   ");
            }
        });

    }

    public boolean isValidUserName(String s) {
        boolean isValid = false;
        // Login may only contain alphanumeric characters or single hyphens,
        // //and cannot begin or end with a hyphen
        String regex = "^[A-Za-z0-9]";
        return Pattern.matches(regex, s);
    }

}

