package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras;
//5
public class ElementoListaColumnasLE<ListaEnlazadaColumnas> {
    private ElementoListaColumnasLE<ListaEnlazadaColumnas> siguiente;
    private ListaEnlazadaColumnas data;

    public ElementoListaColumnasLE(ListaEnlazadaColumnas dato){
        this.data = dato;
        this.siguiente = null;
    }
    public ElementoListaColumnasLE(ListaEnlazadaColumnas dato, ElementoListaColumnasLE<ListaEnlazadaColumnas> siguiente){
        this.data = dato;
        this.siguiente = siguiente;
    }

    protected void insertarmeEn(ElementoListaColumnasLE<ListaEnlazadaColumnas> el){
        el.siguiente = this.siguiente;
        this.siguiente=el;
    }
    public ElementoListaColumnasLE<ListaEnlazadaColumnas> getSiguiente(){
        return siguiente;
    }


    public void setData(ListaEnlazadaColumnas data) {
        this.data = data;
    }

    public ListaEnlazadaColumnas getData() {
        return data;
    }


    public void setSiguiente(ElementoListaColumnasLE<ListaEnlazadaColumnas> siguiente) {
        this.siguiente = siguiente;
    }


}
