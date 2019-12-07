
package gameObjetcs;

import graphics.Assets;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import math.Vector2D;
import states.GameState;

public class Enemigo extends MovimientoObjetos {
    private Tiempo fireRate;
    private ArrayList<Vector2D> path;//Arreglo que contenga los nodos del camino
    private Vector2D currentNodo;
    private int index;
    private boolean following;
    public Enemigo(Vector2D position, Vector2D velocidad, double maxVelocidad, BufferedImage texture,ArrayList<Vector2D> path, GameState gameState) {
        super(position, velocidad, maxVelocidad, texture, gameState);
        this.path=path;
        index=0;
        following=true;
        fireRate =new Tiempo();
        fireRate.run(Constantes.enemfireRate);
    }
    private Vector2D pathFollowing(){
        currentNodo=path.get(index);
        double distanceNodo=currentNodo.subtract(getCenter()).getMagnitude();
        if(distanceNodo<Constantes.Nodo){
            index++;
            if(index>=path.size()){
                following=false;
            }
        }
        return seekForce(currentNodo);
    }
    private Vector2D seekForce(Vector2D target){
        Vector2D desiredVelocity=target.subtract(getCenter());
        desiredVelocity=desiredVelocity.normalizar().scale(maxVelocidad);
        return desiredVelocity.subtract(velocidad);
    }
    @Override
    public void actualizar() {
        Vector2D pathFollowing;
        if(following)
            pathFollowing=pathFollowing();
        else
            pathFollowing=new Vector2D();
        
        pathFollowing= pathFollowing.scale(1/Constantes.Masa);
        velocidad=velocidad.add(pathFollowing);
        velocidad=velocidad.limit(maxVelocidad);
        position=position.add(velocidad);
        
        if(position.getX()>Constantes.ancho || position.getY()>Constantes.altura ||
                position.getX()<0|| position.getY()<0)
            Destruir();
        
        //Cada vez que el tiempo no este corriendo que te devuelva flasa
        if(!fireRate.isRunning()){
            Vector2D toPlayer=gameState.getPlayer().getCenter().subtract(getCenter());
            toPlayer=toPlayer.normalizar();
            
            double currentAngulo=toPlayer.getAngulo();
            currentAngulo+=Math.random()*Constantes.EnemAnguloRango - Constantes.EnemAnguloRango/2;
            
            if(toPlayer.getX()<0)
                currentAngulo=-currentAngulo+Math.PI;
            
            toPlayer=toPlayer.setDireccion(currentAngulo);
            
            Laser laser =new Laser(
                getCenter().add(toPlayer.scale(ancho)),//position incial
                toPlayer,//Velocidad=vector toplayer
                Constantes.velLaser,
                currentAngulo + Math.PI/2,//nuevo angulo
                Assets.verdelaser,
                gameState);
            
            gameState.getMovimientoObjetos().add(0,laser);
            fireRate.run(Constantes.enemfireRate);
                    
        }   
        
        angulo+=0.05; 
        colision();
        fireRate.actualizar();
      
    
    }
     @Override
    public void Destruir(){
        gameState.addScore(Constantes.enemScore);
        super.Destruir();
    }
    @Override
    public void dibujar(Graphics g) {
        Graphics2D g2d= (Graphics2D)g;
        rotar=AffineTransform.getTranslateInstance(position.getX(), position.getY());
        rotar.rotate(angulo,ancho/2,altura/2);
        
        g2d.drawImage(texture,rotar,null);
        
        
       
        }
     
    
    
    
}
