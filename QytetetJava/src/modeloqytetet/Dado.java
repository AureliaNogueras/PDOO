package modeloqytetet;

/**
 *
 * @author aurelia
 */
public class Dado {
    private static final Dado instance = new Dado();
    
    private Dado(){}
    
    public static Dado getInstance(){
        return instance;
    }
    
    int tirar(){
        //return (int) (Math.random()*6) + 1;
        return 3;
    }
    
}
