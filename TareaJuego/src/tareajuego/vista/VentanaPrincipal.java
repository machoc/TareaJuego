package tareajuego.vista;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import tareajuego.control.Control;
import tareajuego.modelo.Jugador;


public class VentanaPrincipal extends JFrame implements Observer {

    public VentanaPrincipal(Control cont, JFrame vent){
        control = cont;
        ventanaPosiciones=vent;
        ajustarConfiguracionInicial();
        ajustarComponentes(getContentPane());
        agregarEventos();
    }
    
    
    private void ajustarConfiguracionInicial(){
        setTitle("Tarea Juego");
        setSize(900,1000);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
       
    
    }
    
    private void ajustarComponentes(Container c){
          c.setLayout(new BorderLayout());
        panelPrincipal = new JPanelConFondo("../vista/imagenes/fondo.jpg");
        panelPrincipal.setLayout(new BorderLayout());
        c.add(panelPrincipal, BorderLayout.CENTER);
        panelCentral = new JPanelThread(control);
        panelCentral.setOpaque(false);
        panelPrincipal.add(panelCentral);
        panelBotones=new JPanel();
        panelBotones.setBackground(Color.WHITE);
        panelPrincipal.add(panelBotones,BorderLayout.SOUTH);
        btnIniciarPartida=new JButton("INICIAR PARTIDA");
        btnIniciarPartida.setForeground(Color.GREEN);
        panelBotones.add(btnIniciarPartida);
        btnDetenerPartida=new JButton("DETENER PARTIDA");
         btnDetenerPartida.setForeground(Color.RED);
        btnDetenerPartida.setEnabled(false);
        panelBotones.add(btnDetenerPartida);
        btnVerPosiciones=new JButton("VER POSICIONES");
         btnVerPosiciones.setForeground(Color.yellow);
        panelBotones.add(btnVerPosiciones);
        panelMarcador=new JPanel();
        panelMarcador.setBackground(Color.WHITE);
        panelPrincipal.add(panelMarcador,BorderLayout.NORTH);
        JLabel puntaje = new JLabel("PUNTAJE:   ");
        puntaje.setForeground(Color.GREEN);
        panelMarcador.add(puntaje);
        labPuntaje=new JLabel("0");
        panelMarcador.add(labPuntaje);
        JLabel vidas =new JLabel("           VIDAS:  ");
        vidas.setForeground(Color.GREEN);
        panelMarcador.add(vidas);
        labVidas=new JLabel("3");
        panelMarcador.add(labVidas);
        JLabel tiempo = new JLabel("         TIEMPO:   ");
        tiempo.setForeground(Color.GREEN);
        panelMarcador.add(tiempo);
        labTiempo = new JLabel();
        panelMarcador.add(labTiempo);
    }
    
    
    
    
    private void agregarEventos(){        
        
        this.addKeyListener(new KeyAdapter(){
        
            @Override
            public void keyPressed(KeyEvent e){
                
                if (e.getKeyCode()== KeyEvent.VK_LEFT)
                    panelCentral.moverIzquierda();
                
                 if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                    panelCentral.moverDerecha();
            }
         });
        
        btnIniciarPartida.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                
                btnIniciarPartida.setEnabled(false);
                btnVerPosiciones.setEnabled(false);
                btnDetenerPartida.setEnabled(true);
                String nombre=null;
                nombre=  JOptionPane.showInputDialog(null,"Escriba su nombre: ","Solicitud",JOptionPane.QUESTION_MESSAGE);
                if(nombre.isEmpty())
                    nombre="Anonimo";
                Jugador jug =new Jugador(nombre);
                control.agregarJugador(jug);
                panelCentral.setBandera();
                tiempo = new Timer();
                tiempoTask = new TimerTask() {
                    @Override
                    public void run() {
                        seg++;                   
                        if (seg<=60){
                            if(seg<10){
                                labTiempo.setText(String.valueOf(min) +":0"+String.valueOf(seg));
                            }else{
                                labTiempo.setText(String.valueOf(min) +":"+String.valueOf(seg));}
                        }
                        else{seg=0;min++;}
                    }
                };                                         
                tiempo.schedule(tiempoTask,0,velocidad);
                darFocusVentana();
            }    
        });
        btnDetenerPartida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.resetear();
            }
        }); 
        
        btnVerPosiciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ventanaPosiciones.setVisible(true);
            }});
    }
    
    public void iniciar(){        
        control.registrar(this);
        setVisible(true);
    }
    
    public void darFocusVentana(){
        this.requestFocus();
    }
    
    public void detenerPartida(){
       
        tiempoTask.cancel();
        seg=0;
        min=0; 
        labPuntaje.setText("0");
        labPuntaje.setText("0");
        panelCentral.detener();
      //  control.guardarJugadores();
        btnIniciarPartida.setEnabled(true);
        btnVerPosiciones.setEnabled(true);
        btnDetenerPartida.setEnabled(false);
       
    }
    
    
    
    
    @Override
    public void update(Observable mod, Object eve) {
        if (eve instanceof String){
            if(eve.toString().equals("Resetear")){
                labPuntaje.setText("0");
                labVidas.setText("3");
                detenerPartida();
                JOptionPane.showMessageDialog(null,"Partida Finalizada..!!","Final de Partida",JOptionPane.INFORMATION_MESSAGE);
            }
            else
                labPuntaje.setText(eve.toString());
        }
        
        else if(eve instanceof Integer){
            labVidas.setText(String.valueOf(eve));
        }
        panelMarcador.repaint();
            }
 
    private JPanel panelPrincipal;
    private JPanelThread panelCentral;
    private JPanel panelBotones;
    private JPanel panelMarcador;
    private JLabel labVidas;
    private JLabel labPuntaje;
    private JLabel labTiempo;
    private JButton btnIniciarPartida;
    private JButton btnVerPosiciones;
    private JButton btnDetenerPartida;
    private Control control;
    private Timer tiempo ;
    private TimerTask tiempoTask;
    private int velocidad = 1000;
    private int seg=0;
    private int min=0;
    private JFrame ventanaPosiciones;
}
