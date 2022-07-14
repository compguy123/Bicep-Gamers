
package com.example.bicepgamers.objects;

public class Game {
    private int id;                 // Id used to identify the game for DB
    private String gameName;
    private DeviceEnum device;          // Type of device used (PC, console ..)
    boolean type;               // False is offline, True is online
    private GenreEnum genre;           // Genre of the game

    // Used for calculating exercises
    private int genreIntensity;
    private int deviceIntensity;
    private int typeIntensity;

    //Constructor 1
    public Game(String gameName, String genre, String device, boolean type) {
        this.gameName = gameName.toUpperCase();
        this.device = DeviceEnum.valueOf(device.toUpperCase());
        this.genre = GenreEnum.valueOf(genre.toUpperCase());
        this.type = type;
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

    public void setDevice(String device) {
        this.device = DeviceEnum.valueOf(device);
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public void setGenre(String genre) {
        this.genre = GenreEnum.valueOf(genre);
    }

    public void setID(int id){
        this.id=id;
    }

    // Getter functions

    public String getGameName() {
        return gameName;
    }

    public String getDevice() {
        return device.toString();
    }

    public String getGenre() {
        return genre.toString();
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

    public boolean getType() {
        return type;
    }

    public int getID() {
        return id;
    }

    @Override
    public String toString() {
        String typeTxt;
        if(getType()) {
            typeTxt = "Online";
        }
        else {
            typeTxt = "Offline";
        }
        return "Game{" +
                "gameName='" + gameName + '\'' +
                "device='" + device + '\'' +
                "type=" + typeTxt +
                "genre='" + genre + '\'' +
                '}';
    }
}

