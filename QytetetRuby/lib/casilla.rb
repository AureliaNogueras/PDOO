#encoding: utf-8

module ModeloQytetet
  class Casilla
    def initialize(c, nc, t, ti)
      @coste = c
      @numeroCasilla = nc
      @numCasas = 0
      @numHoteles = 0
      @tipo = t
      @titulo = ti
    end
    
    def self.newCasillaNoCalle(nc,t)
      new(nc,0,t,nil)
    end
    
    def self.newCasillaCalle(nc,c,ti)
      new(nc,c,TipoCasilla::CALLE,ti)
    end
    
    attr_reader :numeroCasilla, :coste, :numCasas, :numHoteles, :tipo, :titulo
   
    attr_writer :numCasas, :numHoteles, :titulo
    
    private :titulo=
    
    def asignarPropietario(jugador)
      
    end
    
    def calcularValorHipoteca
      
    end
    
    def cancelarHipoteca
      
    end
    
    def cobrarAlquiler
      
    end
    
    def edificarCasa
      
    end
    
    def edificarHotel
      
    end
    
    def estaHipotecada
      
    end
    
    def getCosteHipoteca
      
    end
    
    def getPrecioEdificar
      
    end
    
    def hipotecar
      
    end
    
    def precioTotalComprar
      
    end
    
    def propietarioEncarcelado
      
    end
    
    def sePuedeEdificarCasa
      
    end
    
    def sePuedeEdificarHotel
      
    end
    
    def soyEdificable
      
    end
    
    def tengoPropietario
      
    end
    
    def venderTitulo
      
    end
   
    def to_s
      "Casilla: \n número de casilla= #{@numeroCasilla} \n coste= #{@coste}
      \n número de casas= #{@numCasas} \n número de hoteles= #{@numHoteles}
      \n tipo de casilla= #{@tipo} \n título de propiedad= #{@titulo} \n"
    end
    
    private 
    def asignarTituloPropiedad
      
    end
  end
end
