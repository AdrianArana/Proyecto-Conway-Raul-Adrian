package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.arbol;

public class ArbolAVL<Individuo> {
    Nodo<Individuo> raiz;

    //Constructores
    public ArbolAVL(Nodo<Individuo> raiz, Nodo<Individuo> derecha, Nodo<Individuo> izquierda) {
        this.raiz = raiz;
        this.raiz.setDerecha(derecha);
        this.raiz.setIzquierda(izquierda);
    }

    public ArbolAVL(Nodo<Individuo> raiz) {
        this.raiz = raiz;
    }

    public ArbolAVL() {
        Nodo<Individuo> nodo = new Nodo<>(null);
        this.raiz = nodo;
    }

    public ArbolAVL(Individuo dato) {
        this.raiz = new Nodo<>(dato);
    }

    //Respuesta lógica
    public boolean isVacia() {
        return this.raiz.dato == null;
    }


    public int getGrado(Nodo<Individuo> nodo) {
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


    public int getAltura(Nodo<Individuo> n, int p) {
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


}