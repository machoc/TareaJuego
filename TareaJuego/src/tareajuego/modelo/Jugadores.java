
package tareajuego.modelo;

import java.util.ArrayList;


public class Jugadores {

    public Jugadores() {
        jugadores = new ArrayList<>();
    }
    
    public void agregarJugador(Jugador jugadorNuevo){
        jugadores.add(jugadorNuevo);
    }
    
    public Jugador recuperarDatos(int p){
        return jugadores.get(p);
    }
    
    public int cantidadJugadores(){
        return jugadores.size();
    }
    
    public void guardarJugadores(){
        
    }
    
    
    
    //Atributos
    private ArrayList<Jugador> jugadores;
    
}