package com.example.bicepgamers.objects;

import android.view.View;

public class Session {
    private Double sessionLength;   // Total length of the user session (break + game)
    private Double gameLength;      // Length of all played within a session
    private Double breakTime;       // break time after every game/when user wants one
    private Double breakLength;     // Total break of one session
    private Game curGame;           // The game being played

    //Constructor
    public Session(Game curGame) {
        sessionLength = 0.0;
        gameLength = 0.0;
        breakTime = 0.0;
        breakLength = 0.0;

        this.curGame = curGame;
    }

    // Setter functions
    public void setSessionLength(Double sessionLength) {
        this.sessionLength = sessionLength;
    }
    public void setGameLength(Double gameLength) {
        this.gameLength = gameLength;
    }
    public void setBreakLength(Double breakLength) {
        this.breakLength = breakLength;
    }

    // Getter functions
    public Double getSessionLength() {
        return sessionLength;
    }
    public Double getGameLength() {
        return gameLength;
    }

    public Double getBreakTime(Double gameTime) {
        breakTime = curGame.getGenreIntensity() + curGame.getDeviceIntensity() +
                curGame.getTypeIntensity() + gameTime;
        return breakTime;
    }

    public Double getBreakLength() {
        return breakLength;
    }


//    public Game getCurGame() {
//        return curGame;
//    }
}
