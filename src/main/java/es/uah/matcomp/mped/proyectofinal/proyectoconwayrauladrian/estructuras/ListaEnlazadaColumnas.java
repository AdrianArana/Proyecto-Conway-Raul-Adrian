package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras;

import com.google.gson.annotations.Expose;

//4
public class ListaEnlazadaColumnas<Casilla> {
    //ListaEnlazadaColumnas que almacena Casillas
    @Expose
    private ElementoCasillaLE<Casilla> primero;

    public ListaEnlazadaColumnas(ElementoCasillaLE<Casilla> primero) {
        this.primero = primero;
    }

    public ListaEnlazadaColumnas() {
        this.primero = null;
    }


    public boolean isVacia() {
        return this.primero == null;
    }

    public void vaciar() {
        this.primero = null;
    }

    public int add(ElementoCasillaLE<Casilla> elem) {
        int posicion = 1;
        if (isVacia()) {
            this.primero = elem;
            return 0;
        }else{
            ElementoCasillaLE<Casilla> puntero = this.primero;
            while (puntero.getSiguiente() != null) {
                puntero = puntero.getSiguiente();
                posicion += 1;
            }
            puntero.setSiguiente(elem);
            return posicion;
        }
    }


    private void insert(ElementoCasillaLE<Casilla> el, int posicion) {
        if (primero == null) {
            add(el);
        } else {
            int contador;
            ElementoCasillaLE<Casilla> temporal = primero;
            for (contador = 0; contador < posicion - 1; contador++) {
                temporal = temporal.getSiguiente();
            }
            el.setSiguiente(temporal.getSiguiente());
            temporal.setSiguiente(el);
        }
    }


    public void delete(int posicion) {

        if (posicion == 0) {
            primero = primero.getSiguiente();
        } else {
            int contador = 0;
            ElementoCasillaLE<Casilla> puntero = primero;
            while (contador < posicion - 1) {
                puntero = puntero.getSiguiente();
                contador++;
            }
            puntero.setSiguiente(puntero.getSiguiente().getSiguiente());
        }

    }
    public int getNumeroColumnas() {
        int contador = 0;
        ElementoCasillaLE<Casilla> puntero = this.primero;
        while (puntero != null) {
            puntero = puntero.getSiguiente();
            contador++;
        }
        return contador;
    }

    public int getPosicion(ElementoCasillaLE<Casilla> el) {
        int posicion = 0;
        if (!isVacia()) {
            ElementoCasillaLE<Casilla> puntero = this.primero;
            while (posicion < this.getNumeroColumnas() && !puntero.getData().equals(el.getData())) {
                if (puntero.getSiguiente()!=null){
                    puntero = puntero.getSiguiente();
                }
                posicion++;
            }
            if(puntero.getData().equals(el.getData())){
                return posicion;
            }
        }
        return -1;
    }


    public ElementoCasillaLE<Casilla> getPrimero() {
        return this.primero;
    }

    public ElementoCasillaLE<Casilla> getUltimo() {
        ElementoCasillaLE<Casilla> puntero = this.primero;
        if (!isVacia()) {
            int contador = 0;
            while (contador < getNumeroColumnas() - 1) {
                puntero = puntero.getSiguiente();
                contador++;
            }
        }
        return puntero;
    }

    public ElementoCasillaLE<Casilla> getSiguiente(ElementoCasillaLE<Casilla> el) {
        if (el.getData() != null) {
            int posicion = getPosicion(el);
            return getElemento(posicion + 1);
        } else {
            return null;
        }
    }

    public ElementoCasillaLE<Casilla> getElemento(int pos) {
        ElementoCasillaLE<Casilla> puntero;
        int contador = 0;
        if (this.primero != null) {
            puntero = this.primero;
            while (contador < pos) {
                puntero = puntero.getSiguiente();
                contador++;
                if (puntero == null) {
                    return null;
                }
            }
            return puntero;
        } else {
            return null;
        }

    }

    public ListaEnlazadaColumnas<Casilla> invertir(ElementoCasillaLE<Casilla> puntero, ListaEnlazadaColumnas<Casilla> lista) {
        lista.add(puntero);

        if (puntero.getSiguiente() != null) {
            invertir(puntero.getSiguiente(), lista);
        }
        return lista;
    }

    public ListaEnlazadaColumnas<Casilla> invertir() {
        ListaEnlazadaColumnas<Casilla> lista = new ListaEnlazadaColumnas<>();
        return this.invertir(this.primero, lista);
    }

}

