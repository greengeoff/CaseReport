package com.glt.imagephile.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.glt.imagephile.R;
import com.glt.imagephile.util.ImageUtil;

import java.io.File;

import java.io.FileFilter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by gltrager on 1/14/17.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private List<Drawable> mImages;

    public ImageAdapter(Context c, List<Drawable> drawableList) {
        mContext = c;
        mImages = drawableList;
    }

    public int getCount() {
        if(mImages == null)
            return 0;
        return mImages.size();
    }

    public Object getItem(int position) {
        return mImages.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(160, 160));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageDrawable(mImages.get(position));
        return imageView;
    }

}