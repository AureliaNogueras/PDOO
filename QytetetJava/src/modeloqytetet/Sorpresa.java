package modeloqytetet;

/**
 *
 * @author aurelia
 */
public class Sorpresa {
    private String texto;
    private int valor;
    private TipoSorpresa tipo;
    
    public Sorpresa(String texto, TipoSorpresa tipo, int valor){
        this.texto = texto;
        this.tipo = tipo;
        this.valor = valor;
    }
    
    String getTexto(){
        return texto;
    }
    
    // Cambiada visibilidad para poder acceder desde ControladorQytetet y saber si la carta sorpresa es la de irACarcel
    public TipoSorpresa getTipo(){
        return tipo;
    }
    
    public int getValor(){
        return valor;
    }
    
    @Override
    public String toString(){
        return "Sorpresa{" + "texto=" + texto + ", valor=" +
                Integer.toString(valor) + ", tipo=" + tipo + "}\n";
    }
}
