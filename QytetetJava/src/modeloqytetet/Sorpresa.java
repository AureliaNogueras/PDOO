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
    
    TipoSorpresa getTipo(){
        return tipo;
    }
    
    int getValor(){
        return valor;
    }
    
    @Override
    public String toString(){
        return "Sorpresa{" + "texto=" + texto + ", valor=" +
                Integer.toString(valor) + ", tipo=" + tipo + "}\n";
    }
}
