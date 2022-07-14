package com.example.bicepgamers.database;

import com.example.bicepgamers.objects.Game;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/*
This class creates a new database
The database is saved as data/mygames in the src folder
The database for iteration 1 contains games
 */
public class HSQLDBConnection {
    private Connection con = null;
    private String connect = "jdbc:hsqldb:file:data/myGames";
    private List <Game> games = new ArrayList<>();

    public HSQLDBConnection() {
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            con = DriverManager.getConnection(connect,"SA","");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /*
    The database has already been initalized but, adds duplicates when run again.
    Fix in iteration 2
     */
    /*
    public void initalizeGames(){

       String table = initalizeTables("src/main/java/com/example/bicepgamers/database/game");
       String games = initalizeTables("gamesTables.txt");
        try {
            con.createStatement().executeUpdate(table);
            con.createStatement().executeUpdate(games);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
*/

    // reads a .txt file that contails sql code and executes
    private String initalizeTables(String filePath){
        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null)
            {
                contentBuilder.append(sCurrentLine).append("\n");
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(contentBuilder.toString());
        return contentBuilder.toString();
    }


    //creates games objects from sql games talble.
    public List<Game> gameList(){
        try {
            PreparedStatement preparedStatement = con.prepareStatement("select * from games");
            preparedStatement.clearParameters();
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                games.add(new Game(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(5),Integer.parseInt(rs.getString(5))));

            }

            for (Game g :games){
                System.out.println(g.toString());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }


        return games;
    }

    public void addANewGame(){

    }
}
