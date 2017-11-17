package modeloqytetet;

/**
 *
 * @author aurelia
 */
public class Casilla {
    private int coste;
    private int numeroCasilla = 0;
    private int numCasas;
    private int numHoteles;
    private TipoCasilla tipo;
    private TituloPropiedad titulo;
    
    // Casillas que no son de tipo calle
    public Casilla(int nC, TipoCasilla t){
        numeroCasilla = nC;
        tipo = t;
        titulo = null;
        numHoteles = 0;
        numCasas = 0;
        coste = 0;
    }
    
    // Casillas de tipo calle
    public Casilla(int nC, int c, TituloPropiedad ti){
        numeroCasilla = nC;
        coste = c;
        tipo = TipoCasilla.CALLE;
        titulo = ti;
        numHoteles = 0;
        numCasas = 0;
    }
    
    public TipoCasilla getTipo(){
        return tipo;
    }
    
    public TituloPropiedad getTitulo(){
        return titulo;
    }
    
    //TituloPropiedad asignarPropietario(Jugador jugador){}
    
    //int calcularValorHipoteca(){}
    
    //int cancelarHipoteca(){}
    
    int cobrarAlquiler(){
        int costeAlquilerBase = titulo.getAlquilerBase();
        int costeAlquiler = costeAlquilerBase;
        titulo.cobrarAlquiler(costeAlquiler);
    }
    
    //int edificarCasa(){}
    
    //int edificarHotel(){}
    
    boolean estaHipotecada(){
        return titulo.getHipotecada();
    }
    
    int getCoste(){
        return coste;
    }
    
    //int getCosteHipoteca(){}
    
    int getNumeroCasilla(){
        return numeroCasilla;
    }
    
    int getNumCasas(){
        return numCasas;
    }
    
    int getNumHoteles(){
        return numHoteles;
    }
    
    //int getPrecioEdificar(){}
    
    //int hipotecar(){}
    
    //int precioTotalComprar(){}
    
    boolean propietarioEncarcelado(){
        boolean encarcelado = titulo.propietarioEncarcelado();
        return encarcelado;
    }
    
    //boolean sePuedeEdificarCasa(){}
    
    //boolean sePuedeEdificarHotel(){}
    
    void setNumCasas(int nuevoNumero){
        numCasas = nuevoNumero;
    }
    
    void setNumHoteles(int nuevoNumero){
        numHoteles = nuevoNumero;
    }
    
    boolean soyEdificable(){
        boolean edificable = false;
        if (tipo == TipoCasilla.CALLE)
            edificable = true;
        return edificable;
    }
    
    boolean tengoPropietario(){
        boolean tengoPropietario = titulo.tengoPropietario();
        return tengoPropietario;
    }
    
    //int venderTitulo(){}
    
    private void setTitulo(TituloPropiedad titulo){
        this.titulo = titulo;
    }
    
    private void asignarTituloPropiedad(){}
    
    @Override
    public String toString(){
        return "Casilla{" + "número de casilla=" + Integer.toString(numeroCasilla) + 
                ", coste=" + Integer.toString(coste) + ", número de casas=" +
                Integer.toString(numCasas) + ", número de hoteles=" + Integer.toString(numHoteles) +
                ", tipo de casilla=" + tipo + ", título de propiedad=" + titulo + "}\n";
    }
}
