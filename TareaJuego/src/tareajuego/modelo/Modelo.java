package tareajuego.modelo;

import java.util.Observable;
import javax.swing.table.TableModel;

public class Modelo extends Observable{
    public Modelo(){
        jugadores = new Jugadores();
        modeloTabla = new ModeloTabla(jugadores);
        vidas=3;
    }
    
    public void agregarJugador(Jugador jug){
        jugadores.agregarJugador(jug);
        actualizar(null);
    }
    
    
    public TableModel modeloTabla(){
        return modeloTabla;
    }
    
    public void setPuntaje(int puntaje){
        jugadores.recuperarDatos(jugadores.cantidadJugadores()-1).setPuntaje(puntaje);
        actualizar(String.valueOf(puntaje));
    }
    
    public void setVidas(int vid){
       vidas=vid;
        actualizar(vidas);
    }
    
    public void resetear(){
       vidas=3;
       jugadores.acomodarJugadores();
        actualizar("Resetear");
    }
    
    public void actualizar(Object evento){
        setChanged();
        notifyObservers(evento);
    }
    
    private int vidas;
    private Jugadores jugadores;
    private ModeloTabla modeloTabla;
}