package followers.githubusersfollowers.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import followers.githubusersfollowers.R;
import followers.githubusersfollowers.view.fragments.UserNameFragment;

public class UserNameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_name);
        userNameFragment();
    }

    private void userNameFragment() {
        UserNameFragment userFragMet = new UserNameFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, userFragMet).commit();
    }


}
