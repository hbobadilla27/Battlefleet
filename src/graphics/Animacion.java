
package graphics;

import java.awt.image.BufferedImage;
import math.Vector2D;


public class Animacion {
    private BufferedImage[] frames;
    private int velocid;
    private int index;//position invial
    private boolean runing;//si laanimacion sigue corriendo
    private Vector2D position;
    private long time,lasTime;
    
    public Animacion(BufferedImage[] frames,int velocid,Vector2D position){
        this.frames=frames;
        this.velocid=velocid;
        this.position=position;
        index=0;
        runing=true;
        time=0;
        lasTime=System.currentTimeMillis();
    }
    
    public void actualizar(){
        //Registro de Tiempo
        time+=System.currentTimeMillis()-lasTime;
        lasTime=System.currentTimeMillis();
        
        if(time>velocid){
            time=0;
            index++;
            if(index>=frames.length){
                runing=false;
            }
            
        }
    }
    
    public boolean isRuning(){
        return runing;
    }
    public Vector2D getPosition(){
        return position;
    }
    //obtiene el fotograma actual
    public BufferedImage getCurrentFrame(){
        return frames[index];
    }
}
