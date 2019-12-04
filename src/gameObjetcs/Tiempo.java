/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjetcs;

/**
 *
 * @author Intel
 */
public class Tiempo {
    private long delta,lasTime;
    private long time;
    private boolean running;
    public Tiempo(){
        delta=0;
        lasTime=0;
        running=false;
        
    }
    //Metodo de arranque del tiempo
    public void run(long time){
        running=true;
        this.time=time;
    }
    public void actualizar(){
        if(running)
            delta+=System.currentTimeMillis()-lasTime;
        if(delta>=time){
            running=false;
            delta=0;
        }
        lasTime=System.currentTimeMillis();
            
    }
    public boolean isRunning(){
        return running;
    }
    
}
