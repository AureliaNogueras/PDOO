package modeloqytetet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

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
    
    public boolean aplicarSorpresa(){
        boolean tienePropietario = false;
        if (cartaActual.getTipo() == TipoSorpresa.PAGARCOBRAR){
            jugadorActual.modificarSaldo(cartaActual.getValor());
        }else if(cartaActual.getTipo() == TipoSorpresa.IRACASILLA){
            boolean esCarcel = tablero.esCasillaCarcel(cartaActual.getValor());
            if (esCarcel){
                encarcelarJugador();
            }else{
                Casilla nuevaCasilla = tablero.obtenerCasillaNumero(cartaActual.getValor());
                tienePropietario = jugadorActual.actualizarPosicion(nuevaCasilla);
            }
        }else if (cartaActual.getTipo() == TipoSorpresa.PORCASAHOTEL){
            jugadorActual.pagarCobrarPorCasaYHotel(cartaActual.getValor());
        }else if (cartaActual.getTipo() == TipoSorpresa.PORJUGADOR){
            for (Jugador jugador: jugadores){
                if (jugador != jugadorActual){
                    jugador.modificarSaldo(cartaActual.getValor());
                }else
                    jugadorActual.modificarSaldo(cartaActual.getValor());
            }
        }
        
        if (cartaActual.getTipo() == TipoSorpresa.SALIRCARCEL){
            jugadorActual.setCartaLibertad(cartaActual);
        }else{
            // Ver después porque no lo tengo claro
            mazo.add(cartaActual);
        }
        return tienePropietario;
    }
    
    public boolean cancelarHipoteca(Casilla casilla){
        boolean cancelarHipoteca = false;
        if (casilla.estaHipotecada() && jugadorActual.puedoPagarHipoteca(casilla)){
            int cantidadRecibida = casilla.getCosteHipoteca();
            jugadorActual.modificarSaldo(-cantidadRecibida); 
            cancelarHipoteca = true;
        }
        return cancelarHipoteca; 
    }
    
    public boolean comprarTituloPropiedad(){
        boolean puedoComprar = jugadorActual.comprarTitulo();
        return puedoComprar;
    }
    
    public boolean edificarCasa(Casilla casilla){
        boolean puedoEdificar = false;
        if (casilla.soyEdificable()){
            boolean sePuedeEdificar = casilla.sePuedeEdificarCasa();
            if (sePuedeEdificar){
                puedoEdificar = jugadorActual.puedoEdificarCasa(casilla);
                if(puedoEdificar){
                    int costeEdificarCasa = casilla.edificarCasa();
                    jugadorActual.modificarSaldo(-costeEdificarCasa);
                }
            }
        }
        return puedoEdificar;
    }
    
    public boolean edificarHotel(Casilla casilla){
        boolean puedoEdificar = false;
        if (casilla.soyEdificable()){
            boolean sePuedeEdificar = casilla.sePuedeEdificarHotel();
            if (sePuedeEdificar){
                puedoEdificar = jugadorActual.puedoEdificarHotel(casilla);
                if(puedoEdificar){
                    int costeEdificarHotel = casilla.edificarHotel();
                    jugadorActual.modificarSaldo(-costeEdificarHotel);
                }
            }
        }
        return puedoEdificar;
    }
    
    public Sorpresa getCartaActual(){
        return cartaActual;
    }
    
    public Jugador getJugadorActual(){
        return jugadorActual;
    }
    
    public ArrayList<Jugador> getJugadores(){
        return jugadores;
    }
    
    public boolean hipotecarPropiedad(Casilla casilla){
        boolean puedoHipotecar = false;
        if (casilla.soyEdificable()){
            boolean sePuedeHipotecar = !casilla.estaHipotecada();
            if (sePuedeHipotecar){
                puedoHipotecar = jugadorActual.puedoHipotecar(casilla);
                if (puedoHipotecar){
                    int cantidadRecibida = casilla.hipotecar();
                    jugadorActual.modificarSaldo(cantidadRecibida);
                }
            }
        }
        return puedoHipotecar;
    }
    
    public void inicializarJuego(ArrayList<String> nombres){
        inicializarJugadores(nombres);
        inicializarTablero();
        inicializarCartasSorpresa();
        salidaJugadores();
    }
    
    public boolean intentarSalirCarcel(MetodoSalirCarcel metodo){
        boolean libre = false;
        if (metodo == MetodoSalirCarcel.TIRANDODADO){
            int valorDado = dado.tirar();
            libre = valorDado>5;
        }else{
            boolean tengoSaldo = jugadorActual.pagarLibertad(Qytetet.PRECIO_LIBERTAD);
            libre = tengoSaldo;
        }
        if (libre)
            jugadorActual.setEncarcelado(false);
        return libre;
    }
    
    public boolean jugar(){
        int valorDado = dado.tirar();
        Casilla casillaPosicion = jugadorActual.getCasillaActual();
        Casilla nuevaCasilla = tablero.obtenerNuevaCasilla(casillaPosicion,valorDado);
        boolean tienePropietario = jugadorActual.actualizarPosicion(nuevaCasilla);
        if (!nuevaCasilla.soyEdificable()){
            if (nuevaCasilla.getTipo() == TipoCasilla.JUEZ){
                encarcelarJugador();
            }else if (nuevaCasilla.getTipo() == TipoCasilla.SORPRESA){
                cartaActual = mazo.get(0);
                // Se borra la carta del mazo
                mazo.remove(cartaActual);
            }
        }
        return tienePropietario;
    }
    
    public Map<String, Integer> obtenerRanking(){
        Map<String, Integer> ranking = new LinkedHashMap<String, Integer>();
        for (Jugador jugador: jugadores){
            int capital = jugador.obtenerCapital();
            // Habría que añadir el nombre y el capital, pero ¿cómo?
            ranking.put(jugador.getNombre(), capital);
        }
        return ranking;
    }
    
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
    
    public boolean venderPropiedad(Casilla casilla){
        boolean puedoVender = false;
        if (casilla.soyEdificable()){
            puedoVender = jugadorActual.puedoVenderPropiedad(casilla);
            if (puedoVender){
                jugadorActual.venderPropiedad(casilla);
            }
        }
        return puedoVender;
    }
    
    private void encarcelarJugador(){
        if (!jugadorActual.tengoCartaLibertad()){
            Casilla casillaCarcel = tablero.getCarcel();
            jugadorActual.irACarcel(casillaCarcel);
        }else{
            Sorpresa carta = jugadorActual.devolverCartaLibertad();
            mazo.add(carta);
            // Revisar
        }
    }
    
    private void inicializarCartasSorpresa(){
        mazo.add(new Sorpresa("Han pillado alguno de tus chanchullos. Ve a la cárcel.",TipoSorpresa.IRACASILLA,tablero.getCarcel().getNumeroCasilla()));
        mazo.add(new Sorpresa("Un extraño tornado te arrastra hasta la casilla 13.",TipoSorpresa.IRACASILLA,13));
        mazo.add(new Sorpresa("El poder de la fuerza te atrae hasta la casilla 2.",TipoSorpresa.IRACASILLA,2));
        mazo.add(new Sorpresa("Quedas libre de la cárcel. No es aplicable en la vida real",TipoSorpresa.SALIRCARCEL,0));
        
        mazo.add(new Sorpresa("Al fin han reconocido tu talento y tu belleza... Recibes dinero.",TipoSorpresa.PAGARCOBRAR,200));
        mazo.add(new Sorpresa("Pagas al banco con la esperanza de que no descubran lo que te traes entre manos.",TipoSorpresa.PAGARCOBRAR,-250));
        
        mazo.add(new Sorpresa("Recibes dinero por cada una de tus maravillosas propiedades.",TipoSorpresa.PORCASAHOTEL,150));
        mazo.add(new Sorpresa("No cumples la normativa, así que debes pagar por cada una de tus odiosas propiedades.",TipoSorpresa.PORCASAHOTEL,-175));
        // No estoy segura de si están bien los signos, por el método aplicarSorpresa 
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
