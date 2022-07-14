package com.example.bicepgamers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.example.bicepgamers.DatabaseTest.AccessGames;
import com.example.bicepgamers.DatabaseTest.GamePersistenceStubTest;
import com.example.bicepgamers.DatabaseTest.SessionPersistenceStubTest;
import com.example.bicepgamers.LogicTest.GameManagerTest;
import com.example.bicepgamers.LogicTest.SessionManagerTest;
import com.example.bicepgamers.objects.Game;
import com.example.bicepgamers.objects.Session;
import com.example.bicepgamers.objects.gameTest;
import com.example.bicepgamers.objects.sessionTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        GamePersistenceStubTest.class,
        SessionPersistenceStubTest.class,
        GameManagerTest.class,
        SessionManagerTest.class,
        gameTest.class,
        sessionTest.class,
})

public class AllTest {

}
