package com.example.bicepgamers.database;

import com.example.bicepgamers.objects.Session;

import java.util.List;

//interface for session database

public interface SessionPersistence {
    //interface for session database
    List<Session> getSessions();

    Session insertSession(Session currentSession);

    void deleteSession(Session currentSession);
}
