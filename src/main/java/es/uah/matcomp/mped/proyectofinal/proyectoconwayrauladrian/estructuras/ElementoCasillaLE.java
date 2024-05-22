package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras;
//3

import com.google.gson.annotations.Expose;

public class ElementoCasillaLE<Casilla> {
    @Expose
    private ElementoCasillaLE<Casilla> siguiente;
    @Expose
    private Casilla data;

    public ElementoCasillaLE(Casilla dato){
        this.data = dato;
        this.siguiente = null;
    }
    public ElementoCasillaLE(Casilla dato, ElementoCasillaLE<Casilla> siguiente){
        this.data = dato;
        this.siguiente = siguiente;
    }

    protected void insertarmeEn(ElementoCasillaLE<Casilla> el){
        el.siguiente = this.siguiente;
        this.siguiente=el;
    }
    public ElementoCasillaLE<Casilla> getSiguiente(){
        return siguiente;
    }


    public void setData(Casilla data) {
        this.data = data;
    }

    public Casilla getData() {
        return data;
    }


    public void setSiguiente(ElementoCasillaLE<Casilla> siguiente) {
        this.siguiente = siguiente;
    }


}
