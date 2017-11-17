#encoding: utf-8

module ModeloQytetet
  class TituloPropiedad
    
    def initialize(n, aB, fR, hB, pE)
      @nombre = n
      @alquilerBase = aB
      @factorRevalorizacion = fR
      @hipotecaBase = hB
      @precioEdificar = pE
      @hipotecada = false
      @propietario = nil
      @casilla = nil
    end
    
    attr_reader :nombre, :hipotecada, :alquilerBase, :factorRevalorizacion, :hipotecaBase, :precioEdificar
    
    attr_writer :hipotecada
    
    def cobrarAlquiler(coste)
      
    end
    
    def propietarioEncarcelado
      
    end
    
    def setCasilla(casilla)
      @casilla = casilla
    end
      
    def setPropietario(propietario)
      @propietario = propietario
    end
    
    def tengoPropietario
      
    end
    
    def to_s
      "Título propiedad: \n nombre= #{@nombre} \n hipotecado= #{@hipotecada} 
      \n alquiler base= #{@alquilerBase} \n factor revalorización= #{@factorRevalorizacion}
      \n hipoteca base= #{@hipotecaBase} \n precio edificar= #{@precioEdificar} \n propietario= 
      #{@propietario} \n casilla= #{@casilla}"
    end
  end
end
