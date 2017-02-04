package com.glt.imagephile.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.glt.imagephile.model.AVnote;
import com.glt.imagephile.model.DamageReport;

/**
 * Created by gltrager on 1/17/17.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Damage.db";

    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(AVnoteContract.SQL_CREATE_AVnote_DB);
        db.execSQL(DamageReportContract.SQL_CREATE_DAMAGE_REPORT_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long createDamageReport(DamageReport damageReport){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues  values = new ContentValues();
        values.put(DamageReportContract.DamageReport.COLUMN_ADDRESS,
                damageReport.getAddress());
        values.put(DamageReportContract.DamageReport.COLUMN_CLAIMANT,
                damageReport.getClaimant());

        long newRowId = db.insert(DamageReportContract.DamageReport.TABLE_NAME,
                null, values);

        damageReport.setID(newRowId);

        return newRowId;
    }

    public long createAVnote(AVnote avNote){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(AVnoteContract.AVnote.COLUMN_OWNER_ID,
                avNote.getOwnerID());
        values.put(AVnoteContract.AVnote.COLUMN_DETAIL,
                avNote.getAudioPath());
        values.put(AVnoteContract.AVnote.COLUMN_IMAGEPATH,
                    avNote.getImagePath());

        long newRowId = db.insert(AVnoteContract.AVnote.TABLE_NAME,
                null, values);
        return newRowId;

    }
}
