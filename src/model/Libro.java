package model;

import java.util.ArrayList;
import java.util.List;

public class Libro implements Comparable<Libro> {
    private String titulo;
    private String codigoISBN;
    private int volumen;
    private String editorial;
    private Sede sede;
    private Autor autor;
    private Campus campus;
    List<Sede> listaSedes = new ArrayList<>();
    List<Campus> listaCampus = new ArrayList<>();
    // Constructor, getters y setters


    public Libro(String titulo, String codigoISBN, int volumen, String editorial, Sede sede, Campus campus, Autor autor) {
        try {
            this.volumen = Integer.parseInt(String.valueOf(volumen));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El volumen debe ser un número entero.");
        }
        // Verifica que el código ISBN tenga exactamente 13 dígitos y que sean números
        if (!codigoISBN.matches("\\d{13}")) {
            throw new IllegalArgumentException("El código ISBN debe contener exactamente 13 dígitos numericos .");
        }
        this.titulo = titulo;
        this.codigoISBN = codigoISBN;
        this.volumen = volumen;
        this.editorial = editorial;
        this.sede = sede;
        this.campus = campus;
        this.autor = autor;
    }

    public void setSede (Sede sede){
        if (listaSedes.contains(sede)) {
            this.sede = sede;
        } else {
            throw new IllegalArgumentException("La sede no existe en la lista de sedes.");
        }
    }
    public void setCampus(Campus campus) {
        if (listaCampus.contains(campus)) {
            this.campus = campus;
        } else {
            throw new IllegalArgumentException("El campus no existe en la lista de campus.");
        }
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCodigoISBN() {
        return codigoISBN;
    }

    public void setCodigoISBN(String codigoISBN) {
        this.codigoISBN = codigoISBN;
    }

    public int getVolumen() {
        return volumen;
    }

    public void setVolumen(int volumen) {
        this.volumen = volumen;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public Sede getSede() {
        return sede;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Campus getCampus() {
        return campus;
    }

    @Override
    public int compareTo(Libro otroLibro) {
        // Usa el método compareTo de la clase String para comparar los títulos
        return this.titulo.compareTo(otroLibro.titulo);
    }


}
