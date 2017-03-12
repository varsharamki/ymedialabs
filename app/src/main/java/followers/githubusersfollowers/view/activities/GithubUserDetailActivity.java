package followers.githubusersfollowers.view.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import followers.githubusersfollowers.R;
import followers.githubusersfollowers.model.GithubUser;
import followers.githubusersfollowers.view.fragments.UserDetailFragment;


public class GithubUserDetailActivity extends AppCompatActivity {
    private GithubUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_github_user_detail);
        user = getIntent().getParcelableExtra("user");

        userDetailFragment();
    }

    private void userDetailFragment() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
         /*
            UserDetailFragment.newInstance(user).setSharedElementEnterTransition(TransitionInflater.from(
                    this).inflateTransition(R.transition.image_transition));
            UserDetailFragment.newInstance(user).setEnterTransition(TransitionInflater.from(
                    this).inflateTransition(android.R.transition.fade));
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, UserDetailFragment.newInstance(user))
                    .addSharedElement(UserDetailsViewModel.getiView(),"profile").commit();
        */
        } else {

        }
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, UserDetailFragment.newInstance(user)).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                //  supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
