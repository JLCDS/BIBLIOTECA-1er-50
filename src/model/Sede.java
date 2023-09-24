package model;


import java.util.List;

public class Sede {
    private String nombre;
    private List<Campus> campusList;

    // Constructor, getters y setters
    public Sede(String nombre) {
        this.nombre = nombre;
        this.campusList = campusList;
    }

    public List<Campus> getCampusList() {
        return campusList;
    }

    public void setCampusList(List<Campus> campusList) {
        this.campusList = campusList;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sede: ").append(nombre).append("\n");
        return sb.toString();
    }
}