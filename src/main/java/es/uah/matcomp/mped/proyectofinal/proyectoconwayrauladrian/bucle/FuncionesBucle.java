package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.bucle;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.Casilla;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazada;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaColumnas;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaFilas;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosEntorno;

import java.util.Random;

public class FuncionesBucle {

    public FuncionesBucle() {
    }

    public void recorrerCasillas(ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero) {
        int filas = tablero.getNumeroFilas();
        for (int fila = 0; fila < filas; fila++) {
            ListaEnlazadaColumnas<Casilla> filaActual = tablero.getElemento(fila).getData();//Obtenemos la lista de elementos que se encuentra en la fila actual
            int columnas = filaActual.getNumeroColumnas();
            for (int columna = 0; columna < columnas; columna++) {//tiene que ser < o <= ???, creo que da igual, ya que solo la recorro,
                // pero REMARCAMOS QUE LOS NUMEROS DE LAS POSICIONES DE LAS CASILLAS SON DESDE EL 1 HASTA EL 10,
                // NO DESDE EL 0 AL 9 COMO AQUI, PUEDE QUE DE PROBLEMAS
                Casilla casillaActual = filaActual.getElemento(columna).getData();//Ya podemos trabajar con cada casilla
                System.out.println("CasillaActual: " + casillaActual.toString());



                //esto segun lo que me explico arana asi chequearemos el bucle???

                tiempoDeVida(casillaActual);
                recursoActivo(casillaActual);
                reproduccion(casillaActual);
                clonacion(casillaActual);
                muerteIndividuos(casillaActual);
                aparicionRecursos(casillaActual);
            }
        }
    }



    //por cada turno, todos los individuos pierden 1 vida
    //si los turnos de vida del individuo son menores o ihuales a cero, se eliminan de la casilla y de la lisat de individuos
    //a los individuos por cada turno, se les actualiza su probabilidad de reproduccion y de clonacion
    public void tiempoDeVida(Casilla casillaActual) {

        ListaEnlazada<Individuo> individuos = casillaActual.getIndividuos();
        for (int i = 0; i < individuos.getNumeroElementos(); i++) {
            individuos.getElemento(i).getData().setTurnosVidaRestantes(individuos.getElemento(i).getData().getTurnosVidaRestantes() - 1);
        }

        for (int j = 0; j < individuos.getNumeroElementos(); j++) {
            if (individuos.getElemento(j).getData().getTurnosVidaRestantes() <= 0) {
                individuos.delete(j);
                casillaActual.setIndividuos(individuos);
            }
        }

        for (int k = 0; k < individuos.getNumeroElementos(); k++) {
            if (individuos.getElemento(k).getData().getProbabilidadReproduccion()>=10){
                individuos.getElemento(k).getData().setProbabilidadReproduccion((individuos.getElemento(k).getData().getProbabilidadReproduccion()) - (10));
            }
            else{
                individuos.getElemento(k).getData().setProbabilidadReproduccion(0);
            }

        }

        for (int l = 0; l < individuos.getNumeroElementos(); l++) {
            if (individuos.getElemento(l).getData().getProbabilidadClonacion()>=10){
                individuos.getElemento(l).getData().setProbabilidadReproduccion((individuos.getElemento(l).getData().getProbabilidadClonacion()) - (10));
            }
            else{
                individuos.getElemento(l).getData().setProbabilidadClonacion(0);
            }

        }
    }

    public void recursoActivo(Casilla casillaActual) {

        ListaEnlazada<Entorno> entorno = casillaActual.getRecursos();

        for (int i = 0; i < entorno.getNumeroElementos(); i++) {
            entorno.getElemento(i).getData().setTiempoAparicion(entorno.getElemento(i).getData().getTiempoAparicion() - 1);
        }

        for (int j = 0; j < entorno.getNumeroElementos(); j++) {
            if (entorno.getElemento(j).getData().getTiempoAparicion() <= 0) {
                entorno.delete(j);
                casillaActual.setRecursos(entorno);
            }
        }

    }


    //HACER LA FUNCION DE LOS MOVIMIENTOS DE LOS INDIVIDUOS //todo










//hacer la funcion de mejoras del individuo
/*
    public void mejorasIndividuos(Casilla casillaActual){

        ListaEnlazada<Entorno> entorno= casillaActual.getRecursos();
        ListaEnlazada<Individuo> individuos= casillaActual.getIndividuos();


        for (int i = 0; i < entorno.getNumeroElementos(); i++) {
            for (int j = 0; j < individuos.getNumeroElementos(); j++) {
                if (entorno.getElemento(i).getData().getCelda() == individuos.getElemento(j).getData().getCelda()) {
                    recursos.getElemento(i).getData().Propiedad(individuos.getElemento(j).getData());
                    recursos.del(i);
                }
            }
        }

    }



 */

    public int generarID(Casilla casillaActual) {
        ListaEnlazada<Individuo> individuos= casillaActual.getIndividuos();


        int id = 0;
        for (int i = 0; i < individuos.getNumeroElementos(); i++) {
            if(individuos.getElemento(i).getData().getId()>id){
                id=individuos.getElemento(i).getData().getId();
            }
        }
        id=id+1;
        return id;


    }




    // Si 2 individuos ocupan la misma posición de la matriz, o se
    //reproducen generando otro individuo más, o mueren ambos
    //La reproducción de dos individuos del mismo tipo da lugar a otro individuo de ese tipo.
    //• La reproducción de individuos de distinto tipo da lugar a otro individuo del tipo más alto


    public void reproduccion(Casilla casillaActual) {
        ListaEnlazada<Individuo> individuos = casillaActual.getIndividuos();

        if (individuos.getNumeroElementos() == 2) {
            Random random = new Random();
            int probabilidadAleatoria = random.nextInt(101);

            Individuo individuo1 = individuos.getElemento(0).getData();
            Individuo individuo2 = individuos.getElemento(1).getData();

            if (individuo1.getProbabilidadReproduccion() >= probabilidadAleatoria ||
                    individuo2.getProbabilidadReproduccion() >= probabilidadAleatoria) {


                //genero su id
                //TODO -> cambiar valores
                Individuo hijo = new Individuo(1,1,1,1,1,1,1,1,1);
                hijo.setId(generarID(casillaActual));
                //su generacion
                int generacion = Math.max(individuo1.getTurnoGeneracion(), individuo2.getTurnoGeneracion());
                hijo.setTurnoGeneracion(generacion);

                //el tipo
                if (individuo1.getTipo() == individuo2.getTipo()) {
                    hijo.setTipo(individuo1.getTipo());
                }
                else {
                    hijo.setTipo(Math.max(individuo1.getTipo(), individuo2.getTipo()));
                }
                //su probabilidad de reproduccion y clonacion sera la misma q la del padre
                hijo.setProbabilidadClonacion(individuo1.getProbabilidadClonacion());
                hijo.setProbabilidadReproduccion(individuo1.getProbabilidadReproduccion());
                hijo.setProbabilidadMuerte(individuo1.getProbabilidadMuerte());
                hijo.setCoordenadaX(individuo1.getCoordenadaX());
                hijo.setCoordenadaY(individuo1.getCoordenadaY());


                individuos.add(hijo);
                casillaActual.setIndividuos(individuos);
            }

            else {
                for (int i=0; i<individuos.getNumeroElementos();i++){
                    if (individuos.getElemento(i).getData().getProbabilidadMuerte()>probabilidadAleatoria){
                        individuos.delete(i);
                        casillaActual.setIndividuos(individuos);
                    }
                }
                System.out.println("No hay reproduccion");
            }
        } else if (individuos.getNumeroElementos() == 3) {
            System.out.println("YA HAY 3 INDIVIDUOS EN LA CELDA, IMPOSIBLE REPRODUCIRSE");
        }
    }





    //un individuo puede clonarse a sí mismo, y el clon
    //aparecerá en la misma posición del mapa.
    public void clonacion(Casilla casillaActual){

        ListaEnlazada<Individuo> individuos = casillaActual.getIndividuos();

        for(int i=0; i<individuos.getNumeroElementos(); i++){
            Random random= new Random();
            int probabilidad= random.nextInt(101);

            if(probabilidad<=individuos.getElemento(i).getData().getProbabilidadClonacion()){


                Individuo clon= new Individuo(); //todo ver si coje bien la informacion el clon

                //id
                clon.setId(generarID(casillaActual));
                //generacion
                clon.setTurnoGeneracion(individuos.getElemento(i).getData().getTurnoGeneracion());

                //el tipo
                clon.setTipo(individuos.getElemento(i).getData().getTipo());
                //su probabilidad de reproduccion y clonacion sera la misma q la del padre
                clon.setProbabilidadClonacion(individuos.getElemento(i).getData().getProbabilidadClonacion());
                clon.setProbabilidadReproduccion(individuos.getElemento(i).getData().getProbabilidadReproduccion());
                clon.setProbabilidadMuerte(individuos.getElemento(i).getData().getProbabilidadMuerte());
                clon.setCoordenadaX(individuos.getElemento(i).getData().getCoordenadaX());
                clon.setCoordenadaY(individuos.getElemento(i).getData().getCoordenadaY());


                individuos.add(clon);


                casillaActual.setIndividuos(individuos);

            }
        }

    }



    //individuos desaparecen cuando sus turnos de vida son menores o iguales que 1 y tambien desaparecen si caen al pozo y luego su proababilidadde muerte es 1-prob.de.reproduccion



    public void muerteIndividuos(Casilla casillaActual){
        Random random= new Random();
        int probabilidadsobrevivir= random.nextInt(101);

        ListaEnlazada<Individuo> individuos = casillaActual.getIndividuos();
        ListaEnlazada<Entorno> entorno = casillaActual.getRecursos();

        for (int i=0; i<individuos.getNumeroElementos(); i++){
            if (individuos.getElemento(i).getData().getTurnosVidaRestantes()<1){
                individuos.delete(i);
                casillaActual.setIndividuos(individuos);
            }

            else if (probabilidadsobrevivir<individuos.getElemento(i).getData().getProbabilidadMuerte()){
                individuos.delete(i);
                casillaActual.setIndividuos(individuos);

            }

            else{
                for (int j=0; j<entorno.getNumeroElementos();j++){
                    if (entorno.getElemento(j).getData().getClass()== Pozo.class){
                        individuos.delete(i);
                        casillaActual.setIndividuos(individuos);
                    }
                }

            }



        }

    }


    //ME FALTA LA FUNCION DE APARICION DE RECURSOS



    //los recursos aparecen si una probabilidad de aparicion de recursos gana a la probabilidad generada aleatoriamente, si haay mas de 3 recursos en una posicion no pueden incluirse mas recursos en esta
    //se va a ir comparando la probabilidad de creacion de un nuevo recurso y la probabilidad de aparecer el recurso x, si la probabiliad de el nuevo recurso a aparecer es mayor que la probabilidad de el recurso x, entonces se crea un nuevo recurso del tipo x.


   //todo /con como lo he hecho, los reecursos saben la posicion en la que van a aparecer???????

    //todo?????????¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿por cada vez que le demos a avanzar va a haber una probabilidad distinta de si puede aparecer un nuevo recurso o no (simulando como que cada tiempo hay distintas vecees en las cuales hay mas recursos o menos) y si esta es mayor que la probabilidad de aparicion de un nuevo recurso entonces se llevaran acabo todas las probabilidades de lo demas
    public void aparicionRecursos(Casilla casillaActual){

        ListaEnlazada<Individuo> individuos = casillaActual.getIndividuos();
        ListaEnlazada<Entorno> entorno = casillaActual.getRecursos();
        ParametrosEntorno parametrosEntorno= new ParametrosEntorno();


        while (entorno.getNumeroElementos()<3){
            Random random= new Random();
            int probabilidaddenuevorecurso= random.nextInt(101);

            if (probabilidaddenuevorecurso> parametrosEntorno.getProbabilidadAgua()){
                Agua agua= new Agua();
                agua.setTiempoAparicion(3);
                entorno.add(agua);
                casillaActual.setRecursos(entorno);
            }

            if(probabilidaddenuevorecurso>parametrosEntorno.getProbabilidadComida()){
                Comida comida= new Comida();
                comida.setTiempoAparicion(3);
                entorno.add(comida);
                casillaActual.setRecursos(entorno);
            }

            if (probabilidaddenuevorecurso>parametrosEntorno.getProbabilidadMontaña()){
                Montaña montaña= new Montaña();
                montaña.setTiempoAparicion(3);
                entorno.add(montaña);
                casillaActual.setRecursos(entorno);
            }

            if (probabilidaddenuevorecurso>parametrosEntorno.getProbabilidadBiblioteca()){
                Biblioteca biblioteca= new Biblioteca();
                biblioteca.setTiempoAparicion(3);
                entorno.add(biblioteca);
                casillaActual.setRecursos(entorno);
            }

            if (probabilidaddenuevorecurso>parametrosEntorno.getProbabilidadPozo()){
                Pozo pozo= new Pozo();
                pozo.setTiempoAparicion(3);
                entorno.add(pozo);
                casillaActual.setRecursos(entorno);
            }


            if (probabilidaddenuevorecurso>parametrosEntorno.getProbabilidadTesoro()){
                Tesoro tesoro= new Tesoro();
                tesoro.setTiempoAparicion(3);
                entorno.add(tesoro);
                casillaActual.setRecursos(entorno);
            }

        }

    }
}


