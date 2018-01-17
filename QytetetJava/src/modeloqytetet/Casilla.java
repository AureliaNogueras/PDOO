package modeloqytetet;

/**
 *
 * @author aurelia
 */
public abstract class Casilla {
    private int numeroCasilla = 0;
    private TipoCasilla tipo;
    
    public Casilla(int nC, TipoCasilla t){
        numeroCasilla = nC;
        tipo = t;
    }
     
    public TipoCasilla getTipo(){
        return tipo;
    }
    
    // Lo pongo public para que sea accesible desde el controlador
    public int getNumeroCasilla(){
        return numeroCasilla;
    }
    
    abstract boolean soyEdificable();
    
    @Override
    public String toString(){
        return "Casilla{" + " número de casilla=" + Integer.toString(numeroCasilla) +
                ", tipo de casilla=" + tipo + "}\n";
    }
}
