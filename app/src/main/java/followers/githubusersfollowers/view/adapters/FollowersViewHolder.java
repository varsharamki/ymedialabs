package followers.githubusersfollowers.view.adapters;

import android.support.v7.widget.RecyclerView;

import followers.githubusersfollowers.databinding.ItemFollowerBinding;

public class FollowersViewHolder extends RecyclerView.ViewHolder {
    public ItemFollowerBinding binding;

    public FollowersViewHolder(ItemFollowerBinding binding) {
        super(binding.followerItemLinear);
        this.binding = binding;
    }

}
