package model;

import java.util.ArrayList;
import java.util.List;

public class Serie {
    private String codigo;
    private String titulo;
    private int anioInicio;
    private String sinopsis;
    private Genero genero;
    private List<Personaje> personajes;

    public Serie(String codigo, String titulo, int anioInicio, String sinopsis, Genero genero) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.anioInicio = anioInicio;
        this.sinopsis = sinopsis;
        this.genero = genero;
        this.personajes = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnioInicio() {
        return anioInicio;
    }

    public void setAnioInicio(int anioInicio) {
        this.anioInicio = anioInicio;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<Personaje> personajes) {
        this.personajes = personajes;
    }

    public void addPersonaje(Personaje personaje) {
        this.personajes.add(personaje);
    }
}