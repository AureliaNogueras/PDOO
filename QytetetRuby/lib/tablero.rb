#encoding: utf-8

module ModeloQytetet
  class Tablero
    
    def initialize
      @casillas = Array.new
      @carcel = nil
      inicializar
    end
    
    def esCasillaCarcel(numeroCasilla)
      comprobacion = false
      if (numeroCasilla == @carcel.numeroCasilla)
        comprobacion = true
      end
      comprobacion
    end
    
    attr_reader :carcel
    
    def obtenerCasillaNumero(numeroCasilla)
      return @casillas.at(numeroCasilla)
    end
    
    def obtenerNuevaCasilla(casilla, desplazamiento)
      nuevoNumCasilla = (casilla.numeroCasilla + desplazamiento) % @casillas.size
      obtenerCasillaNumero(nuevoNumCasilla)
    end
    
    def inicializar
        # Casillas
        @casillas << Casilla.newCasillaNoCalle(0,TipoCasilla::SALIDA)
        
        t = TituloPropiedad.new("Avenida Cervantes",50,-12,200,250)
        @casillas << Casilla.newCasillaCalle(1,200,t)
        
        t = TituloPropiedad.new("Catedral",75,11,600,500);
        @casillas << Casilla.newCasillaCalle(2,400,t)
        
        @casillas << Casilla.newCasillaNoCalle(3,TipoCasilla::SORPRESA)
        
        t = TituloPropiedad.new("Alhambra",100,10,1000,750)
        @casillas << Casilla.newCasillaCalle(4,700,t)
        
        t = TituloPropiedad.new("Puerta Real",55,13,550,300)
        @casillas << Casilla.newCasillaCalle(5,200,t)
        
        @casillas << Casilla.newCasillaNoCalle(6,TipoCasilla::IMPUESTO)
        
        t = TituloPropiedad.new("Corral del Carbón",80,17,500,400)
        @casillas << Casilla.newCasillaCalle(7,300,t)
        
        t = TituloPropiedad.new("Generalife",85,-20,900,450)
        @casillas << Casilla.newCasillaCalle(8,450,t)
        
        @carcel = Casilla.newCasillaNoCalle(9,TipoCasilla::CARCEL)
        @casillas << @carcel
        
        t = TituloPropiedad.new("Calle Mesones",55,-10,300,350)
        @casillas << Casilla.newCasillaCalle(10,200,t)
        
        @casillas << Casilla.newCasillaNoCalle(11,TipoCasilla::SORPRESA)
        
        t = TituloPropiedad.new("Albaicín",65,-16,400,550)
        @casillas << Casilla.newCasillaCalle(12,500,t)
        
        t = TituloPropiedad.new("Sierra Nevada",95,-14,700,700)
        @casillas << Casilla.newCasillaCalle(13,600,t)
        
        @casillas << Casilla.newCasillaNoCalle(14,TipoCasilla::PARKING)
        
        t = TituloPropiedad.new("Paseo de los Tristes",70,11,600,650)
        @casillas << Casilla.newCasillaCalle(15,600,t)
        
        @casillas << Casilla.newCasillaNoCalle(16,TipoCasilla::JUEZ)
        
        t = TituloPropiedad.new("Los Italianos",60,-15,800,600)
        @casillas << Casilla.newCasillaCalle(17,350,t)
        
        @casillas << Casilla.newCasillaNoCalle(18,TipoCasilla::SORPRESA)
        
        t = TituloPropiedad.new("Avenida de la Constitución",65,10,150,550)
        @casillas << Casilla.newCasillaCalle(19,400,t)
        
    end
    
    def to_s
      "Tablero: \n casillas= #{@casillas} \n cárcel= #{@carcel}"
    end
  end
end
