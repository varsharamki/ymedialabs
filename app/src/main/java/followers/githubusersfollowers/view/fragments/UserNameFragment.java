package followers.githubusersfollowers.view.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import followers.githubusersfollowers.GithubUsersFollowersApplication;
import followers.githubusersfollowers.R;
import followers.githubusersfollowers.databinding.FragmentUserNameBinding;
import followers.githubusersfollowers.model.GithubUserFollowers;
import followers.githubusersfollowers.viewmodel.UserFollowersViewModel;

public class UserNameFragment extends Fragment {
    private static final String USER_NAME = "userName";
    private String userName;

    public UserNameFragment() {
        // Required empty public constructor
    }

    public static UserNameFragment newInstance(String param1) {
        UserNameFragment fragment = new UserNameFragment();
        Bundle args = new Bundle();
        args.putString(USER_NAME, param1);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final FragmentUserNameBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_name, container, false);
        GithubUserFollowers user = new GithubUserFollowers();
        UserFollowersViewModel userFollowersViewModel = new UserFollowersViewModel(getActivity(), user);
        binding.setGithubuser(user);
        binding.setViewModel(userFollowersViewModel);
        if (GithubUsersFollowersApplication.isNetworkAvailable(getActivity())) {
            if (userFollowersViewModel.isValidUserName(binding.editText.getText().toString().trim())) {
                user.setLogin(binding.editText.getText().toString().trim());
            } else {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setTitle("Incorrect Github User Name");

                alertDialogBuilder
                        .setMessage("Git hub user names only contain alpahanumeric characters ")
                        .setCancelable(false)
                        .setPositiveButton("OK ?? ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
            }
        } else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
            alertDialogBuilder.setTitle("Unable to connect to a network");

            alertDialogBuilder
                    .setMessage("You are currently offline ,Please connect to a network to proceed")
                    .setCancelable(false)
                    .setPositiveButton("OK ?? ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                          if(GithubUsersFollowersApplication.isNetworkAvailable(getActivity())) {
                              dialog.dismiss();
                          }
                        }
                    });

        }

        return binding.linear;
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
