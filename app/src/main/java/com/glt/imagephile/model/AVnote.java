package com.glt.imagephile.model;

/**
 * Created by gltrager on 1/14/17.
 */

public class AVnote {

    private long ownerID;
    private String imagePath; //absolute path of img file
    private String audioPath; //absolute path of audio file
    private String details;

    public AVnote() {
    }

    public AVnote(long ownerID, String details) {
        this.details = details;
        this.ownerID = ownerID;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imgageAbsPath) {
        this.imagePath = imgageAbsPath;
    }

    public long getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(long ownerID) {
        this.ownerID = ownerID;
    }

}
