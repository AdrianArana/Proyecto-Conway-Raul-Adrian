package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos;

import com.google.gson.annotations.Expose;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno.Entorno;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.Cola;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ElementoLE;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.arbol.Arbol;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.arbol.Nodo;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosIndividuo;

public class Individuo extends ParametrosIndividuo {
    @Expose
    public int coordenadaX;
    @Expose
    public int coordenadaY;
    @Expose
    public int id;
    @Expose
    public int tipo;
    @Expose
    public int turnoGeneracion;
    @Expose
    public ElementoLE<Entorno> objetivo;
    @Expose
    public int turnosVividos = 0;
    @Expose
    public int cantidadDeAguaBebida = 0;

    public int getCantidadDeAguaBebida() {
        return cantidadDeAguaBebida;
    }

    public void setCantidadDeAguaBebida(int cantidadDeAguaBebida) {
        this.cantidadDeAguaBebida = cantidadDeAguaBebida;
    }

    public int getNumeroDeClonaciones() {
        return numeroDeClonaciones;
    }

    public void setNumeroDeClonaciones(int numeroDeClonaciones) {
        this.numeroDeClonaciones = numeroDeClonaciones;
    }


    @Expose
    public int numeroDeClonaciones = 0;

    public int getNumeroDeReproducciones() {
        return numeroDeReproducciones;
    }

    public void setNumeroDeReproducciones(int numeroDeReproduccionesDado) {
        this.numeroDeReproducciones = numeroDeReproduccionesDado;
    }

    @Expose
    public int numeroDeReproducciones = 0;

    public Cola<String> cola = new Cola<>();

    public Cola<String> getCola() {
        return cola;
    }

    public void setCola(Cola<String> cola) {
        this.cola = cola;
    }

    public int getTurnosVividos() {
        return turnosVividos;
    }

    public void setTurnosVividos(int turnosVividos) {
        this.turnosVividos = turnosVividos;
    }

    @Expose
    public Arbol arbolDelIndividuo = new Arbol(new Nodo(this), null, null);

    public Arbol getArbolDelIndividuo() {
        return arbolDelIndividuo;
    }

    public void setArbolDelIndividuo(Arbol arbolDelIndividuo) {
        this.arbolDelIndividuo = arbolDelIndividuo;
    }

    public Individuo(int x, int y, int id, int tipo, int turnosVidaRestantes, int turnoGeneracion, int probabilidadMuerte, int probabilidadClonacion, int probabilidadReproduccion) {
        super(turnosVidaRestantes, probabilidadMuerte, probabilidadClonacion, probabilidadReproduccion);
        this.coordenadaX = x;
        this.coordenadaY = y;
        this.id = id;
        this.tipo = tipo;
        this.turnoGeneracion = turnoGeneracion;
    }

    public Individuo() {
    }


    @Override
    public String toString() {
        String tipoString = "";
        if (tipo == 1) {
            tipoString = "Básico";
        } else if (tipo == 2) {
            tipoString = "Normal";
        } else if (tipo == 3) {
            tipoString = "Avanzado";
        }
        return
                "\n Coordenadas: (" + coordenadaX + "," + coordenadaY +
                        ")\n Id: '" + id +
                        "'\n Tipo: '" + tipoString +
                        "'\n Turno de Generacion= '" + turnoGeneracion +
                        "'\n Turnos vividos: '" + getTurnosVividos() +
                        "'\n Probabilidad de muerte: '" + getProbabilidadMuerte() +
                        "'\n Probabilidad de clonación: '" + getProbabilidadClonacion() +
                        "'\n Probabilidad de reproducción: '" + getProbabilidadReproduccion() +
                        "'\n Turnos de vida restantes: '" + getTurnosVidaRestantes() + "'";
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(int coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public int getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(int coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTurnoGeneracion() {
        return turnoGeneracion;
    }

    public void setTurnoGeneracion(int turnoGeneracion) {
        this.turnoGeneracion = turnoGeneracion;
    }


    public void setTurnosVidaRestantes(int i) {
        super.setTurnosVidaRestantes(i);
    }

    public void restarUnoDeVida() {
        this.setTurnosVidaRestantes(getTurnosVidaRestantes() - 1);
    }

    public void setObjetivo(ElementoLE<Entorno> objetivo) {
        this.objetivo = objetivo;
    }

    public ElementoLE<Entorno> getObjetivo() {
        return objetivo;
    }

    public void sumarUnaClonacion() {
        this.setNumeroDeClonaciones(getNumeroDeClonaciones() + 1);
    }

    public void sumarUnaReproduccion() {
        this.setNumeroDeReproducciones(getNumeroDeReproducciones() + 1);
    }
}
