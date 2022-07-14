package com.example.bicepgamers.database.hsqldb;

import com.example.bicepgamers.Logic.GameManager;
import com.example.bicepgamers.database.SessionPersistence;
import com.example.bicepgamers.objects.Game;
import com.example.bicepgamers.objects.Session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//session databse
public class SessionPersistenceDB implements SessionPersistence {

    private final String dbPath;
    private GameManager gameManager = new GameManager();

    public SessionPersistenceDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    //helper function to create a session form the data that was from the databse
    private Session fromResultSet(final ResultSet rs) throws SQLException {
        final int sessionLength = rs.getInt(2);
        final int gameLength = rs.getInt(3);
        final int breakLength = rs.getInt(4);

        //create new session using the second constructor. this creates a session using the info form the database
        Session session = new Session(sessionLength, gameLength, breakLength, Integer.parseInt(rs.getString(1)), rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
        return session;
    }

    //get all session from the database
    @Override
    public List<Session> getSessions() {
        final List<Session> sessions = new ArrayList<>();

        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM sessions");
            while (rs.next())
            {
                //use helper function
                final Session session = fromResultSet(rs);
                sessions.add(session);
            }
            rs.close();
            st.close();

            return sessions;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    //insert sessions
    @Override
    public Session insertSession(Session currentSession) {
        try (final Connection c = connection()) {
            System.out.println(currentSession.getGameName());
            final PreparedStatement st = c.prepareStatement("insert into sessions (sessionLength, gameLength, breakLength, gameName, gameGenre, gameDevice, gameType) values (?, ?, ?, ?,?,?,?)");
            st.setInt(1, currentSession.getSessionLength().intValue());
            st.setInt(2, currentSession.getGameLength().intValue());
            st.setInt(3, currentSession.getBreakLength().intValue());
            st.setString(4, currentSession.getGameName());
            st.setString(5,currentSession.getGameGenre());
            st.setString(6,currentSession.getGameDevice());
            st.setString(7,currentSession.getGameType());

            st.executeUpdate();

            return currentSession;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    //delete a session
    @Override
    public void deleteSession(Session currentSession) {
        try (final Connection c = connection()) {
            final PreparedStatement sc = c.prepareStatement("DELETE FROM sessions WHERE sessionID = ?");
            sc.setInt(1, currentSession.getID());
            sc.executeUpdate();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }
}
