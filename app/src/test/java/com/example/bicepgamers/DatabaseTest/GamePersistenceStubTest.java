package com.example.bicepgamers.DatabaseTest;

import com.example.bicepgamers.Logic.GameManager;
import com.example.bicepgamers.databaseStub.GamePersistenceStub;
import com.example.bicepgamers.objects.Game;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

//test fake database
public class GamePersistenceStubTest {
    private GameManager gameManager;
//testing fake game databse
    @Before
    public void setup(){
        this.gameManager= new GameManager(new GamePersistenceStub());
    }

    @Test
    //test get games
    public void getGames(){
        List<Game> games = gameManager.getAllGames();
        assertEquals(3, games.size());
        // name turns to all caps in game constructor
        assertEquals("FAKELOL",games.get(0).getGameName());
    }

    //test get game based on id
    @Test
    public void getGame(){
        Game game = gameManager.getGame(0);
        assertEquals("FAKELOL",game.getGameName());

       game = gameManager.getGame(1000);
        assertNull(game);
    }

    //aedding a new game in database
    @Test
    public void testInsert (){
        //set fake values when adding a new game
        Game game = new Game("fakeDota", "moba", "Pc",true);
        game.setDeviceIntensity(10);
        game.setTypeIntensity(10);
        game.setGenreIntensity(10);
        gameManager.insertGame(game);

        //see if game is added
        assertEquals("FAKEDOTA", gameManager.getGame(3).getGameName());
        assertEquals(10,gameManager.getGame(3).getGenreIntensity());
    }

    @Test
    public void testDelete(){
        //add this game from database
        Game game = new Game("fakeSmite", "moba", "Pc",true);
        game.setID(100);
        gameManager.insertGame(game);
        assertEquals(4,gameManager.getAllGames().size());

        //see if deleted
        gameManager.deleteGame(game);
        assertEquals(3,gameManager.getAllGames().size());
    }

    @Test
    public void getIntensities(){
        //we know that intensities we put
        assertEquals(5,gameManager.getAllGames().get(0).getGenreIntensity());
        assertEquals(3,gameManager.getAllGames().get(0).getDeviceIntensity());
        assertEquals(3,gameManager.getAllGames().get(0).getTypeIntensity());

    }
}
