package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras;

public class Cola<T> {
    public T dato;
    private ListaEnlazada<T> datos;
    public Cola() {
        datos = new ListaEnlazada<>();
    }

    public ListaEnlazada<T> getDatos() {
        return datos;
    }
    public void push(T elemento) {
        datos.add(elemento);
    }
    public T pull() {
        T borrado=datos.getElemento(0).getData();
        datos.delete(0);
        return borrado;
    }
    public boolean isVacia() {
        return datos.isVacia();
    }
}
