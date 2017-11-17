#encoding: utf-8

module ModeloQytetet
  
  require "singleton"
  
  class Qytetet
    
    include Singleton

    MAXJUGADORES = 4
    MAXCARTAS = 10
    MAXCASILLAS = 20
    PRECIOLIBERTAD = 200
    SALDOSALIDA = 1000
    
    def initialize
      @cartaActual = nil
      @mazo = Array.new
      @jugadores = Array.new
      @jugadorActual = nil
      @tablero = nil
      @dado = Dado.instance
    end
    
    def aplicarSorpresa
      
    end
    
    def cancelarHipoteca(casilla)
      
    end
    
    def comprarTituloPropiedad
      
    end
    
    def edificarCasa(casilla)
      
    end
    
    def edificarHotel(casilla)
      
    end
    
    def getCartaActual
      @cartaActual
    end
    
    def getJugadorActual
      @jugadorActual
    end
    
    def hipotecarPropiedad(casilla)
      
    end
    
    def inicializarJuego(nombres)
      
    end
    
    def intentarSalirCarcel(metodo)
      
    end
    
    def jugar
      
    end
    
    def obtenerRanking
      
    end
    
    def propiedadesHipotecadasJugador(hipotecadas)
      
    end
    
    def siguienteJugador
      
    end
    
    def venderPropiedad(casilla)
      
    end
    
    private
    
    def encarcelarJugador
      
    end
    
    def inicializarCartasSorpresa
      @mazo << Sorpresa.new("Han pillado alguno de tus chanchullos. Ve a la cárcel.",TipoSorpresa::IRACASILLA,@tablero.carcel.numeroCasilla)
      @mazo << Sorpresa.new("Un extraño tornado te arrastra hasta la casilla 13.",TipoSorpresa::IRACASILLA,13)
      @mazo << Sorpresa.new("El poder de la fuerza te atrae hasta la casilla 2.",TipoSorpresa::IRACASILLA,2)
      @mazo << Sorpresa.new("Quedas libre de la cárcel. No es aplicable en la vida real",TipoSorpresa::SALIRCARCEL,0)
        
      @mazo << Sorpresa.new("Al fin han reconocido tu talento y tu belleza... Recibes dinero.",TipoSorpresa::PAGARCOBRAR,200)
      @mazo << Sorpresa.new("Pagas al banco con la esperanza de que no descubran lo que te traes entre manos.",TipoSorpresa::PAGARCOBRAR,-250)
        
      @mazo << Sorpresa.new("Recibes dinero por cada una de tus maravillosas propiedades.",TipoSorpresa::PORCASAHOTEL,150)
      @mazo << Sorpresa.new("No cumples la normativa, así que debes pagar por cada una de tus odiosas propiedades.",TipoSorpresa::PORCASAHOTEL,-175)
        
      @mazo << Sorpresa.new("Los demás jugadores sospechan de ti. Los sobornas para que no te delaten.",TipoSorpresa::PORJUGADOR,-100)
      @mazo << Sorpresa.new("El resto de jugadores te indemniza para comprar tu silencio.",TipoSorpresa::PORJUGADOR,100)
      @mazo.shuffle!
    end
    
    def inicializarJugadores(nombres)
      @nombres.each{ |j|
            j = Jugador.new(n)
            @jugadores << j
      } 
    end
    
    def inicializarTablero
      @tablero = Tablero.new
    end
    
    def salidaJugadores
      
    end
    
    public
    
    def to_s
        "Qytetet \n carta actual= #{@cartaActual} \n cartas sorpresa= #{@mazo} \n
                jugador actual= #{@jugadorActual} \n jugadores= #{@jugadores} \n tablero=
                #{@tablero} \n dado= #{@dado} \n"
    end
    
  end
end
