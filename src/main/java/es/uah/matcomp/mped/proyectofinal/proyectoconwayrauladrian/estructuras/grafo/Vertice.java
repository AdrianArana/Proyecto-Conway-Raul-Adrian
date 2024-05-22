package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.grafo;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazada;

public class Vertice<TipoDato> {
    private TipoDato id;
    private String nombre;
    private boolean conectado = false;
    private ListaEnlazada<Arco<TipoDato>> frontera=new ListaEnlazada<>();


    public Vertice(TipoDato id) {
        this.id = id;
    }


    public Vertice(TipoDato id, String nombreCiudad) {
        this.id = id;
        this.nombre = nombreCiudad;
    }

    public TipoDato getId() {
        return id;
    }

    public void setId(TipoDato id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    public ListaEnlazada<Arco<TipoDato>> getFrontera() {
        return frontera;
    }

    public void setFrontera(ListaEnlazada<Arco<TipoDato>> frontera) {
        this.frontera = frontera;
    }

    public void addFrontera(Arco<TipoDato> adyacente) {
        frontera.add(adyacente);
    }


    @Override
    public String toString() {
        return "Vertice{" +
        "id=" + id +
        ", nombre='" + nombre + '\'' +
        ", conectado=" + conectado +
        ", frontera="  +
        '}';

        }

    }
