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
import gameObjetcs.MovimientoObjetos;
import gameObjetcs.Player;
import graphics.Assets;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import math.Vector2D;
public class GameState {
    private Player player;
    private ArrayList<MovimientoObjetos> movimientoObjetos=new ArrayList<MovimientoObjetos>();
    public GameState(){
        player=new Player(new Vector2D(350,300),new Vector2D(),6,Assets.player,this);
        movimientoObjetos.add(player);
    }
    public void actualizar(){
        for(int i=0;i<movimientoObjetos.size();i++)
            movimientoObjetos.get(i).actualizar();
            
            
        
    }
    public void dibujar(Graphics g){
        Graphics2D g2d=(Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR );
        
        for(int i=0;i<movimientoObjetos.size();i++)
            movimientoObjetos.get(i).dibujar(g);
        
    }

    public ArrayList<MovimientoObjetos> getMovimientoObjetos() {
        return movimientoObjetos;
    }
    
}


