package modeloqytetet;

/**
 *
 * @author aurelia
 */
public class OtraCasilla extends Casilla{
    
    public OtraCasilla(int nC, TipoCasilla t){
        super(nC,t);
    }
    
    @Override
    boolean soyEdificable(){
        return false;
    }
    
}
