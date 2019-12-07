/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

/**
 *
 * @author Cabrón en que lo cambie!
 */
import gameObjetcs.Constantes;
import gameObjetcs.Enemigo;
import gameObjetcs.Meteoro;
import gameObjetcs.MovimientoObjetos;
import gameObjetcs.Player;
import gameObjetcs.Tamaños;
import graphics.Animacion;
import graphics.Assets;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import math.Vector2D;
public class GameState {
    private Player player;
    private ArrayList<MovimientoObjetos> movimientoObjetos=new ArrayList<MovimientoObjetos>();
    private ArrayList<Animacion> explosion=new ArrayList<Animacion>();
    private int score=0;
    private int lifes=3;
    private int meteoro;
    
    
    public GameState(){
        player=new Player(new Vector2D(350,300),new Vector2D(),6,Assets.player,this);
        movimientoObjetos.add(player);
        meteoro=2;
        starWave();
    }
    
    public void addScore(int value){
        score+=value;
    }
    public void divideMeteoro(Meteoro meteoro){
        Tamaños tamaño=meteoro.getTamaños();
        BufferedImage[] textures=tamaño.texture;
        Tamaños newTamaño=null;
         switch(tamaño){
             case grande:
                 newTamaño=Tamaños.mediano;
                 break;
             case mediano:
                 newTamaño=Tamaños.pequeño;
                 break;
             case pequeño:
                 newTamaño=null;
                 break;
             default:
                 return;
                 
         }
         for(int i =0;i<tamaño.cantidad;i++){
             movimientoObjetos.add(new Meteoro(
                    meteoro.getPosition(),
                    new Vector2D(0,1).setDireccion(Math.random()*Math.PI*2),
                    Constantes.MeteorVeloc*Math.random()+1,
                    textures[(int)(Math.random()*textures.length)],
                    this,
                    newTamaño
                    ));
             
         }
         
        
    }
    private void starWave(){
        double x,y;
        for(int i=0;i<meteoro;i++){
            x=i%2==0?Math.random()*Constantes.ancho:0;
            y=i%2==0?0:Math.random()*Constantes.altura;
            BufferedImage texture=Assets.grande[(int)(Math.random()*Assets.grande.length)];
            
            movimientoObjetos.add(new Meteoro(
                    new Vector2D(x,y),
                    new Vector2D(0,1).setDireccion(Math.random()*Math.PI*2),
                    Constantes.MeteorVeloc*Math.random()+1,
                    texture,
                    this,
                    Tamaños.grande
                    ));
            
        
        }
        meteoro+=1;
        spawnEnemigo();
    }
    public void playExplosion(Vector2D position){
        explosion.add(new Animacion(
        Assets.explosion,
        50,
        position.subtract(new Vector2D(Assets.explosion[0].getWidth()/2,Assets.explosion[0].getHeight()/2))));
    }
    private void spawnEnemigo(){
        int rand= (int)(Math.random()*2);
        double x= rand==0?(Math.random()*Constantes.ancho):Constantes.ancho ;
        double y= rand==0?Constantes.altura:(Math.random()*Constantes.altura);
        
        ArrayList<Vector2D> path=new ArrayList<Vector2D>();
        
        double posX,posY;
        
        posX=Math.random()*Constantes.ancho/2;
        posY=Math.random()*Constantes.altura/2;
        path.add(new Vector2D(posX,posY));
        
        posX=Math.random()*(Constantes.ancho/2)+Constantes.ancho/2;
        posY=Math.random()*Constantes.altura/2;
        path.add(new Vector2D(posX,posY));
       
        posX=Math.random()*Constantes.ancho/2;
        posY=Math.random()*(Constantes.altura/2)+Constantes.altura/2;
        path.add(new Vector2D(posX,posY));
        
        posX=Math.random()*(Constantes.ancho/2)+Constantes.ancho/2;
        posY=Math.random()*(Constantes.altura/2)+Constantes.altura/2;
        path.add(new Vector2D(posX,posY));
        
        movimientoObjetos.add(new Enemigo(
            new Vector2D(x,y),
            new Vector2D(),
            Constantes.VelocEnemigo,
            Assets.enemigo,
            path,
            this));
        
    }
    public void actualizar(){
        for(int i=0;i<movimientoObjetos.size();i++)
            movimientoObjetos.get(i).actualizar();
        
        for(int i=0;i<explosion.size();i++){
            Animacion anim = explosion.get(i);
            anim.actualizar();
            if(!anim.isRuning()){
                explosion.remove(i);
            }   
        }
        
       
            
        //Nueva ola de meteoros
        for(int i=0;i<movimientoObjetos.size();i++)
             if(movimientoObjetos.get(i) instanceof Meteoro)
                 return; 
        
        starWave();
       
            
            
        
    }
    public void dibujar(Graphics g){
        Graphics2D g2d=(Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR );
        
        for(int i=0;i<movimientoObjetos.size();i++)
            movimientoObjetos.get(i).dibujar(g);
        
         for(int i=0;i<explosion.size();i++){
            Animacion anim = explosion.get(i);
            g2d.drawImage(anim.getCurrentFrame(), (int)anim.getPosition().getX(), (int)anim.getPosition().getY(),null);
        
         }
         drawScore(g);
         drawVida(g);
    }
    private void drawScore(Graphics g){
        //Dibujamos el score
        Vector2D posi=new Vector2D(1000,25);
        
        String scoreToString= Integer.toString(score);
        for( int i=0;i<scoreToString.length();i++){
            g.drawImage(Assets.numeros[Integer.parseInt(scoreToString.substring(i, i+1))],
                    (int)posi.getX(),(int)posi.getY(),null);
            
            posi.setX(posi.getX()+20);
        }
        
        
    }
     private void drawVida(Graphics g){
        //Dibujamos el score
        Vector2D vidaposi=new Vector2D(25,25);
        g.drawImage(Assets.life,(int)vidaposi.getX(),(int)vidaposi.getY(),null);
         g.drawImage(Assets.numeros[10],(int)vidaposi.getX()+40,
                 (int)vidaposi.getY()+5,null);
        
         String vidaToString= Integer.toString(lifes);
        Vector2D pos=new Vector2D(vidaposi.getX(),vidaposi.getY());
        
        for( int i=0;i<vidaToString.length();i++){
            int number=Integer.parseInt(vidaToString.substring(i,i+1));
            if(number<=0)
                break;
            g.drawImage(Assets.numeros[number],
                    (int)pos.getX()+60,(int)pos.getY()+5,null);
            
            pos.setX(pos.getX()+20);
        }
        
        
    }
    
    public ArrayList<MovimientoObjetos> getMovimientoObjetos() {
        return movimientoObjetos;
        
  
    }
    public Player getPlayer(){
        return player;
    }
    
}


