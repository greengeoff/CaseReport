package com.glt.imagephile.fragment;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.glt.imagephile.R;
import com.glt.imagephile.manager.ReportManager;

/**
 * Created by gltrager on 1/18/17.
 */

public class AddDamageReportFragment extends Fragment {
    TextInputLayout adjusterInputLayout,
                    addressInputLayout,
                    claimantInputLayout;



    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_damage_report, container, false);



        return view;
    }

    static AddDamageReportFragment newInstance(ReportManager manager){
        AddDamageReportFragment fragment = new AddDamageReportFragment();

        return fragment;

    }
}
