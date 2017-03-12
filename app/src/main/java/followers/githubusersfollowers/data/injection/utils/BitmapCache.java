package followers.githubusersfollowers.data.injection.utils;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

public class BitmapCache {
    public Bitmap bitmap;
    public String avtarUrl;
    public Map<ImageView, String> imageViewMap = Collections.synchronizedMap(
            new WeakHashMap<ImageView, String>());

    public BitmapCache() {
    }

    public BitmapCache(Bitmap bitmap, String avatarUrl, Map<ImageView, String> imageViewMap) {
        this.bitmap = bitmap;
        this.avtarUrl = avatarUrl;
        this.imageViewMap = imageViewMap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getAvtarUrl() {
        return avtarUrl;
    }

    public void setAvtarUrl(String avtarUrl) {
        this.avtarUrl = avtarUrl;
    }

    public Map<ImageView, String> getImageViewMap() {
        return imageViewMap;
    }

    public void setImageViewMap(Map<ImageView, String> imageViewMap) {
        this.imageViewMap = imageViewMap;
    }
}