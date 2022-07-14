package com.example.bicepgamers.GUI;

import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bicepgamers.Logic.VideoManager;
import com.example.bicepgamers.R;
import com.example.bicepgamers.database.hsqldb.VideoPersistence;
import com.example.bicepgamers.objects.VideoObject;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
public class ExerciseActivity extends AppCompatActivity {

    // break timer display and break button
    private TextView breakView;
    private Button breakButton;

    // control break time running
    private Timer breakTimer;
    private TimerTask breakTimerTask;
    private Double breakTime = 0.0;

    //video start button
    private Button startButton;
    private VideoObject video;

    boolean videoPlaying = false;

    private ArrayList<VideoObject> videoList;
    private RecyclerView recyclerView;
    private WorkoutAdapter adapter;
    private LinearLayoutManager layoutManager;

    private VideoManager videoManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.backup);

        initData();   //creates the video list
        initRecyclerView();  //inializes the recyclerview for the videos
        createNotificationChannel();   //notification method for latest versions of android

        breakView = findViewById(R.id.breakTime);
        breakButton = findViewById(R.id.endbreakbutton);
        breakTimer = new Timer();

        //catches the break time received from previous activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            breakTime = extras.getDouble("breakTime");
        }

        startBreak();

        //end break button
        breakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                breakTimerTask.cancel();
                endBreak();
            }
        });
    }


    //initialize recycler view
    private void initRecyclerView() {
        recyclerView = findViewById(R.id.workoutvideos);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter =  new WorkoutAdapter(videoList);
        recyclerView.setAdapter(adapter);


        //sets the listener to catch which video the user clicks on
        adapter.setOnVideoListener(new WorkoutAdapter.OnVideoListener() {
            @Override
            public void onPlayClick(int position) {

                video = new VideoObject();
                video = videoList.get(position);
                popupWindow(video);
            }
        });

    }


    //pop up window to run the videos once clicked
    private void popupWindow(VideoObject v){
        String path="android.resource://com.example.bicepgamers/raw/"+v.getPath();

        VideoView mVideoView =new VideoView(this);

        mVideoView.setVideoPath(String.valueOf(path));

        Dialog dialog = new Dialog(this);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(mVideoView);

        popupWindowWidth(mVideoView);

        dialog.show();
        mVideoView.start();
        videoPlaying = true;

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            //when the user closes the pop up screen
            public void onDismiss(final DialogInterface arg0) {
                videoPlaying = false;
                if(breakOver()){
                    endBreak();  //ends the break if break time is up
                }
            }
        });

    }

    //Set pop up window full width
    private void popupWindowWidth(VideoView v){
        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        v.setLayoutParams(new FrameLayout.LayoutParams(width,height));
    }

    //starts the break timer.
    private void startBreak() {
        breakTimerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    breakView.setText(getTimerText(breakTime));
                    breakTime--;
                    if( breakOver() && (!videoPlaying) ){
                        endBreak();
                    }
                });
            }
        };
        breakTimer.scheduleAtFixedRate(breakTimerTask, 0, 1000);
    }

    //checks if the break time has ended.
    private boolean breakOver(){
        boolean result = false;
        if(breakTime<0.0){
            breakTimerTask.cancel();
            result  = true;
        }
        return result;
    }



    //returns to previous activity
    private void endBreak(){
        giveNotification();   //sound notification when the break ends.
        finish();
    }


    //method to implement sound notification at the end of break
    private void giveNotification(){

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "Breakover");
        builder.setPriority(NotificationCompat.PRIORITY_MAX);
        builder.setContentTitle("Break Ends");
        builder.setContentIntent(null);
        builder.setSmallIcon(R.drawable.imageedit_4_8282271901);
        builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        builder.setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(1, builder.build());

    }

    //creates the list of videos.
    private void initData(){
        videoManager = new VideoManager();
        videoList = new ArrayList<VideoObject>();
        videoList = videoManager.getAllVideos();
    }


    //notification helper function for android versions greater than Oreo
    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("Breakover", "Breakover", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = getSystemService(NotificationManager.class);

            manager.createNotificationChannel(channel);
        }
    }


    // turn a double into time
    private String getTimerText(double time) {
        int round = (int) Math.round(time);
        int seconds = ((round % 86400) % 3600) % 60;
        int minutes = ((round % 86400) % 3600) / 60;
        int hours = ((round % 86400) / 3600);

        return formatTime(seconds, minutes, hours);
    }

    //format time to display to the user
    private String formatTime(int seconds, int minutes, int hours) {
        return String.format(Locale.getDefault(), "%02d", hours) + ":" +
                String.format(Locale.getDefault(),"%02d", minutes) + ":" +
                String.format(Locale.getDefault(),"%02d", seconds);
    }

}
