
package tareajuego.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import xml.UtilidadesXML;


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
    
    public void guardar(Document doc,Node r){
        for(int i =0;i<cantidadJugadores();i++){
            r.appendChild(recuperarDatos(i).toXML(doc));
        }
    }
    
    public void cargar(Node nodo){
        NodeList arbolEtiquetas = nodo.getChildNodes();
        int numJugadores = arbolEtiquetas.getLength();
        for (int i = 0; i < numJugadores; i++) {
            Node etiquetaJugador = arbolEtiquetas.item(i);
            Jugador nuevoJugador = new Jugador();
            nuevoJugador.leerXML(etiquetaJugador);
            jugadores.add(nuevoJugador);            
        }
    }
    
    
    
    //Atributos
    private ArrayList<Jugador> jugadores;
    
}