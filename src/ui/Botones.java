
package ui;
import graphics.Assets;
import graphics.Texto;
import input.Mouse;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import math.Vector2D;

public class Botones {
    private BufferedImage mouseOutImg;
    private BufferedImage mouseInImg;
    private boolean mouseIn;
    private Rectangle boundingBox;
    private Accion accion;
    private String text;

    public Botones(BufferedImage mouseOutImg, BufferedImage mouseInImg, int x,int y, String text,Accion accion) {
        this.mouseOutImg = mouseOutImg;
        this.mouseInImg = mouseInImg;
        this.text = text;
        boundingBox = new Rectangle(x,y,mouseInImg.getWidth(),mouseInImg.getHeight());
        this.accion=accion;
    }
    
    public void actualizar(){
        if(boundingBox.contains(Mouse.X,Mouse.Y)){
            mouseIn=true;
        }else{
            mouseIn=false;
        }
        if(mouseIn && Mouse.MLB){
            accion.doAccion();
            
        }
    }
    public void dibujar(Graphics g){
        if(mouseIn){
            g.drawImage(mouseInImg, boundingBox.x, boundingBox.y, null);
        }
        else{
            g.drawImage(mouseOutImg,boundingBox.x, boundingBox.y,null);
        }  
        
        Texto.dibujarTexto(
        g,
        text,
        new Vector2D(
            boundingBox.getX()+boundingBox.getWidth(),
            boundingBox.getY()+boundingBox.getHeight()),
        
        true,
        Color.BLACK,
        Assets.fontMed);
    }
        
        
            }
    
    

