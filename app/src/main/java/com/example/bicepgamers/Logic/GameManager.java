package com.example.bicepgamers.Logic;

import com.example.bicepgamers.Application.Services;
import com.example.bicepgamers.database.GamePersistence;
import com.example.bicepgamers.objects.Game;

import java.util.Collections;
import java.util.List;

//logic layer for the game databse
public class GameManager {

    private GamePersistence gamePersistence;
    private List<Game> games;

    public GameManager() {
        gamePersistence = Services.getGamePersistence();
        games = null;
    }

    //used for testing
    public GameManager(final GamePersistence gamePersistence) {
        this();
        this.gamePersistence = gamePersistence;
    }

    //databse methods
    public Game getGame(int gameID) {
        return gamePersistence.getGame(gameID);
    }

    public List<Game> getAllGames() {
        games = gamePersistence.getGames();
        return Collections.unmodifiableList(games);
    }

    public Game insertGame(Game newGame) {
        return gamePersistence.insertGame(newGame);
    }

    public void deleteGame(Game currentGame)
    {
        gamePersistence.deleteGame(currentGame);
    }

    public int getGenreIntensity(Game currentGame) {
        return gamePersistence.getGenreIntensity(currentGame);
    }

    public int getDeviceIntensity(Game currentGame) {
        return gamePersistence.getDeviceIntensity(currentGame);
    }

    public int getTypeIntensity(Game currentGame) {
        return gamePersistence.getTypeIntensity(currentGame);
    }

}
