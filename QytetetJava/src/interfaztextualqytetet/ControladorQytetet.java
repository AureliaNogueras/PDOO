package interfaztextualqytetet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
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
    private static final Scanner in = new Scanner (System.in);
    
    public void desarrolloJuego() throws IOException{
        boolean bancarrota = false;
        boolean tienePropietario;
        
        while (!bancarrota){
            vista.mostrar("Es el turno de " + jugador.getNombre() + "\n");
            casilla = jugador.getCasillaActual();
            vista.mostrar("Su posición actual es: \n" + casilla + "\n");
        
            if (jugador.getEncarcelado()){
                vista.mostrar("El jugador está en la cárcel :( \n");
                
                if (salirCarcel())
                    vista.mostrar("El jugador vuelve a ser libre \n");
                else
                    vista.mostrar("El jugador no ha conseguido salir de prisión \n");
            
                vista.mostrar("Pulsa \'intro\' para continuar");
                System.in.read();
            }
        
            tienePropietario = juego.jugar();
            jugador = juego.getJugadorActual();
            casilla = jugador.getCasillaActual();
            vista.mostrar("Se tira el dado \n");
            vista.mostrar("El jugador " + jugador.getNombre() + " ha caído en la casilla \n" + casilla + "\n");
            vista.mostrar("Pulsa \'intro\' para continuar");
            System.in.read();
            
            eleccionSegunCasilla(casilla.getTipo(),tienePropietario);
            gestionInmobiliaria();
            
            if (jugador.getSaldo() > 0)
                siguienteTurno();
            else{
                vista.mostrar(juego.obtenerRanking().toString());
                bancarrota = true;
            }
        }
    }

    private void siguienteTurno(){
        jugador = juego.siguienteJugador();
        if (jugador.getSaldo() <= 0){
            vista.mostrar(juego.obtenerRanking().toString());
        }
    }
    
    private boolean salirCarcel(){
        int opcion = vista.menuSalirCarcel();
        boolean libre = false;
        MetodoSalirCarcel metodo = null;
        if (opcion == 0)
            metodo = MetodoSalirCarcel.TIRANDODADO;
        else if (opcion == 1)
            metodo = MetodoSalirCarcel.PAGANDOLIBERTAD;
        
        libre = juego.intentarSalirCarcel(metodo);
        return libre;
    }
    
    private void eleccionSegunCasilla(TipoCasilla tipo, boolean tienePropietario){
        if (jugador.getSaldo()>0 && !jugador.getEncarcelado()){
            if (tipo == TipoCasilla.CALLE){
                if(!tienePropietario){
                    if(vista.elegirQuieroComprar()){
                        if(juego.comprarTituloPropiedad()){
                            jugador = juego.getJugadorActual();
                            vista.mostrar("Se ha realizado la compra \n");
                        }else
                            vista.mostrar("No se ha podido comprar \n");
                    }
                }
  
            }else if (tipo == TipoCasilla.SORPRESA){
                tienePropietario = juego.aplicarSorpresa();
                jugador = juego.getJugadorActual();
                casilla = jugador.getCasillaActual();
                vista.mostrar("Se coge una carta sorpresa: " + juego.getCartaActual());
                vista.mostrar("La casilla actual es \n" + casilla + "\n");
                vista.mostrar("El estado del jugador tras la sorpresa es \n" + jugador + "\n");
                if (casilla.getTipo() == TipoCasilla.CALLE){
                    eleccionSegunCasilla(TipoCasilla.CALLE, tienePropietario);
                }
            }
        }
    }
     
    private void gestionInmobiliaria(){
        if (!jugador.getEncarcelado() && jugador.getSaldo() > 0 && jugador.tengoPropiedades())
        {
            int opcion = vista.menuGestionInmobiliaria();
            if (opcion == 5)
                casilla = elegirPropiedad(juego.propiedadesHipotecadasJugador(true));
            else if (opcion != 1 && opcion != 0)
                casilla = elegirPropiedad(juego.propiedadesHipotecadasJugador(false));
                
            while (opcion != 0){
                menuInmobiliario(opcion);
                jugador = juego.getJugadorActual();
                vista.mostrar("El estado del jugador tras la acción es \n" + jugador + "\n");
            }
        }
    }
    
    private void menuInmobiliario(int opcion){
        switch (opcion) {
            case 1:
                juego.edificarCasa(casilla);
                break;
            case 2:
                juego.edificarHotel(casilla);
                break;
            case 3:
                juego.venderPropiedad(casilla);
                break;
            case 4:
                juego.hipotecarPropiedad(casilla);
                break;
            case 5:
                juego.cancelarHipoteca(casilla);
                break;
            default:
                if (jugador.getSaldo() <= 0)
                    vista.mostrar(juego.obtenerRanking().toString());
                break; 
        }
    }
    
    public void inicializacionJuego() throws IOException{
        juego = Qytetet.getInstance();
        vista = new VistaTextualQytetet();
        ArrayList<String> nombres = new ArrayList();
        nombres = vista.obtenerNombreJugadores();
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
    
    public static void main(String[] args) throws IOException {
        ControladorQytetet cq = new ControladorQytetet();
        cq.inicializacionJuego();
        cq.desarrolloJuego();
    }
 
}
