package com.example.bicepgamers.GUI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bicepgamers.R;
import com.example.bicepgamers.objects.Game;
import com.example.bicepgamers.objects.Session;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
/*
This class is the gui implemented for iteration 1
It is the session class. The user starts the timmer
 */

public class SessionTimer extends AppCompatActivity {

    private Session curSession = null;

    // session timer display and session button
    private TextView sessionView;
    private Button sessionButton;

    // control session time running
    private Timer sessionTimer;
    private TimerTask sessionTimerTask;
    private Double sessionTime = 0.0;

    // break timer display and break button
    private TextView breakView;
    private Button breakButton;

    // control break time running
    private Timer breakTimer;
    private TimerTask breakTimerTask;
    private Double breakTime = 0.0;

    // control game time running
    private Timer gameTimer;
    private TimerTask gameTimerTask;
    private Double gameTime = 0.0;

    private boolean onBreak = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.session_timer);

        sessionView = findViewById(R.id.sessionTime);
        sessionButton = findViewById(R.id.sessionButton);

        breakView = findViewById(R.id.breakTime);
        breakButton = findViewById(R.id.breakButton);
        breakButton.setEnabled(false);

        sessionTimer = new Timer();
        gameTimer = new Timer();
        breakTimer = new Timer();
    }

    // onClick function for session button
    public void startEndSession(View view) {
        if(curSession == null) {

            Game curGame = new Game(1, 2, 3);
            curSession = new Session(curGame);

            startSession();
        }
        else {
            endSession();
        }
    }

    // onClick function for break button
    public void startEndBreak(View view) {
        if(!onBreak) {
            startBreakClick();
        }
        else {
            endBreakClick();
        }
    }

    // what to do when a session start
    private void startSession() {
        setButtonUI(sessionButton,"End Session");

        sessionTimerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    sessionTime++;
                    sessionView.setText(getTimerText(sessionTime));
                });
            }
        };
        sessionTimer.scheduleAtFixedRate(sessionTimerTask, 0, 1000);

        breakButton.setEnabled(true);

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

                endBreak();
                endGame();

                curSession.setSessionLength(sessionTime);
                setButtonUI(sessionButton,"Start Session");
                sessionTime = 0.0;
                curSession = null;
                sessionView.setText(getTimerText(sessionTime));

                breakButton.setEnabled(false);
                Log.i("im here", "im here");
            }
        });

        resetAlert.setNeutralButton("Cancel", (DialogInterface dialogInterface, int i) -> {});

        resetAlert.show();
    }

    // what to do when a break start
    private void startBreakClick() {
        startBreak();
        endGame();
    }

    // what to do when a break end
    private void endBreakClick() {
        AlertDialog.Builder resetAlert = new AlertDialog.Builder((this));
        resetAlert.setTitle("End Break");
        resetAlert.setMessage("Do you want to end this break?");
        resetAlert.setPositiveButton("End", (DialogInterface dialogInterface, int i) -> {

            endBreak();
            startGame();
        });

        resetAlert.setNeutralButton("Cancel", (DialogInterface dialogInterface, int i) -> {});

        resetAlert.show();
    }

    // keep track when game time start
    private void startGame() {
        gameTimerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> gameTime++);
            }
        };
        gameTimer.scheduleAtFixedRate(gameTimerTask, 0, 1000);
    }

    // keep track when game time end
    private void endGame() {
        if(gameTimerTask != null) {
            gameTimerTask.cancel();
            curSession.setGameLength(curSession.getGameLength() + gameTime);
            gameTime = 0.0;
        }
    }

    // keep track when break time start
    private void startBreak() {
        breakTime = curSession.getBreakTime(gameTime);
        setButtonUI(breakButton,"End Break");
        onBreak = true;

        breakTimerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    breakView.setText(getTimerText(breakTime));
                    breakTime--;
                    if(breakTime <= 0) {
                        endBreak();
                        startGame();
                    }
                });
            }
        };
        breakTimer.scheduleAtFixedRate(breakTimerTask, 0, 1000);
    }

    // keep track when break time end
    private void endBreak() {
        if(breakTimerTask != null) {
            breakTimerTask.cancel();
            curSession.setBreakLength(curSession.getBreakLength() + breakTime);
            setButtonUI(breakButton,"Start Break");
            breakTime = 0.0;
            onBreak = false;
            breakView.setText(getTimerText(breakTime));
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

    private String formatTime(int seconds, int minutes, int hours) {
        return String.format(Locale.getDefault(), "%02d", hours) + ":" +
                String.format(Locale.getDefault(),"%02d", minutes) + ":" +
                String.format(Locale.getDefault(),"%02d", seconds);
    }
}
