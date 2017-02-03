package com.glt.imagephile.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gltrager on 1/14/17.
 */

public class DamageReport {
    private String adjuster;
    private String address;
    private String claimant;
    private long ID;
    private List<AVnote> AVnoteList;




    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }



    public String getAdjuster() {
        return adjuster;
    }

    public void setAdjuster(String adjuster) {
        this.adjuster = adjuster;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClaimant() {
        return claimant;
    }

    public void setClaimant(String claimant) {
        this.claimant = claimant;
    }

    public List<AVnote> getAVnoteList() {
        return AVnoteList;
    }

    public void setAVnoteList(List<AVnote> AVnoteList) {
        this.AVnoteList = AVnoteList;
    }

    public void addAVnote(AVnote note){
        if(AVnoteList == null)
            this.AVnoteList = new ArrayList<>();
        this.AVnoteList.add(note);
    }

    @Override
    public String toString() {
        return "id: " + ID + "\n" +
                "claimant: " + claimant + '\n' +
                "address: " + address ;
    }
}
