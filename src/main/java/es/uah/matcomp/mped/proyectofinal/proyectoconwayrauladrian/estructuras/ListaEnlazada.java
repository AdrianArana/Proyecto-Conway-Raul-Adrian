package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras;
//2
public class ListaEnlazada<TipoDelDato> {
    private ElementoLE<TipoDelDato> primero;

    public ListaEnlazada(ElementoLE<TipoDelDato> primero) {
        this.primero = primero;
    }

    public ListaEnlazada() {
        this.primero = null;
    }


    public boolean isVacia() {
        return this.primero == null;
    }

    public void vaciar() {
        this.primero = null;
    }

    protected int add(ElementoLE elem) {
        int posicion = 1;
        if (isVacia()) {
            this.primero = elem;
            return 0;
        }else{
            ElementoLE actual = this.primero;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
                posicion += 1;
            }
            elem.insertarmeEn(actual);
            return posicion;
        }
    }

    public void add(String string) {
        TipoDelDato stringTipoDelDato = (TipoDelDato) string;
        ElementoLE<TipoDelDato> stringTipoLE = new ElementoLE<>(stringTipoDelDato);
        add(stringTipoLE);
    }

    public void add(Object objeto) {
        TipoDelDato objetoTipoDelDato = (TipoDelDato) objeto;
        ElementoLE<TipoDelDato> objetoTipoLE = new ElementoLE<>(objetoTipoDelDato);
        add(objetoTipoLE);
    }

    private void insert(ElementoLE<TipoDelDato> el, int posicion) {
        if (primero == null) {
            add(el.getData());
        } else {
            int contador;
            ElementoLE<TipoDelDato> temporal = primero;
            for (contador = 0; contador < posicion - 1; contador++) {
                temporal = temporal.getSiguiente();
            }
            el.setSiguiente(temporal.getSiguiente());
            temporal.setSiguiente(el);
        }
    }

    public void insert(String string, int posicion) {
        TipoDelDato stringTipoDelDato = (TipoDelDato) string;
        ElementoLE<TipoDelDato> stringElementoLE = new ElementoLE<>(stringTipoDelDato);
        insert(stringElementoLE, posicion);
    }

    public void insert(Object objeto, int posicion) {
        TipoDelDato objectTipoDelDato = (TipoDelDato) objeto;
        ElementoLE<TipoDelDato> objectElementoLE = new ElementoLE<>(objectTipoDelDato);
        insert(objectElementoLE, posicion);
    }

    public void delete(int posicion) {

        if (posicion == 0) {
            primero = primero.getSiguiente();
        } else {
            int contador = 0;
            ElementoLE<TipoDelDato> puntero = primero;
            while (contador < posicion - 1) {
                puntero = puntero.getSiguiente();
                contador++;
            }
            puntero.setSiguiente(puntero.getSiguiente().getSiguiente());
        }

    }

    public int getNumeroElementos() {
        int contador = 0;
        ElementoLE<TipoDelDato> puntero = this.primero;
        while (puntero != null) {
            puntero = puntero.getSiguiente();
            contador++;
        }
        return contador;
    }

    public int getPosicion(ElementoLE<TipoDelDato> el) {
        int posicion = 0;
        if (!isVacia()) {
            ElementoLE<TipoDelDato> puntero = this.primero;
            while (posicion < this.getNumeroElementos() && !puntero.getData().equals(el.getData())) {
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


    public ElementoLE<TipoDelDato> getPrimero() {
        return this.primero;
    }

    public ElementoLE<TipoDelDato> getUltimo() {
        ElementoLE<TipoDelDato> puntero = this.primero;
        if (!isVacia()) {
            int contador = 0;
            while (contador < getNumeroElementos() - 1) {
                puntero = puntero.getSiguiente();
                contador++;
            }
        }
        return puntero;
    }

    public ElementoLE<TipoDelDato> getSiguiente(ElementoLE<TipoDelDato> el) {
        if (el.getData() != null) {
            int posicion = getPosicion(el);
            return getElemento(posicion + 1);
        } else {
            return null;
        }
    }

    public ElementoLE<TipoDelDato> getElemento(int pos) {
        ElementoLE<TipoDelDato> puntero;
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

    public ListaEnlazada<TipoDelDato> invertir(ElementoLE<TipoDelDato> puntero, ListaEnlazada<TipoDelDato> lista) {
        lista.add(puntero.getData());

        if (puntero.getSiguiente() != null) {
            invertir(puntero.getSiguiente(), lista);
        }
        return lista;
    }

    public ListaEnlazada<TipoDelDato> invertir() {
        ListaEnlazada<TipoDelDato> lista = new ListaEnlazada<>();
        return this.invertir(this.primero, lista);
    }

    public int suma(ElementoLE<Integer> el) {
        int resultado = el.getData();
        if (el.getSiguiente() == null) {
            return resultado;
        } else {
            return el.getData() + suma(el.getSiguiente());
        }
    }
}
