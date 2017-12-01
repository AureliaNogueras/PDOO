package modeloqytetet;

import java.util.ArrayList;

/**
 *
 * @author aurelia
 */
public class Jugador {
    private boolean encarcelado = false;
    private String nombre;
    private int saldo = 7500;
    private Sorpresa cartaLibertad; // Su tipo será SALIRCARCEL
    private Casilla casillaActual;
    private ArrayList<TituloPropiedad> propiedades;
    
    public Jugador(String nombre){
        this.nombre = nombre;
        encarcelado = false;
        saldo = 7500;
        cartaLibertad = null;
        casillaActual = null;
        propiedades = new ArrayList();
    }
    
    // Añadido para el controlador
    public int getSaldo(){
        return saldo;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    // Añadido el método para propiedadesHipotecadasJugador
    public ArrayList<TituloPropiedad> getPropiedades(){
        return propiedades;
    }
    
    public Casilla getCasillaActual(){
        return casillaActual;
    }
    
    public boolean getEncarcelado(){
        return encarcelado;
    }
    
    public boolean tengoPropiedades(){
        boolean tengo = false;
        if (!propiedades.isEmpty())
            tengo = true;
        return tengo;
    }
    
    // Modificados signos de modificarSaldo
    boolean actualizarPosicion(Casilla casilla){
        if (casilla.getNumeroCasilla() < casillaActual.getNumeroCasilla())
            modificarSaldo(Qytetet.SALDO_SALIDA);
        boolean tienePropietario = false;
        setCasillaActual(casilla);
        if (casilla.soyEdificable()){
            tienePropietario = casilla.tengoPropietario();
            if (tienePropietario){
                boolean encarcelado = casilla.propietarioEncarcelado();
                if (!encarcelado){
                    int costeAlquiler = casilla.cobrarAlquiler();
                    modificarSaldo(-costeAlquiler);
                }
            }
        }else if (casilla.getTipo() == TipoCasilla.IMPUESTO){
            int coste = casilla.getCoste();
            modificarSaldo(-coste);
        }
        return tienePropietario;    
    }
    
    boolean comprarTitulo(){
        boolean puedoComprar = false;
        if (casillaActual.soyEdificable()){
          boolean tengoPropietario = casillaActual.tengoPropietario();
          if (!tengoPropietario){
              int costeCompra = casillaActual.getCoste();
              if (costeCompra <= saldo){
                  TituloPropiedad titulo = casillaActual.asignarPropietario(this);
                  propiedades.add(titulo);
                  this.modificarSaldo(-costeCompra);
                  puedoComprar = true;
              }
          }
        }
        return puedoComprar;
    }
    
    Sorpresa devolverCartaLibertad(){
        Sorpresa carta = cartaLibertad;
        cartaLibertad = null;
        return carta;
    }
    
    void irACarcel(Casilla casilla){
        setCasillaActual(casilla);
        setEncarcelado(true);
    }
    
    void modificarSaldo(int cantidad){
        saldo += cantidad;
    }
    
    int obtenerCapital(){
        int capital = saldo;
        for (TituloPropiedad t: propiedades){
            capital += t.getCasilla().getCoste() + (t.getCasilla().getNumCasas() + t.getCasilla().getNumHoteles())*t.getPrecioEdificar();
            if (t.getHipotecada())
                capital -= t.getHipotecaBase();
        }
        return capital;
    }
    
    // Ahora lo hago, revisar el de qytetet
    ArrayList<TituloPropiedad> obtenerPropiedadesHipotecadas(boolean hipotecada){
        ArrayList<TituloPropiedad> titulos = new ArrayList();
        for (TituloPropiedad t: propiedades){
            if (t.getHipotecada() == hipotecada)
                titulos.add(t);
        }
        return titulos;
    }
    
    void pagarCobrarPorCasaYHotel(int cantidad){
        int numeroTotal = cuantasCasasHotelesTengo();
        modificarSaldo (numeroTotal*cantidad);
    }
    
    boolean pagarLibertad(int cantidad){
        boolean tengoSaldo = tengoSaldo(cantidad);
        if (tengoSaldo){
            modificarSaldo(cantidad);
        }
        return tengoSaldo;
    }
    
    boolean puedoEdificarCasa(Casilla casilla){
        boolean esMia = esDeMiPropiedad(casilla);
        boolean tengoSaldo = false;
        if(esMia){
            int costeEdificarCasa = casilla.getPrecioEdificar();
            tengoSaldo = tengoSaldo(costeEdificarCasa);
        }
        return esMia && tengoSaldo;
    }
    
    boolean puedoEdificarHotel(Casilla casilla){
        boolean esMia = esDeMiPropiedad(casilla);
        boolean tengoSaldo = false;
        if(esMia){
            int costeEdificarHotel = casilla.getPrecioEdificar();
            tengoSaldo = tengoSaldo(costeEdificarHotel);
        }
        return esMia && tengoSaldo;
    }
    
    boolean puedoHipotecar(Casilla casilla){
        boolean esMia = esDeMiPropiedad(casilla);
        return esMia;
    }
    
    boolean puedoPagarHipoteca(Casilla casilla){
        boolean puedoPagar = tengoSaldo(casilla.getCosteHipoteca());
        return puedoPagar;
    }
    
    boolean puedoVenderPropiedad(Casilla casilla){
        boolean esMia = esDeMiPropiedad(casilla);
        boolean hipotecada = casilla.estaHipotecada();
        return esMia && !hipotecada;
    }
    
    void setCartaLibertad(Sorpresa carta){
        cartaLibertad = carta;
    }
    
    void setCasillaActual(Casilla casilla){
        casillaActual = casilla;
    }
    
    void setEncarcelado(boolean encarcelado){
        this.encarcelado = encarcelado;
    }
    
    boolean tengoCartaLibertad(){
        boolean tengo = false;
        if (cartaLibertad != null)
            tengo = true;
        return tengo;
    }
    
    void venderPropiedad(Casilla casilla){
        int precioVenta = casilla.venderTitulo();
        modificarSaldo(precioVenta);
        eliminarDeMisPropiedades(casilla);
    }
    
    private int cuantasCasasHotelesTengo(){
        int total= 0;
        for (TituloPropiedad t: propiedades){
            total += t.getCasilla().getNumCasas() + t.getCasilla().getNumHoteles();
        }
        return total;
    }
    
    private void eliminarDeMisPropiedades(Casilla casilla){
        for (TituloPropiedad t: propiedades){
            if (t == casilla.getTitulo())
                propiedades.remove(t);
        }
    }
    
    private boolean esDeMiPropiedad(Casilla casilla){
        boolean propiedad = false;
        for (TituloPropiedad t: propiedades){
            if (t == casilla.getTitulo())
                propiedad = true;
        }
        return propiedad;
    }
    
    private boolean tengoSaldo(int cantidad){
        boolean tengo = false;
        if (saldo >= cantidad)
            tengo = true;
        return tengo;
    }
    
    @Override
    public String toString(){
        return "Jugador {" + "nombre =" + nombre + ", saldo =" + Integer.toString(saldo) + ", encarcelado =" +
                encarcelado + ", carta de libertad =" + cartaLibertad + ", casilla actual =" +
                casillaActual + ", propiedades =" + propiedades + "}\n";
    }
}
