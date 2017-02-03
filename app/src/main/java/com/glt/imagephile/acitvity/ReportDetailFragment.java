package com.glt.imagephile.acitvity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.glt.imagephile.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class ReportDetailFragment extends Fragment {

    public ReportDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_report_detail, container, false);
    }
}
