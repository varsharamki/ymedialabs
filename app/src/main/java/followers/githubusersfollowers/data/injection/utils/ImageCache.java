package followers.githubusersfollowers.data.injection.utils;

import android.widget.ImageView;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

public class ImageCache {
    public ImageView imageView;
    public Map<ImageView, String> imageViewMap;

    public ImageCache(ImageView imageView, Map<ImageView, String> imageViewsMap) {
        this.imageView = imageView;
        this.imageViewMap = Collections.synchronizedMap(
                new WeakHashMap<ImageView, String>());
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Map<ImageView, String> getImageViewMap() {
        return imageViewMap;
    }

    public void setImageViewMap(Map<ImageView, String> imageViewMap) {
        this.imageViewMap = imageViewMap;
    }
}