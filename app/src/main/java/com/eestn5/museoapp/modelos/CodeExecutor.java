package com.eestn5.museoapp.modelos;

import org.json.JSONException;

@FunctionalInterface
public interface CodeExecutor {

    public void run(Object ... o) throws Exception;

}
