package com.glt.imagephile.data;

import android.provider.BaseColumns;

import com.glt.imagephile.model.AVnote;

/**
 * Created by gltrager on 1/16/17.
 */

public final class AVnoteContract {
    private AVnoteContract() {}


    public static final String SQL_CREATE_AVnote_DB =
            "CREATE TABLE " + AVnote.TABLE_NAME + " (" +
                    AVnote._ID + " INTEGER PRIMARY KEY," +
                    AVnote.COLUMN_IMAGEPATH+ " TEXT," +
                    AVnote.COLUMN_AUDIOPATH_ + " TEXT," +
                    AVnote.COLUMN_DETAIL + " TEXT," +
                    AVnote.COLUMN_OWNER_ID  + " INTEGER)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + AVnote.TABLE_NAME;

        /* Inner class that defines the table contents */
        public static class AVnote implements BaseColumns {
            public static final String TABLE_NAME = "av_note";

            public static final String COLUMN_IMAGEPATH = "image_path";
            public static final String COLUMN_AUDIOPATH_ ="audio_path";
            public static final String COLUMN_DETAIL = "title";
            public static final String COLUMN_OWNER_ID = "owner_id";
        }
}
