
package gameObjetcs;
import graphics.Assets;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import math.Vector2D;
import states.GameState;



public abstract class MovimientoObjetos extends GameObject {
    protected Vector2D velocidad;
    protected AffineTransform rotar;
    protected double angulo;
    protected double maxVelocidad;
    protected int ancho;
    protected int altura;
    protected GameState gameState;
    
    public MovimientoObjetos(Vector2D position,Vector2D velocidad,double maxVelocidad, BufferedImage texture,GameState gameState){
        
        super(position, texture);
        
        this.velocidad=velocidad;
        this.maxVelocidad=maxVelocidad;
        this.gameState=gameState;
        ancho=texture.getWidth();
        altura=texture.getHeight();
        angulo=0;
    }
    protected void colision(){
        
        ArrayList<MovimientoObjetos> movimientoObjetos=gameState.getMovimientoObjetos();
       
        for(int i=0;i<movimientoObjetos.size();i++){
            MovimientoObjetos m=movimientoObjetos.get(i);
            if(m.equals(this))
                continue;
            double distance=m.getCenter().subtract(getCenter()).getMagnitude();
            if(distance<m.ancho/2 + ancho/2 && movimientoObjetos.contains(this)){
                objectCollision(m, this);
            }
                
        }
         
    }
    private void objectCollision(MovimientoObjetos a,MovimientoObjetos b){
        if(!(a instanceof Meteoro && b instanceof Meteoro)){
            gameState.playExplosion(getCenter());
            a.Destruir();
            b.Destruir();
        }
        
    }
    protected void Destruir(){
        gameState.getMovimientoObjetos().remove(this);
    }
    protected  Vector2D getCenter()
    {
        return new Vector2D(position.getX()+ ancho/2, position.getY()+ altura/2);
    }
   
}
