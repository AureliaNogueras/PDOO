#encoding: utf-8

module ModeloQytetet
  class Casilla
    def initialize(nc, c, t, ti)
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
      @titulo.setPropietario(jugador)
      @titulo
    end
    
    def calcularValorHipoteca
      hipotecaBase = @titulo.hipotecaBase
      cantidadRecibida = hipotecaBase + @numCasas*0.5*hipotecaBase + @numHoteles*hipotecaBase
      cantidadRecibida
    end
    
    # Devuelve el precio que hay que pagar para deshipotecar la propiedad
    def cancelarHipoteca
      @titulo.hipotecada = false
      valor = calcularValorHipoteca + (0.1*calcularValorHipoteca)
      valor
    end
    
    def cobrarAlquiler
      costeAlquilerBase = @titulo.alquilerBase
      costeAlquiler = costeAlquilerBase + @numCasas*0.5 + @numHoteles*2
      @titulo.cobrarAlquiler(costeAlquiler)
      costeAlquiler
    end
    
    def edificarCasa
      @numCasas = @numCasas + 1
      costeEdificarCasa = @titulo.precioEdificar
      costeEdificarCasa
    end
    
    def edificarHotel
      @numHoteles = @numHoteles + 1
      costeEdificarHotel = @titulo.precioEdificar
      costeEdificarHotel
    end
    
    def estaHipotecada
      hipotecada = @titulo.hipotecada
      hipotecada
    end
    
    # Devuelve lo que cuesta deshipotecar
    def getCosteHipoteca
      cancelarHipoteca
    end
    
    def getPrecioEdificar
      @titulo.precioEdificar
    end
    
    def hipotecar
      @titulo.hipotecada = true
      cantidadRecibida = calcularValorHipoteca
      cantidadRecibida
    end
    
    def precioTotalComprar
      precio = @coste + (@numCasas + @numHoteles)*@titulo.precioEdificar
      precio = precio + (precio*@titulo.factorRevalorizacion)
      precio
    end
    
    def propietarioEncarcelado
      encarcelado = @titulo.propietarioEncarcelado
      encarcelado
    end
    
    def sePuedeEdificarCasa
      @numCasas < 4
    end
    
    def sePuedeEdificarHotel
      @numHoteles < 4 and @numCasas == 4
    end
    
    def soyEdificable
      edificable = false
      if (tipo == TipoCasilla::CALLE)
        edificable = true
      end
      edificable
    end
    
    def tengoPropietario
      tengoPropietario = @titulo.tengoPropietario
      tengoPropietario
    end
    
    def venderTitulo
      @titulo.setPropietario(nil)
      numHoteles = 0
      numCasas = 0
      precioTotalComprar
    end
   
    def to_s
      "Casilla: \n número de casilla= #{@numeroCasilla} \n coste= #{@coste}
      \n número de casas= #{@numCasas} \n número de hoteles= #{@numHoteles}
      \n tipo de casilla= #{@tipo} \n título de propiedad= #{@titulo} \n"
    end
  end
end
