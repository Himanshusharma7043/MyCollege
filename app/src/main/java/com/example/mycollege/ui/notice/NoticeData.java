package com.example.mycollege.ui.notice;

public class NoticeData {
    String title, image, date, time, key,todepart,toyear,uploader_name;

    public NoticeData(String title, String image, String date, String time, String key, String todepart, String toyear, String uploader_name) {

        this.title = title;
        this.image = image;
        this.date = date;
        this.time = time;
        this.key = key;
        this.todepart = todepart;
        this.toyear = toyear;
        this.uploader_name = uploader_name;
    }

    public String getUploader_name() {

        return uploader_name;
    }

    public void setUploader_name(String uploader_name) {

        this.uploader_name = uploader_name;
    }

    public NoticeData() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
