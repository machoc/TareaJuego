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
               caidaTomate();
               caidaBomba();
               caidaNaranja();
               caidaPina();
               if (yMaiz>yCanasta-36 &&( xMaiz> xCanasta -28 && xMaiz< xCanasta +100)){
                    resetearMaizCayendo();
               }
               
               if(yMaiz > yCanasta+140){
                   resetearMaizCayendo();
               }
               if(yTomate>yCanasta-36 &&( xTomate>xCanasta -28 && xTomate < xCanasta +100)){
                   resetearTomateCayendo();
               }
               if(yTomate > yCanasta+140){
                   resetearTomateCayendo();
               }
               if(yBomba>yCanasta-36 &&( xBomba>xCanasta -28 && xBomba < xCanasta +100)){
                   resetearBombaCayendo();
               }
               if(yBomba > yCanasta+140){
                   resetearBombaCayendo();
               }
               if(yNaranja>yCanasta-36 &&( xNaranja>xCanasta -28 && xNaranja < xCanasta +100)){
                   resetearNaranjaCayendo();
               }
               if(yNaranja > yCanasta+140){
                   resetearNaranjaCayendo();
               }
               if(yPina>yCanasta-36 &&( xPina>xCanasta -28 && xPina < xCanasta +100)){
                   resetearPinaCayendo();
               }
               if(yPina > yPina+140){
                   resetearPinaCayendo();
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
    
    private void caidaTomate(){
        yTomate += 5;
    }
    
    private void caidaBomba(){
        yBomba += 3;
    }
    
    private void caidaNaranja(){
        yNaranja += 5;
    }
    
    private void caidaPina(){
        yPina+= 7;
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
      if(xTomate==0){
          xTomate=ThreadLocalRandom.current().nextInt(40,this.getWidth()-160);
      }
      if(xBomba==0){
          xBomba=ThreadLocalRandom.current().nextInt(40,this.getWidth()-160);
      }
      if(xNaranja==0){
          xNaranja = ThreadLocalRandom.current().nextInt(40, this.getWidth()-160);
      }
      if(xPina==0){
          xPina = ThreadLocalRandom.current().nextInt(40, this.getWidth()-160);
      }
        g.drawImage(canasta, xCanasta , yCanasta,null);
        g.drawImage(maiz, xMaiz, yMaiz, null);
        g.drawImage(tomate, xTomate, yTomate, null);
        g.drawImage(bomba, xBomba, yBomba, null);
        g.drawImage(naranja, xNaranja, yNaranja, null);
        g.drawImage(pina, xPina, yPina, null);
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
    
    public void resetearMaizCayendo(){
        xMaiz=0;
        yMaiz=-50;
    }
    
    public void resetearTomateCayendo(){
       xTomate=0;
       yTomate=-700; 
    }
    
    public void resetearBombaCayendo(){
        xBomba = 0;
        yBomba = -1000;
    }
    
    public void resetearNaranjaCayendo(){
        xNaranja = 0;
        yNaranja = -4000;
    }
    
    public void resetearPinaCayendo(){
        xNaranja = 0;
        yNaranja = -5000;
    }
    
    public void detener(){
        hiloPrincipal.stop();
    }
    
    //---ATRIBUTOS
    private int limiteIzquierdo;
    private int limiteDerecho;
    private Control control;
    private Thread hiloPrincipal = null;
    private boolean bandera = false;
    private Image canasta= new ImageIcon(this.getClass().getResource("../vista/imagenes/canasta.png")).getImage();
    private Image maiz = new ImageIcon(this.getClass().getResource("../vista/imagenes/maiz.png")).getImage();
    private Image tomate = new ImageIcon(this.getClass().getResource("../vista/imagenes/tomate.png")).getImage();
    private Image bomba = new ImageIcon(this.getClass().getResource("../vista/imagenes/bomba.png")).getImage();
    private Image naranja = new ImageIcon(this.getClass().getResource("../vista/imagenes/naranja.png")).getImage();
    private Image pina = new ImageIcon(this.getClass().getResource("../vista/imagenes/piña.png")).getImage();
    private int yCanasta=0;
    private int xCanasta=0;
    private int xMaiz = 0;
    private int yMaiz = -50;
    private int xTomate = 0;
    private int yTomate = -1000;
    private int xBomba = 0;
    private int yBomba = -2000;
    private int xNaranja = 0;
    private int yNaranja = -4000;
    private int xPina = 0;
    private int yPina = -5000;

}