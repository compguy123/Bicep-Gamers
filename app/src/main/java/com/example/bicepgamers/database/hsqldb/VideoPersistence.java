package com.example.bicepgamers.database.hsqldb;

import com.example.bicepgamers.R;
import com.example.bicepgamers.objects.VideoObject;

import java.util.ArrayList;
import java.util.List;

//This class creates the list of videos where we manually add all the videos to the list.
public class VideoPersistence implements com.example.bicepgamers.database.VideoPersistence {

    private ArrayList<VideoObject> videoList;

    //constructor initializes the arraylist and adds the videos to the system
    public VideoPersistence(){
        videoList = new ArrayList<VideoObject>();
        initData();
    }

    //method the get the videoList. used by logic layer to get the videos.
    public ArrayList<VideoObject> getVideoList(){
        if(videoList==null){
            initData();
        }
        return videoList;
    }

    //adds the videos  to the arraylist
    public void initData() {
      if(videoList==null){
          videoList = new ArrayList<VideoObject>();
      }
        videoList.add(new VideoObject(R.drawable.ic_tv, "Barrel Stretch", "barellstretch"));
        videoList.add(new VideoObject(R.drawable.ic_tv, "Hamstring Stretch", "hamstringstretch"));
        videoList.add(new VideoObject(R.drawable.ic_tv, "Cut Camel", "cutcamel"));
        videoList.add(new VideoObject(R.drawable.ic_tv, "Joint Distraction", "jointdistraction"));
        videoList.add(new VideoObject(R.drawable.ic_tv, "Prayer Position Stretch", "prayerpositionstretch"));
        videoList.add(new VideoObject(R.drawable.ic_tv, "Pronated Extendor Stretch", "pronatedextendorstretch"));
        videoList.add(new VideoObject(R.drawable.ic_tv, "Reverse Prayer Position Stretch", "reverseprayerpostionstretch"));
        videoList.add(new VideoObject(R.drawable.ic_tv, "Spinal Twist", "spinaltwist"));
        videoList.add(new VideoObject(R.drawable.ic_tv, "Superanted Flexor Stretch", "superantedflexorstretch"));
        videoList.add(new VideoObject(R.drawable.ic_tv, "Thumb Adducotor Stretch", "thumbadducotorstretch"));
        videoList.add(new VideoObject(R.drawable.ic_tv, "Thumb Extensor Stretch", "thumbextensorstretch"));
        videoList.add(new VideoObject(R.drawable.ic_tv, "Wrist Distraction", "wristdistraction"));

    }

}
