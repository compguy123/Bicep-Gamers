package com.example.bicepgamers.Logic;

import com.example.bicepgamers.Application.Services;
import com.example.bicepgamers.database.SessionPersistence;
import com.example.bicepgamers.objects.Session;

import java.util.Collections;
import java.util.List;

//logic layer for the session database
public class SessionManager {
    private SessionPersistence sessionPersistence;
    private List<Session> sessions;

    public SessionManager() {
        sessionPersistence = Services.getSessionPersistence();
        sessions = null;
    }

    //for testing
    public SessionManager(final SessionPersistence sessionPersistence) {
        this();
        this.sessionPersistence = sessionPersistence;
    }

    //database methods
    public List<Session> getAllSessions() {
        sessions = sessionPersistence.getSessions();
        return Collections.unmodifiableList(sessions);
    }

    public Session insertSession(Session newSession) {
        return sessionPersistence.insertSession(newSession);
    }

    public void deleteSession(Session currentSession)
    {
        sessionPersistence.deleteSession(currentSession);
    }
}
