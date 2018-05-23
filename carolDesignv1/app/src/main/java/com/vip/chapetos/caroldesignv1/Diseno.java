package com.vip.chapetos.caroldesignv1;

public class Diseno {
    private String id_diseno;
    private String diseno;
    private String urlImg;
    private String id_categoria;


    public Diseno() {
    }

    public Diseno(String id_diseno, String diseno, String urlImg, String id_categoria) {
        this.id_diseno = id_diseno;
        this.diseno = diseno;
        this.urlImg = urlImg;
        this.id_categoria = id_categoria;
    }

    /***********************
     * METODOS MODIFICADORES
     * @return
     *************************/
    public String getId_diseno(){
        return this.id_diseno;
    }
    public String getDiseno(){
        return this.diseno;
    }
    public String getUrlImg(){
        return this.urlImg;
    }
    public String getId_categoria(){
        return id_categoria;
    }

    /*************************
     * metodos modificadores
     ************************/
    public void setId_diseno(String id_diseno){
        this.id_diseno = id_diseno;
    }
    public void setDiseno(String diseno){
        this.diseno = diseno;
    }
    public void setUrlImg(String urlImg){
        this.urlImg = urlImg;
    }
    public void setId_categoria(String id_categoria){
        this.id_categoria = id_categoria;
    }


}
