package com.example.bicepgamers.objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
//testing game object
public class gameTest {
    Game newGame;

    @Test
    public void createGame(){
        int deviceIntensity = 12;
        int genreIntensity = 10;
        int typeIntensity = 5;
        String name = "TestGame";
        String device = "PC";
        String genre = "Shooter";
        boolean type = false;
        newGame = new Game( name, genre, device, type);
        newGame.setDeviceIntensity(deviceIntensity);
        newGame.setGenreIntensity(genreIntensity);
        newGame.setTypeIntensity(typeIntensity);

        assertEquals(deviceIntensity,newGame.getDeviceIntensity());
        assertEquals(genreIntensity,newGame.getGenreIntensity());
        assertEquals(typeIntensity,newGame.getTypeIntensity());
        assertEquals(name.toUpperCase(),newGame.getGameName());

        assertEquals(device.toUpperCase(),newGame.getDevice());
        assertEquals(genre.toUpperCase(),newGame.getGenre());
        assertNotEquals(true,newGame.getType());



    }


    @Test
    public void session(){

        Double sessionLength = 40.0;
        Double breakLength = 4.0;
        Double gameLength = 15.0;


        newGame = new Game("LOL","MOBA","PC",true);
        newGame.setDeviceIntensity(12);
        newGame.setTypeIntensity(5);
        newGame.setGenreIntensity(10);

        Session newSession = new Session(newGame);
        newSession.setSessionLength(sessionLength);
        newSession.setBreakLength(breakLength);
        newSession.setGameLength(gameLength);

        assertEquals(sessionLength,newSession.getSessionLength());
        assertEquals(breakLength,newSession.getBreakLength());
        assertEquals(gameLength,newSession.getGameLength());


    }

}
