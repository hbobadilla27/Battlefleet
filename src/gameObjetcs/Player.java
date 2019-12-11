/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjetcs;


import Main.Ventana;
import graphics.Assets;
import graphics.Sonido;
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
    
    private boolean  spawning,visible;
    private Tiempo spawnTime,parpadearTime;
    private Sonido disparo,loose;
    
    
    
    
    
    
    public Player(Vector2D position,Vector2D velocidad,double maxVelocidad,BufferedImage texture,GameState gameState){
        super(position,velocidad,maxVelocidad, texture,gameState);
      
        direccion=new Vector2D(0,1);
        aceleracion=new Vector2D();
        fireRate=new Tiempo();
        spawnTime=new Tiempo();
        parpadearTime=new Tiempo();
        disparo=new Sonido(Assets.playerlaser);
        loose=new Sonido(Assets.playerLoose);
        
    }

    
    @Override
    public void actualizar() {
        if(!spawnTime.isRunning()){
            spawning=false;
            visible=true;
        }
        if(spawning){
            if(!parpadearTime.isRunning()){
                parpadearTime.run(Constantes.parpadearTime);
                visible=!visible;
            }
        }
        
        if(KeyBoard.disparar && !fireRate.isRunning() && !spawning){
            gameState.getMovimientoObjetos().add(0,new Laser(
                    getCenter().add(direccion.scale(ancho)),
                    direccion,
                    Constantes.velLaser,
                    angulo,
                    Assets.rojolaser,
                    gameState
            
            ));
        fireRate.run(Constantes.velDispara);
        disparo.play();
        }
        
        if(disparo.getFramePosition()>9000){
            disparo.stop();
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
        spawnTime.actualizar();
        parpadearTime.actualizar();
        colision();
    }
    @Override
    public void Destruir(){
        spawning=true;
        spawnTime.run(Constantes.spawnTime);
        loose.play();
        resetValue();
        gameState.restarVida();
    }
    
    private void resetValue(){
        angulo=0;
        velocidad=new Vector2D();
        position=new Vector2D(Constantes.ancho/2- Assets.player.getWidth()/2,
        Constantes.altura/2-Assets.player.getHeight()/2);
    }
    
    
    @Override
    public void dibujar(Graphics g) {
       if(!visible)
           return;
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
    
    public boolean isSpawning(){
        return spawning;
    }
        
    
   
   
  
     
}

