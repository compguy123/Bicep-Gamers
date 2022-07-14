package com.example.bicepgamers.database;

import com.example.bicepgamers.objects.Game;

import java.util.List;

//interface for game databse
public interface GamePersistence {
    List<Game> getGames();

    Game getGame(int gameID);

    Game insertGame(Game currentGame);

    void deleteGame(Game currentGame);

    int getGenreIntensity(Game currentGame);

    int getDeviceIntensity(Game currentGame);

    int getTypeIntensity(Game currentGame);

}
