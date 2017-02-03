package com.glt.imagephile.acitvity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.glt.imagephile.R;
import com.glt.imagephile.manager.ReportManager;
import com.glt.imagephile.model.DamageReport;

public class ReportListActivity extends AppCompatActivity {
    private static String Tag = ReportListActivity.class.getSimpleName();

    public static String REPORT_EXTRA = "report_extra";
    public static String REPORT_ID_EXTRA = "report_id";
    public static String REPORT_CLAIMANT_EXTRA = "report_claimant";
    public static String REPORT_ADDRESS_EXTRA = "report_address";

    ReportManager rm;
    ArrayAdapter<DamageReport> adapter;

    // widgets in 'new report' dialog
    EditText name;
    EditText address;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initFAB();

        rm = ReportManager.getInstance(this);

        //load the report manager with some fake data
        //MockerData.addMockDataToManager();



        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, rm.getReportList());
        ListView listView = (ListView)findViewById(R.id.reportListView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(listReportListener);

    }

    public void initFAB(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(ReportListActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.dialog_new_damage_report, null);
                builder.setView(dialogView)
                        .setPositiveButton(R.string.confirm_create_report,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        name = (EditText)dialogView.findViewById(R.id.newReportName);
                                        String nameStr = name.getText().toString();

                                        address = (EditText)dialogView.findViewById(R.id.newReportAddress);
                                        String addressStr = address.getText().toString();

                                        DamageReport newDamageReport = new DamageReport();
                                        newDamageReport.setClaimant(nameStr);
                                        newDamageReport.setAddress(addressStr);

                                        rm.addReport(newDamageReport);
                                        adapter.notifyDataSetChanged();
                                    }
                                })
                        .setNegativeButton(R.string.cancel_create_report,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        // user cancelled
                                    }
                                });
                builder.create().show();

            }
        });
    }

    AdapterView.OnItemClickListener listReportListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(ReportListActivity.this, ReportDetailActivity.class);

            DamageReport dr = rm.getReportList().get(i);
            intent.putExtra(REPORT_ID_EXTRA, dr.getID());
            intent.putExtra(REPORT_CLAIMANT_EXTRA, dr.getClaimant());
            intent.putExtra(REPORT_ADDRESS_EXTRA, dr.getAddress());

            startActivity(intent);

        }
    };



}
