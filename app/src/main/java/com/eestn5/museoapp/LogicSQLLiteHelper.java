package com.eestn5.museoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LogicSQLLiteHelper extends SQLiteOpenHelper {
    private static final String NOMBRE_BASE_DE_DATOS = "User";

    private static final int VERSION_BASE_DE_DATOS = 1;

    public LogicSQLLiteHelper(@Nullable Context context) {
        super(context, NOMBRE_BASE_DE_DATOS, null, VERSION_BASE_DE_DATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE SCORE (_id INTEGER PRIMARY KEY AUTOINCREMENT, puntaje INT, fecha STRING,nombre_juego STRING);");
        db.execSQL("CREATE TABLE CONFIG (_id INTEGER PRIMARY KEY AUTOINCREMENT, config INT);");
    }

    public void AddSCORE(Puntaje p) {
        SQLiteDatabase db = getWritableDatabase();
        if(db != null){
            ContentValues valores = new ContentValues();
            valores.put("puntaje", p.Puntaje);
            valores.put("fecha", p.fecha_de_creacion);
            valores.put("nombre_juego", p.fecha_de_creacion);
            db.insert("score", null, valores);
            db.close();
        }
    }

    public List<Puntaje> getSCORE() {
        SQLiteDatabase db = getReadableDatabase();
        List<Puntaje> lista_de_puntajes = new ArrayList<Puntaje>();
        String[] valores_recuperar = { "puntaje", "fecha","nombre_juego"};
        Cursor c = db.query("score", valores_recuperar,
                null, null, null, null, "puntaje DESC", String.valueOf(25));
        c.moveToFirst();
        do {
            Puntaje puntaje = new Puntaje(c.getInt(0), c.getInt(1),c.getString(2));
            lista_de_puntajes.add(puntaje);
        } while (c.moveToNext());
        db.close();
        c.close();
        return lista_de_puntajes;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
