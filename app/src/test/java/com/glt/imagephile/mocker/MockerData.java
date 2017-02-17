package com.glt.imagephile.mocker;

import com.glt.imagephile.data.AVnoteContract;
import com.glt.imagephile.data.DamageReportContract;
import com.glt.imagephile.manager.ReportManager;
import com.glt.imagephile.model.AVnote;
import com.glt.imagephile.model.DamageReport;

/**
 * Created by gltrager on 1/19/17.
 */

public class MockerData {
    public static DamageReport dr1, dr2;
    public static AVnote av1,av2,av3,av4,av5;

    public static void addMockDataToManager(){

        dr1 = new DamageReport();
        dr1.setAddress("123 main");
        dr1.setAdjuster("jon smith");
        dr1.setClaimant("broke molly");
        dr1.setID(1);

        dr2 = new DamageReport();
        dr2.setAddress("2664 lincoln");
        dr2.setAdjuster("jon smith");
        dr2.setClaimant("sad tom");
        dr2.setID(2);

        av1 = new AVnote(dr1.getID(), "broken window");
        av2 = new AVnote(dr1.getID(), "spotty floor");
        dr1.addAVnote(av1);
        dr1.addAVnote(av2);

        av3 = new AVnote(dr2.getID(), "broken window");
        av4 = new AVnote(dr2.getID(), "drippy ceiling");
        av5 = new AVnote(dr2.getID(), "hail damage everywhere");
        dr2.addAVnote(av3);
        dr2.addAVnote(av4);
        dr2.addAVnote(av5);

        //ReportManager rm = ReportManager.getInstance();
        //rm.getReportList().clear();
        //rm.addReport(dr1);
        //rm.addReport(dr2);



    }


}
