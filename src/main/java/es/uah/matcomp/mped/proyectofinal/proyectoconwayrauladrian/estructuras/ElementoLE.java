package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras;

import com.google.gson.annotations.Expose;

//1
public class ElementoLE<TipoDelDato> {
    @Expose
    public ElementoLE<TipoDelDato> siguiente;
    @Expose
    public TipoDelDato data;

    public ElementoLE(TipoDelDato dato){
        this.data = dato;
        this.siguiente = null;
    }
    public ElementoLE(TipoDelDato dato, ElementoLE<TipoDelDato> siguiente){
        this.data = dato;
        this.siguiente = siguiente;
    }

    public ElementoLE() {

    }

    protected void insertarmeEn(ElementoLE<TipoDelDato> el){
        el.siguiente = this.siguiente;
        this.siguiente=el;
    }
    public ElementoLE<TipoDelDato> getSiguiente(){
        return siguiente;
    }


    public void setData(TipoDelDato data) {
        this.data = data;
    }

    public TipoDelDato getData() {
        return data;
    }


    public void setSiguiente(ElementoLE<TipoDelDato> siguiente) {
        this.siguiente = siguiente;
    }


}
