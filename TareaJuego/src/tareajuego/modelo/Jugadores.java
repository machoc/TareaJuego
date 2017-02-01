
package tareajuego.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
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
    
    public void crearXML(){
        try {       
            Document d = UtilidadesXML.crearDocumento();
            Node r = d.createElement("DatosJugador");
            for(int i = 0; i < jugadores.size(); i ++){
            r.appendChild(recuperarDatos(i).toXML(d));     
            }
            d.appendChild(r);  
            UtilidadesXML.guardarArchivoXML(d, "jugadores.xml"); 
            System.out.println("Programa finalizado..");
        } catch (ParserConfigurationException ex) {
            
        }
    }
    
    
    
    //Atributos
    private ArrayList<Jugador> jugadores;
    
}