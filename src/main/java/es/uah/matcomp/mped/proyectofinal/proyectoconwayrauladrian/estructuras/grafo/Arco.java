package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.grafo;

public class Arco<TipoDato> {
    private Vertice<TipoDato> v1;
    private Vertice<TipoDato> v2;
    private double peso;
    private String nombre;

    public Arco(Vertice<TipoDato> v1, Vertice<TipoDato> v2, double peso) {
        this.v1 = v1;
        this.v2 = v2;
        this.peso = peso;
    }

    public Arco(Vertice<TipoDato> v1, Vertice<TipoDato> v2, double peso, String nombre) {
        this.v1 = v1;
        this.v2 = v2;
        this.peso = peso;
        this.nombre = nombre;
    }

    public Vertice<TipoDato> getV1() {
        return v1;
    }

    public void setV1(Vertice<TipoDato> v1) {
        this.v1 = v1;
    }

    public Vertice<TipoDato> getV2() {
        return v2;
    }

    public void setV2(Vertice<TipoDato> v2) {
        this.v2 = v2;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}
