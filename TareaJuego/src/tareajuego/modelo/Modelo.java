package tareajuego.modelo;

import java.io.File;
import java.util.Observable;
import javax.swing.table.TableModel;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import xml.UtilidadesXML;

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
    
     public void cargarDatos(){
         File file = new File("jugadores.xml");
        
        Document documentoXML = UtilidadesXML.crearXMLDocumento(file);
        Node raiz = documentoXML.getDocumentElement();
        jugadores.cargar(raiz);
       actualizar(null);
    }
     
      public void guardarDatos(){
         try {
            Document doc = UtilidadesXML.crearDocumento();
            
            Node r = doc.createElement("DatosJugadores");
            jugadores.guardar(doc,r);
            doc.appendChild(r); 
            UtilidadesXML.guardarArchivoXML(doc, "jugadores.xml");
            actualizar(null);
        } catch (ParserConfigurationException ex) {
            
        }
    }
    
    private int vidas;
    private Jugadores jugadores;
    private ModeloTabla modeloTabla;
}