package com.glt.imagephile.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.content.ContextCompat;

import com.glt.imagephile.data.DBHelper;
import com.glt.imagephile.data.DamageReportContract;
import com.glt.imagephile.model.DamageReport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gltrager on 1/14/17.
 */
public class ReportManager {
    private static ReportManager ourInstance = new ReportManager();

    public static ReportManager getInstance(Context c) {
        //provide context for the DB call
        context = c;
        loadReportsFromDB();
        return ourInstance;
    }
    private ReportManager() {
    }

    private static Context context;

    // Singleton
    ///////////

    private  static List<DamageReport> reportList = new ArrayList<>();

    public static void addReport(DamageReport report){


        long id = addDamageReportToDB(report);
        report.setID(id);
        reportList.add(report);

    }
    public List<DamageReport> getReportList(){
        return reportList;
    }

    private static void loadReportsFromDB(){
        DBHelper dbHelper = new DBHelper(context);
        Cursor cursor = dbHelper.getReadableDatabase()
                .rawQuery("select * from " + DamageReportContract.DamageReport.TABLE_NAME, null);

        if( cursor.moveToFirst()){
            reportList.clear();
            reportList.add(makeReport(cursor));

            while(cursor.moveToNext()){
                reportList.add(makeReport(cursor));
            }
        }
        cursor.close();
        dbHelper.close();
    }

    private static DamageReport makeReport(Cursor cursor){
        DamageReport dr = new DamageReport();
        dr.setID(cursor.getLong(cursor.getColumnIndex(
                DamageReportContract.DamageReport._ID)));

        dr.setClaimant(cursor.getString(cursor.getColumnIndex(
                DamageReportContract.DamageReport.COLUMN_CLAIMANT)));

        dr.setAddress(cursor.getString(cursor.getColumnIndex(
                DamageReportContract.DamageReport.COLUMN_ADDRESS)));

        return dr;
    }

    private static long addDamageReportToDB(DamageReport report){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(DamageReportContract.DamageReport.COLUMN_CLAIMANT,
                report.getClaimant());

        cv.put(DamageReportContract.DamageReport.COLUMN_ADDRESS,
                report.getAddress());

        long id = db.insert(DamageReportContract.DamageReport.TABLE_NAME,
                null, cv);

        dbHelper.close();
        return id;

    }

}
