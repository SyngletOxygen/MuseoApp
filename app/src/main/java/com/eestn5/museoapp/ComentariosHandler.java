package com.eestn5.museoapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mysql.jdbc.*;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ComentariosHandler {

    Conexion conexion = new Conexion();
    String records = "",error="";
    public void enviarDatos(EditText opinion, EditText texto)
    {

        ResultSet resultSet = null;
        try {
            resultSet = conexion.getStatement().executeQuery
                    ("INSERT INTO comentarios (comentaio,fecha,opinion)"+ "values("+texto+"DEFAULT"+opinion+")");
            int i = conexion.getStatement().executeUpdate(String.valueOf(resultSet));
            if (i > 0) {
                System.out.println("ROW INSERTED");
            } else {
                System.out.println("ROW NOT INSERTED");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}


