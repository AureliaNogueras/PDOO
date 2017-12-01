package modeloqytetet;

/**
 *
 * @author aurelia
 */
public class TituloPropiedad {
    private int alquilerBase;
    private float factorRevalorizacion;
    private int hipotecaBase;
    private boolean hipotecada;
    private String nombre;
    private int precioEdificar;
    private Jugador propietario;
    private Casilla casilla;
    
    public TituloPropiedad(String n, int aB, float fR, int hB, int pE){
        nombre = n;
        alquilerBase = aB;
        factorRevalorizacion = fR;
        hipotecaBase = hB;
        precioEdificar = pE;
        hipotecada = false;
    }
    
    void cobrarAlquiler(int coste){
        propietario.modificarSaldo(coste);
    }
    
    // Añadido para propiedadesHipotecadasJugador
    Casilla getCasilla(){
        return casilla;
    }
    
    int getAlquilerBase(){
        return alquilerBase;
    }
    
    float getFactorRevalorizacion(){
        return factorRevalorizacion;
    }
    
    int getHipotecaBase(){
        return hipotecaBase;
    } 
      
    boolean getHipotecada(){
        return hipotecada;
    }
    
    // Cambiada visibilidad para el controlador
    public String getNombre(){
        return nombre;
    }
  
    int getPrecioEdificar(){
        return precioEdificar;
    }
    
    // Implementado por mí
    boolean propietarioEncarcelado(){
        return propietario.getEncarcelado();
    }
    
    void setCasilla(Casilla casilla){
        this.casilla = casilla;
    }
      
    public void setHipotecada(boolean hipotecada){
        this.hipotecada = hipotecada;
    }
    
    void setPropietario(Jugador propietario){
        this.propietario = propietario;
    }
    
    boolean tengoPropietario(){
        boolean tengo = false;
        if (propietario != null)
            tengo = true;
        return tengo;
    }
    
    @Override
    public String toString(){
        return "Título propiedad" + " \n nombre=" + nombre + " \n hipotecado=" +
                hipotecada + " \n alquiler base=" + Integer.toString(alquilerBase) + 
                " \n factor revalorización=" + factorRevalorizacion
                + " \n hipoteca base=" + Integer.toString(hipotecaBase) + " \n precio edificar="
                + Integer.toString(precioEdificar);
    }
}
