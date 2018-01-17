package modeloqytetet;

/**
 *
 * @author aurelia
 */
public class Calle extends Casilla{
    private int numCasas;
    private int numHoteles;
    private TituloPropiedad titulo;
    private int coste;
    
    // Casillas de tipo calle
    public Calle(int nC, int c, TituloPropiedad ti){
        super(nC,TipoCasilla.CALLE);
        numHoteles = 0;
        numCasas = 0;
        coste = c;
        titulo = ti;
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
    
    public boolean sePuedeEdificarCasa(int factorEspeculador){
        return numCasas < 4*factorEspeculador;
    }
    
    public boolean sePuedeEdificarHotel(int factorEspeculador){
        return numHoteles < 4*factorEspeculador && numCasas == 4*factorEspeculador;
    }
    
    void setNumCasas(int nuevoNumero){
        numCasas = nuevoNumero;
    }
    
    void setNumHoteles(int nuevoNumero){
        numHoteles = nuevoNumero;
    }
    
    @Override
    boolean soyEdificable(){
        return true;
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
    
    @Override
    public String toString(){
        return super.toString() + ", coste=" + Integer.toString(coste) + ", número de casas=" +
                Integer.toString(numCasas) + ", número de hoteles=" + Integer.toString(numHoteles) 
                + ", título de propiedad=" + titulo + "}\n";
    }
    
}
