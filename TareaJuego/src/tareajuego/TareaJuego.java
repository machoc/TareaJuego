
package tareajuego;

import tareajuego.control.Control;
import tareajuego.vista.VentanaPosiciones;
import tareajuego.vista.VentanaPrincipal;


public class TareaJuego {

    
    public static void main(String[] args) {
        Control control = new Control(); 
        VentanaPosiciones posiciones= new VentanaPosiciones(control);
        VentanaPrincipal ventana = new VentanaPrincipal(control,posiciones);
       
        ventana.iniciar();
    }
    
}
