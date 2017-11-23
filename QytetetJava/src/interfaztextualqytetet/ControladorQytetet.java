package interfaztextualqytetet;

import java.io.IOException;
import java.util.ArrayList;
import modeloqytetet.*;

/**
 *
 * @author aurelia
 */
public class ControladorQytetet {
    private Qytetet juego;
    private Jugador jugador;
    private Casilla casilla;
    private VistaTextualQytetet vista;
    
    public void desarrolloJuego() throws IOException{
        boolean libre = true;
        System.out.println(jugador);
        System.out.println(casilla);
        if (jugador.getEncarcelado()){
            vista.mostrar("El jugador está en la cárcel :( \n");
            int opcion = vista.menuSalirCarcel();
            MetodoSalirCarcel metodo = null;
            if (opcion == 0)
                metodo = MetodoSalirCarcel.TIRANDODADO;
            else if (opcion == 1)
                metodo = MetodoSalirCarcel.PAGANDOLIBERTAD;
            libre = juego.intentarSalirCarcel(metodo);
            vista.mostrar("Pulsa \'intro\' para continuar");
            System.in.read();
        }
        
        // Se juega
        if (libre){
            boolean tienePropietario = juego.jugar();
            jugador = juego.getJugadorActual();
            casilla = jugador.getCasillaActual();
            System.out.println(casilla);
            vista.mostrar("Pulsa \'intro\' para continuar");
            System.in.read();
            if (jugador.getSaldo() <= 0){
            
            }else{
                if (!jugador.getEncarcelado()){
                    TipoCasilla tipo = casilla.getTipo();
                    if (tipo == TipoCasilla.CALLE){
                        if(tienePropietario){
                            boolean compra = vista.elegirQuieroComprar();
                            boolean compraConfirmada = false;
                            if (compra)
                                compraConfirmada = juego.comprarTituloPropiedad();
                            if (!compra || !compraConfirmada){
                            // Completar
                            }
                        }
                    }else if (tipo == TipoCasilla.SORPRESA){
                        tienePropietario = juego.aplicarSorpresa();
                        jugador = juego.getJugadorActual();
                        casilla = jugador.getCasillaActual();
                        if (!jugador.getEncarcelado()){
                            if (jugador.getSaldo() > 0){
                                if (casilla.getTipo() == TipoCasilla.CALLE){
                                    if (tienePropietario){
                                        boolean compra2 = vista.elegirQuieroComprar();
                                        boolean compraConfirmada2 = false;
                                        if (compra2){
                                            compraConfirmada2 = juego.comprarTituloPropiedad();
                                        }
                                    }
                                }
                            }
                        }else{
                        
                        }
                    }
                }else{
                
                }
            }
        }else{
        // Se pasa de turno
            jugador = juego.siguienteJugador();
        }
    }
     
    public void inicializacionJuego() throws IOException{
        juego = Qytetet.getInstance();
        ArrayList<String> nombres = vista.obtenerNombreJugadores();
        juego.inicializarJuego(nombres);
        jugador = juego.getJugadorActual();
        casilla = jugador.getCasillaActual();
        System.out.println(juego);
        vista.mostrar("Pulsa \'intro\' para continuar");
        System.in.read();
    }
    
    private Casilla elegirPropiedad(ArrayList<Casilla> propiedades){ 
    //este método toma una lista de propiedades y genera una lista de strings, con el número y nombre de las propiedades
    //luego llama a la vista para que el usuario pueda elegir.
        vista.mostrar("\tCasilla\tTítulo");
        int seleccion;
        ArrayList<String> listaPropiedades= new ArrayList();
        for ( Casilla casilla: propiedades) {
            listaPropiedades.add( "\t"+casilla.getNumeroCasilla()+"\t"+casilla.getTitulo().getNombre()); 
        }
        seleccion=vista.menuElegirPropiedad(listaPropiedades);  
        return propiedades.get(seleccion);
    }
 
}
