package com.gummyfish.appuntes3;

/**
 * Created by enekourunuela on 04/02/16.
 */
public class RowItem {
    private int imageId;
    private String title;
    private String desc;
    private int puntos;
    private double puntuacion;

    public RowItem(int imageId, String title, String desc, int puntos, double puntuacion) {
        this.imageId = imageId;
        this.title = title;
        this.desc = desc;
        this.puntos = puntos;
        this.puntuacion = puntuacion;
    }
    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public String toString() {
        return title + "\n" + desc;
    }
    public String getPuntos(){
        return String.valueOf(puntos);
    }
    public void setPuntos(int puntos){
        this.puntos = puntos;
    }
    public String getPuntuacion(){

        return String.valueOf(puntuacion);
    }
    public void setPuntuacion(double puntuacion){
        this.puntuacion = puntuacion;
    }
}
