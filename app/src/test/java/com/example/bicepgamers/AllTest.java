package com.example.bicepgamers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.example.bicepgamers.objects.Game;
import com.example.bicepgamers.objects.Session;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class AllTest {
    Game newGame;

    @Test
    public void createGame(){
        int id  = 1;
        int deviceIntensity = 12;
        int genreIntensity = 10;
        int typeIntensity = 5;
        String name = "TestGame";
        String device = "PC";
        String genre = "Shooter";
        int type = 0;
        newGame = new Game(id, name, genre, device, type);
        newGame.setDeviceIntensity(deviceIntensity);
        newGame.setGenreIntensity(genreIntensity);
        newGame.setTypeIntensity(typeIntensity);

        assertEquals(deviceIntensity,newGame.getDeviceIntensity());
        assertEquals(genreIntensity,newGame.getGenreIntensity());
        assertEquals(typeIntensity,newGame.getTypeIntensity());
        assertEquals(id,newGame.getId());
        assertEquals(name,newGame.getGameName());

        assertEquals(device,newGame.getDevice());
        assertEquals(genre,newGame.getGenre());
        assertNotEquals(1,newGame.getType());



    }


    @Test
    public void session(){

        int deviceIntensity = 12;
        int genreIntensity = 10;
        int typeIntensity = 5;
        Double sessionLength = 40.0;
        Double breakLength = 4.0;
        Double gameLength = 15.0;
        Double gameTime = 12.0;
        Double breakTime = 39.0;

        newGame = new Game(deviceIntensity, genreIntensity, typeIntensity);

        Session newSession = new Session(newGame);
        newSession.setSessionLength(sessionLength);
        newSession.setBreakLength(breakLength);
        newSession.setGameLength(gameLength);

        assertEquals(sessionLength,newSession.getSessionLength());
        assertEquals(breakLength,newSession.getBreakLength());
        assertEquals(gameLength,newSession.getGameLength());
        assertEquals(breakTime,newSession.getBreakTime(gameTime));

    }

}
