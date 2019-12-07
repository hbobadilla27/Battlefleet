package Main;


import gameObjetcs.Constantes;
import graphics.Assets;
import input.KeyBoard;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;



import javax.swing.JFrame;
import states.GameState;


public class Ventana extends JFrame implements Runnable{
    private Canvas canvas;
    private Thread hilo;
    private boolean running=false;//controlar el estado
    private BufferStrategy bs;
    private Graphics g;
    
    private final int FPS=60;
    private double TARGETIME=1000000000/FPS;
    private double delta=0;//cambio con respecto al tiempo ue pasa
    private int AVERAGEFPS=FPS;
   
    private GameState gameState;
    private KeyBoard keyBoard;
    
    public Ventana(){
        setTitle("Battlefleet");
        setSize(Constantes.ancho,Constantes.altura);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        
        
        canvas =new Canvas();        
        keyBoard=new KeyBoard();
        
        canvas.setPreferredSize(new Dimension(Constantes.ancho,Constantes.altura)); //limitarlo 
        canvas.setMaximumSize(new Dimension(Constantes.ancho,Constantes.altura));
        canvas.setMinimumSize(new Dimension(Constantes.ancho,Constantes.altura));
        canvas.setFocusable(true);//recibir entradas por teclado
        
        add(canvas);
        canvas.addKeyListener(keyBoard);
        setVisible(true);
        
    }
    
    public static void main(String[] args) {
        new Ventana().Start();
        
    }
  
    private void actualizar(){
        keyBoard.actualizar();
        gameState.actualizar();
    }
    private void dibujar(){
       bs=canvas.getBufferStrategy();
       
       if(bs==null){
           canvas.createBufferStrategy(3);//numero de bufeer que utiliza csnvas
            return;
       }
       
       g=bs.getDrawGraphics();
       //g.setColor(Color.BLACK);
       g.drawImage(Assets.space, 0, 0,Constantes.ancho, Constantes.altura, this);
       gameState.dibujar(g);
       g.drawString(""+AVERAGEFPS,10,20);
       
      
       g.dispose();
       bs.show();
    }
    
    private void init(){
        Assets.init();
        gameState=new GameState();
    }
    
    
    
    @Override //define el cuerpo abstracto run
    public void run() {
       long now=0;
       long lastTime=System.nanoTime();//hora actual del sistema en nanosegundos
       int frames=0;
       long time=0;
       init();
       while(running){
           now=System.nanoTime();
           delta+=(now-lastTime)/TARGETIME;
           time+=(now-lastTime);
           lastTime=now;
           
           if(delta>=1){
                actualizar(); 
                dibujar();
                delta--;
                frames++;
               
           }
           
           if(time>=1000000000){
               AVERAGEFPS=frames;              
               frames=0;
               time=0;
               
           }
        }
        stop();
    }
    
    private void Start(){  //metodo iniciar el hilo
        hilo= new Thread(this); //esta clase implemnta el Runnable(interfas)
        hilo.start();//llama el metodo run
        running=true; 
    }
    private void stop(){ 
        try {
        // metodod detener el hilo
            hilo.join(); // encerramos en try cash
            running=false; 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
