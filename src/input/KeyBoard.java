/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package input;

/**
 *
 * @author Cabrón en que lo cambie!
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class KeyBoard implements KeyListener{
    private boolean[] keys= new boolean[256];
    public static boolean UP,LEFT,RIGHT,disparar;
    public KeyBoard(){
        
        UP=false;
        LEFT=false;
        RIGHT=false;        
        disparar=false;
    }
    public void actualizar(){
        UP=keys[KeyEvent.VK_W];
        LEFT=keys[KeyEvent.VK_A];
        RIGHT=keys[KeyEvent.VK_D];      
        disparar=keys[KeyEvent.VK_L];
        
    }
   

    @Override
    public void keyPressed(KeyEvent e) { //presionas la tecla
         keys[e.getKeyCode()]=true; 
    }

    @Override
    public void keyReleased(KeyEvent e) {   //soltamos la tecla
        keys[e.getKeyCode()]=false; 
    }
    
    @Override
    public void keyTyped(KeyEvent e) { 
        
    }
    
}
