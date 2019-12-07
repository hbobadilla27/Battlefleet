/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjetcs;

import graphics.Assets;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import math.Vector2D;
import states.GameState;

/**
 *
 * @author Intel
 */
public class Laser extends MovimientoObjetos {

    public Laser(Vector2D position, Vector2D velocidad, double maxVelocidad,double angulo, BufferedImage texture,GameState gameState) {
        super(position, velocidad, maxVelocidad, texture, gameState);
        this.angulo=angulo;
        this.velocidad=velocidad.scale(maxVelocidad);
    }

    @Override
    public void actualizar() {
        position=position.add(velocidad);
        
        //Eliminar los laser
        if(position.getX()<0 || position.getX()>Constantes.ancho ||
                position.getY()<0 || position.getY()>Constantes.altura){
            Destruir();
        }
        colision();
    }

    @Override
    public void dibujar(Graphics g) {
         Graphics2D g2d=(Graphics2D)g;
          rotar= AffineTransform.getTranslateInstance(position.getX()-ancho/2, position.getY());
          rotar.rotate(angulo,ancho/2,0);
          g2d.drawImage(texture, rotar,null);
        
    }
    @Override
    public Vector2D getCenter(){
        return new Vector2D(position.getX()+ancho/2,position.getY()+ancho/2);
    }
    
}
