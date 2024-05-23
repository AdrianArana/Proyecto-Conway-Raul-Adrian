package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.arbol;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazada;

public class ArbolAVL<TipoDato> {
    Nodo<TipoDato> raiz;

    //Constructores
    public ArbolAVL(Nodo<TipoDato> raiz, Nodo<TipoDato> derecha, Nodo<TipoDato> izquierda) {
        this.raiz = raiz;
        this.raiz.setDerecha(derecha);
        this.raiz.setIzquierda(izquierda);
    }

    public ArbolAVL(Nodo<TipoDato> raiz) {
        this.raiz = raiz;
    }

    public ArbolAVL() {
        Nodo<TipoDato> nodo = new Nodo<>(null);
        this.raiz = nodo;
    }

    public ArbolAVL(TipoDato dato) {
        this.raiz = new Nodo<>(dato);
    }

    //Respuesta lógica
    public boolean isVacia() {
        return this.raiz.dato == null;
    }

    //Método para añadir un elemento al árbol
    public Nodo<TipoDato> add(TipoDato dato, Nodo<TipoDato> nodoRaiz) {
        Nodo<TipoDato> n1 = new Nodo<>(dato);
        Comparable c = (Comparable) nodoRaiz.getDato();
        if (isVacia()) {
            this.raiz.setDato(dato);
            this.raiz.setDerecha(null);
            this.raiz.setIzquierda(null);

        } else {
            if (c.compareTo(dato) <= 0) {
                if (nodoRaiz.getDerecha() == null) {
                    nodoRaiz.setDerecha(n1);
                } else {
                    add(dato, nodoRaiz.getDerecha());
                }
            } else {
                if (nodoRaiz.getIzquierda() == null) {
                    nodoRaiz.setIzquierda(n1);
                } else {
                    add(dato, nodoRaiz.getIzquierda());
                }
            }
        }
        return n1;
    }

    public void add(TipoDato dato) {
        add(dato, this.raiz);
    }

    //Método para hallar el grado del nodo recibido
    public int getGrado(Nodo<TipoDato> nodo) {
        int result = 0;
        if (result < nodo.gradoNodo()) {
            result = nodo.gradoNodo();
        }
        if (nodo.getIzquierda() != null) {
            getGrado(nodo.getIzquierda());
        }
        if (nodo.getDerecha() != null) {
            getGrado(nodo.getDerecha());
        }
        return result;
    }

    //Método getCamino() y getLongitud()
    public ListaEnlazada<TipoDato> getCamino(Nodo<TipoDato> n, Nodo<TipoDato> raiz, ListaEnlazada<TipoDato> lista) {
        Comparable c = (Comparable) n.getDato();
        lista.add(raiz.getDato());
        if (c.compareTo(raiz.getDato()) < 0) {
            getCamino(n, raiz.getIzquierda(), lista);
        } else if (c.compareTo(raiz.getDato()) > 0) {
            getCamino(n, raiz.getDerecha(), lista);
        }

        System.out.print("" + raiz.getDato() + ",");
        return lista;
    }

    public ListaEnlazada<TipoDato> getCamino(Nodo<TipoDato> n) {
        ListaEnlazada<TipoDato> lista = new ListaEnlazada<>();
        ListaEnlazada<TipoDato> lista1 = getCamino(n, this.raiz, lista);
        System.out.println();
        return lista1.invertir();
    }

    public int getLongitud(Nodo<TipoDato> nodo) {
        return (getCamino(nodo).getNumeroElementos() + 1);
    }


    //Métodos para generar los subárboles
    public ArbolAVL<TipoDato> getSubArbolIzq() {
        ArbolAVL<TipoDato> subArbol = new ArbolAVL<>(this.raiz.getIzquierda());
        return subArbol;
    }

    public ArbolAVL<TipoDato> getSubArbolDcha() {
        ArbolAVL<TipoDato> subArbol = new ArbolAVL<>(this.raiz.getDerecha());
        return subArbol;
    }

    //Métodos preOrden, ordenCental y postOrden
    public ListaEnlazada<TipoDato> preorden(Nodo<TipoDato> n, ListaEnlazada<TipoDato> lista) {
        lista.add(n.dato);
        if (n.getIzquierda() != null) {
            preorden(n.getIzquierda(), lista);
        }
        if (n.getDerecha() != null) {
            preorden(n.getDerecha(), lista);
        }
        return lista;
    }

    public ListaEnlazada<TipoDato> preorden() {
        ListaEnlazada<TipoDato> lista = new ListaEnlazada<>();
        return preorden(this.raiz, lista).invertir();
    }

    public ListaEnlazada<TipoDato> ordenCentral(Nodo<TipoDato> n, ListaEnlazada<TipoDato> lista) {
        if (n.getIzquierda() != null) {
            ordenCentral(n.getIzquierda(), lista);
        }
        //System.out.println(n.dato);
        lista.add(n.dato);
        if (n.getDerecha() != null) {
            ordenCentral(n.derecha, lista);
        }
        return lista;
    }

    public ListaEnlazada<TipoDato> ordenCentral() {
        ListaEnlazada<TipoDato> lista = new ListaEnlazada<>();
        return ordenCentral(this.raiz, lista).invertir();
    }

    public ListaEnlazada<TipoDato> postOrder(Nodo<TipoDato> n, ListaEnlazada<TipoDato> lista) {
        if (n.getIzquierda() != null) {
            postOrder(n.getIzquierda(), lista);
        }
        if (n.getDerecha() != null) {
            postOrder(n.getDerecha(), lista);
        }
        //System.out.println(n.dato);
        lista.add(n.dato);
        return lista;
    }

    public ListaEnlazada<TipoDato> postOrder() {
        ListaEnlazada<TipoDato> lista = new ListaEnlazada<>();
        return postOrder(this.raiz, lista).invertir();
    }

    //Método para hallar la altura de nuestro árbol
    public int getAltura(Nodo<TipoDato> n, int p) {
        int x = 0;
        int y = 0;
        if (n.getDerecha() != null) {
            y = getAltura(n.getDerecha(), p + 1);
        }
        if (n.getIzquierda() != null) {
            x = getAltura(n.getIzquierda(), p + 1);
        }
        if (x >= y && x > p) {
            return x;
        } else if (x < y && y > p) {
            return y;
        }
        return p;
    }

    public int getAltura() {
        return 1 + getAltura(this.raiz, 0);
    }

    //RESPUESTAS LÓGICAS
    boolean bool1 = true;

    public boolean isArbolCompleto(Nodo<TipoDato> n, int numero) {
        ListaEnlazada<TipoDato> lista = new ListaEnlazada<>();
        if (n.getIzquierda() == null && n.getDerecha() == null) {
            lista = getCamino(n);
            int num = lista.getNumeroElementos();
            if (num != numero) {
                bool1 = false;
            }
        }
        if (n.getIzquierda() != null) {
            isArbolCompleto(n.getIzquierda(), numero);
        }
        if (n.getDerecha() != null) {
            isArbolCompleto(n.getDerecha(), numero);
        }
        return bool1;
    }

    //Gracias a este método, podemos usar el método original sin parámetros
    public boolean isArbolCompleto() {
        bool1 = true;
        int num = 1;
        Nodo<TipoDato> nodo = this.raiz;
        while (nodo.getIzquierda() != null) {
            num++;
            nodo = nodo.getIzquierda();
        }
        return isArbolCompleto(this.raiz, num);
    }

    public boolean isArbolHomogeneo(Nodo<TipoDato> n) {
        int grado = n.gradoNodo();
        if (grado != 2 && grado != 0) {
            return false;
        }
        if (n.getIzquierda() != null) {
            isArbolHomogeneo(n.getIzquierda());
        }
        if (n.getDerecha() != null) {
            isArbolHomogeneo(n.getDerecha());
        }
        return true;
    }

    public boolean isArbolHomogeneo() {     //Así podemos usar correctamente el método sin parámetros
        bool1 = true;
        return isArbolHomogeneo(this.raiz);
    }


    ///////////////////FUNCIONES NUEVAS AVL/////////////////////////////
    //Método a proponer al profe
        /*public void isEquilibrado(Nodo<TipoDato> n, int altura) {
        if (n.getIzquierda() != null) {
            altura++;

            isEquilibrado(n.getIzquierda(), altura);
            altura--;

        }
        if (n.getDerecha() != null) {
            altura++;
            isEquilibrado(n.getDerecha(), altura);
            altura--;

        }
        if (n.esHoja()) {
            x = Math.max(x, altura);
            if (y == 0) {
                y = altura;
            } else {
                y = Math.min(y, altura);
            }
        }

    }
    int x = 0;
    int y = 0;

    public boolean isEquilibrado() {

        int altura = 0;

        isEquilibrado(raiz, altura);
        if (Math.abs(x - y) > 1) {
            return false;
        } else {
            return true;
        }
    } */

    public boolean isEquilibradoParticular(Nodo<TipoDato> nodo) {
        if (this.raiz == null) {
            return true;
        }

        if (Math.abs(getSubArbolIzq().getAltura() - getSubArbolDcha().getAltura()) < 2) {
            return true;
        } else {
            return false;
        }

    }

    public boolean isEquilibradoNodo(Nodo<TipoDato> nodo) {
        if (!isEquilibradoParticular(nodo)) {
            return false;
        }
        if (!isEquilibradoParticular(nodo.getIzquierda())) {
            return false;
        }
        if (!isEquilibradoParticular(nodo.getDerecha())) {
            return false;
        }
        return true;
    }

    public boolean isEquilibrado() {
        return isEquilibradoNodo(this.raiz);
    }

    public void rotacionSimpleDerecha(Nodo<TipoDato> n) {
        Nodo<TipoDato> nodoIzqN = n.getIzquierda();
        nodoIzqN.setDerecha(n);
        if (nodoIzqN.getDerecha() != null) {
            n.setIzquierda(nodoIzqN.getDerecha());
        }
    }

    public void rotacionSimpleIzquierda(Nodo<TipoDato> n) {
        Nodo<TipoDato> nodoDchaN = n.getDerecha();
        nodoDchaN.setIzquierda(n);
        if (nodoDchaN.getIzquierda() != null) {
            n.setDerecha(nodoDchaN.getIzquierda());
        }
    }

    public void rotacionDerechaIzquierda(Nodo<TipoDato> n) {
        rotacionSimpleDerecha(n.getIzquierda());
        rotacionSimpleIzquierda(n);
    }

    public void rotacionIzquierdaDerecha(Nodo<TipoDato> n) {
        rotacionSimpleIzquierda(n.getDerecha());
        rotacionSimpleDerecha(n);
    }

    Nodo<TipoDato> nodoErroneo;

    public void getDesequilibrio(Nodo<TipoDato> nodo) {
        if (this.isEquilibradoParticular(nodo)) {
            if (nodo.getIzquierda() != null) {
                isEquilibradoParticular(nodo.getIzquierda());
            }
            if (nodo.getDerecha() != null) {
                isEquilibradoParticular(nodo.getDerecha());
            }
        } else {
            nodoErroneo = nodo;
        }
    }

    public Nodo<TipoDato> getDesequilibrio() {
        getDesequilibrio(this.raiz);
        return nodoErroneo;
    }

    public void insertarAVL(TipoDato dato, Nodo<TipoDato> nodoRaiz) {
        Nodo<TipoDato> nodoInsertar = new Nodo(dato);
        Comparable datoNodo = (Comparable) dato;//Numero que meto
        if (datoNodo.compareTo(nodoRaiz.getDato()) < 0) {
            if (nodoRaiz.getIzquierda() == null) {
                nodoRaiz.setIzquierda(nodoInsertar);
            } else {
                insertarAVL(dato, nodoRaiz.getIzquierda());
            }
        } else {
            if (nodoRaiz.getDerecha() == null) {
                nodoRaiz.setDerecha(nodoInsertar);
            } else {
                insertarAVL(dato, nodoRaiz.getDerecha());
            }
        }
        if (!isEquilibradoParticular(nodoRaiz)) {
            balancear(nodoRaiz);
        }

    }

    public void balancear(Nodo<TipoDato> nodoRaiz) {
        int balance = getBalance(nodoRaiz);
        //Para la derecha simple
        if (balance == -2) {
            if (nodoRaiz.getIzquierda().getIzquierda() != null) {
                rotacionSimpleDerecha(nodoRaiz);
            } else {
                rotacionIzquierdaDerecha(nodoRaiz);
            }
        }
        //Para la izquierda
        if (balance == 2) {
            if (nodoRaiz.getDerecha().getDerecha() != null) {
                rotacionSimpleIzquierda(nodoRaiz);
            } else {
                rotacionDerechaIzquierda(nodoRaiz);
            }
        }
    }

    public void eliminarAVL(Comparable datoEliminar, Nodo<TipoDato> nodoRaiz) {

        if (datoEliminar.compareTo(nodoRaiz.getDato()) < 0) {
            eliminarAVL(datoEliminar, nodoRaiz.getIzquierda());
        }
        if (datoEliminar.compareTo(nodoRaiz.getDato()) > 0) {
            eliminarAVL(datoEliminar, nodoRaiz.getDerecha());
        } else {
            nodoRaiz.setDato(null);
        }
        if (!isEquilibradoParticular(nodoRaiz)) {
            balancear(nodoRaiz);
        }
    }

    public void eliminarAVL(TipoDato dato) {
        Comparable datoEliminar = (Comparable) dato;
        eliminarAVL(datoEliminar, this.raiz);
    }

    public int getBalance(Nodo<TipoDato> nodo) {
        int diferencia;
        diferencia = (getSubArbolDcha().getAltura()) - (getSubArbolIzq().getAltura());
        return diferencia;
    }
}