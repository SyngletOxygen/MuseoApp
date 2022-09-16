package com.eestn5.museoapp;

public class Puntos {
    private String descripcion, titulo, orden,id;

    public Puntos(String id,String descripcion, String titulo, String orden ) {
        this.descripcion = descripcion;
        this.titulo = titulo;
        this.orden = orden;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getComentario() {
        return descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getOrden() {
        return orden;
    }
}
