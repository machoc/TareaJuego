package tareajuego.vista;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class JPanelConFondo extends JPanel {
 
    private Image imagen;
 
    public JPanelConFondo(String direccion){
        URL url = getClass().getResource(direccion);
        imagen = new ImageIcon(url).getImage();
    }
    
    @Override
    public void paint(Graphics g) {
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(),
                        this);
 
        setOpaque(false);
        super.paint(g);
    }
 
   
}
