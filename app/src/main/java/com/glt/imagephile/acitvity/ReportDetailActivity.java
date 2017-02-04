package com.glt.imagephile.acitvity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.glt.imagephile.R;
import com.glt.imagephile.adapter.ImageAdapter;
import com.glt.imagephile.data.AVnoteContract;
import com.glt.imagephile.manager.ReportManager;
import com.glt.imagephile.model.AVnote;
import com.glt.imagephile.model.DamageReport;
import com.glt.imagephile.util.ImageUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.glt.imagephile.util.ImageUtil.createImageFile;

public class ReportDetailActivity extends AppCompatActivity {
    ImageAdapter adapter;
    GridView gridview;

    // provides access to db
    ReportManager rm;

    // extra from DamageListActivity
    long reportId;
    DamageReport dr;
    List<AVnote> noteList;
    List<Drawable> drawableList;

    //widgets
    private TextView claimantTV;
    private TextView addressTV;
    private TextView idTV;

    // list of image files
    private File[] files;

    // set these vars whie building note data
    private String avDescription = null;
    private String avImagePath = null;

    private AVnote newNote;



    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Report manager is currently providing db access
        rm = ReportManager.getInstance(this);
        //

        // prepare list (yagniy?)
        drawableList = new ArrayList<>();
        //

        Intent intent = getIntent();
        reportId = intent.getLongExtra(ReportListActivity.REPORT_ID_EXTRA, 0);
        loadReportFromDB();


        // attach the reports id to the activity
        // this is used to identify images

        idTV = (TextView)findViewById(R.id.reportDetailClaimant);
        idTV.setText(String.valueOf(reportId));
        claimantTV = (TextView)findViewById(R.id.reportDetailAddress);
        claimantTV.setText(dr.getClaimant());
        addressTV = (TextView)findViewById(R.id.reportDetailReportId);
        addressTV.setText(dr.getAddress());
        addressTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ReportDetailActivity.this,
                        "touch",
                        Toast.LENGTH_SHORT).show();
                loadImagesFromFiles();
            }
        });



        //get DamageReport from manager


        // make db query to get rows from DB
        // to make AVnotes for this damageReport



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ReportDetailActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.dialog_note_description, null);
                builder.setView(dialogView);
                builder.setPositiveButton("Create Note", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText description = (EditText)dialogView.findViewById(R.id.editText);
                        avDescription = description.getText().toString();
                        dispatchTakePictureIntent();

                    }
                }).setNegativeButton(R.string.cancel_create_report,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        // user cancelled
                                    }
                                });


            }
        });

        gridview = (GridView) findViewById(R.id.gridView);
        loadImagesFromFiles();
        adapter =  new ImageAdapter(this, drawableList);
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(ReportDetailActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void loadReportFromDB() {

    }


    @Override
    protected void onResume() {
        loadImagesFromFiles();
        adapter.notifyDataSetChanged();
        gridview.invalidate();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /////////////////////////////////////////////////// Think Geek
    /////
    /////  *** **  **s

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File imageFile = ImageUtil.createImageFile(ReportDetailActivity.this, this.reportId);

            // Beware of tight coupling !! ***** encapsulation !!

            avImagePath = imageFile.toString();

            newNote = new AVnote(reportId, avDescription);


            // Danger above *******************************


            Uri photoUri = FileProvider.getUriForFile(
                    ReportDetailActivity.this,
                    "com.glt.imagephile.fileprovider",
                    imageFile);

            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            //takePictureIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Toast.makeText(ReportDetailActivity.this,
                    photoUri.toString(),
                     Toast.LENGTH_LONG).show();

            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            newNote.setImagePath(avImagePath);
            adapter.notifyDataSetChanged();
            File imageDir = ReportDetailActivity.this.getFilesDir();
            Log.d("image/ exist", String.valueOf(imageDir.exists())
                    + String.valueOf(imageDir.listFiles().length));
            loadImagesFromFiles();
        }

    };

    private void loadImagesFromFiles(){
        drawableList.clear();
        files = ImageUtil.getFilesList(ReportDetailActivity.this, reportId);
        if(files == null){
            Log.d("got a null list", "");
            return;
        }
        for(File f: files){
            System.out.println(f.length());
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize= 4;
            Bitmap bp = BitmapFactory.decodeFile(f.getPath(), options);
            if (bp == null)
                Log.e("bitmap", " is null");
            Drawable drawable = new BitmapDrawable(getResources(), bp);
            if (drawableList == null)
                Log.e("drawable", " is null");


            if(drawable != null)
              drawableList.add(drawable);
        }


    }

}
