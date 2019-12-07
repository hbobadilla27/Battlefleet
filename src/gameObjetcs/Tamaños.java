
package gameObjetcs;

import graphics.Assets;
import java.awt.image.BufferedImage;


public enum Tamaños {
    grande(3,Assets.mediano),mediano(2,Assets.pequeño),pequeño(0,null);
    public int cantidad;
    public BufferedImage[]texture;
    private Tamaños(int cantidad,BufferedImage[]texture ){
        this.cantidad=cantidad;
        this.texture=texture;
    }
    
    
    
}
