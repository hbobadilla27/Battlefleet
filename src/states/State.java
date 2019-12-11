/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import java.awt.Graphics;

/**
 *
 * @author Intel
 */
public abstract class State {
    public static State estadoActual=null;
    public static State getestadoActual(){
        return estadoActual;
    }
    
    public static void cambiarEstado(State newState){
      estadoActual=newState;
    }
    public abstract void actualizar();
    public abstract void dibujar(Graphics g);
        
    
    
}
