package com.example.bicepgamers.LogicTest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.bicepgamers.Logic.SessionManager;
import com.example.bicepgamers.database.SessionPersistence;
import com.example.bicepgamers.objects.Game;
import com.example.bicepgamers.objects.Session;


import java.util.ArrayList;
import java.util.List;
//using mock to test database of session
public class SessionManagerTest {
    private SessionManager sessionManager;
    private SessionPersistence sessionPersistence;
    List<Session> sessions;
    Session session;

    @Before
    public void setup() {
        sessions = new ArrayList<>();
        sessionPersistence = mock(SessionPersistence.class);
        sessionManager = new SessionManager(sessionPersistence);
    }

    @Test
    public void testGetSessions() {
        System.out.println("\n Testing get Sessions");
        session = new Session(10, 10, 10, 0, "fakeLOL", "Moba", "Pc", "online");
        sessions.add(session);
        when(sessionPersistence.getSessions()).thenReturn(sessions);

        //check if list has been returned
        List<Session> getSessions = sessionManager.getAllSessions();
        assertEquals("PC", getSessions.get(0).getGameDevice());
    }

    @Test
    //testing add a new Session
    public void testAddSession() {
        System.out.println("\n Testing add new Session ");
        Session session = new Session(10, 10, 10, 0, "LOL", "Moba", "Pc", "online");
        sessions.add(session);
        when(sessionPersistence.insertSession(session)).thenReturn(sessions.get(0));

        //add  game through game manager
        Session newSession = sessionManager.insertSession(session);

        assertEquals("MOBA", newSession.getGameGenre());

    }

}
