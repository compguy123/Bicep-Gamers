package com.example.bicepgamers.Logic;


import com.example.bicepgamers.database.VideoPersistence;
import com.example.bicepgamers.objects.VideoObject;

import java.util.ArrayList;

//logic layer for videos.
public class VideoManager {
    private VideoPersistence videoPersistence;
    private ArrayList<VideoObject> videoList;

    //constructor creates the videoList by interacting with data layer
    public VideoManager(){

        videoPersistence = new com.example.bicepgamers.database.hsqldb.VideoPersistence();
        videoList = new ArrayList<VideoObject>();
    }

    //initializes and returns the list of videos from the data layer.
    public ArrayList<VideoObject> getAllVideos(){
        videoList = videoPersistence.getVideoList();
        return videoList;
    }
}
