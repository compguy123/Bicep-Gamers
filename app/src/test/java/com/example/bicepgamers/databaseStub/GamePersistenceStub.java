package com.example.bicepgamers.databaseStub;

import com.example.bicepgamers.database.GamePersistence;
import com.example.bicepgamers.objects.Game;

import java.util.ArrayList;
import java.util.List;

//fake game database
public class GamePersistenceStub implements GamePersistence {
    private List<Game> games;

    public GamePersistenceStub(){
        //create fake games
        this.games = new ArrayList<>();
        Game game;
        game = new Game("fakeLOL","MOBA","PC",true);
        game.setID(0);
        game.setDeviceIntensity(3);
        game.setGenreIntensity(5);
        game.setTypeIntensity(3);
        games.add(game);

        game = new Game("fakeMario","platformer","console",false);
        game.setID(1);
        game.setDeviceIntensity(2);
        game.setGenreIntensity(4);
        game.setTypeIntensity(2);
        games.add(game);

        game = new Game("fakeCallOfDuty","shooter","console",true);
        game.setID(2);
        game.setDeviceIntensity(2);
        game.setGenreIntensity(4);
        game.setTypeIntensity(3);
        games.add(game);

    }
    //database methods
    @Override
    public List<Game> getGames() {
        return games;
    }

    @Override
    public Game getGame(int gameID) {
        Game game = null;
       for(int i =0;i<games.size();i++){
            if(gameID==games.get(i).getID()){
                game= games.get(i);
                break;
            }
       }
       return game;
    }

    @Override
    public Game insertGame(Game currentGame) {
        Game game = currentGame;
        //set id+1 to the last item in the list
        int iD = games.get(games.size()-1).getID()+1;
        game.setID(iD);
        games.add(currentGame);
        return currentGame;
    }

    @Override
    public void deleteGame(Game currentGame) {
        for(int i =0;i<games.size();i++){
            if(currentGame.getID()==games.get(i).getID()){
                games.remove(i);
                break;
            }
        }

    }

    @Override
    public int getGenreIntensity(Game currentGame) {
        return currentGame.getGenreIntensity();
    }

    @Override
    public int getDeviceIntensity(Game currentGame) {
        return currentGame.getDeviceIntensity();
    }

    @Override
    public int getTypeIntensity(Game currentGame) {
       return currentGame.getTypeIntensity();
    }
}
