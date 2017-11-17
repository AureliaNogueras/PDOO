package modeloqytetet;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author aurelia
 */
public class Qytetet {
    private static final Qytetet instance = new Qytetet();
    public static final int MAX_JUGADORES = 4;
    static final int MAX_CARTAS = 10;
    static final int MAX_CASILLAS = 20;
    static final int PRECIO_LIBERTAD = 200;
    static final int SALDO_SALIDA = 1000;
    private Sorpresa cartaActual;
    private ArrayList<Sorpresa> mazo;
    private ArrayList<Jugador> jugadores;
    private Jugador jugadorActual;
    private Tablero tablero;
    private Dado dado;
    
    private Qytetet(){
        cartaActual = null;
        mazo = new ArrayList();
        jugadores = new ArrayList();
        jugadorActual = null;
        tablero = null;
        dado = Dado.getInstance();
    }
    
    public static Qytetet getInstance(){
        return instance;
    }
    
    //public boolean aplicarSorpresa(){}
    
    //public boolean cancelarHipoteca(Casilla casilla){}
    
    //public boolean comprarTituloPropiedad(){}
    
    //public boolean edificarCasa(Casilla casilla){}
    
    //public boolean edificarHotel(Casilla casilla){}
    
    public Sorpresa getCartaActual(){
        return cartaActual;
    }
    
    public Jugador getJugadorActual(){
        return jugadorActual;
    }
    
    public ArrayList<Jugador> getJugadores(){
        return jugadores;
    }
    
    //public boolean hipotecarPropiedad(Casilla casilla){}
    
    public void inicializarJuego(ArrayList<String> nombres){
        inicializarJugadores(nombres);
        inicializarCartasSorpresa();
        inicializarTablero();
        salidaJugadores();
    }
    
    //public boolean intentarSalirCarcel(MetodoSalirCarcel metodo){}
    
    //public boolean jugar(){}
    
    //public ArrayList<Jugador> obtenerRanking(){}
    
    //Añadido getCasilla, hecho a partir del de Jugador
    public ArrayList<Casilla> propiedadesHipotecadasJugador(boolean hipotecadas){
        ArrayList<TituloPropiedad> titulos = jugadorActual.obtenerPropiedadesHipotecadas(hipotecadas);
        ArrayList<Casilla> casillas = new ArrayList();
        for (TituloPropiedad t: titulos){
            casillas.add(t.getCasilla());
        }
        return casillas;
    }
    
    public Jugador siguienteJugador(){
        int indice = jugadores.indexOf(jugadorActual);
        indice +=1;
        indice %= jugadores.size();
        jugadorActual = jugadores.get(indice);
        return jugadorActual;
    }
    
    //public boolean venderPropiedad(Casilla casilla){}
    
    //private void encarcelarJugador(){}
    
    private void inicializarCartasSorpresa(){
        mazo.add(new Sorpresa("Han pillado alguno de tus chanchullos. Ve a la cárcel.",TipoSorpresa.IRACASILLA,tablero.getCarcel().getNumeroCasilla()));
        mazo.add(new Sorpresa("Un extraño tornado te arrastra hasta la casilla 13.",TipoSorpresa.IRACASILLA,13));
        mazo.add(new Sorpresa("El poder de la fuerza te atrae hasta la casilla 2.",TipoSorpresa.IRACASILLA,2));
        mazo.add(new Sorpresa("Quedas libre de la cárcel. No es aplicable en la vida real",TipoSorpresa.SALIRCARCEL,0));
        
        mazo.add(new Sorpresa("Al fin han reconocido tu talento y tu belleza... Recibes dinero.",TipoSorpresa.PAGARCOBRAR,200));
        mazo.add(new Sorpresa("Pagas al banco con la esperanza de que no descubran lo que te traes entre manos.",TipoSorpresa.PAGARCOBRAR,-250));
        
        mazo.add(new Sorpresa("Recibes dinero por cada una de tus maravillosas propiedades.",TipoSorpresa.PORCASAHOTEL,150));
        mazo.add(new Sorpresa("No cumples la normativa, así que debes pagar por cada una de tus odiosas propiedades.",TipoSorpresa.PORCASAHOTEL,-175));
        
        mazo.add(new Sorpresa("Los demás jugadores sospechan de ti. Los sobornas para que no te delaten.",TipoSorpresa.PORJUGADOR,-100));
        mazo.add(new Sorpresa("El resto de jugadores te indemniza para comprar tu silencio.",TipoSorpresa.PORJUGADOR,100));
        Collections.shuffle(mazo);
    }
    
    private void inicializarJugadores(ArrayList<String> nombres){
        Jugador j;
        for (String n: nombres){
            j = new Jugador(n);
            jugadores.add(j);
        }
    }
    
    private void inicializarTablero(){
        tablero = new Tablero();
    }
    
    // Suponemos saldo a 7500
    private void salidaJugadores(){
        for (Jugador j: jugadores){
            j.setCasillaActual(tablero.obtenerCasillaNumero(0));
        }
        int numero = (int) (Math.random()*jugadores.size());
        jugadorActual = jugadores.get(numero);
    }
    
    @Override
    public String toString(){
        return "Qytetet {" + "carta actual=" + cartaActual + ", cartas sorpresa=" + mazo +
                ", jugador actual=" + jugadorActual + ", jugadores=" + jugadores + ", tablero="+
                tablero + ", dado=" + dado + "}\n";
    
    }
    
}
