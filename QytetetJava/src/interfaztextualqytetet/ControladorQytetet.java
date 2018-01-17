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
            boolean libre = !jugador.getEncarcelado();
            
            if (!libre){
                vista.mostrar("El jugador está en la cárcel :( \n");
                
                if (salirCarcel())
                    vista.mostrar("El jugador vuelve a ser libre \n");
                else
                    vista.mostrar("El jugador no ha conseguido salir de prisión \n");
            
                vista.mostrar("Pulsa \'intro\' para continuar");
                System.in.read();
            }
            
            if (libre){
                tienePropietario = juego.jugar();
                jugador = juego.getJugadorActual();
                casilla = jugador.getCasillaActual();
                vista.mostrar("Se tira el dado \n");
                vista.mostrar("El jugador " + jugador.getNombre() + " ha caído en la casilla \n" + casilla + "\n");
                vista.mostrar("Pulsa \'intro\' para continuar");
                System.in.read();
                
                eleccionSegunCasilla(casilla.getTipo(),tienePropietario);
                gestionInmobiliaria();
            
            }
            
            if (jugador.getSaldo() > 0){
                siguienteTurno();
                bancarrota = finJuego();
            }else
                bancarrota = finJuego();
        }
    }
    
    
    private boolean finJuego(){
        boolean fin = false;
        if (jugador.getSaldo()<=0){
            vista.mostrar(juego.obtenerRanking().toString());
            fin = true;
            vista.mostrar("El juego ha terminado \n");
        }
        return fin;
    }

    private void siguienteTurno(){
        jugador = juego.siguienteJugador();
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
            if (casilla.getTipo() == TipoCasilla.CALLE){
                if(!tienePropietario){
                    if(vista.elegirQuieroComprar()){
                        if(juego.comprarTituloPropiedad()){
                            jugador = juego.getJugadorActual();
                            vista.mostrar("Se ha realizado la compra. Las propiedades del jugador son: \n");
                            vista.mostrar(jugador.getPropiedades().toString());
                        }else
                            vista.mostrar("No se ha podido comprar \n");
                    }
                }
  
            }else if (casilla.getTipo() == TipoCasilla.SORPRESA){
                tienePropietario = juego.aplicarSorpresa();
                jugador = juego.getJugadorActual();
                casilla = jugador.getCasillaActual();
                System.out.println("Se coge una carta sorpresa: " + juego.getCartaActual());
                System.out.println("La casilla actual es \n" + casilla + "\n");
                System.out.println("El estado del jugador tras la sorpresa es \n" + jugador + "\n");
                if (jugador.getSaldo()>0 && !jugador.getEncarcelado() && casilla.getTipo() == TipoCasilla.CALLE){
                    if(!tienePropietario){
                        if(vista.elegirQuieroComprar()){
                            if(juego.comprarTituloPropiedad()){
                                jugador = juego.getJugadorActual();
                                vista.mostrar("Se ha realizado la compra. Las propiedades del jugador son: \n");
                                vista.mostrar(jugador.getPropiedades().toString());
                            }else
                                vista.mostrar("No se ha podido comprar \n");
                        }
                    }
                }
            }
        }
    }
     
    private void gestionInmobiliaria(){
        if (!jugador.getEncarcelado() && jugador.getSaldo() > 0 && jugador.tengoPropiedades())
        {
            int opcion = -1;
            while(opcion != 0){
                opcion = vista.menuGestionInmobiliaria();
                if (opcion == 5){
                    ArrayList<Casilla> propiedades = juego.propiedadesHipotecadasJugador(true);
                    vista.mostrar(propiedades.toString());
                    casilla = elegirPropiedad(propiedades);
                }else if (opcion != 0){
                    ArrayList<Casilla> propiedades = juego.propiedadesHipotecadasJugador(false);
                    vista.mostrar(propiedades.toString());
                    casilla = elegirPropiedad(propiedades);
                }
                
            
                menuInmobiliario(opcion);
                jugador = juego.getJugadorActual();
                vista.mostrar("El estado del jugador tras la acción es: \n"); 
                vista.mostrar(jugador.toString());
            }
        }
    }
    
    private void menuInmobiliario(int opcion){
        switch (opcion) {
            case 1:
                if(juego.edificarCasa((Calle)casilla))
                    vista.mostrar("Se ha edificado la casa \n");
                else
                    vista.mostrar("No se ha podido edificar la casa \n");
                break;
            case 2:
                if(juego.edificarHotel((Calle)casilla))
                    vista.mostrar("Se ha edificado el hotel \n");
                else
                    vista.mostrar("No se ha edificado el hotel \n");
                break;
            case 3:
                if(juego.venderPropiedad((Calle)casilla))
                    vista.mostrar("Se ha vendido la propiedad \n");
                else
                    vista.mostrar("No se ha vendido la propiedad \n");
                break;
            case 4:
                if(juego.hipotecarPropiedad((Calle)casilla))
                    vista.mostrar("Se ha hipotecado la propiedad \n");
                else
                    vista.mostrar("No se ha podido hipotecar la propiedad \n");
                break;
            case 5:
                if(juego.cancelarHipoteca((Calle)casilla))
                    vista.mostrar("Se ha cancelado la hipoteca \n");
                else
                    vista.mostrar("No se ha podido cancelar la hipoteca \n");
                break;
            case 0:
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
        for ( Casilla c: propiedades) {
            if(c != null){
                listaPropiedades.add( "\t"+c.getNumeroCasilla()+"\t"+((Calle) c).getTitulo().getNombre()); 
            }
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
