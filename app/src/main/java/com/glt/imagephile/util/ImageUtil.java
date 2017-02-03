package com.glt.imagephile.util;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.ContentValues.TAG;

/**
 * Created by gltrager on 1/14/17.
 */

public class ImageUtil {

    public static File createImageFile(Context context, long reportID) {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "." + String.valueOf(reportID) + "_" + timeStamp;


        File imageDir = new File (context.getFilesDir(),".images");
        if (!imageDir.exists())imageDir.mkdirs();


        File nomedia = new File(imageDir, ".nomedia");

        if (!nomedia.exists()) try {
            nomedia.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File image = new File(imageDir, imageFileName );


        // Save a file: path for use with ACTION_VIEW intents
        Log.d("created", image.getAbsolutePath());
        return image;
    }

    public static void storeImage(Bitmap image, File file) {
        File pictureFile = file;
        if (pictureFile == null) {
            Log.d(TAG,
                    "Error creating media file, check storage permissions: ");// e.getMessage());
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.d(TAG, "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d(TAG, "Error accessing file: " + e.getMessage());
        }
    }

    public static  File[] getFilesList(Context context, final long reportID) {
        File imageDir = new File (context.getFilesDir(),".images");
        ////

        //Log.d("load image from file: ", dir.toString());
        File[] imageFiles = imageDir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return Uri.fromFile(file).getLastPathSegment()
                        .startsWith(String.valueOf("." + reportID + "_"));
            }
        });
        if (imageFiles == null){
            Log.d("no files","no files");
            return null;
        }
        StringBuilder sb = new StringBuilder("start");
        for (File f : imageFiles){
            sb.append(f.toString() +'\n');
        }
        Log.d("util file list", sb.toString());
        return imageFiles;
    }

}
