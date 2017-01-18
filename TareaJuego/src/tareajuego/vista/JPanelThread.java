package tareajuego.vista;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import tareajuego.control.Control;


public class JPanelThread extends JPanel implements Runnable{
     @Override
    public void run() {
       while (hiloPrincipal == Thread.currentThread() && bandera){
           try {
             
               
                repaint();
              
               Thread.sleep(20);
           } catch (InterruptedException ex) {
               System.out.println("Ocurrio un error de interrupcion...");
           }
       }
    }
    
    public JPanelThread(Control cont){ 
       control=cont;
       iniciar();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        dibujarComponentes(g);
        
    }
    
    private void iniciar() {
            hiloPrincipal = new Thread(this);
            if (hiloPrincipal != null){
                hiloPrincipal.start();
            }
    }
    
 
    private void dibujarComponentes(Graphics g) {
        
       
       int yCanasta= this.getWidth() - 150 ;
      if(xCanasta==0)
          setXCanasta();
        g.drawImage(canasta, xCanasta , yCanasta,null);
        
    }
    
    public void setBandera(){
        bandera = true;
        iniciar();
    }  
    
    public void setXCanasta(){
        xCanasta= this.getHeight()/2- 70;
    }
    
    public void moverIzquierda(){
        xCanasta -= 15;
    }
    
    public void moverDerecha(){
         xCanasta += 15;
    }
    
    //---ATRIBUTOS
    private Control control;
    private Thread hiloPrincipal = null;
    private boolean bandera = false;
    private Image canasta= new ImageIcon(this.getClass().getResource("../vista/imagenes/canasta.png")).getImage();
    private int xCanasta=0;

}