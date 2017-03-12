package followers.githubusersfollowers.view.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

import at.favre.lib.dali.Dali;
import followers.githubusersfollowers.R;
import followers.githubusersfollowers.data.injection.utils.AvatarAsyncTask;
import followers.githubusersfollowers.data.injection.utils.ImageCache;
import followers.githubusersfollowers.databinding.GithubUserDetailBinding;
import followers.githubusersfollowers.model.GithubUser;
import followers.githubusersfollowers.viewmodel.UserDetailsViewModel;


public class UserDetailFragment extends Fragment {
    private static final String USER = "user";
    private GithubUser userDetail;

    public UserDetailFragment() {
    }

    public static UserDetailFragment newInstance(GithubUser param1) {
        UserDetailFragment fragment = new UserDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(USER, param1);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userDetail = getArguments().getParcelable("user");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        GithubUserDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.github_user_detail, container, false);
        GithubUser user = getArguments().getParcelable("user");
        UserDetailsViewModel userModel = new UserDetailsViewModel(getActivity(), user);
        binding.setViewModel(userModel);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        Map<ImageView, String> imageViewMapRef = Collections.synchronizedMap(
                new WeakHashMap<ImageView, String>());
        ImageCache imageCache = new ImageCache(binding.userCollapsable.imageView, imageViewMapRef);
        new AvatarAsyncTask(imageCache).execute(userDetail.getAvatarUrl());
        Dali.create(getActivity()).load(R.drawable.bgnd_image).skipCache().brightness(2).blurRadius(20).into(binding.backdrop);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            binding.userCollapsable.imageView.setTransitionName("profile");
        }
        return binding.coordinator;
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
