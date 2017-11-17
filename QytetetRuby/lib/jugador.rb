#encoding: utf-8

module ModeloQytetet
  class Jugador
    def initialize(nombre)
      @nombre = nombre
      @encarcelado = false
      @saldo = 7500
      @cartaLibertad = nil # Su tipo será SALIRCARCEL
      @casillaActual = nil
      @propiedades = Array.new
    end
    
    def getCasillaActual
      @casillaActual
    end
    
    def getEncarcelado
      @encarcelado
    end
    
    def tengoPropiedades
      
    end
    
    def actualizarPosicion(casilla)
      
    end
    
    def comprarTitulo
      
    end
    
    def devolverCartaLibertad
      
    end
    
    def irACarcel(casilla)
      
    end
    
    def modificarSaldo(cantidad)
      
    end
    
    def obtenerCapital
      
    end
    
    def obtenerPropiedadesHipotecadas(hipotecada)
      
    end
    
    def pagarCobrarPorCasaYHotel(cantidad)
      
    end
    
    def pagarLibertad(cantidad = PRECIOLIBERTAD)
      
    end
    
    def puedoEdificarCasa(casilla)
      
    end
    
    def puedoEdificarHotel(casilla)
      
    end
    
    def puedoHipotecar(casilla)
      
    end
    
    def puedoPagarHipoteca(casilla)
      
    end
    
    def puedoVenderPropiedad(casilla)
      
    end
    
    def setCartaLibertad(carta)
      @cartaLibertad = carta
    end
    
    def setCasillaActual(casilla)
      @casillaActual = casilla
    end
    
    def setEncarcelado(encarcelado)
      @encarcelado = encarcelado
    end
    
    def tengoCartaLibertad
      
    end
    
    def venderPropiedad(casilla)
      
    end
    
    private 
    
    def cuantasCasasHotelesTengo
      
    end
    
    def eliminarDeMisPropiedades(casilla)
      
    end
    
    def esDeMiPropiedad(casilla)
      
    end
    
    def tengoSaldo(cantidad)
      
    end
    
    public
    
    def to_s
      "Jugador \n nombre = #{@nombre} \n saldo = #{@saldo} \n encarcelado = #{@encarcelado} \n
      carta de libertad = #{@cartaLibertad} \n casilla actual = #{@casillaActual} \n 
      propiedades = #{@propiedades} \n"
    end
  end
end
