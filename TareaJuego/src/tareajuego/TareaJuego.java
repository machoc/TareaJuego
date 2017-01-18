
package tareajuego;

import tareajuego.control.Control;
import tareajuego.vista.VentanaPrincipal;


public class TareaJuego {

    
    public static void main(String[] args) {
        Control control = new Control();
        VentanaPrincipal ventana = new VentanaPrincipal(control);
        ventana.iniciar();
    }
    
}
