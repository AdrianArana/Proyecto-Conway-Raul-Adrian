package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras;
//6
public class ListaEnlazadaFilas<ListaEnlazadaColumnas> {
    //Lista que almacena listas de tipo ListaEnlazadaColumnas
    private ElementoListaColumnasLE<ListaEnlazadaColumnas> primero;

    public ListaEnlazadaFilas(ElementoListaColumnasLE<ListaEnlazadaColumnas> primero) {
        this.primero = primero;
    }

    public ListaEnlazadaFilas() {
        this.primero = null;
    }


    public boolean isVacia() {
        return this.primero == null;
    }

    public void vaciar() {
        this.primero = null;
    }

    public int add(ElementoListaColumnasLE<ListaEnlazadaColumnas> elem) {//Este elemento será solamente una ListaEnlazadaColumnas
        int posicion = 1;
        if (isVacia()) {
            this.primero = elem;
            return 0;
        }else{
            ElementoListaColumnasLE<ListaEnlazadaColumnas> puntero = this.primero;
            while (puntero.getSiguiente() != null) {
                puntero = puntero.getSiguiente();
                posicion += 1;
            }
            puntero.setSiguiente(elem);
            return posicion;
        }
    }


    //Insert solo de ListaEnlazadaColumnas
    private void insert(ElementoListaColumnasLE<ListaEnlazadaColumnas> el, int posicion) {
        if (primero == null) {
            add(el);
        } else {
            int contador;
            ElementoListaColumnasLE<ListaEnlazadaColumnas> temporal = primero;
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
            ElementoListaColumnasLE<ListaEnlazadaColumnas> puntero = primero;
            while (contador < posicion - 1) {
                puntero = puntero.getSiguiente();
                contador++;
            }
            puntero.setSiguiente(puntero.getSiguiente().getSiguiente());
        }

    }

    public int getNumeroFilas() {
        int contador = 0;
        ElementoListaColumnasLE<ListaEnlazadaColumnas> puntero = this.primero;
        while (puntero != null) {
            puntero = puntero.getSiguiente();
            contador++;
        }
        return contador;
    }

    public int getPosicion(ElementoListaColumnasLE<ListaEnlazadaColumnas> el) {
        int posicion = 0;
        if (!isVacia()) {
            ElementoListaColumnasLE<ListaEnlazadaColumnas> puntero = this.primero;
            while (posicion < this.getNumeroFilas() && !puntero.getData().equals(el.getData())) {
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


    public ElementoListaColumnasLE<ListaEnlazadaColumnas> getPrimero() {
        return this.primero;
    }

    public ElementoListaColumnasLE<ListaEnlazadaColumnas> getUltimo() {
        ElementoListaColumnasLE<ListaEnlazadaColumnas> puntero = this.primero;
        if (!isVacia()) {
            int contador = 0;
            while (contador < getNumeroFilas() - 1) {
                puntero = puntero.getSiguiente();
                contador++;
            }
        }
        return puntero;
    }

    public ElementoListaColumnasLE<ListaEnlazadaColumnas> getSiguiente(ElementoListaColumnasLE<ListaEnlazadaColumnas> el) {
        if (el.getData() != null) {
            int posicion = getPosicion(el);
            return getElemento(posicion + 1);
        } else {
            return null;
        }
    }

    public ElementoListaColumnasLE<ListaEnlazadaColumnas> getElemento(int pos) {
        ElementoListaColumnasLE<ListaEnlazadaColumnas> puntero;
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

    public ListaEnlazadaFilas<ListaEnlazadaColumnas> invertir(ElementoListaColumnasLE<ListaEnlazadaColumnas> puntero, ListaEnlazadaFilas<ListaEnlazadaColumnas> lista) {
        lista.add(puntero);

        if (puntero.getSiguiente() != null) {
            invertir(puntero.getSiguiente(), lista);
        }
        return lista;
    }

    public ListaEnlazadaFilas<ListaEnlazadaColumnas> invertir() {
        ListaEnlazadaFilas<ListaEnlazadaColumnas> lista = new ListaEnlazadaFilas<>();
        return this.invertir(this.primero, lista);
    }
}