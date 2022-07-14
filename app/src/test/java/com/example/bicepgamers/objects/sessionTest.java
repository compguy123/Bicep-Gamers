package com.example.bicepgamers.objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


import org.junit.Before;
import org.junit.Test;

//testing session object
public class sessionTest {
    Game game;
    Session session;
    @Before
    public void initalize(){
        game = new Game("LOL","MOBA","PC", false);
        session = new Session(game);

    }
    @Test
    public void testGetters(){
        // uses Double in object
        assertEquals((Object) 0.0,session.getBreakLength());
        assertEquals((Object) 0.0,session.getSessionLength());
        assertEquals((Object) 0.0,session.getGameLength());
    }
    @Test
    public void breakTime(){
        Double gameTime = 15.0;

        Double getTime = session.getBreakTime(gameTime);
        Double actualBreakTime = (gameTime*10)/100;
        assertEquals(actualBreakTime, getTime);
        System.out.println("Finished testSession");
    }


}
