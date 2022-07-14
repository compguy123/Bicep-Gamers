package com.example.bicepgamers.database.hsqldb;

import com.example.bicepgamers.database.GamePersistence;
import com.example.bicepgamers.objects.Game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//game database
public class GamePersistenceDB implements GamePersistence {

    private final String dbPath;

    public GamePersistenceDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    //helper functions to create a game that was grabbed from the database
    private Game fromResultSet(final ResultSet rs) throws SQLException {
        final String gameName = rs.getString(2);
        final String gameGenre = rs.getString(3);
        final String gameDevice = rs.getString(4);
        final int gameType = rs.getInt(5);
        boolean type;
        if(gameType == 1) {
            type = true;
        }
        else {
            type = false;
        }
        //create a game from that data that was taken from the database
        Game game = new Game(gameName, gameGenre, gameDevice, type);
        game.setID( Integer.parseInt(rs.getString(1)));

        return game;
    }

    //grab all games from database
    @Override
    public List<Game> getGames() {
        final List<Game> games = new ArrayList<>();

        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM games");
            while (rs.next())
            {
                final Game game = fromResultSet(rs);
                games.add(game);
            }
            rs.close();
            st.close();

            return games;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    //grab based on id
    @Override
    public Game getGame(int gameID) {
        try (final Connection c = connection()) {
            final PreparedStatement sc = c.prepareStatement("SELECT * FROM games where gameID = ?");
            sc.setInt(1, gameID);
            Game game = null;

            //use helper fuction
            final ResultSet rs = sc.executeQuery();
            while (rs.next())
            {
                game = fromResultSet(rs);
            }
            rs.close();
            sc.close();

            return game;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

//insert game into database
    @Override
    public Game insertGame(Game currentGame) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("insert into games (gameName, gameGenre, gameDevice, gameType) values (?, ?, ?, ?)");
            st.setString(1, currentGame.getGameName());
            st.setString(2, currentGame.getGenre());
            st.setString(3, currentGame.getDevice());
            int gameType;
            if(currentGame.getType()) {
                gameType = 1;
            }
            else {
                gameType = 0;
            }
            st.setInt(4, gameType);

            st.executeUpdate();

            return currentGame;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    //delete game from databse , IT compares the ID to delete
    @Override
    public void deleteGame(Game currentGame) {
        try (final Connection c = connection()) {
            final PreparedStatement sc = c.prepareStatement("DELETE FROM games WHERE gameID = ?");
            sc.setInt(1, currentGame.getID());
            sc.executeUpdate();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    //intensity is a number rather than an enum of low med high because we used to int intensity to calculate the break time
    //the higher the number, the greater intensity
    @Override
    public int getGenreIntensity(Game currentGame) {
        try (final Connection c = connection()) {
            final PreparedStatement sc = c.prepareStatement("SELECT INTENSITY FROM GENREINTENSITY WHERE GENRE = ?");
            sc.setString(1, currentGame.getGenre().toUpperCase());
            int genreIntensity = 0;

            final ResultSet rs = sc.executeQuery();
            while (rs.next()) {
                genreIntensity = rs.getInt(1);
            }

            rs.close();
            sc.close();

            return genreIntensity;

        } catch (final SQLException e) {
             throw new PersistenceException(e);
        }
    }

    //intensity is a number rather than an enum of low med high because we used to int intensity to calculate the break time
    //the higher the number, the greater intensity
    @Override
    public int getDeviceIntensity(Game currentGame) {
        try (final Connection c = connection()) {
            final PreparedStatement sc = c.prepareStatement("SELECT INTENSITY FROM DEVICEINTENSITY WHERE DEVICE = ?");
            sc.setString(1, currentGame.getDevice().toUpperCase());
            int deviceIntensity = 0;

            final ResultSet rs = sc.executeQuery();
            while (rs.next()) {
                deviceIntensity = rs.getInt(1);
            }

            rs.close();
            sc.close();

            return deviceIntensity;

        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }


    //intensity is a number rather than an enum of low med high because we used to int intensity to calculate the break time
    //the higher the number, the greater intensity
    @Override
    public int getTypeIntensity(Game currentGame) {
        try (final Connection c = connection()) {
            final PreparedStatement sc = c.prepareStatement("SELECT INTENSITY FROM TYPEINTENSITY WHERE TYPE = ?");
            String type;
            if(currentGame.getType()){
                type = "ONLINE";
            }
            else {
                type = "OFFLINE";
            }
            sc.setString(1, type);
            int typeIntensity = 0;

            final ResultSet rs = sc.executeQuery();
            while (rs.next()) {
                typeIntensity = rs.getInt(1);
            }

            rs.close();
            sc.close();

            return typeIntensity;

        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }
}
