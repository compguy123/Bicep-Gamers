package com.example.bicepgamers.objects;

import android.view.View;

public class Session {
    private int id;
    private Double sessionLength;   // Total length of the user session (break + game)
    private Double gameLength;      // Length of all played within a session
    private Double breakTime;       // break time after every game/when user wants one
    private Double breakLength;     // Total break of one session
    private Game curGame;
    private String gameName;// The game being played
    private String gameType;
    private DeviceEnum gameDevice;
    private GenreEnum gameGenre;

    //Constructor
    //This constructor is used for testing the session class
    // This constructor is also used when the user initially starts a game session. We dont know the values for parameters thus, they are zero
    public Session(Game curGame) {
        sessionLength = 0.0;
        gameLength = 0.0;
        breakTime = 0.0;
        breakLength = 0.0;

        this.curGame = curGame;
        this.gameGenre = GenreEnum.valueOf(curGame.getGenre());
        this.gameDevice = DeviceEnum.valueOf(curGame.getDevice());
        this.gameName= curGame.getGameName();
        if(curGame.getType()){
            this.gameType="ONLINE";
        }else{
            this.gameType="OFFLINE";
        }
    }

    //This is used when we get data from database of the local emulator and create a new session. The database contains all the info for the parameters
    public Session(int sessionLength, int gameLength, int breakLength,int id, String gameName,String gameGenre, String gameDevice,String gameType) {
        setSessionLength(Double.valueOf(sessionLength));
        setGameLength(Double.valueOf(gameLength));
        setBreakLength(Double.valueOf(breakLength));
        this.id= id;
        this.gameType=gameType;
        this.gameName=gameName;
        this.gameDevice=DeviceEnum.valueOf(gameDevice.toUpperCase());
        this.gameGenre=GenreEnum.valueOf(gameGenre.toUpperCase());
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
    public void setID(int id) {
        this.id = id;
    }
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public void setGameDevice(String gameDevice) {
        this.gameDevice = DeviceEnum.valueOf(gameDevice);
    }

    public void setGameGenre(String gameGenre) {
        this.gameGenre = GenreEnum.valueOf(gameGenre);
    }

    // Getter functions
    public Double getSessionLength() {
        return sessionLength;
    }
    public Double getGameLength() {
        return gameLength;
    }
    public Double getBreakLength() {
        return breakLength;
    }
    public int getID() {
        return id;
    }

    public String getGameName() {
        return gameName;
    }

    public String getGameType() {
        return gameType;
    }

    public String getGameDevice() {
        return gameDevice.toString();
    }

    public String getGameGenre() {
        return gameGenre.toString();
    }

    public Double getBreakTime(Double gameTime) {
        breakTime = (curGame.getGenreIntensity() + curGame.getDeviceIntensity() +
                curGame.getTypeIntensity()) +(gameTime* 10)/100;
//
        return breakTime;
    }

    public Game getCurGame() {
        return curGame;
    }
}