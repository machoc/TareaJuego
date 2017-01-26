
package tareajuego.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


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
    
    public void acomodarJugadores(){
        Collections.sort(jugadores, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Jugador j1 =(Jugador)o1;
                Jugador j2 =(Jugador)o2;
                return new Integer(j2.getPuntaje()).compareTo(new Integer(j1.getPuntaje()));
            }
        });
    }
    
    
    
    //Atributos
    private ArrayList<Jugador> jugadores;
    
}