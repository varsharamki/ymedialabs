package followers.githubusersfollowers.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import followers.githubusersfollowers.R;
import followers.githubusersfollowers.model.GithubUserFollowers;
import followers.githubusersfollowers.view.fragments.UserFollowersFragment;

public class GithubUserFollowersActivity extends AppCompatActivity {
    private ArrayList<GithubUserFollowers> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_github_users_followers);
        list = getIntent().getParcelableArrayListExtra("mylist");
        userFollowersFragment();

    }

    private void userFollowersFragment() {

        getSupportFragmentManager().beginTransaction().replace(R.id.frame, UserFollowersFragment.newInstance(list)).commit();

    }
}
