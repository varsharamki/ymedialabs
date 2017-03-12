package followers.githubusersfollowers.view.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import followers.githubusersfollowers.R;
import followers.githubusersfollowers.databinding.GithubUserFollowersBinding;
import followers.githubusersfollowers.model.GithubUserFollowers;
import followers.githubusersfollowers.view.adapters.UserFollowersAdapter;
import followers.githubusersfollowers.viewmodel.UserFollowersViewModel;

public class UserFollowersFragment extends Fragment {
    private static final String MY_LIST = "mylist";
    private UserFollowersAdapter followerAdapter;

    public UserFollowersFragment() {

    }

    public static UserFollowersFragment newInstance() {
        UserFollowersFragment fragment = new UserFollowersFragment();
        return fragment;
    }

    public static UserFollowersFragment newInstance(ArrayList<GithubUserFollowers> param1) {
        UserFollowersFragment fragment = new UserFollowersFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(MY_LIST, param1);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ArrayList<GithubUserFollowers> list = getArguments().getParcelableArrayList("mylist");
            followerAdapter = new UserFollowersAdapter(getActivity(), list);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        GithubUserFollowersBinding binding = DataBindingUtil.inflate(inflater, R.layout.github_user_followers, container, false);
        GithubUserFollowers user = new GithubUserFollowers();
        UserFollowersViewModel userFollowersViewModel = new UserFollowersViewModel(getActivity(), user);
        populateRecylerView(binding);

        return binding.followersRecyclerView;
    }

    private void populateRecylerView(GithubUserFollowersBinding bind) {
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        bind.followersRecyclerView.setLayoutManager(layoutManager);
        bind.followersRecyclerView.setHasFixedSize(true);
        bind.followersRecyclerView.setAdapter(followerAdapter);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

}
