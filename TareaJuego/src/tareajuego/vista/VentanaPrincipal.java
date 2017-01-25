package tareajuego.vista;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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


public class VentanaPrincipal extends JFrame implements Observer {

    public VentanaPrincipal(Control cont){
        control = cont;
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
        panelBotones.add(btnIniciarPartida);
        btnDetenerPartida=new JButton("DETENER PARTIDA");
        btnDetenerPartida.setEnabled(false);
        panelBotones.add(btnDetenerPartida);
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
                btnDetenerPartida.setEnabled(true);
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
                btnIniciarPartida.setEnabled(true);
                btnDetenerPartida.setEnabled(false);
                panelCentral.detener();
                tiempoTask.cancel();
                darFocusVentana();
            }
        }); 
    }
    
    public void iniciar(){        
        control.registrar(this);
        setVisible(true);
    }
    
    public void darFocusVentana(){
        this.requestFocus();
    }
    
    
    @Override
    public void update(Observable mod, Object eve) {
        
            }
 
    private JPanel panelPrincipal;
    private JPanelThread panelCentral;
    private JPanel panelBotones;
    private JPanel panelMarcador;
    private JLabel labVidas;
    private JLabel labPuntaje;
    private JLabel labTiempo;
    private JButton btnIniciarPartida;
    private JButton btnDetenerPartida;
    private Control control;
    private Timer tiempo ;
    private TimerTask tiempoTask;
    private int velocidad = 1000;
    private int seg=0;
    private int min=0;
}
