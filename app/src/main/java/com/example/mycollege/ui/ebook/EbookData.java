package com.example.mycollege.ui.ebook;

public class EbookData {
    private String title, pdfurl, date, time,todepart,toyear;


    public EbookData() {
    }

    public EbookData(String title, String pdfurl, String date, String time, String todepart, String toyear) {

        this.title = title;
        this.pdfurl = pdfurl;
        this.date = date;
        this.time = time;
        this.todepart = todepart;
        this.toyear = toyear;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getPdfurl() {

        return pdfurl;
    }

    public void setPdfurl(String pdfurl) {

        this.pdfurl = pdfurl;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {

        this.date = date;
    }

    public String getTime() {

        return time;
    }

    public void setTime(String time) {

        this.time = time;
    }

    public String getTodepart() {

        return todepart;
    }

    public void setTodepart(String todepart) {

        this.todepart = todepart;
    }

    public String getToyear() {

        return toyear;
    }

    public void setToyear(String toyear) {

        this.toyear = toyear;
    }

}
