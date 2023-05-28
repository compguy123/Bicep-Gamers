package com.example.bicepgamers.Application;
//import com.example.bicepgamers.database.HSQLDBConnection;
//import com.example.bicepgamers.objects.Game;
//
//import java.sql.SQLOutput;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
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
public class Main {

    private static String dbName="BG";

    public static void main(String[] args) {
    }


    public static void setDBPathName(final String name) {
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dbName = name;
    }

    public static String getDBPathName() {
        return dbName;
    }

}



