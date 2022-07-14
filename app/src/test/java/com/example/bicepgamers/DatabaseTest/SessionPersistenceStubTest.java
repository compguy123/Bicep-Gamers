package com.example.bicepgamers.DatabaseTest;

import static org.junit.Assert.assertEquals;

import com.example.bicepgamers.Logic.SessionManager;
import com.example.bicepgamers.databaseStub.SessionPersistenceStub;
import com.example.bicepgamers.objects.Session;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
//test fake session database
public class SessionPersistenceStubTest {
    //testing fake databse for session
    private SessionManager sessionManager;

    @Before
    public void setup(){
        this.sessionManager= new SessionManager(new SessionPersistenceStub());
    }

    @Test
    //test get all session
    public void testGetSession(){
        List<Session> sessionList = sessionManager.getAllSessions();
        //check the contents are correct
        assertEquals(3,sessionList.size());
        assertEquals("fakeLOL",sessionList.get(0).getGameName());
    }

    @Test
    //check if session is added
    public void insertSessions(){
        Session session = new Session(10,10,10,5,"Smite", "moba", "Pc","offline");
        sessionManager.insertSession(session);
        //check if added
        assertEquals(4,sessionManager.getAllSessions().size());

    }

    @Test
    //delete a session
    public void deleteSession(){
        //delete this session from the list
        Session session= new Session(10,10,10,0,"fakeLOL","moba","pc","online");
        sessionManager.deleteSession(session);
        assertEquals(2,sessionManager.getAllSessions().size());
    }
}
