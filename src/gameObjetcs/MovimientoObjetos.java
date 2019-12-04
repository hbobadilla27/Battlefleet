
package gameObjetcs;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
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
        this.gameState=gameState;
        this.velocidad=velocidad;
        this.maxVelocidad=maxVelocidad;
        ancho=texture.getWidth();
        altura=texture.getHeight();
        angulo=0;
    }

   
}
