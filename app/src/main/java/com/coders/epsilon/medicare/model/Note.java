package com.coders.epsilon.medicare.model;

/**
 * Created by nilima on 10/6/2015.
 */
public class Note {
    private String notename;
    private String notedetail;
    String id;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Note(String notename, String notedetail, String id) {
        this.notename = notename;
        this.notedetail = notedetail;

        this.id = id;
    }
    public Note() {

    }
    public String getNotename() {
        return notename;
    }

    public void setNotename(String notename) {
        this.notename = notename;
    }

    public String getNotedetail() {
        return notedetail;
    }

    public void setNotedetail(String notedetail) {
        this.notedetail = notedetail;
    }

    @Override
    public String toString() {
        return "Name: "+notename +" Note:"+ notedetail;
    }
}


