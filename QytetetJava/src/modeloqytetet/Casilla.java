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
    
    TituloPropiedad asignarPropietario(Jugador jugador){
        titulo.setPropietario(jugador);
        return titulo;
    }
    
    int calcularValorHipoteca(){
        int hipotecaBase = titulo.getHipotecaBase();
        int cantidadRecibida = hipotecaBase + (int) (numCasas*0.5*hipotecaBase + numHoteles*hipotecaBase);
        return cantidadRecibida;
    }
    
    // Devuelve el precio que hay que pagar para deshipotecar la propiedad
    int cancelarHipoteca(){
        titulo.setHipotecada(false);
        int valor = calcularValorHipoteca() + (int) (0.1*calcularValorHipoteca());
        return valor;
    }
    
    int cobrarAlquiler(){
        int costeAlquilerBase = titulo.getAlquilerBase();
        int costeAlquiler = costeAlquilerBase + (int) (numCasas*0.5 + numHoteles*2);
        titulo.cobrarAlquiler(costeAlquiler);
        return costeAlquiler;
    }
    
    int edificarCasa(){
        this.setNumCasas(numCasas+1);
        int costeEdificarCasa = titulo.getPrecioEdificar();
        return costeEdificarCasa;
    }
    
    int edificarHotel(){
        this.setNumHoteles(numHoteles+1);
        int costeEdificarHotel = titulo.getPrecioEdificar();
        return costeEdificarHotel;
    }
    
    boolean estaHipotecada(){
        boolean hipotecada = titulo.getHipotecada();
        return hipotecada;
    }
    
    int getCoste(){
        return coste;
    }
    
    // Devuelve lo que cuesta deshipotecar
    int getCosteHipoteca(){
        return cancelarHipoteca();
    }
    
    // Lo pongo public para que sea accesible desde el controlador
    public int getNumeroCasilla(){
        return numeroCasilla;
    }
    
    int getNumCasas(){
        return numCasas;
    }
    
    int getNumHoteles(){
        return numHoteles;
    }
    
    int getPrecioEdificar(){
        return titulo.getPrecioEdificar();
    }
    
    int hipotecar(){
        titulo.setHipotecada(true);
        int cantidadRecibida = calcularValorHipoteca();
        return cantidadRecibida;
    }
    
    int precioTotalComprar(){
        int precio = coste + (numCasas + numHoteles)*titulo.getPrecioEdificar();
        precio = precio + (int) (precio*titulo.getFactorRevalorizacion());
        return precio;
    }
    
    boolean propietarioEncarcelado(){
        boolean encarcelado = titulo.propietarioEncarcelado();
        return encarcelado;
    }
    
    boolean sePuedeEdificarCasa(){
        return numCasas < 4;
    }
    
    boolean sePuedeEdificarHotel(){
        return numHoteles < 4 && numCasas == 4;
    }
    
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
    
    int venderTitulo(){
        titulo.setPropietario(null);
        setNumHoteles(0);
        setNumCasas(0);
        return precioTotalComprar();
    }
    
    private void setTitulo(TituloPropiedad titulo){
        this.titulo = titulo;
    }
    
    private void asignarTituloPropiedad(){
    
    }
    
    @Override
    public String toString(){
        return "Casilla{" + " número de casilla=" + Integer.toString(numeroCasilla) + 
                ", coste=" + Integer.toString(coste) + ", número de casas=" +
                Integer.toString(numCasas) + ", número de hoteles=" + Integer.toString(numHoteles) +
                ", tipo de casilla=" + tipo + ", título de propiedad=" + titulo + "}\n";
    }
}
