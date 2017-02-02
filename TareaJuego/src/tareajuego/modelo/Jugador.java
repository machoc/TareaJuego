package tareajuego.modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import xml.UtilidadesXML;

public class Jugador {
    public  Jugador(){
        nombre="";
        puntaje=0;
    }
    
     public Jugador(String nom){
        nombre=nom;
        puntaje=0;
    }
     
     public String getNombre(){
         return nombre;
     }
     
      public int getPuntaje(){
         return puntaje;
     }
      
    public void setNombre(String nom){
     nombre = nom;
     }
        
    public void setPuntaje(int punt){
     puntaje = punt;
     }
    
     
     public static String[] nombreCampos(){
         return NOMBRE_CAMPOS;
     }
     
     public Object[] toArray(){
        Object[] r = new Object[2];
        r[0] = getNombre();
        r[1] = getPuntaje();
        
        return r;
    }
     
      public void fijarAtributo(Object aValue, int columnIndex){
        switch(columnIndex){
            case 0:
                setNombre(aValue.toString());
                break;
            case 1:
                setPuntaje((Integer)aValue);
                break;
            
            default:
                throw new IndexOutOfBoundsException();
        }
      }
      
      public String getNodeName() {
        return DESCRIPCION_XML;
    }

    public Node toXML(Document doc) {
        Node r = doc.createElement("jugador");
        r.appendChild(UtilidadesXML.crearNodo(doc, "nombre", nombre));
        r.appendChild(UtilidadesXML.crearNodo(doc, "puntaje", String.valueOf(puntaje)));
        return r;
    }
     
     public void leerXML(Node nodo) {        
        NodeList arbolEtiquetas = nodo.getChildNodes(); 
        nombre = ((Element)arbolEtiquetas.item(0)).getTextContent();
        puntaje = Integer.parseInt(((Element)arbolEtiquetas.item(1)).getTextContent());
    }
    
    
    
    private static final String[] NOMBRE_CAMPOS={"Nombre","Puntaje"};
    private static final String DESCRIPCION_XML = "jugador";
    private String nombre;
    private int puntaje;
}
