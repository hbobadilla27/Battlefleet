/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjetcs;


import Main.Ventana;
import graphics.Assets;
import input.KeyBoard;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import math.Vector2D;
import states.GameState;




public class Player extends MovimientoObjetos {
    private Vector2D direccion; 
    private Vector2D aceleracion;
  
    private boolean aceleration=false;
    private Tiempo fireRate;
    
    
    
    
    
    public Player(Vector2D position,Vector2D velocidad,double maxVelocidad,BufferedImage texture,GameState gameState){
        super(position,velocidad,maxVelocidad, texture,gameState);
      
        direccion=new Vector2D(0,1);
        aceleracion=new Vector2D();
        fireRate=new Tiempo();
        
        
    }

    
    @Override
    public void actualizar() {
        
        if(KeyBoard.disparar && !fireRate.isRunning()){
            gameState.getMovimientoObjetos().add(0,new Laser(
                    getCenter().add(direccion.scale(ancho)),
                    direccion,
                    Constantes.velLaser,
                    angulo,
                    Assets.rojolaser,
                    gameState
            
            ));
        fireRate.run(Constantes.velDispara);
        }
        if(KeyBoard.RIGHT)
            angulo+=Constantes.rota;
        if(KeyBoard.LEFT)
            angulo-=Constantes.rota;
        
            
        if(KeyBoard.UP){
              aceleracion=direccion.scale(Constantes.ACC);
              aceleration=true;
        } 
        else{
            if(velocidad.getMagnitude()!=0)
                aceleracion=(velocidad.scale(-1).normalizar()).scale(Constantes.ACC/2);
                aceleration=false;
        }
        velocidad=velocidad.add(aceleracion);
        velocidad=velocidad.limit(maxVelocidad);
        
        direccion =direccion.setDireccion(angulo-Math.PI/2);
        position=position.add(velocidad);
      
        
       if(position.getX()>915 )
            position.setX(915);
        if(position.getY()>510)
            position.setY(510);
            
        
        if(position.getX()<0)
            position.setX(0);
        if(position.getY()<0)
           position.setY(0); 
        
       
        fireRate.actualizar();
        colision();
    }

    @Override
    public void dibujar(Graphics g) {
       Graphics2D g2d=(Graphics2D)g;
       AffineTransform rotar1= AffineTransform.getTranslateInstance(position.getX()+ancho/2 +9, position.getY()+altura/2 +8);
       AffineTransform rotar2= AffineTransform.getTranslateInstance(position.getX()+16, position.getY()+altura/2 +8);
       
       rotar1.rotate(angulo,-9,-8);
       rotar2.rotate(angulo,ancho/2-16,-8);
       if(aceleration){
             g2d.drawImage(Assets.speed,rotar1,null);
             g2d.drawImage(Assets.speed,rotar2,null);
       }

       rotar= AffineTransform.getTranslateInstance(position.getX(), position.getY());
       rotar.rotate(angulo,ancho/2,altura/2);
       g2d.drawImage(texture, rotar,null);
    }
    
    
   
   
  
     
}

