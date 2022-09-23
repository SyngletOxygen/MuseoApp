package com.eestn5.museoapp.Static;

public class Config {
    public static final int NONDISHABLED = 0;
    public static final int BLIND = 1;
    public static final int DEAF = 2;
    public static final int MENTALYDISHABLED = 3;
    public static final int NOCONFIG = -1;
    private static int config;
    public static String url = "olimpiadas2022eestn5.herokuapp.com";
    public static void  setconfig (int conf){

        config = conf;

    }

    public static int  getConfig(){
        return config;
    }

}
