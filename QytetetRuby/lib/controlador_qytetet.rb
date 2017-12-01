#encoding: utf-8

module InterfazTextualQytetet
  require_relative "qytetet.rb"
  require_relative "vista_textual_qytetet.rb"
  require_relative "dado.rb"
  require_relative "jugador.rb"
  require_relative "sorpresa.rb"
  require_relative "tipo_sorpresa.rb"
  require_relative "tablero.rb"
  require_relative "casilla.rb"
  require_relative "tipo_casilla.rb"
  require_relative "titulo_propiedad.rb"
  require_relative "metodo_salir_carcel.rb"
  
  class ControladorQytetet
    
    def initialize
      @juego = nil
      @jugador = nil
      @casilla = nil
      @vista = VistaTextualQytetet.new
    end
  
    def desarrolloJuego
      bancarrota = false
      tienePropietario = false
        
      while (!bancarrota)
        @vista.mostrar("Es el turno de #{@jugador.getNombre} \n")
        @casilla = @jugador.getCasillaActual
        @vista.mostrar("Su posición actual es: #{@casilla} \n")
        libre = !@jugador.getEncarcelado
            
        if (!libre)
          @vista.mostrar("El jugador está en la cárcel :( \n")
                
          if (salirCarcel)
            @vista.mostrar("El jugador vuelve a ser libre \n")
          else
            @vista.mostrar("El jugador no ha conseguido salir de prisión \n")
          end
          @vista.mostrar("Pulsa \'intro\' para continuar")
          pausa
        end
            
        if (libre)
          tienePropietario = @juego.jugar
          @jugador = @juego.getJugadorActual
          @casilla = @jugador.getCasillaActual
          @vista.mostrar("Se tira el dado \n")
          @vista.mostrar("El jugador #{@jugador.getNombre} ha caído en la casilla #{@casilla} \n")
          @vista.mostrar("Pulsa \'intro\' para continuar")
          pausa
                
          eleccionSegunCasilla(@casilla.tipo,tienePropietario)
          gestionInmobiliaria
            
        end
            
        if (@jugador.getSaldo > 0)
          siguienteTurno
          bancarrota = finJuego
        else
          bancarrota = finJuego
        end
      end
    end
    
    
    def finJuego
      fin = false
      if (@jugador.getSaldo<=0)
        @vista.mostrar(@juego.obtenerRanking)
        fin = true
        @vista.mostrar("El juego ha terminado \n");
      end
      fin
    end

    def siguienteTurno
      @jugador = @juego.siguienteJugador
    end
    
    def salirCarcel
      opcion = @vista.menuSalirCarcel
      libre = false
      metodo = nil
      if (opcion == 0)
        metodo = ModeloQytetet::MetodoSalirCarcel::TIRANDODADO
      elsif (opcion == 1)
        metodo = ModeloQytetet::MetodoSalirCarcel::PAGANDOLIBERTAD
      end
        
      libre = @juego.intentarSalirCarcel(metodo)
      libre
    end
    
    def eleccionSegunCasilla(tipo, tienePropietario)
        
        if (@jugador.getSaldo>0 and !@jugador.getEncarcelado)
            if (@casilla.tipo == ModeloQytetet::TipoCasilla::CALLE)
              if(!tienePropietario)
                if(@vista.elegirQuieroComprar)
                  if(@juego.comprarTituloPropiedad)
                    @jugador = @juego.getJugadorActual
                    @vista.mostrar("Se ha realizado la compra \n")
                  else
                    @vista.mostrar("No se ha podido comprar \n")
                  end
                end
              end
            elsif (@casilla.tipo == ModeloQytetet::TipoCasilla::SORPRESA)
              tienePropietario = @juego.aplicarSorpresa
              @jugador = @juego.getJugadorActual
              @casilla = @jugador.getCasillaActual
              @vista.mostrar("Se coge una carta sorpresa: #{@juego.getCartaActual} \n")
              @vista.mostrar("La casilla actual es #{@casilla} \n")
              @vista.mostrar("El estado del jugador tras la sorpresa es #{@jugador} \n")
              if (@jugador.getSaldo>0 and !@jugador.getEncarcelado and @casilla.tipo == ModeloQytetet::TipoCasilla::CALLE)
                if(!tienePropietario)
                  if(@vista.elegirQuieroComprar)
                    if(@juego.comprarTituloPropiedad)
                      @jugador = @juego.getJugadorActual
                      @vista.mostrar("Se ha realizado la compra \n")
                      @vista.mostrar("#{@jugador.getPropiedades()}\n")
                    else
                      @vista.mostrar("No se ha podido comprar \n")
                    end
                  end
                end
              end
            end
        end
    end
     
    def gestionInmobiliaria
        if (!@jugador.getEncarcelado() and @jugador.getSaldo() > 0 and @jugador.tengoPropiedades())
          opcion = -1
          while(opcion != 0)
            opcion = @vista.menuGestionInmobiliaria
            if (opcion == 5)
              @casilla = elegirPropiedad(@juego.propiedadesHipotecadasJugador(true))
            elsif (opcion != 0)
              @casilla = elegirPropiedad(@juego.propiedadesHipotecadasJugador(false))
            end
            
            menuInmobiliario(opcion)
            @jugador = @juego.getJugadorActual
            @vista.mostrar("El estado del jugador tras la acción es #{@jugador}\n")
          end
        end
    end
    
    def menuInmobiliario(opcion)
      case opcion
        when 1
          @juego.edificarCasa(@casilla)
        when 2
          @juego.edificarHotel(@casilla)
        when 3
          @juego.venderPropiedad(@casilla)
        when 4
          @juego.hipotecarPropiedad(@casilla)
        when 5
          @juego.cancelarHipoteca(@casilla)
      else
        
      end
    end
    
    def inicializacionJuego
      @juego = ModeloQytetet::Qytetet.instance  
      nombres = Array.new
      nombres = @vista.obtener_nombre_jugadores
      @juego.inicializarJuego(nombres)
      @jugador = @juego.getJugadorActual
      @casilla = @jugador.getCasillaActual
      @vista.mostrar("#{@juego}")
    end
    
    def pausa
      puts "Pulsa \'intro\' para continuar"
      gets
    end
    
    #   # --------------------el siguiente método va en ControladorQytetet
    def elegir_propiedad(propiedades) # lista de propiedades a elegir
      @vista.mostrar("\tCasilla\tTitulo");
        
      listaPropiedades= Array.new
      for prop in propiedades  # crea una lista de strings con numeros y nombres de propiedades
        propString= prop.numeroCasilla.to_s+' '+prop.titulo.nombre; 
        listaPropiedades<<propString
      end
      seleccion=@vista.menu_elegir_propiedad(listaPropiedades)  # elige de esa lista del menu
      propiedades.at(seleccion)
    end
   
    
    def main
      inicializacionJuego
      desarrolloJuego
    end
 
  end
  c = ControladorQytetet.new
  c.main
end
