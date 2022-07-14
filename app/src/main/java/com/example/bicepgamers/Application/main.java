package com.example.bicepgamers.Application;
import com.example.bicepgamers.database.HSQLDBConnection;
import com.example.bicepgamers.objects.Game;
import java.util.List;
/*
    Killer Nano Robots Group 16
    App Name: Bicep Gamers
    Group Members: Laren Castelino
                   Obaid Ali
                   Hai Nquyen
                   Zelin Qiu
                   ArshDeep Singh

    This projects needs the android version: AS bumblebee 2021.1.1 Patch 2
 */
public class main {
    public static void main(String[] args) {


        //get databse connection and initalize the games
        HSQLDBConnection a = new HSQLDBConnection();
       // a.initalizeGames();
        List<Game> g = a.gameList();



    }

}



