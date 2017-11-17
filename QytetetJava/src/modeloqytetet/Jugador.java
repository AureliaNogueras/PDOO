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
                    modificarSaldo(costeAlquiler);
                }
            }
        }else if (casilla.getTipo() == TipoCasilla.IMPUESTO){
            int coste = casilla.getCoste();
            modificarSaldo(coste);
        }
        return tienePropietario;    
    }
    
    //boolean comprarTitulo(){}
    
    Sorpresa devolverCartaLibertad(){
        Sorpresa carta = cartaLibertad;
        cartaLibertad = null;
        return carta;
    }
    
    void irACarcel(Casilla casilla){}
    
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
    
    void pagarCobrarPorCasaYHotel(int cantidad){}
    
    //boolean pagarLibertad(int cantidad = PRECIO_LIBERTAD){}
    
    //boolean puedoEdificarCasa(Casilla casilla){}
    
    //boolean puedoEdificarHotel(Casilla casilla){}
    
    //boolean puedoHipotecar(Casilla casilla){}
    
    //boolean puedoPagarHipoteca(Casilla casilla){}
    
    boolean puedoVenderPropiedad(Casilla casilla){
        return esDeMiPropiedad(casilla) && !casilla.getTitulo().getHipotecada();
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
    
    void venderPropiedad(Casilla casilla){}
    
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
