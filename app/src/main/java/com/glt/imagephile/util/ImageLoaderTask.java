package com.glt.imagephile.util;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.glt.imagephile.acitvity.ReportDetailActivity;

import java.lang.ref.WeakReference;

/**
 * Created by gltrager on 2/3/17.
 */

public class ImageLoaderTask
        extends AsyncTask<String, Void, BitmapDrawable> {

    // weak reference to the UI ImageView that is update
    private final WeakReference<ImageView> imageViewWeakRef;
    private final Context context;

    public ImageLoaderTask(Context c, ImageView imageView) {
        this.imageViewWeakRef = new WeakReference<ImageView>(imageView);
        this.context = c;
    }

    @Override
    protected BitmapDrawable doInBackground(String... strings) {
        BitmapDrawable drawable =
           ImageUtil.createDrawable(context, strings[0]);
        return drawable;
    }

    @Override
    protected void onPostExecute(BitmapDrawable bitmapDrawable) {
        ImageView imageView = this.imageViewWeakRef.get();
        if(imageView != null){
            imageView.setImageDrawable(bitmapDrawable);
        }
    }
}
