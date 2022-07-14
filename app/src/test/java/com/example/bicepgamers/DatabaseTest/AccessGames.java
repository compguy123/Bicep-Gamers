package com.example.bicepgamers.DatabaseTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.example.bicepgamers.Logic.GameManager;
import com.example.bicepgamers.Util.TestUtils;
import com.example.bicepgamers.database.GamePersistence;
import com.example.bicepgamers.database.hsqldb.GamePersistenceDB;
import com.example.bicepgamers.objects.Game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AccessGames {
    private GameManager gameManager;
    private File tempDB;

    @Before
    public void setup() throws IOException{
        this.tempDB = TestUtils.copyDB();
        final GamePersistence persistence = new GamePersistenceDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.gameManager = new GameManager(persistence);
    }

    @Test
    //test get games
    public void getGames(){
        List<Game> games = gameManager.getAllGames();
        assertEquals(3, games.size());
        // name turns to all caps in game constructor
        assertEquals("LEAGUE OF LEGENDS",games.get(0).getGameName());
    }

    //test get game based on id
    @Test
    public void getGame(){
        Game game = gameManager.getGame(0);
        assertEquals("LEAGUE OF LEGENDS",game.getGameName());
        game = gameManager.getGame(1000);
        assertNull(game);
    }

    //aedding a new game in database
    @Test
    public void testInsert (){
        //set fake values when adding a new game
        Game game = new Game("Dota", "MOBA", "PC",true);
        gameManager.insertGame(game);

        //see if game is added
        assertEquals("DOTA", gameManager.getGame(3).getGameName());
    }

    @Test
    public void testDelete(){
        //add this game from database
        Game game = new Game("SMITE", "MOBA", "PC",true);
        game.setID(3);
        gameManager.insertGame(game);
        assertEquals(4,gameManager.getAllGames().size());

        //see if deleted
        gameManager.deleteGame(game);
        assertEquals(3,gameManager.getAllGames().size());
    }

    @Test
    public void getIntensities(){
        //set game intensities
        Game game = gameManager.getAllGames().get(0);
        assertEquals(5,gameManager.getGenreIntensity(game));
        assertEquals(3,gameManager.getDeviceIntensity(game));
        assertEquals(3,gameManager.getTypeIntensity(game));

    }

    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }

}


