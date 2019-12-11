
package gameObjetcs;

import graphics.Texto;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import math.Vector2D;
import states.GameState;


public class Mensajes {
    private GameState gameState;
    private float alpha;
    private String text;
    private Vector2D position;
    private Color color;
    private boolean center;
    private boolean desvaneser;
    private Font font;
    private final float deltaAlpha=0.01f;
    
    public Mensajes(Vector2D position, boolean desvaneser, String text,Color color, boolean center,Font font, GameState gameState){
        this.font=font;
        this.gameState=gameState;
        this.text=text;
        this.position=position;
        this.desvaneser=desvaneser;
        this.color=color;
        this.center=center;
        
        if(desvaneser)
            alpha=1;
        else
            alpha=0;
        
        
    }
    public void dibujar (Graphics2D g2d){
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        Texto.dibujarTexto(g2d, text, position, center, color, font);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        position.setY(position.getY()-1);
        
        if(desvaneser)
            alpha-=deltaAlpha;
        else
            alpha+=deltaAlpha;
        
        if(desvaneser && alpha <0 || !desvaneser && alpha>1){
            gameState.getMensajes().remove(this);
            
        }
    }
    
    
}
