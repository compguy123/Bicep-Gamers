package com.example.bicepgamers;
import com.example.bicepgamers.DatabaseTest.AccessGames;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


// runs the database test
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessGames.class
})

public class IntegrationTest {
}
