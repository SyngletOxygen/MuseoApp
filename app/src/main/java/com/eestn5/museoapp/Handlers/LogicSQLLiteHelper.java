package com.eestn5.museoapp.Handlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.eestn5.museoapp.modelos.Puntaje;

import java.util.ArrayList;
import java.util.List;

public class LogicSQLLiteHelper extends SQLiteOpenHelper {
    private static final String NOMBRE_BASE_DE_DATOS = "User";

    private static final int VERSION_BASE_DE_DATOS = 1;

    private static LogicSQLLiteHelper INSTANCE;

    public LogicSQLLiteHelper(@Nullable Context context) {
        super(context, NOMBRE_BASE_DE_DATOS, null, VERSION_BASE_DE_DATOS);
    }

    /**
     This class is a singleton, this is the getter of the Instance.

     @return returns the Instance.
     */
    public static LogicSQLLiteHelper getInstance(){

        return INSTANCE;
    }

    /**
     This class is a singleton,you may need to create an instance before using it throughout the application
     @param context the context of the Activity
     @return returns a Boolean  (has been created  = false, needs to be created  = true).
     */
    public static boolean createInstance(Context context){
        boolean trigger = false;
        if(INSTANCE == null) {
            INSTANCE = new LogicSQLLiteHelper(context);
            trigger = true;
        }
        return trigger;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE SCORE (_id INTEGER PRIMARY KEY AUTOINCREMENT, puntaje INT, fecha STRING,nombre_juego STRING);");
        db.execSQL("CREATE TABLE CONFIG (_id INTEGER PRIMARY KEY AUTOINCREMENT, config INT);");
    }

    /**
     Lets you Add a SCORE to the Database.
     @param p this is the Puntaje that you want to Add.
     */
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

    /**
     Sets the Configuration of the whole application into a table inside a database, so it is persistent.
     @param discapacidad Int representing the disability set by the user. [0 = none,1 =deaf,2 = blind,3 =mental disability]
     */
    public void SetConfig(int discapacidad){
        SQLiteDatabase db = getWritableDatabase();
        if(db != null){

            ContentValues valores = new ContentValues();
            valores.put("config", discapacidad);
            valores.put("_id",1);
            try{
            db.insertOrThrow("config",null,valores);}
            catch(Exception e){
                db.replaceOrThrow("config",null,valores);}

            }


            db.close();

        }


    /**
     Gets the Configuration of the whole application from a table inside a database.
     @return returns the disability set by the user. [0 = none,1 =deaf,2 = blind,3 =mental disability]
     */
    public int GetConfig (){

        int config = -1;
        SQLiteDatabase db = getReadableDatabase();
        if(db != null) {
            try {
                String[] valores_recuperar = {"config"};
                Cursor c = db.query("config", valores_recuperar, null, null, null, null, "config DESC", String.valueOf(25));
                c.moveToFirst();
                config = c.getInt(0);
                db.close();
                c.close();
            }catch (Exception e){}
        }
        return config;
    }

    /**
     Gets the list of the Puntaje from the SCORE database. This is for games only.
     @return returns the disability set by the user. [0 = none,1 =deaf,2 = blind,3 =mental disability]
     */
    public List<Puntaje> getSCORE() {
        SQLiteDatabase db = getReadableDatabase();

        List<Puntaje> lista_de_puntajes = new ArrayList<Puntaje>();
        if(db != null) {
            String[] valores_recuperar = {"puntaje", "fecha", "nombre_juego"};
            Cursor c = db.query("score", valores_recuperar,
                    null, null, null, null, "puntaje DESC", String.valueOf(25));
            c.moveToFirst();
            do {
                Puntaje puntaje = new Puntaje(c.getInt(0), c.getInt(1), c.getString(2));
                lista_de_puntajes.add(puntaje);
            } while (c.moveToNext());
            db.close();
            c.close();
        }
        return lista_de_puntajes;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
