package followers.githubusersfollowers.view.adapters;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

import followers.githubusersfollowers.R;
import followers.githubusersfollowers.data.injection.utils.AvatarAsyncTask;
import followers.githubusersfollowers.data.injection.utils.ImageCache;
import followers.githubusersfollowers.databinding.ItemFollowerBinding;
import followers.githubusersfollowers.model.GithubUser;
import followers.githubusersfollowers.model.GithubUserFollowers;
import followers.githubusersfollowers.view.fragments.UserFollowersFragment;
import followers.githubusersfollowers.viewmodel.UserDetailsViewModel;
import followers.githubusersfollowers.viewmodel.UserFollowersViewModel;

public class UserFollowersAdapter extends RecyclerView.Adapter<FollowersViewHolder> {
    private ArrayList<GithubUserFollowers> followers;
    private Context context;

    public UserFollowersAdapter(Context context, ArrayList<GithubUserFollowers> followers) {
        this.context = context;
        this.followers = followers;
    }

    @Override
    public FollowersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemFollowerBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_follower, parent, false);

        return new FollowersViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final FollowersViewHolder holder, final int position) {
        ItemFollowerBinding followerBinding = holder.binding;
        followerBinding.setViewModel(new UserFollowersViewModel(context, followers.get(position)));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.binding.imageView.setTransitionName("profile");
        }
     /*
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setProtocols(Arrays.asList(Protocol.HTTP_1_1));
        OkHttpDownloader downloader = new OkHttpDownloader(okHttpClient);
        Picasso picasso = new Picasso.Builder(GithubUsersFollowersApplication.getAppContext()).downloader(downloader).build();
        picasso.with(GithubUsersFollowersApplication.getAppContext()).load(followers.get(position).getAvatarUrl()).into(holder.binding.imageView);
        */

        Map<ImageView, String> imageViewMapRef = Collections.synchronizedMap(
                new WeakHashMap<ImageView, String>());
        ImageCache imageCache = new ImageCache(holder.binding.imageView, imageViewMapRef);
        new AvatarAsyncTask(imageCache).execute(followers.get(position).getAvatarUrl());
        holder.binding.followerItemLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GithubUser gitUser = new GithubUser();
                gitUser.setLogin(followers.get(position).getLogin());
                UserDetailsViewModel detail = new UserDetailsViewModel(context, gitUser);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    UserFollowersFragment.newInstance().setSharedElementReturnTransition(TransitionInflater.from((Activity) context).inflateTransition(R.transition.image_transition));
                    UserFollowersFragment.newInstance().setExitTransition(TransitionInflater.from((Activity) context).inflateTransition(android.R.transition.fade));

                }
                detail.gitUserDetails(holder.binding.imageView);
            }
        });
    }

    @Override
    public int getItemCount() {
        return followers.size();
    }
}
