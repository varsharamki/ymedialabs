package followers.githubusersfollowers.data.injection.utils;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;


public class AvatarAsyncTask extends AsyncTask<String, Void, BitmapCache> {

    String url;
    private WeakReference<ImageView> imageViewWeakReference;
    private Map<ImageView, String> imageViewMapRef = Collections.synchronizedMap(
            new WeakHashMap<ImageView, String>());

    public AvatarAsyncTask(ImageCache iCache) {
        imageViewWeakReference = new WeakReference<ImageView>(iCache.imageView);
        imageViewMapRef = iCache.imageViewMap;
    }

    private static boolean isMemoryLimit() {
        if (Runtime.getRuntime().freeMemory() < (Runtime.getRuntime().maxMemory() / 5)) {
            return true;
        }
        return false;
    }

    @Override
    protected BitmapCache doInBackground(String... params) {
        BitmapCache bcache = null;
        if (isMemoryLimit()) {
            bcache = new BitmapCache();
            bcache.bitmap = bitmapFromUrl(params[0]);
            imageViewMapRef.put(imageViewWeakReference.get(), params[0]);
            bcache.imageViewMap = imageViewMapRef;

        }
        return bcache;
    }

    @Override
    protected void onPostExecute(BitmapCache bitmap) {
        super.onPostExecute(bitmap);
        try {
            if (isMemoryLimit()) {
                if (imageViewWeakReference != null && bitmap != null) {
                    ImageView image = imageViewWeakReference.get();
                    if (image != null) {
                        image.setImageBitmap(bitmap.bitmap);
                        imageViewMapRef.put(image, url);
                    }
                }
            } else {
                /// pop up a dialog saying u have execcedd the limit for processing pelase kill something !!!!
            }
        } catch (Exception e) {

        }
    }

    private Bitmap bitmapFromUrl(String url) {
        HttpURLConnection connection = null;
        Bitmap bitmap = null;
        try {
            URL avatar = new URL(url);
            connection = (HttpURLConnection) avatar.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream is = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);


        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return bitmap;
    }

}