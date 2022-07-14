package com.example.bicepgamers.objects;

public class Game {
    private int id;                 // Id used to identify the game for DB
    private String gameName;
    private String device;          // Type of device used (PC, console ..)
    private int type;               // False is offline, True is online
    private String genre;           // Genre of the game

    // Used for calculating exercises
    private int genreIntensity;
    private int deviceIntensity;
    private int typeIntensity;

    // Constructors are going to be changed
    //Constructor 1
    public Game(int id, String gameName,String genre, String device, int type) {
        this.id = id;
        this.gameName = gameName;
        this.device = device;
        this.genre = genre;
        this.type = type;
    }

    // Constructor 2
    public Game(int genreIntensity, int deviceIntensity, int typeIntensity) {
        this.setGenreIntensity(genreIntensity);
        this.setDeviceIntensity(deviceIntensity);
        this.setTypeIntensity(typeIntensity);
    }

    // Setter functions
    public void setGenreIntensity(int genreIntensity) {
        this.genreIntensity = genreIntensity;
    }
    public void setDeviceIntensity(int deviceIntensity) {
        this.deviceIntensity = deviceIntensity;
    }
    public void setTypeIntensity(int typeIntensity) {
        this.typeIntensity = typeIntensity;
    }
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setDevice(String device) {
        this.device = device;
    }
    public void setType(int type) {
        this.type = type;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    // Getter functions
    public int getId() {
        return id;
    }
    public String getGameName() {
        return gameName;
    }
    public String getDevice() {
        return device;
    }
    public String getGenre() {
        return genre;
    }
    public int getGenreIntensity() {
        return genreIntensity;
    }
    public int getDeviceIntensity() {
        return deviceIntensity;
    }
    public int getTypeIntensity() {
        return typeIntensity;
    }
    public int getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", gameName='" + gameName + '\'' +
                ", device='" + device + '\'' +
                ", type=" + type +
                ", genre='" + genre + '\'' +
                '}';
    }
}


