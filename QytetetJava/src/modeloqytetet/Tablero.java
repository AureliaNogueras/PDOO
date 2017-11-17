package modeloqytetet;

import java.util.ArrayList;

/**
 *
 * @author aurelia
 */
public class Tablero {
    private ArrayList<Casilla> casillas = new ArrayList();
    private Casilla carcel;
    
    public Tablero(){
        inicializar();
    }
    
    boolean esCasillaCarcel(int numeroCasilla){
        boolean comprobacion = false;
        if (numeroCasilla == carcel.getNumeroCasilla())
           comprobacion = true;
        return comprobacion;
    }
    
    Casilla getCarcel(){
        return carcel;
    }
    
    Casilla obtenerCasillaNumero(int numeroCasilla){
        Casilla cas = null;
        for (Casilla c: casillas){
            if (c.getNumeroCasilla() == numeroCasilla)
                cas = c;
        }
        return cas;
    }
    
    Casilla obtenerNuevaCasilla(Casilla casilla, int desplazamiento){
        int nuevoNumCasilla = casilla.getNumeroCasilla() + desplazamiento;
        nuevoNumCasilla %= 20;
        return casillas.get(nuevoNumCasilla);
    }
    
    private void inicializar(){
        // Casillas que no son calles
        casillas.add(new Casilla(0,TipoCasilla.SALIDA));
        casillas.add(new Casilla(3,TipoCasilla.SORPRESA));
        casillas.add(new Casilla(6,TipoCasilla.IMPUESTO));
        carcel = new Casilla(9,TipoCasilla.CARCEL);
        casillas.add(carcel);
        casillas.add(new Casilla(11,TipoCasilla.SORPRESA));
        casillas.add(new Casilla(14,TipoCasilla.PARKING));
        casillas.add(new Casilla(16,TipoCasilla.JUEZ));
        casillas.add(new Casilla(18,TipoCasilla.SORPRESA));
        
        // Casillas que son calles
        TituloPropiedad t = new TituloPropiedad("Avenida Cervantes",50,-12,200,250);
        casillas.add(new Casilla(1,200,t));
        
        t = new TituloPropiedad("Catedral",75,11,600,500);
        casillas.add(new Casilla(2,400,t));
        
        t = new TituloPropiedad("Alhambra",100,10,1000,750);
        casillas.add(new Casilla(4,700,t));
        
        t = new TituloPropiedad("Puerta Real",55,13,550,300);
        casillas.add(new Casilla(5,200,t));
        
        t = new TituloPropiedad("Corral del Carbón",80,17,500,400);
        casillas.add(new Casilla(7,300,t));
        
        t = new TituloPropiedad("Generalife",85,-20,900,450);
        casillas.add(new Casilla(8,450,t));
        
        t = new TituloPropiedad("Calle Mesones",55,-10,300,350);
        casillas.add(new Casilla(10,200,t));
        
        t = new TituloPropiedad("Albaicín",65,-16,400,550);
        casillas.add(new Casilla(12,500,t));
        
        t = new TituloPropiedad("Sierra Nevada",95,-14,700,700);
        casillas.add(new Casilla(13,600,t));
        
        t = new TituloPropiedad("Paseo de los Tristes",70,11,600,650);
        casillas.add(new Casilla(15,600,t));
        
        t = new TituloPropiedad("Los Italianos",60,-15,800,600);
        casillas.add(new Casilla(17,350,t));
        
        t = new TituloPropiedad("Avenida de la Constitución",65,10,150,550);
        casillas.add(new Casilla(19,400,t));
    }
    
    @Override
    public String toString(){
        return "Tablero{" + "casillas =" + casillas + ", cárcel =" + carcel + "}\n";
    }
}
