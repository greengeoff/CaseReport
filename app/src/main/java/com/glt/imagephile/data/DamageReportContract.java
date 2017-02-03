package com.glt.imagephile.data;

import android.provider.BaseColumns;

/**
 * Created by gltrager on 1/16/17.
 */

public class DamageReportContract {
    private DamageReportContract() {
    } // prevent outside creation

    public static final String SQL_CREATE_DAMAGE_REPORT_DB =
            "CREATE TABLE " + DamageReport.TABLE_NAME + " (" +
                    DamageReport._ID + " INTEGER PRIMARY KEY," +
                    DamageReport.COLUMN_ADJUSTER + " TEXT," +
                    DamageReport.COLUMN_ADDRESS + " TEXT," +
                    DamageReport.COLUMN_CLAIMANT + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DamageReport.TABLE_NAME;


    public static class DamageReport implements BaseColumns{
        public static final String TABLE_NAME = "damage_report";

        public static final String COLUMN_ADJUSTER = "adjuster";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_CLAIMANT = "claimant";
        public static final String COLUMN_OWNER_ID = "av_note";
    }
}
