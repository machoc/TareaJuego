
package tareajuego.control;

import java.util.Observer;
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
    
   
     
    private Modelo modelo;
}