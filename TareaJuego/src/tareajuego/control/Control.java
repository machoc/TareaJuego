
package tareajuego.control;

import java.util.Observer;
import javax.swing.table.TableModel;
import tareajuego.modelo.Jugador;
import tareajuego.modelo.Modelo;


public class Control {
    
    public Control(Modelo mod){
        modelo=mod;
    }
    
    public Control(){
     this(new Modelo());
    }
    
    public void registrar(Observer nuevoObserver){
        modelo.addObserver(nuevoObserver);
    }
    
    public TableModel modeloTabla(){
        return modelo.modeloTabla();
    }
    
    
    
     public void agregarJugador(Jugador nuevoJugador){
         modelo.agregarJugador(nuevoJugador);
     }
     
      public void setearPuntaje(int puntaje){
         modelo.setPuntaje(puntaje);
     }
      
       public void setearVidas(int vidas){
         modelo.setVidas(vidas);
     }
       
        public void resetear(){
         modelo.resetear();
     }
     
     public void guardarDatos(){
         modelo.guardarDatos();
     }
     
     public void cargarDatos(){
         modelo.cargarDatos();
     }
     
    private Modelo modelo;
}