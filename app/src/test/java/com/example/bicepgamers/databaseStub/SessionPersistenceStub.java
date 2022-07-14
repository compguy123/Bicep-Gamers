package com.example.bicepgamers.databaseStub;

import com.example.bicepgamers.database.SessionPersistence;
import com.example.bicepgamers.objects.Session;

import java.util.ArrayList;
import java.util.List;

//fake session
public class SessionPersistenceStub implements SessionPersistence {
    private List <Session> sessions;

    public SessionPersistenceStub(){
        this.sessions= new ArrayList<>();
        //create fake sessions
        Session session;

        session= new Session(10,10,10,0,"fakeLOL","moba","pc","online");
        sessions.add(session);

        session= new Session(20,20,20,1, "fakeMario","platformer","console","offline");
        sessions.add(session);

        session = new Session(30,30,30,2,"fakeCallOfDuty","shooter","console","online");
        sessions.add(session);
    }
    //session methods
    @Override
    public List<Session> getSessions() {
        return  this.sessions;
    }

    @Override
    public Session insertSession(Session currentSession) {
        this.sessions.add(currentSession);
        return currentSession;
    }

    @Override
    public void deleteSession(Session currentSession) {
        for(int i =0;i<sessions.size();i++){
            if(sessions.get(i).getID()==currentSession.getID()){
                sessions.remove(i);
                break;
            }
        }
    }
}
