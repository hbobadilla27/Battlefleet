/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 *
 * @author Intel
 */
public class Sonido {
    private Clip clip;
    private FloatControl volumen;
    
    public Sonido(Clip clip){
        this.clip=clip;
       volumen = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
    }
    
    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }
    public void loop(){
        clip.setFramePosition(0);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
    
    public int getFramePosition(){
        return clip.getFramePosition();
   }
    public void changeVolumen(float value){
        volumen.setValue(value);
    }
}
