package modeloqytetet;

import java.util.ArrayList;

/**
 *
 * @author aurelia
 */
public class PruebaQytetet {
    private static ArrayList<Sorpresa> mazo = new ArrayList();
    private static Tablero tablero = new Tablero();
    
    private static void inicializarSorpresas(){
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
    }
    
    private static ArrayList<Sorpresa> mayorQue0(){
        ArrayList<Sorpresa> mayores = new ArrayList();
        
        for (Sorpresa m: mazo){
            if (m.getValor() > 0)
                mayores.add(m);
        }
        return mayores;
    }
    
    private static ArrayList<Sorpresa> tipoCasilla(){
        ArrayList<Sorpresa> tipo = new ArrayList();
        
        for (Sorpresa t: mazo){
            if (t.getTipo() == TipoSorpresa.IRACASILLA)
                tipo.add(t);
        }
        return tipo;
    }
    
    private static ArrayList<Sorpresa> tipoEspecifico(TipoSorpresa t){
        ArrayList<Sorpresa> especifico = new ArrayList();
        
        for (Sorpresa s: mazo){
            if (s.getTipo() == t)
                especifico.add(s);
        }
        return especifico;
    }
    
    public static void main(String[] args) {
        inicializarSorpresas();
        //System.out.println(mazo);
        
        //System.out.println(mayorQue0());
        //System.out.println(tipoCasilla());
        //System.out.println(tipoEspecifico(TipoSorpresa.SALIRCARCEL));
        
        //System.out.println(tablero.getCarcel());
        
        Jugador j = new Jugador ("Aurelia");
        Tablero t = new Tablero();
        Sorpresa s = new Sorpresa("Hola, ve a la casilla 4",TipoSorpresa.IRACASILLA,4);
        Casilla c = new Casilla(0,TipoCasilla.SALIDA);
        TituloPropiedad ti = new TituloPropiedad("Catedral",75,11,600,500);
        Qytetet q = Qytetet.getInstance();
        
        System.out.println(j);
        System.out.println(t);
        System.out.println(s);
        System.out.println(c);
        System.out.println(ti);
        System.out.println(q);
        
    }
    
}
