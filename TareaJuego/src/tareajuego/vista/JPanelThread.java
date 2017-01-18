package tareajuego.vista;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import tareajuego.control.Control;


public class JPanelThread extends JPanel implements Runnable{

    public JPanelThread() {
        this.iniciar();
    }
    
    
     @Override
    public void run() {
       while (hiloPrincipal == Thread.currentThread() && bandera){
           try {
               caidaMaiz();
             
               if (yMaiz>yCanasta-36 &&( xMaiz> xCanasta -28 && xMaiz< xCanasta +100)){
                    resetearObjetoCallendo();
               }
               
               if(yMaiz > yCanasta+140){
                   resetearObjetoCallendo();
               }
                  
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
    
    private void caidaMaiz(){
        yMaiz += 3;
    }
    
 
    private void dibujarComponentes(Graphics g) {
       if(xMaiz==0){
           xMaiz=ThreadLocalRandom.current().nextInt(40,this.getWidth()-160);
       }
      if(xCanasta==0){
          setXYCanasta();
          limiteIzquierdo = 30;
          limiteDerecho = this.getWidth()-170;
      }
        g.drawImage(canasta, xCanasta , yCanasta,null);
        g.drawImage(maiz, xMaiz, yMaiz, null);
        
    }
    
    public void setBandera(){
        bandera = true;
        iniciar();
    }  
    
    public void setXYCanasta(){
        yCanasta= this.getWidth() - 150 ;
        xCanasta= this.getHeight()/2- 70;
    }
    
    public void moverIzquierda(){
        if(xCanasta > limiteIzquierdo)
        xCanasta -= 15;
    }
    
    public void moverDerecha(){
         if(xCanasta < limiteDerecho)
         xCanasta += 15;
    }
    
    public void resetearObjetoCallendo(){
        xMaiz=0;
        yMaiz=-50;
    }
    
    //---ATRIBUTOS
    private int limiteIzquierdo;
    private int limiteDerecho;
    private Control control;
    private Thread hiloPrincipal = null;
    private boolean bandera = false;
    private Image canasta= new ImageIcon(this.getClass().getResource("../vista/imagenes/canasta.png")).getImage();
    private Image maiz = new ImageIcon(this.getClass().getResource("../vista/imagenes/maiz.png")).getImage();
    private int yCanasta=0;
    private int xCanasta=0;
    private int xMaiz = 0;
    private int yMaiz = -50;

}