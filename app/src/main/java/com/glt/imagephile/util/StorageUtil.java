package com.glt.imagephile.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.glt.imagephile.model.DamageReport;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by gltrager on 1/14/17.
 */

public class StorageUtil {

    public static String getAppData(Context c){
        SharedPreferences sharedPref = c.getSharedPreferences( "appData", Context.MODE_WORLD_WRITEABLE );
        return sharedPref.getString("json", "none");

    }

    public static void storeAppData(Context c, String str){
        //SharedPreferences sharedPref = c.getSharedPreferences( "appData", Context.MODE_WORLD_WRITEABLE );
        SharedPreferences.Editor prefEditor = c.getSharedPreferences( "appData", Context.MODE_WORLD_WRITEABLE ).edit();
        prefEditor.putString( "json", str );
        prefEditor.apply();
    }
}
