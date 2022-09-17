package com.eestn5.museoapp;

import android.os.AsyncTask;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;

public class Conexion extends AsyncTask<Void, Void, Void> {
    private Connection connection;
    private Statement statement;
    String records = "",error="";
    public Void doInBackground(Void... voids) {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");

            connection = (Connection) DriverManager.getConnection("jdbc:mysql://192.168.1.2:3306/android", "root", "");

            statement = (Statement) connection.createStatement();

    }catch(Exception e)
        {
            error = e.toString();
        }

        return null;

    }


    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }
}