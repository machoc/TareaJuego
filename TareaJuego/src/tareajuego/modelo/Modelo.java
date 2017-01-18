package tareajuego.modelo;

import java.util.Observable;

public class Modelo extends Observable{
    
    public Modelo(){
       
    }
    
    public void actualizar(Object obj){
        setChanged();
        notifyObservers(obj);
    }
    
   
    
}