package com.example.bicepgamers.objects;

import android.os.Environment;

import com.example.bicepgamers.R;

import java.io.File;

public class VideoObject {
    private int thumbnail;
    private String title;   //title of the video. it is displayed to the user.
    private String path;   //path to the video file

    //constructor used to add videos
    public VideoObject(int imageView, String title, String path) {
        this.thumbnail = imageView;
        this.title = title;
        this.path = path;
    }

    //empty constructor used to initialize the video object and assign it to one that the user clicks to play the video
    public VideoObject(){
    }

    //Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getImageView() {
        return thumbnail;
    }

    public void setImageView(int imageView) {
        this.thumbnail = imageView;
    }

    public String getPath() { return path; }

}
