package com.example.bicepgamers.LogicTest;

import com.example.bicepgamers.Logic.GameManager;
import com.example.bicepgamers.database.GamePersistence;
import com.example.bicepgamers.objects.Game;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
//using mock the test game databse
public class GameManagerTest {
    private GameManager gameManager;
    private GamePersistence gamePersistence;
    List<Game> games ;
    Game game;
    @Before
    public void setup(){
        games = new ArrayList<>();
        gamePersistence = mock(GamePersistence.class);
        gameManager= new GameManager(gamePersistence);
    }

    @Test
    //create a fake list and check if get list is returned
    public void testGetList(){
        System.out.println("\n testing get List");
        //add fake object in the list
        game = new Game("LOL", "Moba", "Pc", true);
        game.setID(0);
        games.add(game);
        when(gamePersistence.getGames()).thenReturn(games);

        //get the list and see if fake object is there
        List<Game> getGames = gameManager.getAllGames();
        assertEquals("LOL", getGames.get(0).getGameName());

    }

    @Test
    //get a game based on its id
    public void testGetGame(){
        System.out.println("\nStarting testGetGame");
        //create fake games
        game = new Game("fakeLOL", "Moba", "Pc", true);
        game.setID(0);
        games.add(game);

        //mock
        when(gamePersistence.getGame(0)).thenReturn(games.get(0));
        //check if we can get fake game
        Game newGame = gameManager.getGame(0);
        assertEquals("PC",newGame.getDevice());
        }

    @Test
    //testing add a new game, same as delete a game .
    public void testAddGame(){
        System.out.println("\n Testing add a new game");
        Game game = new Game ("fakeCOD", "Shooter", "Console", true);
        game.setID(1);
        games.add(game);

        when(gamePersistence.insertGame(game)).thenReturn(games.get(0));

        //add  game through game manager
        Game newGame = gameManager.insertGame(game);

        //check if it went through
        assertEquals("FAKECOD", newGame.getGameName());

    }

    @Test
    // test get intensities
    public void testIntensities(){
        System.out.println("\n Testing intensities");
        Game game = new Game ("fakeCOD", "Shooter", "Console", true);
        game.setID(1);
        game.setGenreIntensity(1);
        game.setTypeIntensity(3);
        game.setDeviceIntensity(2);
        games.add(game);

        when(gamePersistence.getGenreIntensity(game)).thenReturn(games.get(0).getGenreIntensity());
        when(gamePersistence.getDeviceIntensity(game)).thenReturn(games.get(0).getDeviceIntensity());
        when(gamePersistence.getTypeIntensity(game)).thenReturn(games.get(0).getTypeIntensity());


        //get intensities
        int genreIntensity = gameManager.getGenreIntensity(game);
        int deviceIntensity = gameManager.getDeviceIntensity(game);
        int typeIntensity = gameManager.getTypeIntensity(game);

        //check if it went through
        assertEquals(genreIntensity,1);
        assertEquals(deviceIntensity,2);
        assertEquals(typeIntensity,3);

    }



}
