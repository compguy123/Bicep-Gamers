package com.example.bicepgamers.GUI;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.example.bicepgamers.Logic.GameManager;
import com.example.bicepgamers.Logic.SessionManager;
import com.example.bicepgamers.R;
import com.example.bicepgamers.objects.Game;
import com.example.bicepgamers.objects.Session;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/*
THis is the class for the session timer gui page
 */
public class SessionTimer extends AppCompatActivity {

    private Session curSession = null;
    private Game curGame = null;

    // session timer display and session button
    private TextView sessionView;
    // for small timer at the right top corner
    private TextView sessionView2;
    private Button sessionButton;

    // control session time running
    private Timer sessionTimer;
    private TimerTask sessionTimerTask;
    private Double sessionTime = 0.0;

    // break timer display and break button
    private TextView breakView;
    private Button breakButton;

    private Double breakTime = 0.0;
    private Double totalBreakLength = 0.0;

    // control game time running
    private Timer gameTimer;
    private TimerTask gameTimerTask;
    private Double gameTime = 0.0;

    private boolean onBreak = false;
    private boolean executeOnResume= false; //differentiates if this activity is resumed or created.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.session_timer);

        //set parameters
        sessionView = findViewById(R.id.sessionTime);
        sessionView2 = findViewById(R.id.sessionTime2);
        sessionView2.setVisibility(View.INVISIBLE);
        sessionButton = findViewById(R.id.sessionButton);

        breakView = findViewById(R.id.breakTime);
        breakView.setVisibility(View.INVISIBLE);
        breakButton = findViewById(R.id.breakButton);
        breakButton.setEnabled(false);
        breakButton.setVisibility(View.INVISIBLE);

        //timer of seesion
        sessionTimer = new Timer();
        gameTimer = new Timer();

        //if a break has not been taken in 2 hrs , give notification to take break
        createNotificationChannel();

        sessionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startEndSession();
            }
        });

        //when the user clicks start break button, show the workout video page
        breakButton.setOnClickListener(view -> openExercisePage());

        //display the details of the game in the top left
        TextView gameSession = findViewById(R.id.gameSession);
        TextView genreSession = findViewById(R.id.genreSession);
        TextView deviceSession = findViewById(R.id.deviceSession);
        TextView typeSession = findViewById(R.id.typeSession);

        Intent intent = getIntent();
        String[] gameVar = intent.getStringArrayExtra("gameVar");

        boolean gameType;
        if(gameVar[3].equalsIgnoreCase("Online")) {
            gameType = true;
        }
        else {
            gameType = false;
        }
        curGame = new Game(gameVar[0], gameVar[1], gameVar[2], gameType);
        curGame.setID(Integer.parseInt(gameVar[4]));
        GameManager gameManager = new GameManager();
        curGame.setGenreIntensity(gameManager.getGenreIntensity(curGame));
        curGame.setDeviceIntensity(gameManager.getDeviceIntensity(curGame));
        curGame.setTypeIntensity(gameManager.getTypeIntensity(curGame));

        gameSession.setText(gameVar[0]);
        genreSession.setText(gameVar[1]);
        deviceSession.setText(gameVar[2]);
        typeSession.setText(gameVar[3]);

    }

    //this is run when the user returns to this page after the break ends.
    @Override
    protected void onResume() {
        super.onResume();
        if(executeOnResume){
            endBreak();
            startGame();
        }
    }


    // onClick function for session button
    private void startEndSession() {
        if(curSession == null) {

            curSession = new Session(curGame);

            startSession();
        }
        else {
            endSession();
        }
    }

    private void openExercisePage(){
        //when the user clicks on start break button
        //start time
        startBreak();
        endGame();
        //show workout video page.
        Intent intent = new Intent(this, ExerciseActivity.class);
        intent.putExtra("breakTime", breakTime);
        startActivity(intent);
    }



    // start session button
    private void startSession() {
        setButtonUI(sessionButton,"End Session");

        sessionTimerTask = new TimerTask() {
            @Override
            public void run() {
                //increment timer
                runOnUiThread(() -> {
                    sessionTime++;
                    sessionView.setText(getTimerText(sessionTime));
                    sessionView2.setText(getTimerText(sessionTime));
                });
            }
        };
        sessionTimer.scheduleAtFixedRate(sessionTimerTask, 0, 1000);

        //show start break button
        breakButton.setEnabled(true);
        breakButton.setVisibility(View.VISIBLE);
        //session has started , start timer
        startGame();
    }

    // what to do when a session end
    private void endSession() {
        AlertDialog.Builder resetAlert = new AlertDialog.Builder((this));
        resetAlert.setTitle("End Session");
        resetAlert.setMessage("Do you want to end this session?");
        resetAlert.setPositiveButton("End", (DialogInterface dialogInterface, int i) -> {
            if(sessionTimerTask != null) {
                sessionTimerTask.cancel();
                //end the timer
                endGame();

                //add this session to the data base
                curSession.setSessionLength(sessionTime);
                totalBreakLength = curSession.getSessionLength() - curSession.getGameLength();
                curSession.setBreakLength(totalBreakLength);
                SessionManager sessionManager = new SessionManager();
                sessionManager.insertSession(curSession);

                // chamge ene session button , to start session
                setButtonUI(sessionButton,"Start Session");
                sessionTime = 0.0;
                curSession = null;
                sessionView.setText(getTimerText(sessionTime));
                sessionView2.setText(getTimerText(sessionTime));

                //remove break button
                breakButton.setEnabled(false);

                //go back to game page when session is done
                Intent intent = new Intent(this, ShowAllGames.class);
                startActivity(intent);
            }
        });

        resetAlert.setNeutralButton("Cancel", (DialogInterface dialogInterface, int i) -> {});

        resetAlert.show();
    }


    // keep track when game time start
    private void startGame() {
        double notificationTime = 7200.00;
        gameTimerTask = new TimerTask() {
            @Override
            public void run() {
                gameTime++;
                if(gameTime==notificationTime){
                    //give notification if session has gonei over 2 hrs without a break
                    giveNotification();
                }
            }
        };
        gameTimer.scheduleAtFixedRate(gameTimerTask, 0, 1000);
    }

    // keep track when game time end
    private void endGame() {
        if(gameTimerTask != null) {
            gameTimerTask.cancel();
            // track when session has ended and put it in databse
            curSession.setGameLength(curSession.getGameLength() + gameTime);
            gameTime = 0.0;
        }
    }

    // calculates the break time
    private void startBreak() {
        breakTime = curSession.getBreakTime(gameTime);
        if(breakTime < 120.0) {
            breakTime = 120.0;
       }
        onBreak = true;
        executeOnResume = true;
    }

    //resets the variables after taking a break
    private void endBreak(){
        breakTime = 0.0;
        onBreak = false;
    }

    //gives sound notification to remind the user to take a break.
    private void giveNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "Notification");
        builder.setPriority(NotificationCompat.PRIORITY_MAX);
        builder.setContentTitle("Break Notification");
        builder.setContentText("Playing for too long. Break is recommended!");
        builder.setContentIntent(null);
        builder.setSmallIcon(R.drawable.imageedit_4_8282271901);
        builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        builder.setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(1, builder.build());

    }

    private void createNotificationChannel(){
        //notification helper function for android versions greater than Oreo
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("Notification", "Notification", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = getSystemService(NotificationManager.class);

            manager.createNotificationChannel(channel);
        }
    }

    // change UI for buttons when clicked
    private void setButtonUI(Button buttonView, String text) {
        buttonView.setText(text);
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
