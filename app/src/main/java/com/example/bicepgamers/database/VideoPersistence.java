package com.example.bicepgamers.database;

import com.example.bicepgamers.objects.VideoObject;

import java.util.ArrayList;


//interface for the videos database
public interface VideoPersistence {

    ArrayList<VideoObject> getVideoList();

    void initData();

}
