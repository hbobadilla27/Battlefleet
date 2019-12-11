/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjetcs;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import math.Vector2D;
import states.GameState;


public class Meteoro extends MovimientoObjetos{
    private Tamaños tamaño;

    public Meteoro(Vector2D position, Vector2D velocidad, double maxVelocidad, BufferedImage texture, GameState gameState,Tamaños tamaño) {
        super(position, velocidad, maxVelocidad, texture, gameState);
        this.tamaño=tamaño;
        this.velocidad=velocidad.scale(maxVelocidad);
    }

   

    @Override
    public void actualizar() {
        position=position.add(velocidad);
        
       
        if(position.getX()>Constantes.ancho)
            position.setX(-ancho);
        if(position.getY()>Constantes.altura)
            position.setY(-altura);
        
        if(position.getX()<-ancho)
            position.setX(Constantes.ancho);
        if(position.getX()<-altura)
            position.setX(Constantes.altura);

        angulo+=Constantes.rota/2;
    }

    @Override
    public void Destruir(){
        gameState.divideMeteoro(this);
        gameState.addScore(Constantes.MetScore,position);
        super.Destruir();
        
    }
    @Override
    public void dibujar(Graphics g) {
        Graphics2D g2d=(Graphics2D)g;
        rotar= AffineTransform.getTranslateInstance(position.getX(), position.getY());
        rotar.rotate(angulo,ancho/2,altura/2);
        g2d.drawImage(texture, rotar,null);
        
    }
    public Tamaños getTamaños(){
        return tamaño;
    
}
    
}
