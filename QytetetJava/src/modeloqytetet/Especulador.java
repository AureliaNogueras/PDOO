package modeloqytetet;

/**
 *
 * @author aurelia
 */
public class Especulador extends Jugador{
    
    static int FactorEspeculador = 2;
    private int fianza;
    
    protected Especulador(Jugador jugador, int fianza){
       super(jugador);
       this.fianza = fianza;
    }
    
    @Override
    public int getFactorEspeculador(){
        return FactorEspeculador;
    }
    
    @Override
    protected void pagarImpuestos(int cantidad){
        super.pagarImpuestos(cantidad/2);
    }
    
    @Override
    protected void irACarcel(Casilla casilla){
        if(!pagarFianza(fianza))    
            super.irACarcel(casilla);
    }
    
    @Override
    protected Especulador convertirme(int fianza){
        return this;
    }
    
    private boolean pagarFianza(int cantidad){
        boolean pagar = false;
        if(this.getSaldo() >= cantidad){
            super.modificarSaldo(-cantidad);
            pagar = true;
        }
        
        return pagar;
    }
    
    @Override
    public String toString(){
        return super.toString() + "\nSe trata de un especulador cuya fianza es " + fianza + "\n";
    }
    
}
