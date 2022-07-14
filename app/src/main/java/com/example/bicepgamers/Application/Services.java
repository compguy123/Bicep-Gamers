package com.example.bicepgamers.Application;

import com.example.bicepgamers.database.GamePersistence;
import com.example.bicepgamers.database.SessionPersistence;
import com.example.bicepgamers.database.hsqldb.GamePersistenceDB;
import com.example.bicepgamers.database.hsqldb.SessionPersistenceDB;
import com.example.bicepgamers.objects.VideoObject;

import java.util.ArrayList;

public class Services {
    private static GamePersistence gamePersistence = null;
    private static SessionPersistence sessionPersistence = null;

    public static synchronized GamePersistence getGamePersistence()
    {
        if (gamePersistence == null)
        {
            gamePersistence = new GamePersistenceDB(Main.getDBPathName());
        }

        return gamePersistence;
    }

    public static synchronized SessionPersistence getSessionPersistence()
    {
        if (sessionPersistence == null)
        {
            sessionPersistence = new SessionPersistenceDB(Main.getDBPathName());
        }

        return sessionPersistence;
    }

}
