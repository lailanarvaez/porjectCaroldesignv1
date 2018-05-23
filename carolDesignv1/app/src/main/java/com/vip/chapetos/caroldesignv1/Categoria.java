package com.vip.chapetos.caroldesignv1;

public class Categoria {
    private String id;
    private String categoria;


    public Categoria() {
    }

    public Categoria(String id, String categoria) {
        this.id = id;
        this.categoria = categoria;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
